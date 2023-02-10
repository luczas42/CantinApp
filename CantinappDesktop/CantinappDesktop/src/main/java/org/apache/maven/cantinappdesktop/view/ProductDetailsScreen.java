package org.apache.maven.cantinappdesktop.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;

public class ProductDetailsScreen {

    private Image productImage;

    private File selectedFile;
    private Stage stage;
    private Parent root;
    RetrofitInit retrofitInit = new RetrofitInit();
    @FXML
    private ImageView productImageView;
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

    Product myProduct = null;

    ////
    //// DELETE, EDIT AND ADD ENDPOINTS
    ////

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
                return;
            }
            Product postResponse = response.body();
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            System.out.println(t.getMessage() + t.getCause());
            t.printStackTrace();
        }
    };

    Callback<Product> editProductCallback = new Callback<Product>() {
        @Override
        public void onResponse(Call<Product> call, Response<Product> response) {
            if (!response.isSuccessful()) {
                System.out.println(response.code());
                return;
            }

            Product postResponse = response.body();
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    ////
    //// IMAGE PROCESS
    ////

    @FXML
    void addProductImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "IMG files (*.png, *.jpg, *.jpeg) ", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Select an Image");
        selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile!=null){
            productImage = new Image(selectedFile.getAbsolutePath());
            productImageView.setImage(productImage);
        }
    }

    ////
    //// POPUP CLICK ACTIONS
    ////

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
        Product product;
        String productName = productNameField.getText();
        Float productPrice = Float.valueOf(productPriceField.getText());
        if(selectedFile!=null){
            byte[] image = imageToByteArray();

//            products = new Products(productName, productPrice, image);
//            retrofitInit.addProducts(addProductCallback, products);
        }else{
            System.out.println("sem imagem");
//            products = new Products(productName, productPrice);
        }

        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    private byte[] imageToByteArray() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(selectedFile);

        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        return ( data.getData() );
    }

    ////
    //// ALTERNATE BETWEEN EDIT AND ADD POPUP
    ////
    public void productAdd() {
        productDeleteButton.setVisible(false);
        productDeleteButton.setManaged(false);
        productEditButton.setVisible(false);
        productEditButton.setManaged(false);
    }

    public void productEdit(Product selectedProduct) {
        productEditLabel.setText("Edição de Produto");
        productRegisterButton.setVisible(false);
        productRegisterButton.setManaged(false);

        myProduct = selectedProduct;
        if(myProduct.getImage()!=null){
            Image image = new Image(new ByteArrayInputStream(selectedProduct.getImage()));
            productImageView.setImage(image);
        }


        productNameField.setText(selectedProduct.getName());
        productPriceField.setText(selectedProduct.getPrice().toString());
    }
}
