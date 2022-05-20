module ua.iv_fr.vpu21.lukach.marian.taskmanagergv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires transitive jakarta.activation;
    requires transitive java.xml;
    requires java.xml.bind;
    requires org.controlsfx.controls;

    opens ua.iv_fr.vpu21.lukach.marian.taskmanagergv to javafx.fxml, java.xml.bind;
    exports ua.iv_fr.vpu21.lukach.marian.taskmanagergv;
    exports ua.iv_fr.vpu21.lukach.marian.taskmanagergv.controllers;
    exports ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models;
    exports ua.iv_fr.vpu21.lukach.marian.taskmanagergv.util;
    opens ua.iv_fr.vpu21.lukach.marian.taskmanagergv.controllers to javafx.fxml;
    opens ua.iv_fr.vpu21.lukach.marian.taskmanagergv.util to java.xml.bind;
    opens ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models to java.xml.bind;

}