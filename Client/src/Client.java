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
	private Device device;
	private String message;
	private String messageReceived;
	
	public Client() {
		loadDeviceName();
	}
	
	private void loadDeviceName() {
		device = new Device();
		device.loadDeviceName();
	}
	
	public void connectSocket() {
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
		sendMessage();
		receiveMessage();
	}
	
	private void sendMessage() {
		String stringToBeSent = device.getDeviceName() + ";" + message;
		System.out.println("Sending: " + stringToBeSent);
		output.println(stringToBeSent);
		output.flush();
		System.out.println("Message sent");
	}
	
	private void receiveMessage() {
		try {
			System.out.println("Receiving");
			String line = input.readLine();
			System.out.println("Receiving complete");
			System.out.println("Received: " + line);
		} catch (IOException e) {
			System.out.println("Client Read failed");
		    System.exit(1);
		}
	}
	
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Socket close fail");
		    System.exit(1);
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
// TO BE DELETED
//	public static void main(String[] args) {
//		Client client = new Client();
//		client.connectSocket();
//		client.setMessage("Dette er marius");
//		client.sendAndReceive();
//		client.closeSocket();
//		System.out.println("client done");
//	}
}
