package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {



	public Server(){
		App myapp = new App();
		ServerSocket ps=null;
		try{
			//ServerSocket
			ps = new ServerSocket(6746);
			System.out.println("Serveur lance !");
			while(true){
				//Accept Client
				Socket as = ps.accept();
				//BufferedReader to get the message
				BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
				//DataOutputStream to send a message
				DataOutputStream out = new DataOutputStream(as.getOutputStream()); 
				String line = null ;
				//Tant qu'il y a des lignes
				while ((line = in.readLine()) != null){
					//On compte les mots de cette ligne
					myapp.countWords(line);
					System.out.println(myapp.map.toString());
				}
				System.out.println("on est sorti de la boucle");
				Map.Entry<String,Integer> maxEntry = myapp.findMax();
				//On affiche le mot le plus frequent, et le nombre d'occurence
				System.out.println("Most frequent word: " + maxEntry.getKey() + " with " + maxEntry.getValue() + " occurrences");
			}

		}catch(Exception i){
			i.printStackTrace();
		}
	}	

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Server s = new Server();
	}


}