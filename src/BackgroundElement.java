import java.awt.Color;
import java.awt.Graphics;

public class BackgroundElement 
{
	Color color;
	int size;
	float xLoc;
	float yLoc;
	
	public BackgroundElement()
	{
		color = Color.blue;
		size = 1;
	}
	
	public BackgroundElement(float x, float y, int size, Color color)
	{
		this.color = color;
		xLoc = x;
		yLoc = y;
		this.size = size;
	}
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect((int)xLoc, (int)yLoc, size, size);
	}

}
