package org.apache.maven.cantinappdesktop.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.model.Image;
import org.apache.maven.cantinappdesktop.model.Products;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.IOException;

public class ProductDetailsScreen {

    private Image productImage;
    private Stage stage;
    @FXML
    private ImageView productImageView;

    RetrofitInit retrofitInit = new RetrofitInit();

    @FXML
    private Button addProductImageButton;

    @FXML
    private Button cancelProductEditButton;

    @FXML
    private Button productDeleteButton;

    @FXML
    private Button productEditButton;

    @FXML
    private Label productEditLabel;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private Button productRegisterButton;

    Products myProduct = null;

    Callback<Void> deleteProductCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful()) {
                System.out.println(response.code());
            }
            System.out.println(response.code());
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            System.out.println(t.getMessage());

        }
    };

    Callback<Products> addProductCallback = new Callback<Products>() {
        @Override
        public void onResponse(Call<Products> call, Response<Products> response) {
            if (!response.isSuccessful()) {
                System.out.println(response.code());
                return;
            }
            Products postResponse = response.body();
        }

        @Override
        public void onFailure(Call<Products> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    Callback<Products> editProductCallback = new Callback<Products>() {
        @Override
        public void onResponse(Call<Products> call, Response<Products> response) {
            if (!response.isSuccessful()) {
                System.out.println(response.code());
                return;
            }

            Products postResponse = response.body();
        }

        @Override
        public void onFailure(Call<Products> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    @FXML
    void addProductImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "IMG files (*.png, *.jpg, *.jpeg) ", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Select an Image");
        fileChooser.showOpenDialog(stage);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile!=null){
            productImage = new Image(selectedFile);
        }
    }

    @FXML
    void closeProductEditScreen(ActionEvent event) {
        Stage stage = (Stage) cancelProductEditButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        retrofitInit.deleteProduct(deleteProductCallback, myProduct.getId());
        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editProduct(ActionEvent event) {
        String productName = productNameField.getText();
        System.out.println(productName);
        float productPrice = Float.parseFloat(productPriceField.getText());
        myProduct.setName(productName);
        myProduct.setPrice(productPrice);
        retrofitInit.editProducts(editProductCallback, myProduct);
        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerProduct(ActionEvent event) throws IOException {
        String productName = productNameField.getText();
        System.out.println(productName);
        Float productPrice = Float.valueOf(productPriceField.getText());
        Products products = new Products(productName, productPrice);

        retrofitInit.addProducts(addProductCallback, products);

        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    public void productAdd() {
        productDeleteButton.setVisible(false);
        productDeleteButton.setManaged(false);
        productEditButton.setVisible(false);
        productEditButton.setManaged(false);
    }

    public void productEdit(Products selectedProduct) {
        productEditLabel.setText("Edição de Produto");
        productRegisterButton.setVisible(false);
        productRegisterButton.setManaged(false);
        myProduct = selectedProduct;
        productNameField.setText(selectedProduct.getName());
        productPriceField.setText(selectedProduct.getPrice().toString());
    }
}
