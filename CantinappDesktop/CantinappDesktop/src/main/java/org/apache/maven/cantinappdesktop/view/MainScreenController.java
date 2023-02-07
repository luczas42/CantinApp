package org.apache.maven.cantinappdesktop.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.maven.cantinappdesktop.App;
import org.apache.maven.cantinappdesktop.model.CloseButton;
import org.apache.maven.cantinappdesktop.model.Products;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FieldMap;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainScreenController {

    RetrofitInit retrofitInit = new RetrofitInit();
    ScheduledExecutorService productsRefreshExecutor = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService scaleRefreshExecutor;
    ScheduledExecutorService employeeRefreshExecutor;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button minimizeAppButton;
    @FXML
    private Pane workdaysPane;
    @FXML
    private ToggleButton employeesButton;
    @FXML
    private ToggleButton shiftsButton;
    @FXML
    private Pane productsPane;
    @FXML
    private ToggleButton productsButton;
    @FXML
    private Pane employeePane;
    @FXML
    private TableView<Products> productTable;
    @FXML
    private Button newProductButton;
    @FXML
    private TableColumn<Products, String> productName;
    @FXML
    private TableColumn<Products, Float> productPrice;
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
    @FXML
    private TableColumn<?, ?> employeeClass;
    @FXML
    private TableColumn<?, ?> employeeName;
    @FXML
    private TableView<?> employeeTable;

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
    void displayEmployees(ActionEvent event) {
        this.productsButton.setSelected(false);
        productsButton.setDisable(false);
        this.shiftsButton.setSelected(false);
        shiftsButton.setDisable(false);
        this.employeePane.toFront();
        employeesButton.setDisable(true);
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
    public void refreshProductsTable() throws InterruptedException {
        retrofitInit.getProducts(this.listCallback);
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

    Callback<List<Products>> listCallback = new Callback<>() {
        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
            List<Products> productsList = response.body();

            assert productsList != null;

            ObservableList<Products> productsObservableList = FXCollections.observableList(productsList);

            MainScreenController.this.productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            MainScreenController.this.productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
            MainScreenController.this.productTable.setItems(productsObservableList);
            productTable.refresh();
        }

        public void onFailure(Call<List<Products>> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    @FXML
    void closeApp (ActionEvent event) throws IOException{
        Platform.exit();
    }

    @FXML void logOut(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("LoginScreen.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void minimizeApp(ActionEvent event) {
        Stage stage = (Stage) minimizeAppButton.getScene().getWindow();
        stage.setIconified(true);
    }

}
