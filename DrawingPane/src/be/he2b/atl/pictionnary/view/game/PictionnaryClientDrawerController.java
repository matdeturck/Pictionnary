/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.atl.pictionnary.view.game;

import esi.atl.deTurck.drawingpane.DrawingPaneControl;
import esi.atl.table.Table;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author g43353
 */
public class PictionnaryClientDrawerController implements Initializable {
    Table table;
    
    @FXML
    private Label nameTable;
    
    @FXML
    private Label statusTable;

    @FXML
    private Label guessWord;

    @FXML
    private TextArea wordProposed;

    @FXML
    private DrawingPaneControl drawingPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wordProposed.setEditable(false);
    }   
    
    public void set(Table table){
        guessWord.setText(table.getWordToGuess());
        nameTable.setText(table.getName());
        statusTable.setText(table.getTableStat().toString());
    }
    
}