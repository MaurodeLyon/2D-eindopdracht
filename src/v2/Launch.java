package v2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Launch extends JPanel{

	private Tekst tekst;
	private Tekst dragObject;
	private Point2D lastClickPosition;
	private Point2D lastMousePosition;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Geanimeerde tekst");
		frame.setSize(1024,768);
		frame.setContentPane(new Launch());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Launch(){

		tekst = new Tekst("JAVA",new Point2D.Double(100,100));
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e){
				Point2D clickPoint = e.getPoint();
				lastClickPosition = clickPoint;
				lastMousePosition = e.getPoint();
				if(tekst.contains(clickPoint)){
					dragObject = tekst;
				}

			}
			public void mouseReleased(MouseEvent e) {
				dragObject = null;
			}
		});
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e) {
				Point2D clickPoint = e.getPoint();
				if(dragObject != null)
				{
					if(SwingUtilities.isLeftMouseButton(e)){
						dragObject.position = new Point2D.Double(
								dragObject.position.getX() - (lastClickPosition.getX() - clickPoint.getX()), 
								dragObject.position.getY() - (lastClickPosition.getY() - clickPoint.getY()));
					}
					else{
						dragObject.rotation += (lastClickPosition.getX() - clickPoint.getX());
					}
				}

				repaint();
				lastMousePosition = e.getPoint();
				lastClickPosition = clickPoint;
			}
		});
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				Point2D clickPoint = e.getPoint();
				if(tekst.contains(clickPoint))
				{
					tekst.scale *= 1 + (e.getPreciseWheelRotation()/10.0);
					tekst.position = new Point2D.Double(tekst.position.getX(),tekst.position.getY());
				}
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		AffineTransform oldTransform = g2.getTransform();
		tekst.draw(g2);	
		g2.setTransform(oldTransform);
	}
}
