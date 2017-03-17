package vue;

import javax.swing.JFrame;

import modele.Niveau;
import modele.Parametres;

public class gamethread extends Thread {
	
	Niveau n;
	FenetreNiveau fn;
	Menu m;
	JFrame frame;
	Parametres p;
	
	public gamethread(Niveau n,Menu m,JFrame frame, Parametres p){
		this.n= n;
		this.m = m;
		this.frame = frame;
		this.p = p;
		fn = new FenetreNiveau(n);
	}
	
	public void run(){
		long dernier = System.currentTimeMillis();
		int images = 0;
		this.frame.setContentPane(m);
		this.frame.validate();
		while (m.isRunning() && !this.isInterrupted()){
			long maintenant = System.currentTimeMillis();
			m.repaint();
			images++;
			if (maintenant -dernier > 1000){
				dernier = maintenant;
				System.out.println(images + " images par seconde");
				images=0;
			}
			while(m.isParam() && !this.isInterrupted()){
				maintenant = System.currentTimeMillis();
				this.frame.remove(m);
				this.frame.setContentPane(p);
				this.frame.revalidate();
				this.frame.repaint();
				p.repaint();
				images++;
				if (maintenant -dernier > 1000){
					dernier = maintenant;
					System.out.println(images + " images par seconde");
					images=0;
				}
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					this.interrupt();
				}
			}
			if(!m.isParam()&& this.frame.getContentPane().equals(p)){
				this.frame.remove(p);
				this.frame.setContentPane(m);
				this.frame.revalidate();
				this.frame.repaint();
			}
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				this.interrupt();
			}
		}	
		this.frame.remove(m);
		this.frame.setContentPane(fn);
		this.frame.revalidate();
		this.frame.repaint();
		while(!this.isInterrupted()){
			long maintenant = System.currentTimeMillis();
			fn.repaint();
			if (n.peutBouger())
				n.bouger();
			images++;
			if (maintenant -dernier > 1000){
				dernier = maintenant;
				System.out.println(images + " images par seconde");
				images=0;
			}
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				this.interrupt();
			}
		}
	}
}
