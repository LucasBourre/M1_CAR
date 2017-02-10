package Client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	@SuppressWarnings("resource")

	public Client(File file){
	Socket as = null ;
	try {
		//establishment of the Connection
		as = new Socket(InetAddress.getLocalHost(),6746);
		System.out.println("Client : Connexion etablie !");
		//DataOutput to send the calcul
		DataOutputStream out = new DataOutputStream(as.getOutputStream());
		//BufferedReader to get the answer
		BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
        byte [] mybytearray  = new byte [(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray,0,mybytearray.length);
        System.out.println("Sending " + file + "(" + mybytearray.length + " bytes)");
        out.write(mybytearray);
        out.flush();
        System.out.println("Done.");
		//Read the answer , and print it
		String reponse = in.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

public static void main(String[] args) {
	//Lecture du fichier
	//Création de l'objet File
  //  File f = new File(args[0]);
	File f = new File("TexteAleatoire2.txt");
	@SuppressWarnings("unused")
	Client c= new Client(f);
	System.out.println("client");

}
}
