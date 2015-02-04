

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Generator {

	public static final int PORT = 1717;
	private static final String pass = "4567";

	public static void startServer() {

		try {
			ServerSocket server = new ServerSocket(PORT);

			while (true) {
				System.out.println("waiting to connect....");
				Socket client = server.accept();
				SocketReadWrite srw = new SocketReadWrite(
						client.getInputStream(), client.getOutputStream());
				Scanner scan = new Scanner(System.in);

				while (true) {
					System.out.println("Enter your pasword: ");
					String msg = scan.nextLine();

					if (msg.equals(pass)) {
						System.out.println("Password is correct!");
						String quote = readQuotes();
						srw.sendMessage(quote);
						System.out.println(quote);
						writeToFile(quote);
					} else {
						System.out.println("Pasword is incorrect!");
						break;
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String readQuotes() {
		String quote = "";
		int random = (int) (1 + Math.random() * 15);

		FileReader fr;
		try {
//			fr = new FileReader("/Users/amrapoprzanovic/Desktop/quotes.txt");
			fr = new FileReader("./TXT/quotes.txt");

			BufferedReader br = new BufferedReader(fr);

			for (int i = 0; i < random; i++) {
				br.readLine();
			}
			quote = br.readLine();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quote;
	}

	private static void writeToFile(String stringQuote) {

		File authLog = new File( "./TXT/auth_log.txt");
//		FileOutputStream fos;
		try {
//		    fos = new FileOutputStream (authLog);
			FileWriter fr = new FileWriter(authLog, true);
			StringBuilder sb = new StringBuilder();
			
			SimpleDateFormat sdf =  new SimpleDateFormat("[ yyyy-MM-dd  H:mm:ss ]");
		    Date date = new Date();
		    String enterDate = sdf.format(date);
		    
		   
		    
//		    fos.write(enterDate.getBytes());
//			fos.write(stringQuote.getBytes());
			
		    fr.write(enterDate.toString() );		
		    fr.write(stringQuote + "\n");
			fr.flush();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		startServer();

	}
}
