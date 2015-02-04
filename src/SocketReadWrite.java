import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class that send and receives messages.
 * @author amrapoprzanovic
 *
 */
public class SocketReadWrite {

	private InputStream  in;
	private OutputStream out;
	
	/**
	 * Constructor for SocketReadWrite
	 * @param in - InputStream
	 * @param out - OutputStream
	 */
	public SocketReadWrite(InputStream in, OutputStream out) {
		super();
		this.in = in;
		this.out = out;
	}
	
	public String getMessage(){
		String str="";
		
		try {
			int msglength = in.read();
			byte[] buffer = new byte[msglength];
			int numByte =0;
			
			StringBuilder sb =  new StringBuilder();
			while( (numByte += in.read(buffer)) >=0){
				sb.append(new String(buffer).replaceAll("\\s", " "));
				
				if( numByte >= msglength){
					break;
				}

			}
			str = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public void sendMessage(String message){
		
		int msgLength;
		try {
		
			byte [] buffer  = message.getBytes();
			out.write(buffer.length);
			out.write(buffer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
}
