import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import java.util.Scanner;

public class MIDISetup
{

	MidiDevice.Info[] deviceInfo = MidiSystem.getMidiDeviceInfo();
	MidiDevice device = null;

	public MIDISetup()
	{
		try
		{
		    System.out.println("deviceInfo has " + deviceInfo.length + " elements");
			for (int i = 0; i < deviceInfo.length; i++)
			{
				
				System.out.println("Device #" + i + ": " + deviceInfo[i]);
			}
			Scanner sc = new Scanner(System.in);
			int deviceSelection;
	        while (true)
			{
				System.out.println("Select a MIDI Device by number:");
		    	deviceSelection = sc.nextInt();
	            break;
			}
			System.out.println("Testing device #" + deviceSelection);
			device = MidiSystem.getMidiDevice(deviceInfo[deviceSelection]);
			if (!device.isOpen())
			{
				device.open();
				System.out.println("device is now open");
			}
		}
		catch (MidiUnavailableException ex)
        {
            System.out.println(ex);
        }
	}
}