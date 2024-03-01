import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main_ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Main_();
	}
	
	public Main_() {
		JFrame calculatorMainJFrame = new JFrame();
		calculatorMainJFrame.setBounds(25, 25, 250, 150);
		calculatorMainJFrame.setVisible(true);
		
		JButton button1JButton = new JButton("Click me!");
		button1JButton.setBounds(0, 0, 100, 30);

//		button1JButton.setPreferredSize(new Dimension(100, 30));
		
		JButton button2JButton = new JButton("Click me 2!");
		button2JButton.setBounds(0, 40, 120, 30);

//		button2JButton.setPreferredSize(new Dimension(150, 30));
		
		calculatorMainJFrame.getContentPane().setLayout(null);
		
		
		
		calculatorMainJFrame.getContentPane().add(button1JButton);
		calculatorMainJFrame.getContentPane().add(button2JButton);
		
		button1JButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Yay! I, Button 1, was clicked!");
			}
		});
		
		button2JButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Yay! I, Button 2, was clicked!");

			}
		});

	}
}

