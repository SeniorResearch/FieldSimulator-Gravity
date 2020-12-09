import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class Vector
{
	double xStart;
	double yStart;
	double xEnd;
	double yEnd;
	double xComponent;
	double yComponent;
	double length;
	Line2D vector;
	Color vectorColor;
	
	public Vector()
	{
		xStart = 0;
		yStart = 0;
		xComponent = 0;
		yComponent = 0;
		xEnd = 0;
		yEnd = 0;
		length = 0;
		vector = getVector();
	}
	
	public Vector(double xStart, double yStart, double xComponent, double yComponent)
	{
		this.xStart = xStart;
		this.yStart = yStart;
		this.xComponent = xComponent;
		this.yComponent = yComponent;
		xEnd = xStart + xComponent;
		yEnd = yStart + yComponent;
		
		//System.out.println("Vector at: X: " + xStart + " Y: " + yStart + " XC " + xComponent + " YC " + yComponent);
		length = calculateLength(xComponent, yComponent);
		vector = getVector();
		
		
	}
	
	public double calculateLength(double x, double y)
	{
		length = (Math.sqrt( (x*x) + (y*y) ));
		return length;
	}
	
	public double getLength()
	{
		return length;
	}
	
	public Line2D getVector()
	{
		vector = new Line2D.Double(xStart, yStart, xEnd, yEnd);
		return vector;
	}
	
	public GeneralPath getTip()
	{
		double tipX = 2;
		double tipY = 2;
		
		GeneralPath tip = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
		
		tip.moveTo(xEnd, yEnd);
		tip.lineTo(xEnd-tipX, yEnd-tipY);
		tip.moveTo(xEnd, yEnd);
		tip.lineTo(xEnd+tipX, yEnd-tipY);
		
		return tip;
	}
	
	public void setColor(Color color)
	{
		vectorColor = color;
	}
	
	public Color getColor()
	{
		return vectorColor;
	}

}
