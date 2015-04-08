package v2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Tekst {

	Point2D position;
	double rotation;
	double scale;
	String tekst;
	double tekstwidth,tekstheight;
	
	public Tekst(String tekst, Point2D position){
		this.tekst = tekst;
		scale = 1;
		rotation = 0;
		this.position = position;
	}

	public void draw(Graphics2D g){
		AffineTransform at = getTransform();
		g.setTransform(at);

		GradientPaint paint = new GradientPaint((int)position.getX(), 
												(int)position.getY(), 
												Color.RED, 
												(int)(position.getX()+tekstwidth), 
												(int)(position.getY()+tekstheight), 
												Color.BLUE
												);
		g.setPaint(paint);

		Font font = new Font("Serif", Font.BOLD, (int) (144 * scale));
		g.setFont(font);
		FontRenderContext frc = new FontRenderContext(at, true, true);

		tekstwidth = font.getStringBounds(tekst, frc).getWidth();
		tekstheight = font.getStringBounds(tekst, frc).getHeight() / 1.9;
		g.drawString(tekst, (int) position.getX(), (int) (position.getY() + tekstheight));
		Shape shape = new Rectangle2D.Double((int) position.getX(),	(int) position.getY(), tekstwidth, tekstheight);
		g.draw(shape);

	}

	private AffineTransform getTransform(){
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(rotation),(int)position.getX()+(tekstwidth/2),(int)position.getY() + (tekstheight/2));
		return at;
	}

	public boolean contains(Point2D point){
		Shape shape = new Rectangle2D.Double((int)position.getX(),(int)position.getY(),tekstwidth,tekstheight);
		return getTransform().createTransformedShape(shape).contains(point);
	}
}
