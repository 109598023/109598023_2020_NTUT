import java.util.Scanner;
import java.util.Vector;

public class TextUI
{

    public void displayMenu()
    {
        System.out.println("1. Load logic circuit file");
        System.out.println("2. Simulation");
        System.out.println("3. Display truth table");
        System.out.println("4. Exit");
        System.out.print("Command: ");
    }

    public void processCommand()
    {
        LogicSimulator logicSimulator = new LogicSimulator();
        boolean isQuit = false;
        Scanner scanner = new Scanner(System.in);
        while(!isQuit)
        {
            displayMenu();
            switch (scanner.nextLine()) {
                        case "1":
                            System.out.print("Please key in a file path: ");
                            if (!logicSimulator.load(scanner.nextLine()))
                            {
                                System.out.println("File not found or file format error!!\n");
                            }
                            else
                            {
                                System.out.printf("Circuit: %d input pins, %d output pins and %d gates\n\n",
                                        logicSimulator.getIPinsSize(),
                                        logicSimulator.getOPinsSize(),
                                        logicSimulator.getCircuitsSize());
                            }
                            break;
                        case "2":
                            if (!logicSimulator.IsLoad()) {
                                System.out.println("Please load an lcf file, before using this operation.");
                            } else {
                                Vector<Boolean> booleans = new Vector<>();
                                boolean isZeroOne;
                                int i = 1, num = 0;
                                while (i <= logicSimulator.getIPinsSize()) {
                                    isZeroOne = true;
                                    System.out.print("Please key in the value of input pin " + i + ": ");
                                    try {
                                        num = Integer.parseInt(scanner.nextLine());
                                        isZeroOne = num == 0 || num == 1;
                                    } catch (NumberFormatException e) {
                                        isZeroOne = false;
                                    }
                                    if (isZeroOne) {
                                        booleans.add(new Boolean((num == 1)));
                                        i++;
                                    } else {
                                        System.out.println("The value of input pin must be 0/1");
                                    }
                                }
                                System.out.print(logicSimulator.getSimulationResult(booleans));
                            }
                            break;
                        case "3":
                            if (!logicSimulator.IsLoad()) {
                                System.out.println("Please load an lcf file, before using this operation.");
                            } else {
                                System.out.print(logicSimulator.getTruthTable());
                            }
                            break;
                        case "4":
                            isQuit = true;
                            System.out.println("Goodbye, thanks for using LS.");
                            break;
                    }
        }
    }
}
