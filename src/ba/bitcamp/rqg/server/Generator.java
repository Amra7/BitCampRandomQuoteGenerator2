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

public class Generator {

	public static final int PORT = 1717;
	private static final String pass = "4567";

	public static void startServer() {

		try {
			ServerSocket server = new ServerSocket(PORT);

			while (true) {
				System.out.println("waiting to connect....");
				Socket client = server.accept();

				InputStream is = client.getInputStream();
				Reader r = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(r);
				Scanner scan = new Scanner(br);

				OutputStream os = client.getOutputStream();
				Writer w = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter pw = new PrintWriter(bw);

				while (true) {
					System.out.println("Enter your pasword: ");
					String msg = scan.nextLine();
					System.out.println("ovdje stane!");
					if (scan.nextLine().equals(pass)) {
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

	private static void writeToFile(String stringQuote) {

		File authLog = new File("./TXT/auth_log.txt");
		// FileOutputStream fos;
		try {
			// fos = new FileOutputStream (authLog);
			FileWriter fr = new FileWriter(authLog, true);
			StringBuilder sb = new StringBuilder();

			SimpleDateFormat sdf = new SimpleDateFormat(
					"[ yyyy-MM-dd  H:mm:ss ]");
			Date date = new Date();
			String enterDate = sdf.format(date);

			// fos.write(enterDate.getBytes());
			// fos.write(stringQuote.getBytes());

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

	public static void main(String[] args) {

		startServer();

	}
}
