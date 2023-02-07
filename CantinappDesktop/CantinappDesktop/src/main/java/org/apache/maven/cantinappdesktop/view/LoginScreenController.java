package org.apache.maven.cantinappdesktop.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.App;
import org.apache.maven.cantinappdesktop.data.service.RetrofitInit;
import org.apache.maven.cantinappdesktop.data.service.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class LoginScreenController {
    RetrofitInit retrofitInit = new RetrofitInit();
    Users connectedUser;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button closeAppButton;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Button minimizeAppButton;
    @FXML
    private Button exitButton;


    @FXML
    private Button loginButton;

    @FXML
    private BorderPane loginPane;

    @FXML
    private Button passwordForgot;

    @FXML
    private Button signupButton;

    @FXML
    private PasswordField textFieldLoginPassword;

    @FXML
    private TextField textFieldLoginUsername;
    @FXML
    private TextField textFieldSignupName;

    @FXML
    private TextField textFieldSignupEmail;

    @FXML
    private PasswordField textFieldSignupPassword;

    @FXML
    private TextField textFieldSignupUsername;

    @FXML
    private PasswordField textFieldSignupPasswordConfirm;
    @FXML
    private Button configButton;
    @FXML
    private TableColumn<?, ?> employeeClass;
    @FXML
    private TableColumn<?, ?> employeeName;
    @FXML
    private TableView<?> employeeTable;

    @FXML
    private ToggleGroup frame;

    @FXML
    private Pane startPane;
    @FXML
    private TableColumn<?, ?> workdayClass;

    @FXML
    private TableColumn<?, ?> workdayDate;

    @FXML
    private TableColumn<?, ?> workdayEmployee;

    @FXML
    private TableColumn<?, ?> workdayPeriod;

    @FXML
    private TableView<?> workdaysTable;

    Callback<Users> checkLoginCallback = new Callback<Users>() {
        @Override
        public void onResponse(Call<Users> call, Response<Users> response) {
//            connectedUser = response.body();
//            System.out.println(connectedUser.getName());


        }

        @Override
        public void onFailure(Call<Users> call, Throwable throwable) {
            System.out.println("teste");
            throwable.printStackTrace();
        }
    };

    public void openMainScene(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("MainScreen.fxml"));
        root = loader.load();

        MainScreenController mainScreenController = loader.getController();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Callback<Users> addUserCallback = new Callback<Users>() {
        @Override
        public void onResponse(Call<Users> call, Response<Users> response) {
            System.out.println(response.code());
        }

        @Override
        public void onFailure(Call<Users> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };



    public LoginScreenController() {
    }

    @FXML
    void closeApplication(ActionEvent event) {
        this.loginPane.setVisible(true);
        this.loginPane.toFront();
    }

    @FXML
    void onForgotPasswordClick(ActionEvent event) {

    }

    @FXML
    void onLogin(ActionEvent event) {
        String username = textFieldLoginUsername.getText();
        String password = textFieldLoginPassword.getText();
        retrofitInit.checkLogin(checkLoginCallback,username,password);
//        if (Objects.equals(username, connectedUser.getUsername())){
//            this.loginPane.setVisible(false);
//        }
    }

    @FXML
    void onSignup(ActionEvent event) {
        String username = textFieldSignupUsername.getText();
        String name = textFieldSignupName.getText();
        String email = textFieldSignupEmail.getText();
        String password = textFieldSignupPassword.getText();
        System.out.println(password);
        Users user = new Users(username, name, email);
        retrofitInit.addUser(addUserCallback, user, password);

    }



    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void minimizeApp(ActionEvent event) {
        Stage stage = (Stage) minimizeAppButton.getScene().getWindow();
        stage.setIconified(true);
    }


}



