package geanimeerdeTekst;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Tekst2 {
	Point2D position;
	double rotation;
	double scale;
	String naam;

	public Tekst2(String naam, Point2D position){
		this.naam 	  = naam;
		this.position = position;
		scale 		  = 1;
		rotation 	  = 0;
	}

	public void draw(Graphics2D g){
		AffineTransform tx = getTransform();
		int x = (int) position.getX();
		int y = (int) position.getY();
		g.drawString(naam, x, y);
	}

	private AffineTransform getTransform() {
		AffineTransform tx = new AffineTransform();
		tx.translate(position.getX(), position.getY());
		tx.scale(scale, scale);
		tx.rotate(Math.toRadians(rotation), position.getX() / 2, position.getY() / 2);
		return tx;
	}

	public boolean contains(Point2D point) {
		Shape shape = new Rectangle2D.Double(0,0,position.getX(), position.getX());
		return getTransform().createTransformedShape(shape).contains(point);
	}
}
