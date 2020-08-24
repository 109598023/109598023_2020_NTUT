import org.junit.*;
import static org.junit.Assert.*;

public class DeviceTest
{
    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testDevicePolymorphism()
    {
        Device device = new IPin();
        assertEquals(IPin.class.getName(), device.getClass().getName());

        device = new OPin();
        assertEquals(OPin.class.getName(), device.getClass().getName());

        device = new GateNOT();
        assertEquals(GateNOT.class.getName(), device.getClass().getName());

        device = new GateAND();
        assertEquals(GateAND.class.getName(), device.getClass().getName());

        device = new GateOR();
        assertEquals(GateOR.class.getName(), device.getClass().getName());
    }

    @Test
    public void testInputAndOutput()
    {
        // 0 = 0
        IPin iPin = new IPin();
        OPin oPin = new OPin();
        iPin.setInput(false);
        oPin.addInputPin(iPin);
        assertEquals(false, oPin.getOutput());

        // 1 = 1
        iPin = new IPin();
        oPin = new OPin();
        iPin.setInput(true);
        oPin.addInputPin(iPin);
        assertEquals(true, oPin.getOutput());
    }

    @Test
    public void testGateNOT()
    {
        // 0 = 1
        GateNOT gateNOT = new GateNOT();
        IPin iPin = new IPin();
        iPin.setInput(false);
        gateNOT.addInputPin(iPin);
        assertEquals(true, gateNOT.getOutput());

        // 1 = 0
        gateNOT = new GateNOT();
        iPin = new IPin();
        iPin.setInput(false);
        gateNOT.addInputPin(iPin);
        assertEquals(true, gateNOT.getOutput());
    }

    @Test
    public void testGateAND()
    {
        // 0 AND 0 = 0
        GateAND gateAND = new GateAND();
        IPin iPin1 = new IPin();
        IPin iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);
        gateAND.addInputPin(iPin1);
        gateAND.addInputPin(iPin2);
        assertEquals(false, gateAND.getOutput());

        // 0 AND 1 = 0
        gateAND = new GateAND();
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(true);
        gateAND.addInputPin(iPin1);
        gateAND.addInputPin(iPin2);
        assertEquals(false, gateAND.getOutput());

        // 1 AND 0 = 0
        gateAND = new GateAND();
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.setInput(true);
        iPin2.setInput(false);
        gateAND.addInputPin(iPin1);
        gateAND.addInputPin(iPin2);
        assertEquals(false, gateAND.getOutput());

        // 1 AND 1 = 1
        gateAND = new GateAND();
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.setInput(true);
        iPin2.setInput(true);
        gateAND.addInputPin(iPin1);
        gateAND.addInputPin(iPin2);
        assertEquals(true, gateAND.getOutput());

    }

    @Test
    public void testGateOR()
    {
        // 0 OR 0 = 0
        GateOR gateOR = new GateOR();
        IPin iPin1 = new IPin();
        IPin iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertEquals(false, gateOR.getOutput());

        // 0 OR 1 = 1
        gateOR = new GateOR();
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertEquals(false, gateOR.getOutput());

        // 1 OR 0 = 1
        gateOR = new GateOR();
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertEquals(false, gateOR.getOutput());

        // 1 OR 1 = 1
        gateOR = new GateOR();
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);
        assertEquals(false, gateOR.getOutput());

    }
}
