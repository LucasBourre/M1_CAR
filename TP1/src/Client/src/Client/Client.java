package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
@SuppressWarnings("resource")
public Client(){
	Socket as = null;
	try {
		//establishment of the Connection
		as = new Socket(InetAddress.getLocalHost(),6748);
		System.out.println("Client : Connexion etablie !");
		//DataOutput to send the calcul
		DataOutputStream out = new DataOutputStream(as.getOutputStream());
		//BufferedReader to get the answer
		BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
		//Scanner : write the calcul
		Scanner sc = new Scanner(System.in);
		while (true){
			System.out.println("Tapez votre calcul, ou 'quit' pour quitter :");
			String Calcul =sc.nextLine() + "\n";
			// if quit , we close the client
			if (Calcul.equals("quit\n")){
				as.close();
				System.out.println("Vous avez quitté !");
				System.exit(0);
			}
		//Send the Calcul
		out.writeBytes(Calcul);
		//Read the answer , and print it
		String reponse = in.readLine();
		System.out.println(Calcul + " = " + reponse);
		}
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

public static void main(String[] args) {
	@SuppressWarnings("unused")
	Client c= new Client();
	System.out.println("client");

}

}