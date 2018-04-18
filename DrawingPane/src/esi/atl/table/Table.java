package esi.atl.table;

import esi.atl.deTurck.users.User;
import java.io.Serializable;
import java.util.List;
import esi.atl.words.RandomWords;
import java.util.ArrayList;
/**
 *
 * @author Geekette Force
 */
public class Table implements Serializable{
    private final int id;
    private boolean open;
    private Status tableStat;
    private List<User> listPlayer;
    private String wordToGuess;
    private List<String> listWordProposed;
    private final String name;

    public Status getTableStat() {
        return tableStat;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public String getName() {
        return name;
    }

    public Table(int id,User drawer,String name) {
        this.id = id;
        open = true;
        tableStat=Status.SEARCHINGPARTNAIR;
        listPlayer= new ArrayList();
        listPlayer.add(drawer);
        listWordProposed = new ArrayList();
        this.name= name;
        
    }

    public boolean isOpen() {
        return open;
    }
    
    public boolean isTheWord(String proposed) {
        this.addListWordProposed(proposed);
        if(proposed.equals(wordToGuess)){
            tableStat=Status.WIN;
            return true;
        }else {
            return false;
        }
    }
    
    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<User> getListplayer() {
        return listPlayer;
    }

    public void addListplayer(User drawingPlayer) {
        this.listPlayer.add(drawingPlayer);
        tableStat= Status.INPROGRESS;
        open = false;
        RandomWords random = new RandomWords();
        wordToGuess= random.getWordsRandom();
    }

    public List<String> getListWordProposed() {
        return listWordProposed;
    }

    private void addListWordProposed(String wordProposed) {
        this.listWordProposed.add(wordProposed);
    }

    public int getId() {
        return id;
    }
    
    public boolean isId(int it) {
        return it==id;
    }
    
    public boolean isPlayerOnTable(User user){
        for (User current : listPlayer){
            if(current.getId()==user.getId()){
                return true;
            }
        }
        return false;
    }
    
}
