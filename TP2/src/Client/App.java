package Client;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class App {
	//HashMap pour compter le nombre d'occurence des mots
    HashMap<String, Integer> map = new HashMap<String, Integer>();
 
    //Fonction countWords qui compte le nombre de mot sur la ligne
    void countWords(String line) {
    	//un mot est coup par //s
        String [] words = line.split("\\s");
        //pour chaque mot, si le mot est deja utilise, on ajoute +1 a sa valeur , sinon on le cree :
        for (String w : words) {
            if (map.containsKey(w)) 
                map.put(w, new Integer(map.get(w).intValue() + 1));
            else
                map.put(w, new Integer(1));
        }
    }

    //On cherche le nombre d'occurence maximal sur la Hashmap
    Map.Entry<String, Integer> findMax() {
        Map.Entry<String, Integer> maxEntry = null;
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }
    
    public static void main( String[] args ){
    	//On lance l'application
        System.out.println( "Comptage des mots :" );
        //Si il n'y a pas un nombre et le nom du fichier, on renvoie une erreur
        if (args.length != 2){
            System.err.println("Usage: \napp <number of thread> <filename>");
            System.exit(-1);
        }
        
        //Creation d'une app myapp
        App myapp = new App();
        try{
        	//Lecture du fichier
            InputStream fis = new FileInputStream(args[0]);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            //String d'une ligne
            String line = null;
            //Tant qu'il y a des lignes
            while ((line = br.readLine()) != null){
            	//On compte les mots de cette ligne
                myapp.countWords(line);
            }
            //IO Exception 
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        //On cherche dans la map l'entree qui a le plus grand Integer (le plus grand nombre de fois appele)
        Map.Entry<String, Integer> maxEntry = myapp.findMax();
        //On affiche le mot le plus frequent, et le nombre d'occurence
        System.out.println("Most frequent word: " + maxEntry.getKey() + " with " + maxEntry.getValue() + " occurrences");
    }
}
