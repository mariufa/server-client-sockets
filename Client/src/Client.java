package Client.src;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public void listenSocket() {
		try {
			socket = new Socket("localhost", 4444);
			output = new PrintWriter(socket.getOutputStream());
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: Marius");
		    System.exit(1);
		} catch (IOException e) {
			System.out.println("No I/O");
		    System.exit(1);
		}
		System.out.println("Connecting to " + socket.getRemoteSocketAddress());
	}
	
	public void sendAndReceive() {
		System.out.println("Sending");
		output.println("Dette er marius");
		output.flush();
		System.out.println("Sending complete");
		try {
			System.out.println("Receiving");
			String line = input.readLine();
			System.out.println("Receiving complete");
			System.out.println("Text received: " + line);
		} catch (IOException e) {
			System.out.println("Client Read failed");
		    System.exit(1);
		}
		
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Socket close fail");
		    System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.listenSocket();
		client.sendAndReceive();
		System.out.println("client done");
	}
}
