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
		System.out.println("Connected to client " + client.getRemoteSocketAddress());
		
		try {
			System.out.println("Trying to receive");
			line = input.readLine();
			System.out.println("Received: " + line);
			output.println(line);
			System.out.println("Sent: " + line);
		} catch (IOException e) {
			System.out.println("Read failed");
	        System.exit(-1);
		}
		
		System.out.println();
	}

}
