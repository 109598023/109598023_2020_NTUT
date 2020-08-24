import java.util.Vector;

public class Device
{
    protected Vector<Device> iPins = new Vector<>();

    public void addInputPin(Device inputPin)
    {
        this.iPins.add(inputPin);
    }

    public void setInput(boolean inputValue)
    {
        throw new RuntimeException("This device is not allowed to call setInput() method.");
    }

    public boolean getOutput()
    {
        throw new RuntimeException("This device is not allowed to call getOutput() method.");
    }
}
