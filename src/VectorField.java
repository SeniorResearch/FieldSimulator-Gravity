import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class VectorField 
{
	private Vector[][] fieldLines;
	private int numLines;
	private int rows;
	private int cols;
	
	private Color color = new Color(125, 125, 125, 125);
	
	public VectorField()
	{
		
	}
	public VectorField(int numLines)
	{
		this.numLines = numLines;
		fieldLines = new Vector[numLines][];
	}
	
	public VectorField(int rows, int cols, int g)
	{
		this.rows = rows;
		this.cols = cols;
		
		fieldLines = new Vector[rows][cols];
			for(int i = 0; i < rows; i++)
			{	
				for(int j = 0; j < cols; j++)
				{
					int xLoc = 20*i;
					int yLoc = 20*j;
					fieldLines[i][j] = new Vector(xLoc, yLoc, 0, g);
				}
			}
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for(int i = 0; i < cols; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				g2d.setColor(color);
				g2d.draw(fieldLines[i][j].getVector());
				g2d.draw(fieldLines[i][j].getTip());
			}
		}
	}
	
}
