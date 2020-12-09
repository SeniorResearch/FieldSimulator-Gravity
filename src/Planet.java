import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Planet
{
	private float x;
	private float y;
	private int massP;
	private int massB;
	private int radius;
	private Color color;
	private PointMass body;
	private float xCenter;
	private float yCenter;
	private BackgroundElement[][] bg;
	
	public Planet()
	{
		x = 0;
		y = 0;
		
		massP = 0;
		massB = 0;
	}
	
	public Planet(float x, float y, int massP, PointMass body)
	{
		this.x = x;
		this.y = y;
		this.massP = massP;
		this.body = body;
		this.massB = body.getMass();
	}
	
	public void setRadius(int radius)
	{
		this.radius = radius;
		xCenter = x+(radius/2);
		yCenter = y+(radius/2);
		
		generateBackground();
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void generateBackground()
	{
		bg = new BackgroundElement[300][250];
		Color c;
		int r = 1;
		int g = 1;
		int b = 1;
		int a = 1;
		
		for(int i = 0; i < 300; i++)
		{	
			for(int j = 0; j < 250; j++)
			{
				float xLoc = i*10;
				float yLoc = j*10;
				r = (int) (255 - 255/getDistance(xLoc, yLoc));
				g = (int) (255 - 255/getDistance(xLoc, yLoc));
				b = (int) (255 - 255/getDistance(xLoc, yLoc));
				a = 125;
				c = new Color(r,g,b,a);
				bg[i][j] = new BackgroundElement(xLoc, yLoc, 10, c);
			}
		}
	}
	
	private float getDistance(float xLoc, float yLoc)
	{
		float x = xCenter-xLoc;
		float y = yCenter-yLoc;
		
		double distance = Math.sqrt((x*x)+(y*y)); 

		//System.out.println(distance);
		return (float)distance;
	}
	public void draw(Graphics g)
	{
		for(int i = 0; i < 300; i++)
		{
			for(int j =0; j < 250; j++)
			{
				bg[i][j].draw(g);
			}
		}
		//g.setColor(color);
		//g.fillOval((int)x, (int)y, (int)radius, (int)radius);
	}

}
