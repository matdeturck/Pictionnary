package esi.atl.words;

import java.util.ArrayList;

/**
 *
 * @author Geekette Force
 */
public class RandomWords {

    public RandomWords() {
        this.words = new ArrayList();
        words.add("Lapin");
        words.add("Oiseau");
    }
    private ArrayList<String> words;
    
    public String getWordsRandom(){
        return "lapin";
    }
}
