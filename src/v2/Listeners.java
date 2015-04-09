package v2;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

public class Listeners{
	private Point2D lastClickPosition;
	private Text dragObject; 
	
	public Listeners(Panel panel,Text text){
		panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point2D clickPoint = e.getPoint();
				lastClickPosition = clickPoint;
				if (text.contains(clickPoint)) {
					dragObject = text;
				}
				if (SwingUtilities.isMiddleMouseButton(e)) {
					for (int i = 0; i < 100; i++) {
						panel.getParticles().add(new Particle(e.getX(), e.getY()));
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				dragObject = null;
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point2D clickPoint = e.getPoint();
				if (dragObject != null) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						dragObject.position = new Point2D.Double(
								dragObject.position.getX() - (lastClickPosition.getX() - clickPoint.getX()),
								dragObject.position.getY() - (lastClickPosition.getY() - clickPoint.getY()));
					}
					else if (SwingUtilities.isRightMouseButton(e)){
						if(dragObject.scale > 20)
							dragObject.scale -= lastClickPosition.getX() - clickPoint.getX();
						else if((lastClickPosition.getX() - clickPoint.getX()) < 0)
							dragObject.scale -= lastClickPosition.getX() - clickPoint.getX();
						else
							dragObject.scale = 20;
						
					}
				}
				panel.repaint();
				lastClickPosition = clickPoint;
			}
		});
		
		panel.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				Point2D clickPoint = e.getPoint();
				if (text.contains(clickPoint)) {
					text.rotation += e.getWheelRotation()*10;
				}
				panel.repaint();
			}
		});
		
	}
}
