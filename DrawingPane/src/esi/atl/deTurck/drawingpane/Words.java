package esi.atl.deTurck.drawingpane;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author g43353
 */
public class Words {
    private ArrayList<String> words;

    public Words() {
        words= new ArrayList();
        words.add("cochon");
        words.add("puce");
    }
    
    public String getRandom(){
        int nb = (int) (Math.random()*(words.size()));
        return words.get(nb);
    }
   
    
    
    
}
