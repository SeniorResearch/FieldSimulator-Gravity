import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.*;

public class FieldPanel extends JPanel
{
	
	private int numCircles = 1000;
	PointMass[] mass;
	BackgroundElement[][] bg;
	
	double startX;
	double startY;
	double endX;
	double endY;
	double xComponent;
	double yComponent;
	
	PointMass test;
	
	Launcher launch;
	
	Planet planet;
	
	boolean drawn = false;
	
	private final double G = 0.00000000006673;
	
	VectorField fieldLines;
	
	boolean ready;
	boolean linesOn;
	
	boolean planetOn;
	boolean traceOn;
	
	public FieldPanel()
	{
		ready = false;
		linesOn = false;
		planetOn = false;
		traceOn = false;
		//generateMovingFreefall(numCircles);
		//generateBackgroundGradient();
		//generatePlanet();
		//fieldLines = new VectorField(50,50,10);
		
		//createLauncher(10,10);
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				int a = e.getX();
				int b = e.getY();
				//launchMassAt(a,b);
				launchMultipleMass(a,b);
				ready = true;
				runSim();
			}

		});
		
		//runSim();
	}
	public void generateNearEarth(int numObjects)
	{
		numCircles = numObjects;
		generateMovingFreefall(numCircles);
		fieldLines = new VectorField(47,47,10);
		ready = true;
		runSim();
	}
	public void setFieldLines(boolean linesOn)
	{
		this.linesOn = linesOn;		
	}
	public void setTraceOn(boolean traceOn)
	{
		this.traceOn = traceOn;
	}
	private void generateBackgroundGradient() 
	{
		bg = new BackgroundElement[235][235];
		Color c;
		//float r = 1;
		//float g = 1;
		//float b = 1;
		//float a = 1/3;
		
		int r = 255;
		int g = 255;
		int b = 255;
		int a = 255;
			
		for(int i = 0; i < 235; i++)
		{	
			for(int j = 0; j < 235; j++)
			{
				float yLoc = i*4;
				float xLoc = j*4;
				//r = yLoc/1000;
				r = (int) (255-i);
				g = (int) (255-i);
				b = (int) (255-i);
				a = (int) (75);
				c = new Color(r,g,b,a);
				bg[i][j] = new BackgroundElement(xLoc, yLoc, 4, c);
			}
		}
	}

	private void createLauncher(int x, int y)
	{
		launch = new Launcher(x,y);
	}
	
	private void launchMultipleMass(float x, float y)
	{
		numCircles = 3;
		mass = new PointMass[numCircles];
		Random rand = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < numCircles; i++)
		{
			float r = rand.nextFloat()*900;
			float g = rand.nextFloat()*900;
			float b = rand.nextFloat()*900;
			
			float xV = i+1;			
			float yV = 0;

			float xA = 0;				
			float yA = 1;
			
			mass[i] = new PointMass(x,y,xV,yV,xA,yA,25,1);
			
			Color color = new Color(r/900, g/900 ,b/900, y/900);
			
			mass[i].setColor(color);
			mass[i].setTraceOn(traceOn);
		}
	}
		
	private void launchMassAt(float x, float y)
	{
		numCircles = 1;
		mass = new PointMass[1];
		mass = new PointMass[numCircles];
		
		Random rand = new Random(System.currentTimeMillis());
		
		float z = rand.nextFloat()*900;

		float xV = rand.nextFloat()*10 - 5;		//Random between -5 and 5		
		float yV = rand.nextFloat()*10 - 5;		//Random between -5 and 5
		
		float xA = 0;				
		float yA = 1;
		
		mass[0] = new PointMass(x,y,xV,yV,xA,yA,50,1);
		
		Color color = new Color(x/900, y/900 ,z/900, y/900);
		
		mass[0].setColor(color);
	}
	private void generateMovingFreefall(int numCircles)
	{
			this.numCircles = numCircles;
			mass = new PointMass[numCircles];
			Random rand = new Random(System.currentTimeMillis());
			
			for(int i = 0; i < numCircles; i++)
			{
				float x = rand.nextFloat()*900;
				float y = rand.nextFloat()*900;
				float z = rand.nextFloat()*900;
				
				
				float xV = rand.nextFloat()*10 - 5;				
				float yV = rand.nextFloat()*10 - 5;

				float xA = 0;				
				float yA = 1;
				
				mass[i] = new PointMass(x,y,xV,yV,xA,yA,10,1);
				
				Color color = new Color(x/900, y/900 ,z/900, y/900);
				
				mass[i].setColor(color);
				mass[i].setTraceOn(traceOn);
			}
				
	}
	
	private void generatePlanet()
	{
		PointMass body = new PointMass(0,0,0,0,0,0,0,0);
		Color color = new Color(255, 196 ,0, 125);
		planet = new Planet(500,500,10000,body);
		planet.setRadius(75);
		planet.setColor(color);
		
		numCircles = 2;
		mass = new PointMass[2];
		
		float x = 500;
		float y = 500;
		float xV = 0;
		float yV = 0;
		float xA = 0;
		float yA = 0;
		int radius = 50;
		int m = 1000;
		mass[0] = new PointMass(x, y, xV, yV, xA, yA, radius, m);
		//Color color = new Color(255, 196 ,0, 125);
		mass[0].setColor(color);
		
		x = 400;
		y = 400;
		xV = 1;
		yV = 0;
		xA = 0;
		yA = 0;
		
		mass[1] = new PointMass(x, y, xV, yV, 1, 1, 10, 1);
		
		planetOn = true;
		runSim();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		for(int i = 0; i < 235; i++)
		{
			for(int j =0; j < 235; j++)
			{
				//bg[i][j].draw(g);
			}
		}
		
		if(ready)
		{
			for(int i = 0; i < numCircles; i++)
			{
				mass[i].draw(g2d);
			}
		}
		if(linesOn)
		{
			fieldLines.draw(g2d);
		}
		
		//fieldLines.draw(g2d);
		g2d.setColor(Color.black);
		g2d.drawLine(0, 925, 925, 925);
		g2d.drawLine(925, 0, 925, 925);
		
		//launch.draw(g2d);
		//planet.draw(g2d);
		//mass[1].draw(g2d);
	}
	
	 public void runSim() 
	 {
		 Thread simThread = new Thread() 
	     {
			 float t = 0;
			 int frameCount = 0;
			 public void run() 
	         {
				 while (true) 
	             {
					 t = (t+1)/4;
					 for(int i = 0; i < numCircles; i++)
					 {
						if(!planetOn)
						{
							mass[i].move(t);
						}
						if(planetOn)
						{
							mass[1].move2(t);
						}
					 }
					 frameCount += 1;
					 System.out.println(frameCount);
	            	 repaint();
	            	 try 
	            	 {
	            		 Thread.sleep(20);
	                 } 
	                 catch (InterruptedException e) 
	                 {
	            	   
	                 }
	             }
	         }
	     };
	      simThread.start();
	     
	 }

}
