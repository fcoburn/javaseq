import java.util.Timer;

public class javaseq
{
	public static void main(String[] args)
	{
		try
	        {
	        	// Basic math to calculate size of grid, using floating point and casting to integers when nessecary

				System.out.println("Starting javaseq");  
				float tempoBPM = 137;
				System.out.println("Tempo is: " + tempoBPM);
				float ticksPerSecond = tempoBPM / 60;
				System.out.println("Ticks per second is " + ticksPerSecond);
				float ticksPerBeat = 1000F / ticksPerSecond;
				System.out.println("Number of ms between beats: " + ticksPerBeat);

				int numbeats = 48;
				int lengthInMillis = (int) (numbeats * ticksPerBeat); 
				System.out.println(numbeats + " beats total length in ms " + lengthInMillis);

				System.out.println("Creating a timeline, the length of the total num of bars in ms");
				int[] timeline = new int[lengthInMillis + 1];

				// This is where all the sequence data is inserted, each value is a beat where the sample will be played at
				int[] events = {1,16,32,48};

				// Loop to calculate the timestamp of events, by multiplying their numeric place on the timeline by
				// calculated values for the exact time in milliseconds

				int eventTime;
				for (int i = 0; i < events.length; i++)
				{
					eventTime = (int) (ticksPerBeat * events[i]);
					System.out.println("event at " + eventTime + "ms");
					timeline[eventTime] = 1;
				}

				// create a pairing of points, ie. the point at bar 1 gets assigned a value of X ms
				// loop through all the milliseconds?
				// might not need to do this

				for (int j = 0; j < timeline.length; j++)
				{
					if (timeline[j] != 0)
					{
						new Timer().scheduleAtFixedRate(new PlayAudioTask(), (long) j, 1L);
						System.out.println("something is here at: " + j);
					}
				}

        }
	          
	    catch (Exception ex) 
	    {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }

	}
}
