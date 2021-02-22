import java.util.Arrays;
import java.net.*;
import java.util.List;
import java.util.ListIterator;

public class DnsClient {
	public QueryType query = QueryType.A;
	private int timeout = 5000;
	private int max_retries = 3;
	private int port = 53;
	private int maxRetries = 3;
	private byte[] server = new byte[4];
	private String name;
	String address;
	public int max_packetSize = 512;
	
	public DnsClient (String args[]) {
		try {
			this.parseInput(args);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error\tIncorrect Input Syntax: Check Arguments\n");
		}
		if (name == null || server == null) {
			throw new IllegalArgumentException("Error\tIncorrect Input Syntax: Domain Name and Server Required\n");
		}
	}
	
	private void parseInput(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public void sendRequest () {
		System.out.println("DnsClient sending request for " + name);
        System.out.println("Server: " + address);
        System.out.println("Request type: " + query);
        sendRequest(1);
	}
	
	public void sendRequest (int retry) {
		if (retry > maxRetries) {
			System.out.println("ERROR\tMaximum number of retries " + maxRetries+ " exceeded");
            return;
		}
		try {
			DatagramSocket clientSocket= new DatagramSocket();
			clientSocket.setSoTimeout(timeout);
			InetAddress IP = InetAddress.getByAddress(server);
			ClientRequest request = new ClientRequest(name, query);
			
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			long start = System.currentTimeMillis();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
			clientSocket.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			long end = System.currentTimeMillis();
			
			System.out.println("Response Received After " + (end - start) / 1000 + " seconds " + 
			"(" + (retry - 1) + " retries)");
			
			//output response
		}
		catch (SocketException e) {
			System.out.println("Error\tCould Not Create Socket");
		}
		catch (SocketTimeoutException e) {
			System.out.println("Error\tSocket Timeout");
			System.out.println("Resending Request");
			sendRequest(++retry);
		}
		catch (UnknownHostException e) {
			System.out.println("Error\tUnknown Host");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
