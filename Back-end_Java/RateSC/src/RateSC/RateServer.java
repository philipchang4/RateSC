package RateSC;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RateServer {
	public RateServer(int port) {
		try {
			System.out.println("Binding to port " + port);
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Succesfully bound to port " + port);
			System.out.println("Listening on Port " + port + "\nWaiting for connections");
			while(true) {
				Socket s = ss.accept(); // blocking line of code
				System.out.println("Connection from: " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this); 
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		}
	}
}
