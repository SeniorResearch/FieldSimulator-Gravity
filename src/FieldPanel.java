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
	
	int numCircles = 10000;
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
	
	public FieldPanel()
	{
		//generateMovingFreefall(numCircles);
		//generateBackgroundGradient();
		generatePlanet();
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				int a = e.getX();
				int b = e.getY();
				launchMassAt(a,b);
			}

		});
		
		runSim();
	}
	
	private void generateBackgroundGradient() 
	{
		bg = new BackgroundElement[300][250];
		Color c;
		float r = 1;
		float g = 1;
		float b = 1;
		float a = 1;
			
		for(int i = 0; i < 300; i++)
		{	
			for(int j = 0; j < 250; j++)
			{
				float xLoc = i*4;
				float yLoc = j*4;
				r = yLoc/1000;
				g = yLoc/1000;
				b = yLoc/1000;
				c = new Color(r,g,b,a);
				bg[i][j] = new BackgroundElement(xLoc, yLoc, 4, c);
			}
		}
	}

	private void createLauncher(int x, int y)
	{
		launch = new Launcher(x,y);
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
			}
				
	}
	private void generateRandomMasses(int num) 
	{
		mass = new PointMass[numCircles];
		Random rand = new Random(System.currentTimeMillis());
		float x;
		float y;
		
		float xV;
		float yV;
		
		float xA;
		float yA;
		
		Color color;
		
		for(int i = 0; i < num; i++)
		{
			x = rand.nextFloat()*900;
			y = rand.nextFloat()*900;
			
			xV = rand.nextFloat()*2;				
			yV = rand.nextFloat()*2;

			xA = rand.nextFloat()/10;				
			yA = rand.nextFloat()/10;
			
			mass[i] = new PointMass(x,y,xV,yV,xA,yA,10,1);
			
			color = new Color(x/900, y/900,xV/2);
			
			mass[i].setColor(color);
				
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
		
		x = 250;
		y = 250;
		xV = 5;
		yV = 0;
		xA = 0;
		yA = 0;
		
		mass[1] = new PointMass(x, y, xV, yV, 1, 1, 10, 1);
	}
	
	private void generateSingleMass()
	{
		numCircles = 1;
		mass = new PointMass[1];
		Random rand = new Random(System.currentTimeMillis());
		
		float x = rand.nextFloat()*900;
		float y = rand.nextFloat()*900;
		
		float xV = 0;				
		float yV = 0;

		float xA = 0;				
		float yA = 1;
		
		mass[0] = new PointMass(x,y,xV,yV,xA,yA,10,1);
		
		Color color = new Color(x/900, y/900,xV/2);
		
		mass[0].setColor(color);
			
	}
	
	
	private void generateStillFreefall(int numMass)
	{
		numCircles = numMass;
		mass = new PointMass[numCircles];
		Random rand = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < numCircles; i++)
		{
			float x = rand.nextFloat()*900;
			float y = rand.nextFloat()*900;
			float z = rand.nextFloat()*900;
			
			
			float xV = 0;				
			float yV = 0;

			float xA = 0;				
			float yA = 1;
			
			mass[i] = new PointMass(x,y,xV,yV,xA,yA,20,1);
			
			Color color = new Color(1/2, 1/2 ,1/2, y/900);
			
			mass[i].setColor(color);
		}
			
	}
	
	private void generateFreefall(int numMass)
	{
		numCircles = numMass;
		mass = new PointMass[numCircles];
		Random rand = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < numCircles; i++)
		{
			float x = rand.nextFloat()*900;
			float y = rand.nextFloat()*900;
			float z = rand.nextFloat()*900;
			
			
			float xV = 0;				
			float yV = 0;

			float xA = 0;				
			float yA = 1;
			
			mass[i] = new PointMass(x,y,xV,yV,xA,yA,20,1);
			
			Color color = new Color(1/2, 1/2 ,1/2, y/900);
			
			mass[i].setColor(color);
		}
			
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				
		for(int i = 0; i < numCircles; i++)
		{
			//mass[i].draw(g2d);
		}
		
		for(int i = 0; i < 300; i++)
		{
			for(int j =0; j < 250; j++)
			{
				//bg[i][j].draw(g);
			}
		}
		
		planet.draw(g2d);
		//for(int i = 0; i < 160; i++)
		//{
			//bgElements[i].draw(g);
		//}
		
	}
	
	 public void runSim() 
	 {
		 Thread simThread = new Thread() 
	     {
			 float t = 0;
			 public void run() 
	         {
				 while (true) 
	             {
					 t = (t+1)/4;
					 for(int i = 0; i < numCircles; i++)
					 {
						// mass[i].move(t);
						// mass[1].move2(t);
					 }
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
