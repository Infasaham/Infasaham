import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ToDoItemJPanel extends JPanel {
	
	private JCheckBox itemDoneCheckBox = new JCheckBox();
	private JTextField toDoItemJTextField = new JTextField();
	
	public ToDoItemJPanel () {
		setBorder(new LineBorder(new Color(0, 0, 0)));

		toDoItemJTextField.setPreferredSize(new Dimension(300, 25));
		add(itemDoneCheckBox);
		add(toDoItemJTextField);
		
	}
	
	public JTextField getToDoItemJTextField() {
		return toDoItemJTextField;
	}
	
	public void setToDoItemJTextField(String text) {
		toDoItemJTextField.setText(text);
	}

}
