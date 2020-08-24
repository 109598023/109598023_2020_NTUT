public class GateAND extends Device
{

    @Override
    public boolean getOutput()
    {
        boolean outputValue = this.iPins.get(0).getOutput();

        for (int i = 0; i< this.iPins.size();i++)
        {
            outputValue = outputValue & iPins.get(i).getOutput();
        }

        return outputValue;
    }
}
