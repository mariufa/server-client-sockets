import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientWorker implements Runnable {
	
	private Socket client;
	
	public ClientWorker(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		String line;
		BufferedReader input = null;
		PrintWriter output = null;
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("in or out failed");
		    System.exit(-1);
		}
		
		while(true) {
			try {
				line = input.readLine();
				output.println(line);
			} catch (Exception e) {
				System.out.println("Read failed");
		        System.exit(-1);
			}
		}
	}

}
