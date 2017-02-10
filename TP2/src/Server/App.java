package Server;

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
}