package v1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Launch extends JPanel implements ActionListener{

	private Tekst tekst;
	private Tekst dragObject;
	private Point2D lastClickPosition;
	private Timer timer;
	public static void main(String[] args) {
		JFrame frame = new JFrame("Geanimeerde tekst");
		frame.setSize(1024, 768);
		frame.setContentPane(new Launch(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Launch(JFrame frame) {
		tekst = new Tekst("tekst", new Point2D.Double(frame.getWidth()/2,frame.getHeight()/2));
		timer = new Timer(1, this);
		timer.start();
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
				repaint();
				lastClickPosition = clickPoint;
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				Point2D clickPoint = e.getPoint();
				if (tekst.contains(clickPoint)) {
					tekst.rotation += e.getWheelRotation()*10;
				}
				repaint();
			}
		});
		frame.setMenuBar(createMenu());
	}

	public MenuBar createMenu(){
		MenuBar bar = new MenuBar();
		
		Menu menu = new Menu("options");

		MenuItem item = new MenuItem();
		item.setLabel("set text");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String box = JOptionPane.showInputDialog("new text");
				tekst.setText(box);
			}
		});
		menu.add(item);
		
		MenuItem item2 = new MenuItem();
		item2.setLabel("set gradient speed");
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String box = JOptionPane.showInputDialog("set gradient speed in milliseconds");
				timer.setDelay(Integer.parseInt(box));
			}
		});
		menu.add(item2);

		bar.add(menu);

		return bar;
	}
	
 	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		tekst.draw(g2);
		//g2.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
		//g2.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
	}
	
	public void actionPerformed(ActionEvent e) {
		tekst.x2 = (int) (tekst.x1 + tekst.width); 	
		tekst.x1++;
		repaint();
	}
}
