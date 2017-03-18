package vue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundThread extends Thread {

	/*
	 * Cette classe est un Thread qui permet de rajouter du son en parallèle du gamethread.
	 */
	
	private AudioInputStream as;
	private Clip c;
	private String fichier;

	public SoundThread(String fichier) {
		super();
		this.fichier = fichier;
	}

	

	public void run() {
		playSound();
	}

	private void playSound() {
		try {
			java.net.URL url = this.getClass().getResource(fichier);
			as = AudioSystem.getAudioInputStream(url);
			DataLine.Info info = new DataLine.Info(Clip.class, as.getFormat());
			c = (Clip) AudioSystem.getLine(info);
			c.open(as);
			c.loop(-1);
			while (!this.isInterrupted()) {
			}
			c.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
