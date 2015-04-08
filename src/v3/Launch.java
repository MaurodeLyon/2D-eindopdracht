package v3;

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
public class Launch extends JPanel {

	private Tekst tekst;
	private Tekst dragObject;
	private Point2D lastClickPosition;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Geanimeerde tekst");
		frame.setSize(1024, 768);
		frame.setContentPane(new Launch(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Launch(JFrame frame) {
		tekst = new Tekst("JAVA", new Point2D.Double(
				frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 100));

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point2D clickPoint = e.getPoint();
				lastClickPosition = clickPoint;
				if (tekst.contains(clickPoint)) {
					dragObject = tekst;
				}

			}

			public void mouseReleased(MouseEvent e) {
				dragObject = null;
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point2D clickPoint = e.getPoint();
				if (dragObject != null) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						dragObject.position = new Point2D.Double(
								dragObject.position.getX()
										- (lastClickPosition.getX() - clickPoint
												.getX()),
								dragObject.position.getY()
										- (lastClickPosition.getY() - clickPoint
												.getY()));
					} else {
						dragObject.rotation += (lastClickPosition.getX() - clickPoint
								.getX());
					}
				}

				repaint();
				lastClickPosition = clickPoint;
			}
		});
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				Point2D clickPoint = e.getPoint();
				if (tekst.contains(clickPoint)) {
					System.out.println(1 + (e.getPreciseWheelRotation() / 10.0));
					tekst.scale *= 1 + (e.getPreciseWheelRotation() / 10.0);
					System.out.println(e.getWheelRotation());
					tekst.position = new Point2D.Double(tekst.position.getX(), tekst.position.getY());
					
				}
				System.out.println(tekst.tekstheight + " " + tekst.tekstwidth);
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		AffineTransform oldTransform = g2.getTransform();
		tekst.draw(g2);
		g2.setTransform(oldTransform);
	}
}
