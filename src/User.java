

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class User {

	public static final String serverAddress = "127.0.0.1";
	public static final int PORT = 1717;

	public static void connectToServer() {
		try {
			Socket client = new Socket(serverAddress, PORT);
			SocketReadWrite srw = new SocketReadWrite(client.getInputStream(),
					client.getOutputStream());
			Scanner scan = new Scanner(System.in);

			while (true) {
//				String msgServer = srw.getMessage();
	//			System.out.println("Server: " + msgServer);
				
				String msgClient = scan.nextLine();
				srw.sendMessage(msgClient);
				
				// kada se korisnik loguje dobije od Servera quote i onda je upisuje u recieved_quotes.txt file
			    String serverQuote = scan.nextLine();
			    writeToFile(serverQuote);
				client.close();
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public static void writeToFile(String stringQuote) {

		File recievedQuotes = new File( "./TXT/recieved_quotes.txt");
		try {
			FileWriter fr = new FileWriter(recievedQuotes, true);
			
			SimpleDateFormat sdf =  new SimpleDateFormat("[ yyyy-MM-dd ]");
		    Date date = new Date();
		    String enterDate = sdf.format(date);

			
		    fr.write(enterDate.toString() + " _ ");			
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

		connectToServer();
	}
}
