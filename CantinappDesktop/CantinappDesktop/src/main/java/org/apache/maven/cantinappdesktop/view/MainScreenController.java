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
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.model.Scale;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainScreenController {

    RetrofitInit retrofitInit = new RetrofitInit();

    /// depois de testar, ver se precisa dos três
    ScheduledExecutorService productsRefreshExecutor = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService scaleRefreshExecutor;
    ScheduledExecutorService employeeRefreshExecutor = Executors.newSingleThreadScheduledExecutor();

//    List<Scale> turnList = new ArrayList<>();
//    List<Scale> auxScaleList = new ArrayList<>();

    List<Scale> scaleList = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button minimizeAppButton;
    @FXML
    private Button newEmployeeButton;
    @FXML
    private Button newScaleButton;
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
    private TableView<Product> productsTableView;
    @FXML
    private Button newProductButton;
    @FXML
    private TableColumn<Product, String> productNameTableColumn;
    @FXML
    private TableColumn<Product, Float> productPriceTableColumn;
    @FXML
    private TableColumn<Scale, String> scaleClassTableColumn;

    @FXML
    private TableColumn<Scale, String> scaleDateTableColumn;

    @FXML
    private TableColumn<Employee, String> scaleEmployeeTableColumn;

    @FXML
    private TableColumn<Scale, String> scalePeriodTableColumn;

    @FXML
    private TableView<Scale> scaleTableView;
    @FXML
    private TableColumn<Employee, String> employeeClassTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeNameTableColumn;
    @FXML
    private TableView<Employee> employeeTableView;


    ////
    //// TABLE DISPLAYING FUNCTIONS: PRODUCTS, EMPLOYEES AND SCALES
    ////
    @FXML
    void displayProducts(ActionEvent event) {
        this.employeesButton.setSelected(false);
        employeesButton.setDisable(false);
        this.shiftsButton.setSelected(false);
        shiftsButton.setDisable(false);
        this.productsPane.toFront();
        productsButton.setDisable(true);
        System.out.println("entrou");
//        productsRefreshExecutor.scheduleAtFixedRate(() -> {
//            try {
//                refreshProductsTable();
//                System.out.println("entrou");
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, 0, 4, TimeUnit.SECONDS);
        try {
            refreshProductsTable();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void displayEmployees(ActionEvent event) {
        this.productsButton.setSelected(false);
        productsButton.setDisable(false);
        this.shiftsButton.setSelected(false);
        shiftsButton.setDisable(false);
        this.employeePane.toFront();
        employeesButton.setDisable(true);
        try {
            refreshEmployeeTable();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        productsRefreshExecutor.scheduleAtFixedRate(() -> {
//            try {
//                refreshProductsTable();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, 0, 2, TimeUnit.SECONDS);
    }

    @FXML
    void displayScales(ActionEvent event) {
        this.productsButton.setSelected(false);
        productsButton.setDisable(false);
        this.employeesButton.setSelected(false);
        employeesButton.setDisable(false);
        shiftsButton.setDisable(true);
        this.workdaysPane.toFront();
        try {
            refreshScalesTable();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        productsRefreshExecutor.scheduleAtFixedRate(() -> {
//            try {
//                refreshProductsTable();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, 0, 2, TimeUnit.SECONDS);
    }

    ////
    //// PRODUCT CLICKING ACTIONS (OPENS PRODUCT DETAILS SCREEN)
    ////

    @FXML
    void productClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ProductDetailsScreen.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load());
                stage.setTitle("Cantinapp");
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                ProductDetailsScreen productDetailsScreenController = fxmlLoader.getController();
                productDetailsScreenController.switchToProductEditScreen(selectedProduct);
                Thread.sleep(100);
                stage.showAndWait();
                productsRefreshExecutor.notify();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void openNewProductScreen(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ProductDetailsScreen.fxml"));
            root = fxmlLoader.load();
            Scene scene = new Scene((Parent) root);
            stage.setTitle("Cantinapp");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            ProductDetailsScreen productDetailsScreenController = fxmlLoader.getController();
            productDetailsScreenController.swichToProductAddScreen();
            stage.showAndWait();
            refreshProductsTable();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    @FXML
//    void employeeCLick(MouseEvent event){
//        if (event.getClickCount() == 2) {
//            Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
//            try {
//                Stage stage = new Stage();
//                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EmployeeDetailsScreen.fxml"));
//                Scene scene = new Scene((Parent) fxmlLoader.load());
//                stage.setTitle("Cantinapp");
//                stage.setScene(scene);
//                stage.initStyle(StageStyle.UNDECORATED);
//                stage.initModality(Modality.APPLICATION_MODAL);
//                EmployeeDetailsScreen employeeDetailsScreen = fxmlLoader.getController();
//                employeeDetailsScreen.switchToEmployeeEditScreen(selectedEmployee);
//                Thread.sleep(100);
//                stage.showAndWait();
//                productsRefreshExecutor.notify();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    @FXML
    void openNewEmployeeScreen(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EmployeeDetailsScreen.fxml"));
            root = fxmlLoader.load();
            Scene scene = new Scene((Parent) root);
            stage.setTitle("Cantinapp");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            EmployeeDetailsScreen employeeDetailsScreenController = fxmlLoader.getController();
            employeeDetailsScreenController.switchToEmployeeAddScreen();
            stage.showAndWait();
//            refreshProductsTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void scaleClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Scale selectedScale = scaleTableView.getSelectionModel().getSelectedItem();
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ScaleDetailsScreen.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load());
                stage.setTitle("Cantinapp");
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                ScaleDetailsScreen scaleDetailsScreen = fxmlLoader.getController();
                scaleDetailsScreen.switchToScaleEditScreen(selectedScale);
                Thread.sleep(100);
                stage.showAndWait();
//                productsRefreshExecutor.notify();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void openNewScaleScreen(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ScaleDetailsScreen.fxml"));
            root = fxmlLoader.load();
            Scene scene = new Scene((Parent) root);
            stage.setTitle("Cantinapp");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            ScaleDetailsScreen scaleDetailsScreenController = fxmlLoader.getController();
            scaleDetailsScreenController.swichToScaleAddScreen();
            stage.showAndWait();
//            refreshProductsTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////
    //// REFRESHING THE PRODUCTS TABLE
    ////

    public void refreshProductsTable() throws InterruptedException {
        retrofitInit.getProducts(this.productCallback);
    }

    public void refreshEmployeeTable() throws InterruptedException {
        retrofitInit.getEmployees(this.employeeCallback);
    }

    public void refreshScalesTable() throws InterruptedException {
        retrofitInit.getScales(this.scalesCallback);
    }


    ////
    //// RETURNING PRODUCTS FROM API
    ////


    Callback<List<Product>> productCallback = new Callback<>() {
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            List<Product> productList = response.body();
            assert productList != null;

            ObservableList<Product> productObservableList = FXCollections.observableList(productList);

            MainScreenController.this.productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            MainScreenController.this.productPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
            MainScreenController.this.productsTableView.setItems(productObservableList);
            productsTableView.refresh();
        }

        public void onFailure(Call<List<Product>> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    Callback<List<Employee>> employeeCallback = new Callback<>() {
        public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
            List<Employee> employeeList = response.body();

            assert employeeList != null;

            ObservableList<Employee> employeeObservableList = FXCollections.observableList(employeeList);

            MainScreenController.this.employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            MainScreenController.this.employeeClassTableColumn.setCellValueFactory(new PropertyValueFactory<>("ClasS"));
            MainScreenController.this.employeeTableView.setItems(employeeObservableList);
            productsTableView.refresh();
        }

        public void onFailure(Call<List<Employee>> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    Callback<List<Scale>> scalesCallback = new Callback<List<Scale>>() {
        @Override
        public void onResponse(Call<List<Scale>> call, Response<List<Scale>> response) {

            scaleList = response.body();
            assert scaleList != null;

            ObservableList<Scale> scaleObservableList = FXCollections.observableList(scaleList);

            for (Scale scale :
                    scaleObservableList) {
                scale.createNameString();
            }
            MainScreenController.this.scaleClassTableColumn.setCellValueFactory(new PropertyValueFactory<>("ClasS"));
            MainScreenController.this.scaleDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Day"));
            MainScreenController.this.scalePeriodTableColumn.setCellValueFactory(new PropertyValueFactory<>("Period"));
            MainScreenController.this.scaleEmployeeTableColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeNamesString"));
            MainScreenController.this.scaleTableView.setItems(scaleObservableList);
            scaleTableView.refresh();

        }

        @Override
        public void onFailure(Call<List<Scale>> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };

    ////
    //// ADDITIONAL BUTTON ACTIONS (LOGOUT, CLOSE AND MINIMIZE)
    ////

    @FXML
    void closeApp(ActionEvent event) throws IOException {
        Platform.exit();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("LoginScreen.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
