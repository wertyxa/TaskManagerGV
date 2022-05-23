package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models.Task;

import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class EditTaskController {


    private StartController startController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField executorField;

    @FXML
    private DatePicker endDayPick;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField mandateField;

    @FXML
    private TextArea nameTestArea;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDayPick;

    @FXML
    private TextField publisherField;


    private boolean warningMandate = false;
    private boolean warningName = false;
    private boolean warningStart = false;
    private boolean warningEnd = false;
    private boolean warningExecutor = false;
    private boolean warningPublisher = false;
    private Task editTask;
    private int idTask;

    @FXML
    void initialize() {
        //попередження для поля довіреності
        mandateField.setOnKeyTyped(e -> textInWarning(mandateField));
        executorField.setOnKeyTyped(e -> textInWarning(executorField));
        publisherField.setOnKeyTyped(e -> textInWarning(publisherField));
        //попередження для поля вводу назви
        nameTestArea.setOnKeyTyped(e->texaAreaWarning());

        dateWarning(startDayPick);
        dateWarning(endDayPick);
        saveButton.setOnAction(actionEvent -> {
            saveTask();
            startController.changeTask(idTask,editTask);
            saveButton.getScene().getWindow().hide();
        });
        cancelButton.setOnAction(a ->{
            cancelButton.getScene().getWindow().hide();
        });

    }

    private void texaAreaWarning() {
        ListIterator<String> lI = nameTestArea.getStyleClass().listIterator();
        if (nameTestArea.getText().length()>4){
            while (lI.hasNext()){
                if (lI.next().equals("text-area-warning")) {
                    nameTestArea.getStyleClass().set(lI.nextIndex() - 1, "null");
                    warningName=true;
                }
            }
        }else {
            while (lI.hasNext()){
                if (lI.next().equals("null")) {
                    nameTestArea.getStyleClass().set(lI.nextIndex() - 1, "text-area-warning");
                    warningName = false;
                }
            }
        }
        checkWarning();
    }

    private void checkWarning() {
        if (warningMandate){
            saveButton.setDisable(false);
            if (warningName){
                saveButton.setDisable(false);
                if (warningStart){
                    saveButton.setDisable(false);
                    if (warningEnd){
                        saveButton.setDisable(false);
                        if (warningExecutor){
                            saveButton.setDisable(false);
                            if (warningPublisher){
                                saveButton.setDisable(false);
                            }else {
                                saveButton.setDisable(true);
                            }
                        }else {
                            saveButton.setDisable(true);
                        }
                    }else {
                        saveButton.setDisable(true);
                    }
                }else {
                    saveButton.setDisable(true);
                }
            }else {
                saveButton.setDisable(true);
            }
        }else {
            saveButton.setDisable(true);
        }
    }

    private void textInWarning(TextField textField) {
            ListIterator<String> stringListIterator = textField.getStyleClass().listIterator();
            if(textField.getText().length()>1){
                while (stringListIterator.hasNext()){
                    if (stringListIterator.next().equals("text-field-warning")) {
                        textField.getStyleClass().set(stringListIterator.nextIndex() - 1, "null");
                        if (textField==executorField){
                            warningExecutor=true;
                        }else if (textField==mandateField){
                            warningMandate=true;
                        }
                    }
                }
            }else {
                while (stringListIterator.hasNext()){
                    if (stringListIterator.next().equals("null")) {
                        textField.getStyleClass().set(stringListIterator.nextIndex() - 1, "text-field-warning");
                        if (textField==executorField){
                            warningExecutor=false;
                        }else if (textField==mandateField){
                            warningMandate=false;
                        }
                    }
                }

            }
            checkWarning();

    }

    private void dateWarning(DatePicker date) {
        date.setOnAction(e ->{
            System.out.println(date.getValue());
            ListIterator<String> lI = date.getStyleClass().listIterator();
            if (date.getValue()!=null){
                while (lI.hasNext()){
                    if (lI.next().equals("text-field-warning")) {
                        date.getStyleClass().set(lI.nextIndex() - 1, "null");
                        if (date==startDayPick){
                            warningStart=true;
                        }else if (date==endDayPick){
                            warningEnd=true;
                        }

                    }
                }
            }else {
                while (lI.hasNext()){
                    if (lI.next().equals("null")) {
                        date.getStyleClass().set(lI.nextIndex() - 1, "text-field-warning");
                        if (date==startDayPick){
                            warningStart=false;
                        }else if (date==endDayPick){
                            warningEnd=false;
                        }

                    }
                }
            }
            checkWarning();
        });
    }

    public void setStartController(StartController startController, Task editTask, int idTask){
        this.startController = startController;
        this.editTask = editTask;
        this.idTask = idTask;
        loadTask();
    }

    private void loadTask() {
        mandateField.setText(editTask.getMandateNumber());
        nameTestArea.setText(editTask.getName());
        startDayPick.setValue(editTask.getStartDate());
        endDayPick.setValue(editTask.getEndDate());
        executorField.setText(editTask.getExecutor());
        publisherField.setText(editTask.getPublisher());
        warningMandate = true;
        warningName = true;
        warningStart = true;
        warningEnd = true;
        warningExecutor = true;
        warningPublisher = true;
        checkWarning();
    }
    private void saveTask() {
        editTask.setMandateNumber(mandateField.getText());
        editTask.setName(nameTestArea.getText());
        editTask.setStartDate(startDayPick.getValue());
        editTask.setEndDate(endDayPick.getValue());
        editTask.setExecutor(executorField.getText());
        editTask.setPublisher(publisherField.getText());
    }

}
