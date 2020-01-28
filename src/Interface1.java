import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Interface1 {
	

	private static String sentence, modifiedSentence;
	private static DataOutputStream outToServer;
	private static BufferedReader inFromServer;

	JFrame frame;
	JTextField textField;
	JLabel clientResult;
	JLabel res,lblFunctionResultIn,resS,res2,lblMajorityVoiting,major,status,sColor,sColor2,sColorS,lblMachineIsOff;
	
	static int popular;
	int targetNumber = 0;
	static int func = 0,func2=0,funcS=0;
	/**
	 * Launch the application.
	 */
	public static void RunIt() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface1 window = new Interface1();
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
	public Interface1() {
		initialize();
		
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterANumber = new JLabel("Enter a number:");
		lblEnterANumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnterANumber.setBounds(48, 63, 138, 40);
		frame.getContentPane().add(lblEnterANumber);
		
		textField = new JTextField();
		textField.setBounds(217, 73, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.setFont(new Font("Tahoma", Font.BOLD, 16));
		Submit.setBounds(376, 69, 116, 31);
		frame.getContentPane().add(Submit);
		
		 Submit.addActionListener(new ActionListener()
		    {
		     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				
				 targetNumber =Integer.parseInt(textField.getText()); //text in int hali
				 func = targetNumber * targetNumber;
				
				 
				 System.out.println("FUNCTION RESULT IN CLIENT1( f(x)=x*x )-->  " + func);
				 sentence = textField.getText();
				 
				 
					try {
						Socket clientSocket = new Socket("localhost", 6000);

						outToServer = new DataOutputStream(clientSocket.getOutputStream());
						inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						// sentence = inFromUser.readLine();

						int errorCounter = 0;
						for (int i = 0; i < 3; i++) {
							if (calc() == false){
								errorCounter++;
								
							}
						}

						if (errorCounter == 3) {
							System.out.println("Machine is faulty!");
							sColor.setBackground(Color.RED);
							lblMachineIsOff.setVisible(true);
							clientSocket.close();
						}
						else
						sColor.setBackground(Color.GREEN);
						
					
				//----------------------------------------------------
						if (func2!=popular) {
							
							sColor2.setBackground(Color.RED);
						}
						else
						sColor2.setBackground(Color.GREEN);
				//---------------------------------------------------
						if (funcS!=popular) {
						
							sColorS.setBackground(Color.RED);
						}
						else
						sColorS.setBackground(Color.GREEN);
					
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				res.setText(Integer.toString(func));
				resS.setText(Integer.toString(funcS));
				res2.setText(Integer.toString(func2));
				
				major.setText(Integer.toString(popular));
				 
			}
		    });
		
		clientResult = new JLabel("Function Result in Client 1:");
		clientResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		clientResult.setBounds(48, 150, 238, 37);
		frame.getContentPane().add(clientResult);
		
		res = new JLabel("");
		res.setFont(new Font("Tahoma", Font.BOLD, 16));
		res.setBounds(297, 150, 176, 37);
		frame.getContentPane().add(res);
		
		lblFunctionResultIn = new JLabel("Function Result of Server:");
		lblFunctionResultIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFunctionResultIn.setBounds(48, 250, 213, 37);
		frame.getContentPane().add(lblFunctionResultIn);
		
		resS = new JLabel("");
		resS.setFont(new Font("Tahoma", Font.BOLD, 16));
		resS.setBounds(297, 250, 176, 37);
		frame.getContentPane().add(resS);
		
		lblMajorityVoiting = new JLabel("Majority Voting:");
		lblMajorityVoiting.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMajorityVoiting.setBounds(48, 300, 193, 37);
		frame.getContentPane().add(lblMajorityVoiting);
		
		major = new JLabel("");
		major.setFont(new Font("Tahoma", Font.BOLD, 16));
		major.setBounds(297, 300, 176, 37);
		frame.getContentPane().add(major);
		
		status = new JLabel("Client 1:");
		status.setFont(new Font("Tahoma", Font.BOLD, 16));
		status.setBounds(92, 380, 92, 40);
		frame.getContentPane().add(status);
		
		sColor = new JLabel("");
		sColor.setForeground(Color.BLACK);
		sColor.setBounds(92, 433, 73, 65);
		sColor.setBackground(Color.BLUE);
		sColor.setOpaque(true);
		frame.getContentPane().add(sColor);
		
		JLabel lblClient = new JLabel("Client 2:");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClient.setBounds(246, 380, 92, 40);
		frame.getContentPane().add(lblClient);
		
		sColor2 = new JLabel("");
		sColor2.setOpaque(true);
		sColor2.setForeground(Color.BLACK);
		sColor2.setBackground(Color.BLUE);
		sColor2.setBounds(246, 433, 73, 65);
		frame.getContentPane().add(sColor2);
		
		sColorS = new JLabel("");
		sColorS.setOpaque(true);
		sColorS.setForeground(Color.BLACK);
		sColorS.setBackground(Color.BLUE);
		sColorS.setBounds(400, 433, 73, 65);
		frame.getContentPane().add(sColorS);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblServer.setBounds(400, 380, 92, 40);
		frame.getContentPane().add(lblServer);
		
		JLabel lblFunctionResultOf = new JLabel("Function Result of Client 2:");
		lblFunctionResultOf.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFunctionResultOf.setBounds(48, 200, 238, 37);
		frame.getContentPane().add(lblFunctionResultOf);
		
		res2 = new JLabel("");
		res2.setFont(new Font("Tahoma", Font.BOLD, 16));
		res2.setBounds(297, 200, 176, 37);
		frame.getContentPane().add(res2);
		
		JLabel lblClent = new JLabel("CLIENT 1");
		lblClent.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClent.setBounds(232, 13, 122, 31);
		frame.getContentPane().add(lblClent);
		
		lblMachineIsOff = new JLabel("Machine is OFF");
		lblMachineIsOff.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMachineIsOff.setBounds(217, 510, 158, 30);
		lblMachineIsOff.setVisible(false);
		frame.getContentPane().add(lblMachineIsOff);
		
		
	}
	public static boolean calc() {
		try {
			outToServer.writeBytes(sentence + '\n');

			modifiedSentence = inFromServer.readLine();
			System.out.println("FUNCTION RESULT IN SERVER--> " + modifiedSentence);

			String[] arr = modifiedSentence.split(" ");// string hali
			int arr2[] = new int[3];
			// System.out.println(Arrays.toString(arr));

			for (int i = 0; i < 3; i++) {
				arr2[i] = Integer.parseInt(arr[i]); // int hali
				func2=arr2[2];
				funcS=arr2[0];
			
			}

			System.out.println(Arrays.toString(arr2));

			int count = 1, tempCount;
			popular = arr2[0];
			int temp = 0;
			for (int i = 0; i < (arr2.length - 1); i++) {
				temp = arr2[i];
				tempCount = 0;
				for (int j = 1; j < arr2.length; j++) {
					if (temp == arr2[j])
						tempCount++;
				}
				if (tempCount > count) {
					popular = temp;
					count = tempCount;
				}
			}
			System.out.println("Majority Voting--> " + popular);

			if (func != popular) {
				
				System.out.println("There is an error at the client 1! ");
				return false;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
