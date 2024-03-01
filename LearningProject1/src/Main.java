import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JTextArea;


public class Main {
	private String appVersionString = "v0.001";
	private JFrame frame;
	private JTextField outputTextField;
	private Timer timer = new Timer();
	int counter = 0;
	private JButton divisionSignJButton;
	private JButton number1JButton;
	private JButton number2JButton;
	private JButton number4JButton;
	private JButton number3JButton;
	private JButton number5JButton;
	private JButton number6JButton;
	private JButton number7JButton;
	private JButton number8JButton;
	private JButton number9JButton;
	private JButton subtractionSignJButton;
	private JButton additionSignJButton;
	private JButton multiplicationSignJButton;
	private JButton equalSignJButton;
	private JButton number0JButton;
	private JButton dotSignJButton;
	private Boolean dotSignUsedBoolean = false;
	Boolean startNewNumberBoolean = true;
	double number1Double = 0;
	double number2Double = 0;
	double previousAnswerDouble = 0;
	String pressedSignString = "";
	private JPanel toDoItemsListJPanel;
	private ArrayList<ToDoItemJPanel> toDoItemsArrayList;
	private JSONArray dataJsonArray;
	private JTextArea outputTextArea;
	private JButton btnLoad;
	private JSONObject dataJsonObject;
	private JLabel appVersionLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		
		String citizenshipCountryString = Countries.Canada.toString() ;
		
		Countries[] countries = Countries.values();
		String countryString = countries[2].toString();
		
//		for (int i = 0; i < countries.length; i++) {
//			System.out.println(countries[2]);
//		}
		
		
		Random random = new Random();
		
		
		System.out.println(countries[random.nextInt(0, 3)].toString());
		
//		System.out.println(citizenshipCountryString);
		
		
		
		
		dataJsonObject = getTickerPrices();
		
		if(dataJsonObject.isNull("result") == false) {
			try {
				outputTextArea.setText(dataJsonObject.get("result").toString());

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		String dataString =	 "[5345,353453,'5dgdf',{'Age':15, 'Name':'Jessica'},345345]";

//		try {
//			dataJsonArray = new JSONArray(dataString);
//			System.out.println(dataJsonArray);
//			
//			for (int i = 0; i < dataJsonArray.length(); i++) {
//				System.out.println(dataJsonArray.get(i));
//				
//				if(i == 3) {
//					JSONObject personJsonObject = dataJsonArray.getJSONObject(i);
//					System.out.println("Person's name is " + personJsonObject.getString("Name") + ". And age is " + personJsonObject.getInt("Age")+ ".");
//					
//				}
//				
//			}
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		
//		if(toDoItemsListJPanel != null) {
//			for (int i = 0; i < toDoItemsListJPanel.getComponentCount(); i++) {
//				ToDoItemJPanel itemToDoItemJPanel = (ToDoItemJPanel) toDoItemsListJPanel.getComponent(i);
//				itemToDoItemJPanel.setToDoItemJTextField("To do item " + i);
//			}
//		}
		
		if(toDoItemsArrayList != null) {
			for (int i = 0; i < toDoItemsArrayList.size(); i++) {			
				toDoItemsArrayList.get(i).setToDoItemJTextField("To do item " + i);
				toDoItemsListJPanel.add(toDoItemsArrayList.get(i));
			}
		}
		
		
		
	}
	
	public JSONObject getTickerPrices() {		
		JSONObject returnedDataJsonObject = new JSONObject();
		String url = "https://api.kraken.com/0/public/Ticker?";
		 Long stamp = System.currentTimeMillis();
		 HttpHeaders headers = new HttpHeaders();
		 String body = null;
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
		 try {
		     JSONObject json = new JSONObject();
		     json.put("path", builder.getPath());
		     json.put("query", builder.getQuery());		     
		     json.put("content-length", body == null ? -1 : body.length());
		     
		     String returnedDataArray = new RestTemplate()
			         .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>("{}", headers), String.class)
			         .getBody(); 
		     returnedDataJsonObject = new JSONObject(returnedDataArray);
		     return returnedDataJsonObject;
//		     System.out.println(returnedDataJsonObject.getJSONObject("result").getJSONObject("XRPETH").getJSONArray("b"));
//		     System.out.println(returnedDataJsonObject);

		 } catch (Exception e) {
		     System.out.println(e);
		 }	
		 return returnedDataJsonObject;
		 
	}
	
	public void insertNumber (String numberString) {		
		try {			
			String currentOutputTextString = outputTextField.getText();	
			
			if (startNewNumberBoolean == true) {
				currentOutputTextString = "0";
			}
				
				
				if(currentOutputTextString.equals("0") || currentOutputTextString.equals("")) {	
					if(numberString.equals(".") == false) {
						currentOutputTextString = numberString;
					} else {
						currentOutputTextString = "0.";
					}		
				} else {
					currentOutputTextString = currentOutputTextString + numberString;
				}		
				
				if(currentOutputTextString.equals("0") == false) {
					startNewNumberBoolean = false;
				}
				
				outputTextField.setText(currentOutputTextString);
				
		} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
		}
				
		
	}
	
	public void signPressed(String signString) {		
	
		if (signString.equals("+") || signString.equals("-") || signString.equals("*") || signString.equals("/")) {
			pressedSignString = signString;
		}
		
		if (signString.equals(".") == false) {
			startNewNumberBoolean = true;
			dotSignUsedBoolean = false;
		}
		
		if (signString.equals(".") && dotSignUsedBoolean == false || outputTextField.getText().equals("")) {
			insertNumber(".");
			dotSignUsedBoolean = true;
		} 	
		
		if(signString.equals("+")) {
			number1Double = Double.parseDouble(outputTextField.getText());
			pressedSignString = "+";
		} else if(signString.equals("-")) {
			number1Double = Double.parseDouble(outputTextField.getText());
			pressedSignString = "-";
		} else if(signString.equals("*")) {
			number1Double = Double.parseDouble(outputTextField.getText());
			pressedSignString = "*";
		} else if(signString.equals("/")) {
			number1Double = Double.parseDouble(outputTextField.getText());
			pressedSignString = "/";
		}
		
		
		
		if(signString.equals("=")) {
			number2Double = Double.parseDouble(outputTextField.getText());
			Double answerDouble = 0.0;
			if(pressedSignString.equals("") == false) {
				
				if(pressedSignString.equals("+")){
					answerDouble = number1Double + number2Double;
				} else if (pressedSignString.equals("-")){
					answerDouble = number1Double - number2Double;
				} else if (pressedSignString.equals("*")){
					answerDouble = number1Double * number2Double;
				} else if (pressedSignString.equals("/")){
					answerDouble = number1Double / number2Double;
				}				
			}
			
			System.out.println(number1Double + " " + pressedSignString + " " + number2Double + " = " + answerDouble);
			
			outputTextField.setText(answerDouble + "");
			
			number1Double = answerDouble;
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1095, 810);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		outputTextField = new JTextField();
		outputTextField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		outputTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		outputTextField.setText("0");
		outputTextField.setBounds(10, 10, 407, 63);
		frame.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 83, 407, 321);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		number7JButton = new JButton("7");
		number7JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("7");
			}
		});
		number7JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number7JButton);
		
		number8JButton = new JButton("8");
		number8JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("8");
			}
		});
		number8JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number8JButton);
		
		number9JButton = new JButton("9");
		number9JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("9");
			}
		});
		number9JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number9JButton);
		
		number4JButton = new JButton("4");
		number4JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("4");
			}
		});
		
		number4JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number4JButton);
		
		number5JButton = new JButton("5");
		number5JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("5");
			}
		});
		number5JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number5JButton);
		
		number6JButton = new JButton("6");
		number6JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("6");
			}
		});
		number6JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number6JButton);
		
		number1JButton = new JButton("1");
		number1JButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertNumber("1");
			}
		});
		number1JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number1JButton);
		
		number2JButton = new JButton("2");
		number2JButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertNumber("2");
			}
		});
		number2JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number2JButton);
		
		number3JButton = new JButton("3");
		number3JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("3");
			}
		});
		number3JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(number3JButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(427, 87, 69, 317);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));
		
		subtractionSignJButton = new JButton("-");
		subtractionSignJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				signPressed("-");
			}
		});
		subtractionSignJButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_1.add(subtractionSignJButton);
		
		additionSignJButton = new JButton("+");
		additionSignJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				signPressed("+");
			}
		});
		additionSignJButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_1.add(additionSignJButton);
		
		divisionSignJButton = new JButton("/");
		divisionSignJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				signPressed("/");
			}
		});
		divisionSignJButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_1.add(divisionSignJButton);
		
		multiplicationSignJButton = new JButton("*");
		multiplicationSignJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				signPressed("*");
			}
		});
		multiplicationSignJButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_1.add(multiplicationSignJButton);
		
		equalSignJButton = new JButton("=");
		equalSignJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				signPressed("=");
			}
		});
		equalSignJButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_1.add(equalSignJButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 414, 407, 58);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		number0JButton = new JButton("0");
		number0JButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNumber("0");
			}
		});
		number0JButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_2.add(number0JButton);
		
		dotSignJButton = new JButton(".");
		dotSignJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				signPressed(".");
			}
		});
		dotSignJButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_2.add(dotSignJButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(514, 10, 456, 394);
		frame.getContentPane().add(scrollPane);
		
		toDoItemsListJPanel = new JPanel();
		scrollPane.setViewportView(toDoItemsListJPanel);
		toDoItemsListJPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		toDoItemsListJPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 493, 1061, 270);
		frame.getContentPane().add(scrollPane_1);
		
		outputTextArea = new JTextArea();
		scrollPane_1.setViewportView(outputTextArea);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("Save/");
					if (file.exists() == false) {
						file.mkdirs();
					}
				} catch (Exception e1) {
					// TODO: handle exception.
					System.out.println("Couldn't create folder 'DataBackups'");
				}
				
				
				try {
					BufferedWriter dataWriter = new BufferedWriter(new FileWriter("Save/textOutput.txt"));
					dataWriter.write(dataJsonObject.toString());
					dataWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
			}
		});
		btnNewButton.setBounds(481, 438, 127, 34);
		frame.getContentPane().add(btnNewButton);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					BufferedReader textReader = new BufferedReader(new FileReader("Save/textOutput.txt"));
				

				if (textReader.ready()) {
					String loadedTextString = textReader.lines().collect(Collectors.joining(System.lineSeparator()));
					dataJsonObject = new JSONObject(loadedTextString);
					outputTextArea.setText(dataJsonObject.toString());
					textReader.close();
				}
				
				}catch (IOException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(627, 438, 127, 34);
		frame.getContentPane().add(btnLoad);
		
		appVersionLabel = new JLabel(appVersionString);
		appVersionLabel.setBounds(1002, 10, 69, 40);
		frame.getContentPane().add(appVersionLabel);
		
		toDoItemsArrayList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {			
			toDoItemsArrayList.add(new ToDoItemJPanel());
		}
		
		
	}
}

class CountTask extends TimerTask {
	int counterLoopTask;
	JLabel displayLabelToShowJLabel;
	
	public CountTask(int count, JLabel displayLabel) {
		counterLoopTask = count;
		displayLabelToShowJLabel = displayLabel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		counterLoopTask++;
		displayLabelToShowJLabel.setText(String.valueOf(counterLoopTask));
	}
	
}
