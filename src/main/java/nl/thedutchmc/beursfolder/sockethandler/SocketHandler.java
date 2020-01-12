package nl.thedutchmc.beursfolder.sockethandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import nl.thedutchmc.beursfolder.BeursFolder;

public class SocketHandler {
	public Socket socket;
	
	public static Socket staticSocket;
	public static DataOutputStream dos;
	
	public synchronized void setSocket(Socket socket) {
		this.socket = staticSocket;
	}
	
	public synchronized Socket getSocket() {
		return staticSocket;
	}
			
	String address = BeursFolder.SERVER_ADDRESS;
	int port = BeursFolder.SERVER_PORT;
	
	//Try to connect to the server
	public void connect(boolean fromSend, String toSend) throws Exception {
		try {
			socket = new Socket(address, port);
			dos = new DataOutputStream(socket.getOutputStream());
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						if(!socket.isConnected()) {
							try {
								connect(false, "");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}).start();
 			System.out.println("Connected to server.");
						
			staticSocket = socket;
			
			//If this is a reconnect initiated from the send method, try sending the message now.
			if(fromSend) {
				send(toSend);
			}
									
		} catch (Exception e) {
			
			//Were unable to connect to the server
			System.err.println("Unable to connect to " + address + " on port " + port + ", trying again in 30 seconds!");
			
			//Wait 30 seconds before trying to reconnect
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e1) {
				e.printStackTrace();
			}
			
			connect(false, ""); //try to reconnect
		}
	}
	
	public void send(String toSend) {
		Socket soc = staticSocket;
		
		//create a new Thread so that the UI can continue to work
		System.out.println("Trying to send...");
		if(soc.isConnected()) {
			try {
				dos.writeUTF(toSend);
				System.out.println("Message sent!");
				
				dos.flush();

			} catch (IOException e) {
				System.err.println("Unable to send to server!");
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Not connected to server, attempting to reconnect");
				connect(true, toSend);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
