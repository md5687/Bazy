/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DUI_UserTypeController implements Initializable {
    
    private ArrayList<LinkedList> alistOfUserType = new ArrayList<>();
    private int id = 1;
    
    private void clearTextFiled(TextField tf){
        tf.setText("");
    }
    
    private void clearTextFiledAll(){
        clearTextFiled(tfUserTypeFirst);
        clearTextFiled(tfUserTypeSecend);
        clearTextFiled(tfUserTypeThrid);
        clearTextFiled(tfUserTypeFourth);
        clearTextFiled(tfUserTypeFifth);
        clearTextFiled(tfUserTypeSixth);
    }
    
    private boolean checkTFNotNull(){
        return ( ( !(tfUserTypeFirst.getText().equals(null)) || !(tfUserTypeFirst.getText().equals("")) ) && ( !(tfUserTypeSecend.getText().equals(null)) || !(tfUserTypeSecend.getText().equals("")) ) && ( !(tfUserTypeThrid.getText().equals(null)) || !(tfUserTypeThrid.getText().equals("")) ) && ( !(tfUserTypeFourth.getText().equals(null)) || !(tfUserTypeFourth.getText().equals("")) ) && ( !(tfUserTypeFifth.getText().equals(null)) || !(tfUserTypeFifth.getText().equals("")) ) && ( !(tfUserTypeSixth.getText().equals(null)) || !(tfUserTypeSixth.getText().equals("")) ) );
    }
    
    private void saveChange() throws IOException{
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("LottoUserType.txt");
            for(LinkedList<Integer> llist: alistOfUserType){
                int i = 0;
                for(int number : llist){
                    fileWriter.write(Integer.toString(number) + " ");
                    i++;
                }
                if(i == llist.size()){
                    fileWriter.write("\n");
                }
            }
        } finally {
            if (fileWriter != null) {
                    fileWriter.close();
            }
        }
    }
    
    private void updataTextArea(){
        textareaUserType.setText("");
        textareaUserType.setText("ID        Szóstka\n");
        int j = 1;
        if(alistOfUserType.size() <= 1){
            if(alistOfUserType.size() == 1){
                textareaUserType.setText( textareaUserType.getText() + j + "    " );
                for(int i = 0; i < 6; i++){
                    textareaUserType.setText(textareaUserType.getText() + alistOfUserType.get(0).get(i) + " ");
                }
            }
        }else {
            for (LinkedList llist : alistOfUserType) {
                textareaUserType.setText(textareaUserType.getText() + j + "    ");
                for (int i = 0; i < llist.size(); i++) {
                    textareaUserType.setText(textareaUserType.getText() + llist.get(i) + " ");
                }
                textareaUserType.setText(textareaUserType.getText() + "\n");
                j++;
            }
        }
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
    
    private void readFile(){
        textareaUserType.setText("");
        textareaUserType.setText("ID        Szóstka\n");
        id = 1;
        BufferedReader file;
        try{
            file = new BufferedReader(new FileReader("LottoUserType.txt"));
            
            
            String line;
            String[] tempTab;
            int number = 0;
            
            line = file.readLine();
            while( line != null ){
                textareaUserType.setText( textareaUserType.getText() + id + "       " + line + "\n" );
                tempTab = line.split(" ");
                
                LinkedList<Integer> llist = new LinkedList<>();
                for(String temp : tempTab){
                    try{
                        number = Integer.parseInt(temp);
                        llist.add(number);
                    }catch(Exception e1){
                        System.out.println("Error in initialize with DUI_UserTypeController in for(String temp : tempTab). \n" + e1.getLocalizedMessage());
                    }
                }
                alistOfUserType.add(llist);
                id++;
                line = file.readLine();
            }
            
        }catch(Exception e){
            System.out.println("Error in initialize with DUI_UserTypeController in read file. \n" + e.getLocalizedMessage());
        }finally{
            
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textareaUserType.setEditable(false);
        readFile();
    }    
    
     @FXML
    private Button buttonPageBack;

    @FXML
    private TextField tfUserTypeFirst;

    @FXML
    private TextField tfUserTypeSecend;

    @FXML
    private TextField tfUserTypeThrid;

    @FXML
    private TextField tfUserTypeFourth;

    @FXML
    private TextField tfUserTypeFifth;

    @FXML
    private TextField tfUserTypeSixth;

    @FXML
    private Button buttonUserTypeAdd;

    @FXML
    private TextArea textareaUserType;

    @FXML
    private TextField textfieldUserTypeDel;

    @FXML
    private Button buttonUserTypeDel;
    
    @FXML
    private Button buttonSaveChange;
    
    @FXML
    private Button buttonBackChange;
    
    @FXML
    private Label labelAlert;
    
    @FXML
    void handleButtonBackChange(ActionEvent event) {
        readFile();
    }
    
    @FXML
    void handleButtonSaveChange(ActionEvent event) throws IOException {
        saveChange();
        clearTextFiledAll();
    }
    
    @FXML
    void handleButtonPageBackClick(ActionEvent event) throws IOException {
        
        saveChange();
        clearTextFiledAll();
        
        CreateStage Stage = new CreateStage("GUI_Lotto.fxml");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void handleButtonUserTypeAdd(ActionEvent event) {
        if(checkTFNotNull()){
            LinkedList<Integer> llist = new LinkedList<>();
            int number = 0; 
            try{
                number = changeStringToInt(tfUserTypeFirst.getText());
                llist.add(number);
                number = changeStringToInt(tfUserTypeSecend.getText());
                llist.add(number);
                number = changeStringToInt(tfUserTypeThrid.getText());
                llist.add(number);
                number = changeStringToInt(tfUserTypeFourth.getText());
                llist.add(number);
                number = changeStringToInt(tfUserTypeFifth.getText());
                llist.add(number);
                number = changeStringToInt(tfUserTypeSixth.getText());
                llist.add(number);
            }catch(Exception e1){
                System.out.println("Error in handleButtonUserTypeAdd. " + e1.getLocalizedMessage());
            }
            alistOfUserType.add(llist);
        }else{
            labelAlert.setText("Wypełnij poprawnie pola.");
        }
        updataTextArea();
        clearTextFiledAll();
    }

    @FXML
    void handleButtonUserTypeDel(ActionEvent event) {
        try{
            int idDel = Integer.parseInt(textfieldUserTypeDel.getText());
            alistOfUserType.remove(idDel - 1);
            updataTextArea();
        }catch(Exception e){
            labelAlert.setText("Niepoprawne id.");
            System.out.println("Error in handleButtonUserTypeDel. " + e.getLocalizedMessage());
        }
    }
    
}
