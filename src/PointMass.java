import java.awt.Color;
import java.awt.Graphics;

public class PointMass
{
	int mass;
	float xPos;
	float yPos;
	
	float xPrv;
	float yPrv;
	float xV;
	float yV;
	float xA;
	float yA;
	float radius;
	Color color = Color.black;
	private final double G = 0.00000000006673;
	
	public PointMass()
	{
		xPrv = 0;
		yPrv = 0;
		
		xPos = 0;
		yPos = 0;
		
		xV = 0;
		yV = 0;
		
		xA = 0;
		yA = 0;
		
		radius = 0;
		mass = 0;
	}
	
	public PointMass(float xPos, float yPos, float xV, float yV, float xA, float yA, float radius, int mass)
	{
		this.xPrv = 0;
		this.yPrv = 0;
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.xV = xV;
		this.yV = yV;
		
		this.xA = xA;
		this.yA = yA;
		
		this.radius = radius;
		this.mass = mass;
	}
	
	public void move()
	{
		checkPosition();
		
		xV += xA;
		yV += yA;
		xPos += xV;
		yPos += yV;
	}
	
	public void move(float t)
	{
		xPrv = xPos;
		yPrv = yPos;
		xPos = xPos + (xV*t) + ((xA*t)*t)/2;
		yPos = yPos + (yV*t) + ((yA*t)*t)/2;
		
		//System.out.println("xPos: " + xPos + " xPrv: " + xPrv + " yPos: " + yPos + " yPrv: " + yPrv);
		xV = xV + (xA*t);
		yV = yV + (yA*t);
		
		//System.out.println("xPos: " + xPos + " yPos: " + yPos);
		//System.out.println("xV: " + xV + " yV: " + yV);
		
		
		checkPosition(); 
	}
	
	public void move2(float t)
	{
		xPrv = xPos;
		yPrv = yPos;
		//xPos = xPos + (xV*t) + ((xA*t)*t)/2;
		//yPos = yPos + (yV*t) + ((yA*t)*t)/2;
		
		//System.out.println("xPos: " + xPos + " xPrv: " + xPrv + " yPos: " + yPos + " yPrv: " + yPrv);
		//xV = xV + (xA*t);
		//yV = yV + (yA*t);
		double theta = Math.atan(yPos/xPos);
		
		xA = (float) ((1000) / ((500-xPos)*(500-xPos)+(500-yPos)*(500-yPos)));
		yA = (float) ((1000) / ((500-xPos)*(500-xPos)+(500-yPos)*(500-yPos)));
		
		xA *= Math.cos(theta);
		yA *= Math.sin(theta);
		
		xV = xV + (xA*t);
		yV = yV + (yA*t);
		
		xPos = xPos + (xV*t) + ((xA*t)*t)/2;
		yPos = yPos + (yV*t) + ((yA*t)*t)/2;
		
		
		//System.out.println("xPos: " + xPos + " yPos: " + yPos);
		//System.out.println("xV: " + xV + " yV: " + yV);
		
		
		checkPosition(); 
	}
	public int getMass()
	{
		return mass;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	private void checkPosition()
	{
		if(xPos > 900)
		{
			xV *= -1;
			xA *= -1;
		}
		if(xPos < 0)
		{
			xV *= -1;
			xA *= -1;
		}
		
		if(yPos > 900)
		{
			if(yV > 0)
			{
				yV *= -1;
			}
		}
		if(yPos < 0)
		{
			yV *= -1;
			//yA *= -1;
			yPos = 0;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)xPos, (int)yPos, (int)radius, (int)radius);
		
		//g.setColor(Color.black);
		//g.drawLine((int)(xPos+25), (int)( yPos+25), (int)(xPrv+25), (int)(yPrv+25));
	}
}
