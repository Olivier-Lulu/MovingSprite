package controleur;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import modele.Niveau;

public class ActionPressedP extends AbstractAction{

	private static final long serialVersionUID = 1L;
	
	private Niveau n;
	
	public ActionPressedP(Niveau n) {
		// TODO Auto-generated method stub
		super("pressed D");
		this.n = n;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		n.aFini = true;
	}

}
