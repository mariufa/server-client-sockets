import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;


public class Server {
	
	private ServerSocket server;
	
	public void listenSocket() {
		try {
			server = new ServerSocket(4444);
		} catch (IOException e) {
			System.out.println("Could not listen on port 4444");
		    System.exit(-1);
		}
		
		while(true) {
			ClientWorker client;
			try {
				client = new ClientWorker(server.accept());
				Thread thread = new Thread(client);
				thread.start();
			} catch (Exception e) {
				System.out.println("Accept failed: 4444");
			    System.exit(-1);
			}
			
		}
	}
	
	protected void finalize() {
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("Could not close socket");
	        System.exit(-1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Server starting");
		Server server = new Server();
		server.listenSocket();
		System.out.println("Server stopped");
	}
}
