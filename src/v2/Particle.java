package v2;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class Particle {

	private double x;
	private int size;
	private int life;
	private Color color;
	private Point2D target;
	private Point2D position;
	private double direction;
	private double speed = 0.25 + Math.random();
	private float alpha;
	private float hue = new Random().nextFloat();

	public Particle(int x, int y, Color c) {
		alpha = 1;
		direction = Math.random() * 361; 
		//this.color = c;
		size = (int) (Math.random() * 6);
		life = (int) Math.random() * (120) + 120;
		target = new Point2D.Double(x, -1000);
		position = new Point2D.Double(x, y);
		rotation();
		
		color = Color.getHSBColor(hue, 0.9f, 1.0f);
	}

	private void rotation() {
		Point2D difference = new Point2D.Double(
				target.getX() - position.getX(), 
				target.getY() + position.getY());

		double newRotation = Math.atan2(difference.getY(), difference.getX());
		double rotDifference = direction - newRotation;
		
		while (rotDifference > Math.PI) {
			rotDifference -= 2 * Math.PI;
		}

		while (rotDifference < -Math.PI) {
			rotDifference += 2 * Math.PI;
		}

		if (Math.abs(rotDifference) < 0.1) {
			direction = newRotation;
		} else if (rotDifference < 0) {
			direction -= 0.015;
		} else if (rotDifference > 0) {
			direction += 0.015;
		}

	}

	public boolean update() {
		rotation();
		position = new Point2D.Double(position.getX() + speed
				* Math.cos(direction), position.getY() + speed
				* Math.sin(direction));
		target = new Point2D.Double(x, -1000);
		x = position.getX();
		life--;
		if (life <= 0) {
			return true;
		}
		return false;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (alpha > 0.1) {
			alpha -= 0.005;
		}
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.setColor(color);
		g2d.fillOval((int) position.getX(), (int) position.getY(), size, size);
	}

}