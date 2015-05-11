package bureau;

import java.awt.event.*;
import javax.swing.*;

public class MouvementListener extends ComponentAdapter {

	private Bureau bureau;

	public MouvementListener(Bureau b) {
		bureau = b;	
		System.out.println("test !");
	}

	public void componentMoved(ComponentEvent e){
		java.awt.Point loc =e.getComponent().getLocation();
		System.out.println("test !");
		Bureau.setMaj(true);
	}
}
