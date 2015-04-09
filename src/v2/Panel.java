package v2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -1067589857206221833L;
	
	private JPanel panel;
	private Text text;
	private Timer timer;
	private int speed = 1;
	private Bar bar;
	
	public Panel(JFrame frame){
		text = new Text("Text", new Point2D.Double(panel.getWidth()/2,panel.getHeight()/2));
		timer = new Timer(1, this);
		timer.start();
		new Listeners(this,text);
		
		bar = new Bar(this, text, timer, speed);
		frame.setMenuBar(bar.getMenuBar());
	}
	
	public JPanel getJPanel(){
		return panel;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		text.draw(g2);
	}
	
	public void actionPerformed(ActionEvent e) {
		int scaler = 1;
		if((text.scale/100) > 1)
			scaler = (int) (text.scale/100);
		text.x1 += speed * scaler;
		repaint();
	}
}
