import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final Dimension SCREENSIZE = new Dimension(1024, 768);
	
	private Panel panel;
	
	public Frame(){
		super("eindopdracht");

		setSize(SCREENSIZE);
		
		panel = new Panel(this);
		
		setContentPane(panel.getPanel());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
