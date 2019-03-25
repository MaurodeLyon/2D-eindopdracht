import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -1067589857206221833L;

	private Text text;
	private Timer timer;
	private int speed = 1;
	private Bar bar;
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public Panel(Frame frame){
		text = new Text("Text", new Point2D.Double(frame.getWidth()/2,frame.getHeight()/2));
		timer = new Timer(1, this);
		timer.start();
		new Listeners(this,text);
		
		bar = new Bar(this, text, timer, speed);
		frame.setMenuBar(bar.getMenuBar());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		text.draw(g2);
		for(Particle particle : particles){
			particle.render(g2);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		int scaler = 1;
		if((text.scale/100) > 1)
			scaler = (int) (text.scale/100);
		text.x1 += speed * scaler;
		Iterator<Particle>  it = particles.iterator();
		while(it.hasNext()){
			if(it.next().update()){
				it.remove();
			}
		}
		repaint();
	}

	public Panel getPanel(){
		return this;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public List<Particle> getParticles(){
		return particles;
	}
}
