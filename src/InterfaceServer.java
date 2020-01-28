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
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InterfaceServer {
	
	private static String clientSentence;
	private static String clientSentence2;

	private static BufferedReader inFromClient;
	private static BufferedReader inFromClient2;

	private static DataOutputStream outToClient;
	private static DataOutputStream outToClient2;

	JFrame frame;
	JTextField textField;
	JLabel clientResult;
	JLabel res,lblFunctionResultIn,res2,lblMajorityVoiting,major,status,sColor,clientResult2,serRes,serResult,sColor1,sColor2;
	
	 int targetNumber = 0;

	 static int func = 0,func2,result,result2;
	 private static int server_res = 0;
	 static int popular;
	 private JLabel lblServer;
	 private JLabel lblMachineIsOff;
	/**
	 * Launch the application.
	 */
	public static void RunIt() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceServer window = new InterfaceServer();
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
	public InterfaceServer() {
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
			
					ServerSocket welcomeSocket, welcomeSocket2;

					try {
						welcomeSocket = new ServerSocket(6000);
						welcomeSocket2 = new ServerSocket(6001);
						
						server_res = targetNumber * targetNumber;
						System.out.println("FUNCTION RESULT OF SERVER( f(x)=x*x )-->  " + server_res);
						System.out.println("---------------------------------- ");
						while (true) {
							Socket connectionSocket = welcomeSocket.accept();
							Socket connectionSocket2 = welcomeSocket2.accept();

							inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
							inFromClient2 = new BufferedReader(new InputStreamReader(connectionSocket2.getInputStream()));

							outToClient = new DataOutputStream(connectionSocket.getOutputStream());
							outToClient2 = new DataOutputStream(connectionSocket2.getOutputStream());

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
								
							}
							else
								sColor.setBackground(Color.GREEN);
							
							
							//-------------------------------------------
							if (result!=popular) {
								
								sColor1.setBackground(Color.RED);
							}
							else
							sColor1.setBackground(Color.GREEN);
							//---------------------------------------------------
							if (result2!=popular) {
								
								sColor2.setBackground(Color.RED);
							}
							else
							sColor2.setBackground(Color.GREEN);
							
							if(result!=popular ){
							connectionSocket.close();
							
							break;}
							else if(result2!=popular)
							{
								connectionSocket2.close();
								
								break;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				res.setText(Integer.toString(result));
				res2.setText(Integer.toString(result2));
				serRes.setText(Integer.toString(server_res));
				
				major.setText(Integer.toString(popular));
				 
			}
		    });
		
		clientResult = new JLabel("Function Result of Client 1:");
		clientResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		clientResult.setBounds(48, 150, 238, 37);
		frame.getContentPane().add(clientResult);
		
		res = new JLabel("");
		res.setFont(new Font("Tahoma", Font.BOLD, 16));
		res.setBounds(297, 150, 176, 37);
		frame.getContentPane().add(res);
		
		clientResult2 = new JLabel("Function Result of Client 2:");
		clientResult2.setFont(new Font("Tahoma", Font.BOLD, 16));
		clientResult2.setBounds(48, 200, 226, 37);
		frame.getContentPane().add(clientResult2);
		
		res2 = new JLabel("");
		res2.setFont(new Font("Tahoma", Font.BOLD, 16));
		res2.setBounds(297, 200, 176, 37);
		frame.getContentPane().add(res2);
		
		lblMajorityVoiting = new JLabel("Majority Voting:");
		lblMajorityVoiting.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMajorityVoiting.setBounds(48, 300, 193, 37);
		frame.getContentPane().add(lblMajorityVoiting);
		
		major = new JLabel("");
		major.setFont(new Font("Tahoma", Font.BOLD, 16));
		major.setBounds(297, 300, 176, 37);
		frame.getContentPane().add(major);
		
		status = new JLabel("Server:");
		status.setFont(new Font("Tahoma", Font.BOLD, 16));
		status.setBounds(400, 380, 158, 40);
		frame.getContentPane().add(status);
		
		sColor = new JLabel("");
		sColor.setForeground(Color.BLACK);
		sColor.setBounds(400, 433, 73, 65);
		sColor.setBackground(Color.BLUE);
		sColor.setOpaque(true);
		frame.getContentPane().add(sColor);
		
		serResult = new JLabel("Function Result of Server:");
		serResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		serResult.setBounds(48, 250, 226, 37);
		frame.getContentPane().add(serResult);
		
		serRes = new JLabel("");
		serRes.setFont(new Font("Tahoma", Font.BOLD, 16));
		serRes.setBounds(297, 250, 176, 37);
		frame.getContentPane().add(serRes);
		
		lblServer = new JLabel("SERVER");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblServer.setBounds(232, 13, 122, 31);
		frame.getContentPane().add(lblServer);
		
		sColor1 = new JLabel("");
		sColor1.setOpaque(true);
		sColor1.setForeground(Color.BLACK);
		sColor1.setBackground(Color.BLUE);
		sColor1.setBounds(92, 433, 73, 65);
		frame.getContentPane().add(sColor1);
		
		sColor2 = new JLabel("");
		sColor2.setOpaque(true);
		sColor2.setForeground(Color.BLACK);
		sColor2.setBackground(Color.BLUE);
		sColor2.setBounds(246, 433, 73, 65);
		frame.getContentPane().add(sColor2);
		
		JLabel lblClient = new JLabel("Client 1:");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClient.setBounds(92, 380, 158, 40);
		frame.getContentPane().add(lblClient);
		
		JLabel lblClient_1 = new JLabel("Client 2:");
		lblClient_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClient_1.setBounds(246, 380, 158, 40);
		frame.getContentPane().add(lblClient_1);
		
		lblMachineIsOff = new JLabel("Machine is OFF");
		lblMachineIsOff.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMachineIsOff.setBounds(217, 510, 138, 30);
		lblMachineIsOff.setVisible(false);
		frame.getContentPane().add(lblMachineIsOff);
	}
	public static boolean calc() {
		try {
			clientSentence = inFromClient.readLine();
			clientSentence2 = inFromClient2.readLine();

			func = Integer.parseInt(clientSentence);
			System.out.println("Received From Client 1: " + clientSentence);
			result = func * func;
			System.out.println("The Function of Client1( f(x)=x*x ) = " + result);
			System.out.println("---------------------------------- ");
			func2 = Integer.parseInt(clientSentence2);
			System.out.println("Received From Client 2: " + clientSentence2);
			result2 = func2 * func2;
			System.out.println("The Function of Client2( f(x)=x*x ) = " + result2);
			System.out.println("---------------------------------- ");

			int arr[] = new int[3];

			arr[0] = server_res;
			arr[1] = result;
			arr[2] = result2;


			String last_Sentence = Integer.toString(server_res) + " " + Integer.toString(result) + " "
					+ Integer.toString(result2) + '\n';
			System.out.println(last_Sentence);

			outToClient.writeBytes(last_Sentence);
			outToClient2.writeBytes(last_Sentence);

			int count = 1, tempCount;
			popular = arr[0];
			int temp = 0;
			for (int i = 0; i < (arr.length - 1); i++) {
				temp = arr[i];
				tempCount = 0;
				for (int j = 1; j < arr.length; j++) {
					if (temp == arr[j])
						tempCount++;
				}
				if (tempCount > count) {
					popular = temp;
					count = tempCount;
				}
			}
			System.out.println("Majority Voting--> " + popular);

			if (server_res != popular) {
				System.out.println("There is an error at the value! ");
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
