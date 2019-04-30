/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class GUI_CheckResultController implements Initializable {
    
    private ArrayList<LinkedList> alistOfUserType = new ArrayList<>();
    private ObservableList<Result> results = FXCollections.observableArrayList();
    
    private String lltoString(LinkedList<Integer> list){
        String result = "";
        for(int i : list){
            result = result + i + " ";
        }
        return result;
    }
    
    private void clearTextArea(){
        textareaResult.setText("");
    }
    
     private boolean checkTFNotNull(){
        return ( ( !(textfiledResultFirst.getText().equals(null)) || !(textfiledResultFirst.getText().equals("")) ) && ( !(textfiledResultSecend.getText().equals(null)) || !(textfiledResultSecend.getText().equals("")) ) && ( !(textfiledResultThrid.getText().equals(null)) || !(textfiledResultThrid.getText().equals("")) ) && ( !(textfiledResultFouth.getText().equals(null)) || !(textfiledResultFouth.getText().equals("")) ) && ( !(textfiledResultFifth.getText().equals(null)) || !(textfiledResultFifth.getText().equals("")) ) && ( !(textfiledResultSixth.getText().equals(null)) || !(textfiledResultSixth.getText().equals("")) ) );
    }
    
    private void addResult(Result r){
        results.add(r);
    } 
     
    private ObservableList<Result> getResult(){
        ObservableList<Result> resultsTemp = FXCollections.observableArrayList();
        resultsTemp.addAll(results);
        results.clear();
        return resultsTemp;
    }
    
    private int changeStringToInt(String s){
        int i = 0;
        
        try{
            i = Integer.parseInt(s);
        }catch(Exception e){
            labelAlert.setText("Nie podałeś liczby.");
            System.out.println("Error in changeStringToInt(String s). " + e.getLocalizedMessage());
        }
        
        return i;
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        textareaResult.setEditable(false);
        
        BufferedReader file;
        try{
            file = new BufferedReader(new FileReader("LottoUserType.txt"));
            
            
            String line;
            String[] tempTab;
            int number = 0;
            
            while( (line = file.readLine()) != null ){
                tempTab = line.split(" ");
                
                LinkedList<Integer> llist = new LinkedList<>();
                for(String temp : tempTab){
                    try{
                        number = Integer.parseInt(temp);
                        llist.add(number);
                    }catch(Exception e1){
                        System.out.println("Error in initialize with GUI_ChechResultController in for(String temp : tempTab). \n" + e1.getLocalizedMessage());
                    }
                }
                alistOfUserType.add(llist);
            }
            
        }catch(Exception e){
            System.out.println("Error in initialize with GUI_ChechResultController in read file. \n" + e.getLocalizedMessage());
        }finally{
            
        }
        
        tvColumnID.setCellValueFactory(new PropertyValueFactory<Result, String>("ID"));
        tvColumnUserType.setCellValueFactory(new PropertyValueFactory<Result, String>("Szóstki"));
        tvColumnWin.setCellValueFactory(new PropertyValueFactory<Result, String>("Zgadzające się"));
        
    }
    
    @FXML
    private TextField textfiledResultFirst;

    @FXML
    private TextField textfiledResultSecend;

    @FXML
    private TextField textfiledResultThrid;

    @FXML
    private TextField textfiledResultFouth;

    @FXML
    private TextField textfiledResultFifth;

    @FXML
    private TextField textfiledResultSixth;

    @FXML
    private TextArea textareaResult;

    @FXML
    private Button buttonBackPage;

    @FXML
    private Button buttonCheckResult;

    @FXML
    private Label labelAlert;
    
    @FXML
    private TableView<Result> tableviewResult;

    @FXML
    private TableColumn<Result, String> tvColumnID;

    @FXML
    private TableColumn<Result, String> tvColumnUserType;

    @FXML
    private TableColumn<Result, String> tvColumnWin;
    
    @FXML
    void handleButtonBackPage(ActionEvent event) throws IOException {
        CreateStage Stage = new CreateStage("GUI_Lotto.fxml");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void handleButtonCheckResult(ActionEvent event) {
        clearTextArea();
        
        if(checkTFNotNull()){
            LinkedList<Integer> llist = new LinkedList<>();
            int number = 0; 
            try{
                number = changeStringToInt(textfiledResultFirst.getText());
                llist.add(number);
                number = changeStringToInt(textfiledResultSecend.getText());
                llist.add(number);
                number = changeStringToInt(textfiledResultThrid.getText());
                llist.add(number);
                number = changeStringToInt(textfiledResultFouth.getText());
                llist.add(number);
                number = changeStringToInt(textfiledResultFifth.getText());
                llist.add(number);
                number = changeStringToInt(textfiledResultSixth.getText());
                llist.add(number);
            }catch(Exception e1){
                System.out.println("Error in handleButtonUserTypeAdd. " + e1.getLocalizedMessage());
            }
            
            textareaResult.setText("ID        Szóstka                     Liczba zgodnych\n");
            int id = 0;
            for(LinkedList<Integer> tempList : alistOfUserType){
                int i = 0;
                int yes = 0;
                while(i < 6){
                    if((int)tempList.get(i) == (int)llist.get(i)){
                        yes++;
                    }
                    i++;
                }
                id++;
                textareaResult.setText(textareaResult.getText() + "\n" + id + "      " + lltoString(tempList) + "                   " + yes);
                Result result = new Result(id, tempList, yes);
                addResult(result);
            }
            tableviewResult.setItems(getResult());
            
        }else{
            labelAlert.setText("Wypełnij poprawnie pola.");
        }
        
    }
    
    
}
