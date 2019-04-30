/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class GUI_LottoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private Button buttonCheckResult;

    @FXML
    private Button buttonUserType;

    public void handleButtonCheckResultClick(ActionEvent event) throws IOException{
        CreateStage Stage = new CreateStage("GUI_CheckResult.fxml");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void handleButtonUserTypeClick(ActionEvent event) throws IOException{
        CreateStage Stage = new CreateStage("DUI_UserType.fxml");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
}
