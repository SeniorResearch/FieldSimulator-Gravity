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
	
	 int[] xPoints;
	 int[] yPoints;
	 
	 int nPoints;
	 int maxPoints = 10000;
	 
	 boolean traceOn;
	
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
		xPoints = new int[maxPoints];
		yPoints = new int[maxPoints];
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
		
		traceOn = false;
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
		
		xV = xV + (xA*t);
		yV = yV + (yA*t);
		
		xPoints[nPoints] = (int)xPos;
		yPoints[nPoints] = (int)yPos;
		nPoints += 1;
		checkPosition(); 
	}
	
	public void move2(float t)
	{
		xPrv = xPos;
		yPrv = yPos;
		double theta = Math.atan(yPos/xPos);
		
		xA = (float) (((1000) / ((500-xPos)*(500-xPos)+(500-yPos)*(500-yPos)))*Math.cos(theta));
		yA = (float) (((1000) / ((500-xPos)*(500-xPos)+(500-yPos)*(500-yPos)))*Math.sin(theta));
		
		xA *= Math.cos(theta);
		yA *= Math.sin(theta);
		
		xV = xV + (xA*t);
		yV = yV + (yA*t);
		
		xPos = xPos + (xV*t) + ((xA*t)*t)/2;
		yPos = yPos + (yV*t) + ((yA*t)*t)/2;
		
		xPoints[nPoints] = (int)xPos;
		yPoints[nPoints] = (int)yPos;
		nPoints += 1;
		
		checkPosition(); 
	}
	public int getMass()
	{
		return mass;
	}
	
	public void setTraceOn(boolean traceOn)
	{
		this.traceOn = traceOn;
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
		
		if(nPoints == maxPoints)
		{
			nPoints = 0;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)xPos, (int)yPos, (int)radius, (int)radius);
		
		if(traceOn)
		{
			g.drawPolyline(xPoints, yPoints, nPoints);
		}

	}
}
