import java.awt.*;
import javax.swing.*;

public class MainProgram extends JFrame
{

	public MainProgram()
	{	
		JLabel label = new JLabel("Menu");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel buttonPanel = new JPanel();
		JPanel field = new FieldPanel();
		JPanel holder = new JPanel();
		holder.setLayout(new BoxLayout(holder, BoxLayout.X_AXIS));

		
		JButton startButton = new JButton("Start");
		JButton stopButton = new JButton( "Stop ");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(label);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		buttonPanel.add(stopButton);
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
		setSize(1100,1000);
	}
}