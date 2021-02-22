
public class App {
	public static void main(String args[]) throws Exception {
		try {
			DnsClient newClient = new DnsClient(args);
			newClient.sendRequest();
		} catch(Exception e) {
			System.out.println(e.getError());
		}
		
	}

}
