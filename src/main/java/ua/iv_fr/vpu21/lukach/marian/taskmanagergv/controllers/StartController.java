package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;
import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.StartApplication;
import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models.Task;
import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models.Tasks;
import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.util.XmlAdapter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class StartController {

    private final Stage loginStage = new Stage();
    private final Stage addTaskStage = new Stage();
    private final Stage editTaskStage = new Stage();
    private final Stage manualStage = new Stage();




    final URL tableRowStyle = StartApplication.class.getResource("style/table_row_style.css");
    final URL gs = StartApplication.class.getResource("style/gs.css");
    final URL rs = StartApplication.class.getResource("style/rs.css");
    final URL ys = StartApplication.class.getResource("style/ys.css");



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addNewTaskButton;

    @FXML
    private TableColumn<Task, Void> deleteColumn;

    @FXML
    private Button editSelectedRow;

    @FXML
    private TableColumn<Task, LocalDate> endDateColumn;

    @FXML
    private TableColumn<Task, String> executorColumn;

    @FXML
    private TableColumn<Task, String> nameTaskColumn;

    @FXML
    private TableColumn<Task, String> numberMandateColumn;

    @FXML
    private TableColumn<Task, String> publisherColumn;

    @FXML
    private TableColumn<Task, LocalDate> startDateColumn;

    @FXML
    private TableView<Task> tableTasks;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableColumn<Task, Void> statusColumn;

    @FXML
    void about(ActionEvent event) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Про програму");
        about.setHeaderText("Щоденник справ");
        about.showAndWait();
    }

    @FXML
    void changeUser(ActionEvent event) {
        login();
    }

    @FXML
    void closeUser(ActionEvent event) {
//        login();
//        tableTasks.getItems().clear();
        System.exit(0);
    }

    @FXML
    void loadDataFromSql(ActionEvent event) {

    }

    @FXML
    public void loadDataFromXml(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML","*.xml"));
        File file = fileChooser.showOpenDialog(new Stage());
        Tasks tasks = XmlAdapter.convertXmlToData(file);
        ObservableList<Task> os = FXCollections.observableList(tasks.getTasks());
        tableTasks.setItems(os);
        usernameLabel.setText(tasks.getUsername());
    }

    @FXML
    void openManual(ActionEvent event) {
        try {
            manualStage.getIcons().add(StartApplication.icon);
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("layouts/manual.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ManualController loginController = (ManualController) fxmlLoader.getController();
            manualStage.setTitle("Інструкція");
            if (manualStage.getModality() == Modality.NONE)
                manualStage.initModality(Modality.APPLICATION_MODAL);
            manualStage.setScene(scene);
            manualStage.setMaximized(true);
            manualStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveDataFromSql(ActionEvent event) {

    }

    @FXML
    void saveDataFromXml(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML","*.xml"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null){
            XmlAdapter.convertDataToXml(new Tasks(tableTasks.getItems(),usernameLabel.getText()),file.getAbsolutePath());
        }

    }



    @FXML
    void initialize() {
        numberMandateColumn.setCellValueFactory(new PropertyValueFactory<>("mandateNumber"));
        nameTaskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        executorColumn.setCellValueFactory(new PropertyValueFactory<>("executor"));
        statusColumn.setCellFactory(statVc -> new TableCell<Task,Void>(){
            private final ToggleSwitch swich;

            {
                swich = new ToggleSwitch("");
                swich.prefWidth(32);
                swich.prefHeight(18);
                swich.setScaleX(1.3);
                swich.setScaleY(1.3);
                swich.setScaleZ(1.3);

                swich.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                    tableTasks.getItems().get(getIndex()).setStatusE(t1);
                    tableTasks.getSelectionModel().select(getIndex());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty){
                    setGraphic(null);
                }else {
                    setGraphic(swich);
                    swich.setSelected(tableTasks.getItems().get(getIndex()).getStatusE());
                }
            }

        });
        deleteColumn.setCellFactory(taskVoidTableColumn -> new TableCell<Task,Void>(){
            private final Button del;
            {
                ImageView imageView = new ImageView(StartApplication.class.getResource("img/delete.png").toString());
                imageView.setFitWidth(25);
                imageView.setFitHeight(32);
                del = new Button("",imageView);
                del.setOnAction(actionEvent -> {
                    tableTasks.getItems().remove(getIndex());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty){
                    setGraphic(null);
                }else {
                    setGraphic(del);
                    if(tableTasks.getItems().size()!=0){
                        tableTasks.getSelectionModel().select(0);
                    }
                }
            }
        });

        login();
        addNewTaskButton.setOnAction(e->{
            try {
                addTaskStage.getIcons().add(StartApplication.icon);
                FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("layouts/addTask.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                AddTaskController loginController = (AddTaskController) fxmlLoader.getController();
                addTaskStage.setTitle("Додавання справи");
                if (addTaskStage.getModality()==Modality.NONE)
                    addTaskStage.initModality(Modality.APPLICATION_MODAL);
                addTaskStage.setScene(scene);
                loginController.setStartController(this);
                addTaskStage.showAndWait();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        TableView.TableViewSelectionModel<Task> sm = tableTasks.getSelectionModel();
        sm.selectedItemProperty().addListener((observableValue, oldTask, newTask) -> {
            if (newTask != null){
                editSelectedRow.setDisable(false);
            }else {
                editSelectedRow.setDisable(true);
            }
        });
        editSelectedRow.setOnAction(ev -> {
            editTask(sm.getSelectedIndex(), tableTasks.getItems().get(sm.getSelectedIndex()));
        });
        rowStyle();
    }

    private void rowStyle() {

        tableTasks.setRowFactory(tasksTable -> new TableRow<>(){

            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                Task item = getItem();
                if (item != null && !empty){
                    LocalDate now = LocalDate.now();
                    long daysElapsed = ChronoUnit.DAYS.between(now, item.getEndDate());
                    if (getStylesheets().size()==0) {
                        getStylesheets().add(tableRowStyle.toExternalForm());
                    }
                    if (getStylesheets().size() == 1) {
                        if (daysElapsed < 1 && !item.getStatusE()){
                            getStylesheets().add(StartApplication.class.getResource("style/rs.css").toExternalForm());
                        }else if(daysElapsed < 3 && !item.getStatusE()){
                            getStylesheets().add(StartApplication.class.getResource("style/ys.css").toExternalForm());
                        }else if (!item.getStatusE()){
                            getStylesheets().add(StartApplication.class.getResource("style/table_row_style.css").toExternalForm());
                        }else if (item.getStatusE()){
                            getStylesheets().add(StartApplication.class.getResource("style/gs.css").toExternalForm());
                        }
                    }else{
                        if (daysElapsed < 1 && !item.getStatusE()){
                            getStylesheets().set(1, rs.toExternalForm());
                        }else if(daysElapsed < 3 && !item.getStatusE()){
                            getStylesheets().set(1, ys.toExternalForm());
                        }else if (item.getStatusE()){
                            getStylesheets().set(1, gs.toExternalForm());
                        }else {
                            getStylesheets().remove(1);
                        }
                    }
                }

            }
        });

    }


    private void login() {
        try {
            loginStage.getIcons().add(StartApplication.icon);
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("layouts/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            LoginController loginController = (LoginController) fxmlLoader.getController();
            loginStage.setTitle("Авторизація");
            if (loginStage.getModality()==Modality.NONE)
                loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setScene(scene);
            loginController.setStartController(this);
            loginStage.setOnCloseRequest(e->{
                System.exit(0);
            });
            loginStage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void authorize(String username){
        usernameLabel.setText(username);
    }


    public void addTask(Task task) {
        task.setPublisher(usernameLabel.getText());
        tableTasks.getItems().add(task);
    }

    private void editTask(int id, Task task){
        try {
            editTaskStage.getIcons().add(StartApplication.icon);
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("layouts/editTask.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            EditTaskController loginController = (EditTaskController) fxmlLoader.getController();
            editTaskStage.setTitle("Редагування справи");
            if (editTaskStage.getModality()==Modality.NONE)
                editTaskStage.initModality(Modality.APPLICATION_MODAL);
            editTaskStage.setScene(scene);
            loginController.setStartController(this,task,id);
            editTaskStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void changeTask(int idTask, Task editTask) {
        tableTasks.getItems().set(idTask, editTask);
    }
}
