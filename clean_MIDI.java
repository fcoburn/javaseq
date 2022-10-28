import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.MidiEvent;

public class clean_MIDI
{
	public static void main(String[] args)
	{
		// Encapsulates MIDI Setup for convenience
		MIDISetup setup = new MIDISetup();

		try
		{
		   	// use above MIDI preamble to setup sequencer

    		Sequencer sequencer = MidiSystem.getSequencer();
    		Transmitter sequencerTransmitter = sequencer.getTransmitter();
    		Receiver receiver = setup.device.getReceiver();
			sequencerTransmitter.setReceiver(receiver);


			// Sequencer parameters

    		int resolution = 96;
    		Sequence sequence = new Sequence(Sequence.PPQ, resolution);
    		float tempoInBPM = 120;
    		sequencer.setTempoInBPM(tempoInBPM);
    		sequencer.open();

    		// Parameters for calculating MIDI timestamps on timeline

    		int ticksPerBar = resolution * 4;
    		int ticksPerSixteenth = resolution / 4;
    		int quarterBar = resolution;

    		// Create some notes and insert them into the sequence

			int midiChannel = 0;
			int midiNoteKick = 36;
			int midiVel = 100;
			int barCounter = 0;

			Track track = sequence.createTrack();

			String testMidiNoteOn = "0,36,100,noteon,0";
			String testMidiNoteOff = "0,36,100,noteoff,0";

			for (int i = 0; i < 256; i++)
			{
				/*
	    		track.add(createMidiMessage(midiChannel,midiNoteKick,midiVel,ShortMessage.NOTE_ON,(barCounter * quarterBar)));
	    		barCounter += 1;
	    		track.add(createMidiMessage(midiChannel,midiNoteKick,midiVel,ShortMessage.NOTE_OFF,(barCounter * quarterBar)-10));
	    		*/
			} 		
    		sequencer.setSequence(sequence);

    		// Play the sequence

    		sequencer.start();
    		System.out.println("sequence playing");
	        while (sequencer.isRunning())
			{
				
			}
			System.out.println("sequence done");
    		sequencer.stop();
    		sequencer.close();
    		receiver.close();
    		sequencerTransmitter.close();
		}
		catch (MidiUnavailableException ex)
        {
            System.out.println(ex);
        }
        catch (InvalidMidiDataException ex)
        {
            System.out.println(ex);
        }
	}

	public static MidiEvent createMidiMessage(int midiChannel, int midiNote, int midiVel, int noteOnOff, long timestamp)
	{
		MidiEvent event = null;
		try
		{
			ShortMessage shortMessage = new ShortMessage();
			shortMessage.setMessage(noteOnOff,midiChannel,midiNote,midiVel);
			event = new MidiEvent(shortMessage, timestamp);
		}
		catch (InvalidMidiDataException ex)
		{
			System.out.println(ex);
		}
		return event;
	}

	public static MidiEvent createMidiFromString(String midiString)
	{

		String delims = "[,]";
		String[] midiStringTokens = midiString.split(delims);
		int noteonoffInteger = 0;

		if (midiStringTokens[3].equals("noteon"))
		{
			noteonoffInteger = ShortMessage.NOTE_ON;
		}

		if (midiStringTokens[3].equals("noteoff"))
		{
			noteonoffInteger = ShortMessage.NOTE_OFF;
		}

		return createMidiMessage(Integer.parseInt(midiStringTokens[0]),Integer.parseInt(midiStringTokens[1]),
			Integer.parseInt(midiStringTokens[2]),noteonoffInteger,Integer.parseInt(midiStringTokens[4]));
	}
}