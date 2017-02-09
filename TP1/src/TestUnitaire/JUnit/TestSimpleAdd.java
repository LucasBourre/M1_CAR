package JUnit;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import junit.framework.TestCase;

public class TestSimpleAdd extends TestCase {
	private String Operand1 = "3";
	private String Operand2 = "5";
	
    public TestSimpleAdd( String testName ){
        super( testName );
    }

	public void testSimpleAdd() throws Exception{
		//on se connecte
		Socket as = new Socket(InetAddress.getLocalHost(),6747);
		//DataOutput to send the calcul
		DataOutputStream out = new DataOutputStream(as.getOutputStream());
		//BufferedReader to get the answer
		BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
		//we send " + 3 5 "
		out.writeBytes("+" + " " + Operand1 + " " + Operand2);
		//We expect to get 8 in answer
		String reponse = in.readLine();
		assertEquals("8",reponse);
		as.close();
	}


}
