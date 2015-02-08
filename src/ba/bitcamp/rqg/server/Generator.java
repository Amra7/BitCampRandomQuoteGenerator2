package ba.bitcamp.rqg.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Server class creates server and connects to the clients.
 * 
 * @author amrapoprzanovic
 *
 */
public class Generator {

	public static final int PORT = 1717;
	private static final String pass = "4567";

	/**
	 * Method that starts server and connects to client.
	 */
	public static void startServer() {

		try {
			ServerSocket server = new ServerSocket(PORT);

			while (true) {
				System.out.println("waiting to connect....");
				Socket client = server.accept();

				InputStream is = client.getInputStream();
				Reader r = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(r);

				OutputStream os = client.getOutputStream();
				Writer w = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter pw = new PrintWriter(bw);

				String line = "";

				while ((line = br.readLine()) != null) {
					System.out.println(line);
					if (line.equals(pass)) {
						System.out.println("Password is correct!");
						String quote = readQuotes();
						pw.write(quote);
						pw.flush();
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

	/**
	 * Method that reads random line quote form file.
	 * 
	 * @return quote line from file written in String.
	 */
	private static String readQuotes() {
		String quote = "";
		int random = (int) (1 + Math.random() * 15);

		FileReader fr;
		try {
			// fr = new FileReader("/Users/amrapoprzanovic/Desktop/quotes.txt");
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

	/**
	 * Writes to new File (uth_log.txt) random quotes that are given from
	 * another File (quotes.txt). Takes one parameter given stringQuote
	 * 
	 * @param stringQuote - String quote that is given from readQote method.
	 */
	private static void writeToFile(String stringQuote) {

		File authLog = new File("./TXT/auth_log.txt");

		try {

			FileWriter fr = new FileWriter(authLog, true);

			SimpleDateFormat sdf = new SimpleDateFormat(
					"[ yyyy-MM-dd  H:mm:ss ]");
			Date date = new Date();
			String enterDate = sdf.format(date);

			fr.write(enterDate.toString());
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

	/**
	 * Main method that starts server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		startServer();

	} // end of main method

} // end of class
