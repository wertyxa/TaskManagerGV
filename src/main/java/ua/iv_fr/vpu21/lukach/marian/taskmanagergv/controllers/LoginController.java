package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorizationButton;

    @FXML
    private TextField usernameField;

    @FXML
    private Button xmlAuthorizationButton;
    private StartController startController;

    @FXML
    void initialize() {
        usernameField.setOnKeyTyped(e->{
            if(usernameField.getText().length()>4){
                authorizationButton.setDisable(false);
            }else {
                authorizationButton.setDisable(true);
            }
        });
        usernameField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().getCode()==10)
                authorizationButton.fire();
        });
        xmlAuthorizationButton.setOnAction(e->{
            xmlAuthorizationButton.getScene().getWindow().hide();
            startController.loadDataFromXml(new ActionEvent());

        });
        authorizationButton.setOnAction(e->{
            startController.authorize(getLogin());
            authorizationButton.getScene().getWindow().hide();

        });

    }

    public String getLogin() {
        return usernameField.getText();
    }
    public void setStartController(StartController startController){
        this.startController = startController;
    }
}
