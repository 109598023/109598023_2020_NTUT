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
        System.out.print("Command");
    }

    public void processCommand()
    {
        LogicSimulator logicSimulator = new LogicSimulator();
        boolean isQuit = false;
        while(!isQuit)
        {
            displayMenu();
            try(Scanner scanner = new Scanner(System.in))
            {
                switch(scanner.nextLine())
                {
                    case "1\n":
                        System.out.print("Please key in a file path: ");
                        if (!logicSimulator.load(scanner.nextLine()))
                        {
                            System.out.println("File not found or file format error!!");
                        }
                        break;
                    case "2\n":
                        if(!logicSimulator.IsLoad())
                        {
                            System.out.println("Please load an lcf file, before using this operation.");
                        }
                        else
                        {
                            Vector<Boolean> booleans = new Vector<>();
                            boolean isZeroOne;
                            int i = 1, num = 0;
                            while(i <= logicSimulator.getIPinsSize())
                            {
                                isZeroOne = true;
                                System.out.print("Please key in the value of input pin " + i + ": ");
                                try {
                                    num = Integer.parseInt(scanner.nextLine());
                                    isZeroOne = num == 0 || num == 1;
                                }
                                catch (NumberFormatException e)
                                {
                                    isZeroOne =false;
                                }
                                if (isZeroOne)
                                {
                                    booleans.add(new Boolean((num == 1)));
                                }
                                else
                                {
                                    System.out.println("The value of input pin must be 0/1");
                                }
                            }
                        }
                        break;
                    case "3\n":
                        if(!logicSimulator.IsLoad())
                        {
                            System.out.println("Please load an lcf file, before using this operation.");
                        }
                        else
                        {
                            System.out.println(logicSimulator.getTruthTable());
                        }
                        break;
                    case "4\n":
                        isQuit = true;
                        System.out.println("Goodbye, thanks for using LS.");
                        break;
                }
            }
        }
    }
}
