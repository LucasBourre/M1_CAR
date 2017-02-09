package Server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Server {
	//Functions for the operators
	public float addition(float x, float y) {
		return x + y;
	}
	public float subtraction(float x, float y){
		return x - y;
	}
	public float multiplication(float x, float y){    
		return x * y;
	}
	public float division(float x, float y){
		float solution = x / y;
		return solution;
	}


	public Server() throws NumberFormatException{
		ServerSocket ps=null;
		//first number
		float Operand1=0;
		//second number
		float Operand2=0;
		float Resultat = 0;
		try{
			//ServerSocket
			ps = new ServerSocket(6748);
			System.out.println("Serveur lancé !");
			while(true){
				//Accept Client
				Socket as = ps.accept();
				//BufferedReader to get the message
				BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
				//DataOutputStream to send a message
				DataOutputStream out = new DataOutputStream(as.getOutputStream());
				try{
					while(true){
						//msg is the message we received
						String msg = in.readLine();
						//we cut the msg, and put it in a Tab[]
						String Tab[] = msg.split(" ");

						// If 3 cases in Tab , then Op1 = Tab[1] and Op[2] = Tab[2]
						if(Tab.length == 3){
							Operand1=Float.parseFloat(Tab[1]);
							Operand2=Float.parseFloat(Tab[2]);
							Resultat=0;
						}else{
							//if 2 cases in Tab, then Operand1 = the result of the last operation
							if(Tab.length == 2) {
								Operand1=Resultat;
								Operand2=Float.parseFloat(Tab[1]);
								//else : more or less than 2/3 entry = error
							}else{
								out.writeBytes("Error , Message doesnt have the good format. try ' + 3 5 '\n");
							}
						}


						try{
							//We put the result in Resultat depending on what operators we put
							switch(Tab[0]){
							case("+"):
								Resultat = addition(Operand1,Operand2);
							break;

							case("-"):
								Resultat =  subtraction(Operand1,Operand2);
							break;

							case("*"):
								Resultat =  multiplication(Operand1,Operand2);
							break;

							case("/"):
								if (Operand2 != 0)
									Resultat =   division(Operand1,Operand2);
								else
									throw new ArithmeticException();
							break;
							}	
							
						}catch (ArithmeticException ae) {
								out.writeBytes("Error, division by 0  \n");
						}catch(Exception e){
							out.writeBytes("Error, write the formule like ' + 3 5' \n");
						}
						//We send a message with the result
						out.writeBytes(Float.toString(Resultat) + "\n");
						System.out.println("Message envoye avec Succes");
					}


				}catch(Exception e){
					System.out.println("connexion non etabli :" + e.getMessage());
				}
			}

		}catch(Exception e){
			System.out.println("connexion non etabli : " + e.getMessage());


		}

	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Server s = new Server();
	}
}