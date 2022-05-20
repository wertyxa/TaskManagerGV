package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.StartApplication;

public class ManualController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webW;
    WebEngine webEngine = null;

    @FXML
    void initialize() throws IOException {
        //WebEngine webEngine = new WebEngine();
        String st = StartApplication.class.getResource("layouts/manual.html").toString();
        webEngine = webW.getEngine();
        webEngine.load(st);

    }

}
