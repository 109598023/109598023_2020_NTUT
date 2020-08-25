import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class LogicSimulator
{

    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;
    private boolean isLoad;

    public boolean load(String filePath)
    {
        boolean load = true;
        circuits = new Vector<>();
        iPins = new Vector<>();
        oPins = new Vector<>();
        try(Scanner scanner = new Scanner(new File(filePath)))
        {
            Vector<Vector<String>> circuitData = new Vector<>();
            int iPinSize = Integer.parseInt(scanner.nextLine());
            int gateSize = Integer.parseInt(scanner.nextLine());
            while(scanner.hasNext())
            {
                circuitData.add(new Vector<>(Arrays.asList(scanner.nextLine().split(" "))));
            }
            load &= buildCircuit(circuitData, iPinSize, gateSize);
        }
        catch (NumberFormatException | FileNotFoundException e) {
            load = false;
        }
        isLoad = load;
        return load;
    }

    public boolean IsLoad()
    {
        return isLoad;
    }

    public int getIPinsSize()
    {
        return iPins.size();
    }

    private boolean buildCircuit(Vector<Vector<String>> circuitData, int iPinSize, int gateSize)
    {
        boolean buildResult = true;
        buildResult &= buildIPins(iPinSize);
        buildResult &= buildGates(circuitData, gateSize);
        if(buildResult)
        {
            int[] useGateCount = new int[gateSize];
            for (int i = 0; i < gateSize; i++)
            {
                Vector<String> tmpData = circuitData.get(i);
                for (int j = 1; j < tmpData.size() - 1; j++)
                {
                    if (tmpData.get(j).contains("-"))
                    {
                        int iPinsIndex = Math.abs(Integer.parseInt(tmpData.get(j))) - 1;
                        circuits.get(i).addInputPin(iPins.get(iPinsIndex));
                    }
                    else
                    {
                        String[] tmpSplits = tmpData.get(j).split("\\.");
                        int circuitsIndex = Integer.parseInt(tmpSplits[0]) - 1;
                        circuits.get(i).addInputPin(circuits.get(circuitsIndex));
                        useGateCount[circuitsIndex] = 1;
                    }
                }
            }
            for (int i = 0; i < gateSize; i++)
            {
                if (useGateCount[i] == 0)
                {
                    oPins.add(circuits.get(i));
                }
            }
        }
        return buildResult;
    }

    private boolean buildIPins(int iPinSize)
    {
        if (iPinSize <= 0)
        {
            return false;
        }
        for (int i = 0 ; i < iPinSize; i++)
        {
            iPins.add(new IPin());
        }
        return true;
    }

    private boolean buildGates(Vector<Vector<String>> circuitData, int gateSize)
    {
        boolean buildResult = true;
        if (gateSize <= 0)
        {
            return false;
        }
        for (int i = 0; i < gateSize; i++)
        {
            switch (circuitData.get(i).get(0))
            {
                case "1":
                    circuits.add(new GateAND());
                    break;
                case "2":
                    circuits.add(new GateOR());
                    break;
                case "3":
                    circuits.add(new GateNOT());
                    break;
                default:
                    buildResult = false;

            }
        }
        return buildResult;
    }

    public String getSimulationResult(Vector<Boolean> booleans)
    {
        String simulationResult = "Simulation Result:\n" + buildTitle();
        for (int i = 0; i < iPins.size(); i++)
        {
            iPins.get(i).setInput(booleans.get(i));
            simulationResult += (iPins.get(i).getOutput() == true ? "1":"0") + " ";
        }
        simulationResult += "|";
        for(int i = 0; i < oPins.size(); i++)
        {
            simulationResult += " " + (oPins.get(i).getOutput() == true ? "1":"0");
        }
        simulationResult += "\n";
        return simulationResult;
    }

    public String getTruthTable()
    {
        String truthTable = "Truth table:\n" + buildTitle();
        for (int i = 0, len = (int)Math.pow(2, iPins.size()); i < len; i++)
        {
            for (int j = iPins.size() - 1, index = 0; j >= 0; j--, index++)
            {
                int result = (i>>j) & 1;
                iPins.get(index).setInput(result == 1? true:false);
                truthTable += result + " ";
            }
            truthTable += "|";
            for(int j = 0; j < oPins.size(); j++)
            {
                truthTable += " " + (oPins.get(j).getOutput() == true ? "1":"0");
            }
            truthTable += "\n";
        }
        return truthTable;
    }

    private String buildTitle()
    {
        String truthTableTitle = "";
        for (int i = 0 ; i < iPins.size(); i ++)
        {
            truthTableTitle += "i ";
        }
        truthTableTitle += "|";
        for(int i = 0 ; i < oPins.size(); i++)
        {
            truthTableTitle += " o";
        }
        truthTableTitle += "\n";

        for (int i = 1 ; i <= iPins.size(); i ++)
        {
            truthTableTitle += i + " ";
        }
        truthTableTitle += "|";
        for(int i = 1 ; i <= oPins.size(); i++)
        {
            truthTableTitle += " " + i;
        }
        truthTableTitle += "\n";

        for (int i = 0 ; i < iPins.size(); i ++)
        {
            truthTableTitle += "--";
        }
        truthTableTitle += "+";
        for(int i = 0 ; i < oPins.size(); i++)
        {
            truthTableTitle += "--";
        }
        truthTableTitle += "\n";
        return truthTableTitle;
    }
}
