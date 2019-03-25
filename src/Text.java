import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Text {
	Point2D position;
	double height = 200 ,width = 200,scale = 100;
	Shape rect;
	String tekst;
	GradientPaint paint;
	int x1,x2,rotation = 0;
	
	public Text(String tekst, Point2D position){
		this.position = position;
		this.tekst = tekst;
	}

	public void draw(Graphics2D g){
		Font font = new Font("Serif", Font.BOLD, (int) scale);
		FontRenderContext frc = new FontRenderContext(null,false,false);
		double width = font.getStringBounds(tekst, frc).getWidth();
		double height = font.getStringBounds(tekst, frc).getHeight() / 1.9;
		g.setFont(font);
		paint = new GradientPaint(x1,(int)position.getY(), Color.RED,(int)(x1+width),(int) position.getY(), Color.BLUE,true);
		g.setPaint(paint);
		g.rotate(Math.toRadians(rotation), position.getX()+ width/2, position.getY() - (height/2));
		g.drawString(tekst, (int)position.getX(), (int)position.getY());
		rect = new Rectangle2D.Double(position.getX(),position.getY()-height, width, height);
		//g.draw(rect);
	}

	public boolean contains(Point2D point) {
		return rect.contains(point);
	}
	
	public void setText(String tekst){
		this.tekst = tekst;
	}
}
