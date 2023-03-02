package org.apache.maven.cantinappdesktop.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.ApiResponse;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.viewmodel.ProductDetailsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;

public class ProductDetailsScreen {

    private final ObservableList<String> typeList = FXCollections.observableArrayList("Salgado", "Doce", "Caseiro");
    private Image productImage;
    private int selectedProductId;
    private File selectedFile;
    private Stage stage;
    private Parent root;
    ProductDetailsViewModel viewModel = new ProductDetailsViewModel();
    @FXML
    private ImageView productImageView;
    @FXML
    private Button addProductImageButton;

    @FXML
    private ComboBox<String> selectProductTypeComboBox;

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

    Product myProduct = null;

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

    Callback<Product> addProductCallback = new Callback<Product>() {
        @Override
        public void onResponse(Call<Product> call, Response<Product> response) {
            if (!response.isSuccessful()) {
                System.out.println(response.code());

            }
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    Callback<ApiResponse> editProductCallback = new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            if (response.body().isSuccess()) {
                System.out.println(response.code() + " Sucesso: " + response.body().getMessage());

            } else {
                System.out.println("Erro: " + response.body().getMessage());
            }
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            System.out.println("???");
            System.out.println(t.getMessage());
        }
    };

    public ProductDetailsScreen() {
    }

    @FXML
    void addProductImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("IMG files (*.png, *.jpg, *.jpeg) ", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Select an Image");
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            productImage = new Image(selectedFile.getAbsolutePath());
            productImageView.setImage(productImage);
        }
    }

    @FXML
    void closeProductEditScreen(ActionEvent event) {
        Stage stage = (Stage) cancelProductEditButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        viewModel.deleteProduct(myProduct.getId(), deleteProductCallback);
        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editProduct(ActionEvent event) {
        String productName = productNameField.getText();
        Float productPrice = Float.valueOf(productPriceField.getText());
        int type = selectProductTypeComboBox.getSelectionModel().getSelectedIndex() + 1;
        if (selectedFile != null) {
            Product products = new Product(productName, productPrice, type, selectedFile);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), selectedFile);
            MultipartBody.Part file = MultipartBody.Part.createFormData("image", selectedFile.getName(), requestBody);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), products.getName());
            RequestBody price = RequestBody.create(MediaType.parse("text/plain"), products.getPrice().toString());
            RequestBody productType = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(products.getProductType()));
            RequestBody productId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(selectedProductId));
            System.out.println(name.toString());
            viewModel.editProducts(name, price, productType, productId, file, editProductCallback);
        } else {
            Product products = new Product(productName, productPrice, type);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), products.getName());
            RequestBody price = RequestBody.create(MediaType.parse("text/plain"), products.getPrice().toString());
            RequestBody productType = RequestBody.create(MediaType.parse("text/plain"), Integer.toString(products.getProductType()));
            RequestBody productId = RequestBody.create(MediaType.parse("text/plain"), Integer.toString(selectedProductId));
            System.out.println(products.getName() + " - " + products.getPrice().toString() + " - " + String.valueOf(products.getProductType()) + " - " + String.valueOf(selectedProductId));
            viewModel.editProducts(name, price, productType, productId, editProductCallback);
        }
        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerProduct() {
        if (!productNameField.getText().isEmpty()) {
            if (!productPriceField.getText().isEmpty()) {
                if (!selectProductTypeComboBox.getSelectionModel().isEmpty()) {
                    String productName = productNameField.getText();
                    Float productPrice = Float.valueOf(productPriceField.getText());
                    int type = selectProductTypeComboBox.getSelectionModel().getSelectedIndex() + 1;
                    if (selectedFile != null) {
                        Product products = new Product(productName, productPrice, type, selectedFile);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), selectedFile);
                        MultipartBody.Part file = MultipartBody.Part.createFormData("image", selectedFile.getName(), requestBody);
                        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), products.getName());
                        RequestBody price = RequestBody.create(MediaType.parse("text/plain"), products.getPrice().toString());
                        RequestBody productType = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(products.getProductType()));
                        viewModel.addProducts(name, price, productType, file, addProductCallback);
                        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
                        stage.close();
                    } else {
                        viewModel.addProducts(productName, productPrice, type, addProductCallback);
                        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
                        stage.close();
                    }

                }
            } else {
                productPriceField.requestFocus();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Digite um preço!");
                alert.setTitle("Registro de produto");
                alert.show();
            }
        } else {
            productNameField.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Digite um nome!");
            alert.setTitle("Registro de produto");
            alert.show();
        }
    }
    public void swichToProductAddScreen() {
        productDeleteButton.setVisible(false);
        productDeleteButton.setManaged(false);
        productEditButton.setVisible(false);
        productEditButton.setManaged(false);
        selectProductTypeComboBox.setItems(FXCollections.observableArrayList(typeList));
    }

    public void switchToProductEditScreen(Product selectedProduct) {
        productEditLabel.setText("Edição de Produto");
        productRegisterButton.setVisible(false);
        productRegisterButton.setManaged(false);
        selectProductTypeComboBox.setItems(FXCollections.observableArrayList(typeList));
        myProduct = selectedProduct;
        selectedProductId = selectedProduct.getId();
        if (selectedProduct.getImageName() != null) {
            byte[] imageData = Base64.getDecoder().decode(selectedProduct.getImageName());

            productImage = new Image(new ByteArrayInputStream(imageData));
            productImageView.setImage(productImage);
        }

        productNameField.setText(selectedProduct.getName());
        productPriceField.setText(selectedProduct.getPrice().toString());
        if (selectedProduct.getLiteralProductType().equalsIgnoreCase("Salgado")) {
            selectProductTypeComboBox.getSelectionModel().select(0);
        } else if (selectedProduct.getLiteralProductType().equalsIgnoreCase("Doce")) {
            selectProductTypeComboBox.getSelectionModel().select(1);
        } else if (selectedProduct.getLiteralProductType().equalsIgnoreCase("Caseiro")) {
            selectProductTypeComboBox.getSelectionModel().select(2);
        }
    }
}
