import java.net.*;
import java.nio.ByteBuffer;

public class ServerResponse {
	
	private byte[] response;
	private byte ID[];
	private boolean QR, AA, TC, RD, RA;
	private int RCode, QDCount, ANCount, NSCount, ARCount;
	private Records[] answerRecords;
    private Records[] additionalRecords;
    private QueryType query;
    private boolean noRecords = false;
    
    public ServerResponse (byte[] response, int requestSize, QueryType queryType) {
    	
    	this.response = response;
    	this.query = queryType;
    	
    }
    
    public static void main (String args[]) throws Exception {
    	DatagramSocket serverSocket = new DatagramSocket (9876);
    	
    	byte[] receiveData = new byte[1024];
    	byte[] sendData = new byte[1024];
    	
    	while (true) {
    		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    		serverSocket.receive(receivePacket);
    		
    		String sentence = new String (receivePacket.getData());
    		InetAddress IP = receivePacket.getAddress();
    		
    		int port = receivePacket.getPort();
    		
    		String capitalizedSentence = sentence.toUpperCase();
    		sendData = capitalizedSentence.getBytes();
    		
    		DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, IP, port);
    		
    		serverSocket.send(sendPacket);
    	}
    }
}
