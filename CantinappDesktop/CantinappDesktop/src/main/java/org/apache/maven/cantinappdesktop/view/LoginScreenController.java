package org.apache.maven.cantinappdesktop.view;

import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.App;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import org.apache.maven.cantinappdesktop.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class LoginScreenController {
    RetrofitInit retrofitInit = new RetrofitInit();
    User connectedUser;
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
    private ToggleGroup frame;

    @FXML
    private Pane startPane;

    Callback<User> checkLoginCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
//            connectedUser = response.body();
//            System.out.println(connectedUser.getName());


        }

        @Override
        public void onFailure(Call<User> call, Throwable throwable) {
            System.out.println("teste");
            throwable.printStackTrace();
        }
    };

    public void openMainScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("MainScreen.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Callback<User> addUserCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {

            if (response.isSuccessful()){
                System.out.println("sucesso na resposta");
            }else{
                System.out.println("falha na resposta");
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable throwable) {
            System.out.println("falha "+ throwable.getMessage());
        }
    };


    public LoginScreenController() throws NoSuchAlgorithmException, KeyManagementException {
    }

    @FXML
    void onForgotPasswordClick(ActionEvent event) throws IOException {
        Desktop.getDesktop().browse(URI.create("http://54.207.241.251/CantinappServer/users/passwordEmailReset.php"));
    }

    @FXML
    void onLogin(ActionEvent event) {
        String username = textFieldLoginUsername.getText();
        String password = textFieldLoginPassword.getText();
        retrofitInit.checkLogin(checkLoginCallback, username, password);
//        if (Objects.equals(username, connectedUser.getUsername())){
//            this.loginPane.setVisible(false);
//        }
    }

    @FXML
    void onSignup(ActionEvent event) {
        if (!textFieldSignupUsername.getText().isEmpty()) {
            if (!textFieldSignupName.getText().isEmpty()) {
                if (!textFieldSignupEmail.getText().isEmpty()) {
                    if (!textFieldSignupPassword.getText().isEmpty()) {
                        if (!textFieldSignupPasswordConfirm.getText().isEmpty()) {
                            if (textFieldSignupPassword.getText().equals(textFieldSignupPasswordConfirm.getText())) {
                                String username = textFieldSignupUsername.getText();
                                String name = textFieldSignupName.getText();
                                String email = textFieldSignupEmail.getText();
                                User user = new User(username, name, email);

                                String password = textFieldSignupPassword.getText();
                                retrofitInit.addUser(addUserCallback, user, password);
                                clearCamps();
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }

    private void clearCamps() {
        textFieldSignupName.clear();
        textFieldSignupUsername.clear();
        textFieldSignupEmail.clear();
        textFieldSignupPassword.clear();
        textFieldSignupPasswordConfirm.clear();
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



