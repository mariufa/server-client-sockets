package Client.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Device {
	
	private String deviceName;
	private static final String DEVICE_FILE = "devicename.txt";
	
	public Device() {
		deviceName = "";
	}
	
	public void loadDeviceName() throws FileNotFoundException {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(DEVICE_FILE));
			deviceName = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDeviceName() {
		return deviceName;
	}
}