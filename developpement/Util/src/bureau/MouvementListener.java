package bureau;

import java.awt.event.*;
import javax.swing.*;

public class MouvementListener extends ComponentAdapter {

	private Bureau bureau;

	public MouvementListener(Bureau b) {
		bureau = b;	
	}

	public void componentMoved(ComponentEvent e){
		Bureau.setMaj(true);
	}
}
