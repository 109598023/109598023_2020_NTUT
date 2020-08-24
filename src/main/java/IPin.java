public class IPin extends Device
{
    private boolean inputValue;

    @Override
    public void setInput(boolean inputValue)
    {
        this.inputValue = inputValue;
    }

    @Override
    public boolean getOutput()
    {
        return this.inputValue;
    }
}
