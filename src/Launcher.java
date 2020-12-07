import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Launcher 
{
	int width;
	int height;
	int x;
	int y;
	float angle;
	
	Color color;

	public Launcher(int x, int y)
	{
		this.width = 100;
		this.height = 50;
		this.x = x;
		this.y = y;
		
		this.angle = 45;
		
		 color = new Color(125,125,125,125);
	}
	
	public void draw(Graphics g)
	{
		//g.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setColor(Color.black);
		//g.drawRect(x, y, width, height);
		
		Rectangle rect = new Rectangle(x,y,width,height);
		Rectangle outline = new Rectangle(x,y,width,height);
		
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(Math.toRadians(angle));
		g2d.draw(outline);
		g2d.fill(rect);
		g2d.rotate(Math.toRadians(-45));
	}
}
