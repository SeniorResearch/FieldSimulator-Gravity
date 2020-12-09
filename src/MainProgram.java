import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainProgram extends JFrame
{
	JButton startButton;
	JButton stopButton;
	
	JPanel field;
	
	JCheckBox nearEarthBox;
	
	public MainProgram()
	{	
		JLabel label = new JLabel("Menu");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel buttonPanel = new JPanel();
		field = new FieldPanel();
		JPanel holder = new JPanel();
		holder.setLayout(new BoxLayout(holder, BoxLayout.X_AXIS));

		
		startButton = new JButton("Start");
		stopButton = new JButton( "Stop ");
		
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		startButton.addActionListener(new ButtonListener());
		stopButton.addActionListener(new ButtonListener());
		
		nearEarthBox = new JCheckBox("Near Earth");
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(label);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		buttonPanel.add(stopButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		buttonPanel.add(nearEarthBox);
		buttonPanel.add(Box.createVerticalGlue());
		
		field.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		holder.add(buttonPanel);
		holder.add(Box.createRigidArea(new Dimension(10,0)));
		holder.add(field);
		
		setTitle("Particle Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(holder);
		pack();
		setVisible(true);
		setSize(1300,1100);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
	      {
			if(e.getSource() == startButton)
			{
				System.out.println("Start button clicked");
				if(nearEarthBox.isSelected())
				{
					field.generateNearEarth(100);
				}
			}
			else if(e.getSource() == stopButton)
			{
				System.out.println("Stop button clicked");
			}
	      }
	}
}