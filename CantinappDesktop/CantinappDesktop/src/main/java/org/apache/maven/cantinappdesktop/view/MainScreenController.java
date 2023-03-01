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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.maven.cantinappdesktop.App;
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.model.Scale;
import org.apache.maven.cantinappdesktop.viewmodel.MainScreenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class MainScreenController {

    MainScreenViewModel mainScreenViewModel = new MainScreenViewModel();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button minimizeAppButton;
    @FXML
    private Button newRegisterButton;
    @FXML
    private Button tableRefreshButton;
    @FXML
    private ToggleButton employeesButton;
    @FXML
    private ToggleButton shiftsButton;
    @FXML
    private ToggleButton productsButton;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> productNameTableColumn;
    @FXML
    private TableColumn<Product, Float> productPriceTableColumn;
    @FXML
    private TableColumn<Scale, String> scaleClassTableColumn;

    @FXML
    private TableColumn<Scale, String> scaleDateTableColumn;

    @FXML
    private TableColumn<Scale, String> scaleEmployeeTableColumn;

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
    private String screenAtMoment;


    ////
    //// TABLE DISPLAYING FUNCTIONS: PRODUCTS, EMPLOYEES AND SCALES
    ////

    @FXML
    void displayProducts() {
        arrangingScreenElements("products");
        try {
            refreshProductsTable();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void displayEmployees() {
        arrangingScreenElements("employees");
        try {
            refreshEmployeeTable();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void displayScales() {
        arrangingScreenElements("scales");
        try {
            refreshScalesTable();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void arrangingScreenElements(String code) {
        switch (code) {
            case "products" -> {
                employeesButton.setSelected(false);
                employeesButton.setDisable(false);
                shiftsButton.setSelected(false);
                shiftsButton.setDisable(false);
                employeeTableView.setVisible(false);
                scaleTableView.setVisible(false);
                productsTableView.setVisible(true);
                productsButton.setDisable(true);
                screenAtMoment = "products";
                newRegisterButton.setVisible(true);
                newRegisterButton.setText("Cadastrar novo produto");
                tableRefreshButton.setVisible(true);
            }
            case "employees" -> {
                productsButton.setSelected(false);
                productsButton.setDisable(false);
                shiftsButton.setSelected(false);
                shiftsButton.setDisable(false);
                scaleTableView.setVisible(false);
                productsTableView.setVisible(false);
                employeeTableView.setVisible(true);
                employeesButton.setDisable(true);
                screenAtMoment = "employees";
                newRegisterButton.setVisible(true);
                newRegisterButton.setText("Cadastrar novo empregado");
                tableRefreshButton.setVisible(true);
            }
            case "scales" -> {
                productsButton.setSelected(false);
                productsButton.setDisable(false);
                employeesButton.setSelected(false);
                employeesButton.setDisable(false);
                productsTableView.setVisible(false);
                employeeTableView.setVisible(false);
                scaleTableView.setVisible(true);
                shiftsButton.setDisable(true);
                screenAtMoment = "scales";
                newRegisterButton.setVisible(true);
                newRegisterButton.setText("Cadastrar nova escala");
                tableRefreshButton.setVisible(true);
            }
        }
    }

    @FXML
    void openNewRegisterScreen() {
        switch (screenAtMoment) {
            case "products" -> {
                openNewProductScreen();
            }
            case "employees" -> {
                openNewEmployeeScreen();
            }
            case "scales" -> {
                openNewScaleScreen();
            }
        }
    }

    ////
    //// PRODUCT CLICKING ACTIONS (OPENS PRODUCT DETAILS SCREEN)
    ////

    @FXML
    void productClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId());
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
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void openNewProductScreen() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void employeeClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EmployeeDetailsScreen.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load());
                stage.setTitle("Cantinapp");
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                EmployeeDetailsScreen employeeDetailsScreen = fxmlLoader.getController();
                employeeDetailsScreen.switchToEmployeeEditScreen(selectedEmployee);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void openNewEmployeeScreen() {
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
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void openNewScaleScreen() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////
    //// REFRESHING THE PRODUCTS TABLE
    ////

    @FXML
    void refreshTable() throws InterruptedException {
        switch (screenAtMoment) {
            case "products" -> {
                refreshProductsTable();
            }
            case "employees" -> {
                refreshEmployeeTable();
            }
            case "scales" -> {
                refreshScalesTable();
            }
        }
    }

    public void refreshProductsTable() throws InterruptedException {
        mainScreenViewModel.getProducts(this.getProductsCallback);
    }

    public void refreshEmployeeTable() throws InterruptedException {
        mainScreenViewModel.getEmployees(this.getEmployeesCallback);
    }

    public void refreshScalesTable() throws InterruptedException {
        mainScreenViewModel.getScales(this.getScalesCallback);
    }

    Callback<List<Product>> getProductsCallback = new Callback<>() {
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            if (response.isSuccessful() && response.body() != null) {
                ObservableList<Product> productObservableList = FXCollections.observableList(response.body());
                setProductTableAttributes(productObservableList);
            }
        }

        public void onFailure(Call<List<Product>> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    private void setProductTableAttributes(ObservableList<Product> productObservableList) {
        MainScreenController.this.productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        for (Product product : productObservableList
        ) {
            DecimalFormat formatter = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.FRANCE));
            product.setFormattedPrice("R$ ".concat(formatter.format(product.getPrice())));
            MainScreenController.this.productPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("FormattedPrice"));
        }
        MainScreenController.this.productsTableView.setItems(productObservableList);
        productNameTableColumn.setResizable(false);
        productPriceTableColumn.setResizable(false);
        productsTableView.refresh();
    }

    Callback<List<Employee>> getEmployeesCallback = new Callback<>() {
        public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
            if (response.isSuccessful() && response.body() != null) {
                ObservableList<Employee> employeeObservableList = FXCollections.observableList(response.body());
                setEmployeeTableAttributes(employeeObservableList);
            }
        }

        public void onFailure(Call<List<Employee>> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    private void setEmployeeTableAttributes(ObservableList<Employee> employeeObservableList) {
        MainScreenController.this.employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        MainScreenController.this.employeeClassTableColumn.setCellValueFactory(new PropertyValueFactory<>("ClasS"));
        MainScreenController.this.employeeTableView.setItems(employeeObservableList);
        employeeNameTableColumn.setResizable(false);
        employeeClassTableColumn.setResizable(false);
        productsTableView.refresh();
    }

    Callback<List<Scale>> getScalesCallback = new Callback<List<Scale>>() {
        @Override
        public void onResponse(Call<List<Scale>> call, Response<List<Scale>> response) {
            if (response.isSuccessful() && response.body() != null) {
                ObservableList<Scale> scaleObservableList = FXCollections.observableList(response.body());
                setScalesTableAttributes(scaleObservableList);
            }
        }

        @Override
        public void onFailure(Call<List<Scale>> call, Throwable throwable) {
            System.out.println("aaa" + throwable.getMessage());
        }
    };

    private void setScalesTableAttributes(ObservableList<Scale> scaleObservableList) {
        MainScreenController.this.scaleClassTableColumn.setCellValueFactory(new PropertyValueFactory<>("_class"));
        MainScreenController.this.scaleDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        MainScreenController.this.scalePeriodTableColumn.setCellValueFactory(new PropertyValueFactory<>("period"));

        for (Scale scale : scaleObservableList) {
            if (scale.getEmployeeList() != null) {
                scale.createNameString();
                MainScreenController.this.scaleEmployeeTableColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeNamesString"));
            }
        }
        MainScreenController.this.scaleTableView.setItems(scaleObservableList);
        scaleClassTableColumn.setResizable(false);
        scaleDateTableColumn.setResizable(false);
        scalePeriodTableColumn.setResizable(false);
        scaleEmployeeTableColumn.setResizable(false);
        scaleTableView.refresh();
    }

    ////
    //// ADDITIONAL BUTTON ACTIONS (LOGOUT, CLOSE AND MINIMIZE)
    ////

    @FXML
    void closeApp() {
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
    void minimizeApp() {
        Stage stage = (Stage) minimizeAppButton.getScene().getWindow();
        stage.setIconified(true);
    }

}
