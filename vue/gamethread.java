package vue;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Niveau;
import modele.Parametres;

public class gamethread implements Runnable {
	
	Niveau n;
	FenetreNiveau fn;
	Menu m;
	JFrame frame;
	Parametres p;
	SelectNiveau sn;
	final JPanel papaPanel = new JPanel();
	final CardLayout card = new CardLayout();
	
	 
	public gamethread(Niveau n,Menu m,JFrame frame, Parametres p,SelectNiveau sn){
		this.n= n;
		this.m = m;
		this.frame = frame;
		this.p = p;
		this.sn = sn;
		fn = new FenetreNiveau(n);
	}
	
	public void start(){
		Thread t = new Thread(this);
        t.start();
	}
	
	public void run(){
		papaPanel.setLayout(card);
		papaPanel.add(m,"un");
		papaPanel.add(p,"deux");
		papaPanel.add(sn,"trois");
		papaPanel.add(fn,"quatre");
		long dernier = System.currentTimeMillis();
		int images = 0;
		this.frame.setContentPane(papaPanel);
		this.frame.validate();
		card.show(papaPanel, "un");
		while (m.isRunning()){
			long maintenant = System.currentTimeMillis();
			m.repaint();
			images++;
			if (maintenant -dernier > 1000){
				dernier = maintenant;
				//System.out.println(images + " images par seconde");
				images=0;
			}
			while(m.isParam()){
				maintenant = System.currentTimeMillis();
				/*this.frame.setContentPane(p);
				this.frame.revalidate();*/
				card.show(papaPanel, "deux");
				p.repaint();
				images++;
				if (maintenant -dernier > 1000){
					dernier = maintenant;
					//System.out.println(images + " images par seconde");
					images=0;
				}
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!m.isParam()&& this.frame.getContentPane().equals(papaPanel)){
				/*this.frame.remove(p);
				this.frame.setContentPane(m);
				this.frame.revalidate();
				this.frame.repaint();*/
				card.show(papaPanel, "un");
			}
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		/*this.frame.remove(m);
		this.frame.setContentPane(sn);
		this.frame.revalidate();
		this.frame.repaint();*/
		card.show(papaPanel, "trois");
		while(true){
			sn.repaint();
			while(sn.getNiveauRunning() != null){
				/*this.frame.remove(sn);
				this.frame.setContentPane(fn);
				this.frame.revalidate();
				this.frame.repaint();*/
				fn.setNiveau(sn.getNiveauRunning());
				card.show(papaPanel, "quatre");
				long maintenant = System.currentTimeMillis();
				fn.repaint();
				n.bouger();
				images++;
				if (maintenant -dernier > 1000){
					dernier = maintenant;
					//System.out.println(images + " images par seconde");
					images=0;
				}
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
