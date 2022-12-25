package org.apache.maven.cantinappdesktop.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.apache.maven.cantinappdesktop.data.service.Products;
import org.apache.maven.cantinappdesktop.data.service.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class FXMLController {
    @FXML
    private Button configButton;
    @FXML
    private ToggleButton employeesButton;
    @FXML
    private Button exitButton;
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
    Callback<List<Products>> listCallback = new Callback<>() {
        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
            List<Products> productsList = response.body();

            assert productsList != null;

            ObservableList<Products> productsObservableList = FXCollections.observableList(productsList);

            for (Products products : productsList) {
                System.out.println(products.name);
            }

            FXMLController.this.productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            FXMLController.this.productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
            FXMLController.this.productTable.setItems(productsObservableList);
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
        this.shiftsButton.setSelected(false);
        this.startPane.toFront();
    }

    @FXML
    void displayProducts(ActionEvent event) {
        this.employeesButton.setSelected(false);
        this.shiftsButton.setSelected(false);
        this.productsPane.toFront();
        RetrofitInit retrofitInit = new RetrofitInit();
        retrofitInit.getProducts(this.listCallback);
    }

    @FXML
    void displayShifts(ActionEvent event) {
    }

    @FXML
    void closeApplication(ActionEvent event) {
        Platform.exit();
    }
}

