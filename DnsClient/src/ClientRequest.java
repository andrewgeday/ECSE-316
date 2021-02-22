import java.nio.ByteBuffer;
import java.util.Random;
import java.io.*;
import java.net.*;

public class ClientRequest {
	
	private String domain;
	private QueryType query;
	
	public ClientRequest (String domain, QueryType query) {
		this.domain = domain;
		this.query = query;
	}

}
