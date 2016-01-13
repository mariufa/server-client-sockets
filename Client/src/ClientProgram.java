package Client.src;

public class ClientProgram {
	
	private Client client;
	
	public void init() {
		client = new Client();
		client.connectSocket();
	}
	
	public void run() {
		client.setMessage("Dette er marius");
		client.sendAndReceive();
		client.closeSocket();
		System.out.println("client done");
	}
	
	public static void main(String[] args) {
		ClientProgram program = new ClientProgram();
		program.init();
		program.run();
	}

}
