package org.apache.maven.cantinappdesktop.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.maven.cantinappdesktop.App;
import org.apache.maven.cantinappdesktop.data.service.Products;
import org.apache.maven.cantinappdesktop.data.service.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FXMLController {
    ScheduledExecutorService productsRefreshExecutor = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService scaleRefreshExecutor;
    ScheduledExecutorService employeeRefreshExecutor;
    RetrofitInit retrofitInit = new RetrofitInit();
    @FXML
    private Button closeAppButton;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Button minimizeAppButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button newProductButton;

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
    private Pane employeePane;

    @FXML
    private TableView<?> employeeTable;
    @FXML
    private ToggleButton employeesButton;
    @FXML
    private ToggleGroup frame;
    @FXML
    private TableColumn<Products, String> productName;
    @FXML
    private TableColumn<Products, Float> productPrice;
    @FXML
    private TableView<Products> productTable;
    @FXML
    private Pane productsPane;
    @FXML
    private ToggleButton productsButton;
    @FXML
    private ToggleButton shiftsButton;
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
    private Pane workdaysPane;

    @FXML
    private TableView<?> workdaysTable;

    Callback<List<Products>> listCallback = new Callback<>() {
        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
            List<Products> productsList = response.body();

            assert productsList != null;

            ObservableList<Products> productsObservableList = FXCollections.observableList(productsList);

            FXMLController.this.productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            FXMLController.this.productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
            FXMLController.this.productTable.setItems(productsObservableList);
            System.out.println("feitoo " + productsList.size());
            productTable.refresh();
        }

        public void onFailure(Call<List<Products>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public FXMLController() {
    }

    @FXML
    void displayEmployees(ActionEvent event) {
        this.productsButton.setSelected(false);
        productsButton.setDisable(false);
        this.shiftsButton.setSelected(false);
        shiftsButton.setDisable(false);
        this.employeePane.toFront();
        employeesButton.setDisable(true);
    }

    @FXML
    void displayProducts(ActionEvent event) {
        this.employeesButton.setSelected(false);
        employeesButton.setDisable(false);
        this.shiftsButton.setSelected(false);
        shiftsButton.setDisable(false);
        this.productsPane.toFront();
        productsButton.setDisable(true);
        productsRefreshExecutor.scheduleAtFixedRate(() -> {
            try {
                refreshProductsTable();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, 2, TimeUnit.SECONDS);

    }

    @FXML
    void onProductSelected(MouseEvent event) {
        Products selectedProduct = productTable.getSelectionModel().getSelectedItem();

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("productEditScreen.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            stage.setTitle("Cantinapp");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            ProductEditScreen productEditScreenController = fxmlLoader.getController();
            productEditScreenController.productEdit(selectedProduct);
            productsRefreshExecutor.wait();
            stage.showAndWait();
            productsRefreshExecutor.notify();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void displayShifts(ActionEvent event) {
        this.productsButton.setSelected(false);
        productsButton.setDisable(false);
        this.employeesButton.setSelected(false);
        employeesButton.setDisable(false);
        shiftsButton.setDisable(true);
        this.workdaysPane.toFront();

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
        this.loginPane.setVisible(false);
    }

    @FXML
    void onSignup(ActionEvent event) {

    }

    @FXML
    void openNewProductScreen(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("productEditScreen.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            stage.setTitle("Cantinapp");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            ProductEditScreen productEditScreenController = fxmlLoader.getController();
            productEditScreenController.productAdd();
            stage.showAndWait();
            refreshProductsTable();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refreshProductsTable() throws InterruptedException {
        retrofitInit.getProducts(this.listCallback);
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



