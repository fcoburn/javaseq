import java.util.TimerTask;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
  
public class PlayAudioTask extends TimerTask
{
	Long currentFrame;
    Clip clip;
    String status;
    AudioInputStream audioInputStream;
    static String filePath;
    
    public PlayAudioTask() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{
			System.out.println("Running constructor code");
            filePath = "C:\\Users\\Frank\\prog\\javaseq\\amencut.wav";
	        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	}
    
    public void run()
    {
	        try
	        {
	            PlayAudioTask audioPlayer = new PlayAudioTask();

	            // this line actually plays the audio
                audioPlayer.play();
	            
	            // scanner with while loop to keep the method running while the audio plays
                Scanner sc = new Scanner(System.in);
		        while (true)
	            {
	                System.out.println("in while loop");
	                int c = sc.nextInt();
	                if (c == 4)
	                break;
	            }
            	sc.close();       	
	        }
	          
	        catch (Exception ex) 
	        {
	            System.out.println("Error with playing sound.");
	            ex.printStackTrace();
	        }
    }

	public void play() 
    {
        clip.start();
        status = "play";
    }
}