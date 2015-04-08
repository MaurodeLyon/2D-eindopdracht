package geanimeerdeTekst;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GeanimeerdeTekst {

	private static final int WIDTH = 250;
	private static final int HEIGHT = 150;
	private static final int SCALE = 4;

	public GeanimeerdeTekst(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initPanel(frame);
		
		frame.pack();
		frame.setVisible(true);
	}

	public void initPanel(JFrame frame){
		JPanel panel = new Tekst();

		panel.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		panel.setMinimumSize  (new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		panel.setMaximumSize  (new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

		frame.add(panel);
	}
}

@SuppressWarnings("serial")
class Tekst extends JPanel {
	public Tekst() {
		initTekst();
	}

	public void initTekst(){
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		GradientPaint paint = new GradientPaint(100, 300, Color.RED, 400, 400, Color.BLUE);
		g2.setPaint(paint);
		Font font = new Font("Serif", Font.BOLD, 144);
		g2.setFont(font);
		g2.drawString("Java", 100, 400);
	}
}