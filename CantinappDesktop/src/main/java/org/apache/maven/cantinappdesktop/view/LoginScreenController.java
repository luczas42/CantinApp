package org.apache.maven.cantinappdesktop.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.App;
import org.apache.maven.cantinappdesktop.model.ApiResponse;
import org.apache.maven.cantinappdesktop.model.User;
import org.apache.maven.cantinappdesktop.viewmodel.LoginScreenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class LoginScreenController {
    LoginScreenViewModel viewModel = new LoginScreenViewModel();
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

    @FXML
    void onForgotPasswordClick(ActionEvent event) throws IOException {
        Desktop.getDesktop().browse(URI.create("http://54.207.241.251/CantinappServer/users/passwordEmailReset.php"));
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
        if (checkFieldsLogin(textFieldLoginUsername)) {
            if (checkFieldsLogin(textFieldLoginPassword)) {
                String email = textFieldLoginUsername.getText();
                String password = textFieldLoginPassword.getText();
                boolean success = viewModel.checkLogin(email, password);
                checkUser(event, success);
            }
        }
    }

    synchronized private void checkUser(ActionEvent event, boolean success) throws IOException {
        if (success) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("MainScreen.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Usuário não encontrado!");
            alert.setTitle("Login");
            alert.show();
        }
    }

    @FXML
    void onSignup(ActionEvent event) {
        if (checkFieldsRegister(textFieldSignupUsername)) {
            if (checkFieldsRegister(textFieldSignupName)) {
                if (checkFieldsRegister(textFieldSignupEmail)) {
                    if (checkFieldsRegister(textFieldSignupPassword)) {
                        if (checkFieldsRegister(textFieldSignupPasswordConfirm)) {
                            if (textFieldSignupPassword.getText().equals(textFieldSignupPasswordConfirm.getText())) {
                                String username = textFieldSignupUsername.getText();
                                String name = textFieldSignupName.getText();
                                String email = textFieldSignupEmail.getText();
                                User user = new User(username, name, email);
                                String password = textFieldSignupPassword.getText();
                                viewModel.addUser(user, password, addUserCallback);
                            } else {
                                textFieldSignupPasswordConfirm.requestFocus();
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setContentText("Senhas não coincidem.");
                                alert.setTitle("Cadastro");
                                alert.show();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkFieldsRegister(TextField textField) {
        if (!textField.getText().isEmpty()) {
            return true;
        } else {
            textField.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Favor preencha todos os campos.");
            alert.setTitle("Cadastro");
            alert.show();
            return false;
        }
    }

    public boolean checkFieldsLogin(TextField textField) {
        if (!textField.getText().isEmpty()) {
            return true;
        } else {
            textField.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Favor preencha todos os campos.");
            alert.setTitle("Login");
            alert.show();
            return false;
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

    Callback<ApiResponse> addUserCallback = new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

            if (response.isSuccessful()) {

                if (response.body().isSuccess()) {
                    Platform.runLater(() -> {
                        Alert alert;
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText(response.body().getMessage());
                        alert.setTitle("Cadastro");
                        alert.show();
                        clearCamps();
                    });
                } else {
                    Platform.runLater(() -> {
                        Alert alert;
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText(response.body().getMessage());
                        alert.setTitle("Cadastro");
                        alert.show();
                        clearCamps();
                    });
                }
            }
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable throwable) {
            System.out.println("falha " + throwable.getMessage());
        }
    };


}



