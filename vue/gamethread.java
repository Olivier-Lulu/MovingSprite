package vue;

import javax.swing.JFrame;

import modele.Parametres;

public class gamethread implements Runnable {
	
	Niveau n;
	Menu m;
	JFrame frame;
	Parametres p;
	
	public gamethread(Niveau n,Menu m,JFrame frame, Parametres p){
		this.n= n;
		this.m = m;
		this.frame = frame;
		this.p = p;
	}
	
	public void start(){
		Thread t = new Thread(this);
        t.start();
	}
	
	public void run(){
		long dernier = System.currentTimeMillis();
		int images = 0;
		this.frame.setContentPane(m);
		this.frame.validate();
		while (m.isRunning()){
			long maintenant = System.currentTimeMillis();
			m.repaint();
			images++;
			if (maintenant -dernier > 1000){
				dernier = maintenant;
				System.out.println(images + " images par seconde");
				images=0;
			}
			while(m.isParam()){
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
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		this.frame.remove(m);
		this.frame.setContentPane(n);
		this.frame.revalidate();
		this.frame.repaint();
		while(true){
			long maintenant = System.currentTimeMillis();
			n.repaint();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
