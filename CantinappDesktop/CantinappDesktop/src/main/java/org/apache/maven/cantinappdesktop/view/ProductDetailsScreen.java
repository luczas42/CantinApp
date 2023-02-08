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
import org.apache.maven.cantinappdesktop.model.Products;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;
import java.nio.ByteBuffer;

public class ProductDetailsScreen {

    private Image productImage;
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

    Products myProduct = null;

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
        File selectedFile = fileChooser.showOpenDialog(stage);

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
        Products products;
        String productName = productNameField.getText();
        Float productPrice = Float.valueOf(productPriceField.getText());
        if(productImageView.getImage()!=null){
            byte[] image = imageToByteArray();
            products = new Products(productName, productPrice, image);
        }else{
            products = new Products(productName, productPrice);
        }
        retrofitInit.addProducts(addProductCallback, products);

        Stage stage = (Stage) productRegisterButton.getScene().getWindow();
        stage.close();
    }

    private byte[] imageToByteArray() {
        Image image = productImageView.getImage();

        int w = (int) image.getWidth();
        int h = (int) image.getHeight();
        byte[] buffer = new byte[w * h * 4];
        PixelReader pxr = image.getPixelReader();
        pxr.getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buffer, 0, w*4);
        try{
            BufferedOutputStream bos = new BufferedOutputStream(new ByteArrayOutputStream());
            for(int i = 0; i<buffer.length; i+=4){
                bos.write(buffer[i + 2]);
                bos.write(buffer[i + 1]);
                bos.write(buffer[i]);
                bos.write(buffer[i + 3]);
            }
            bos.flush();
            bos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return new byte[0];
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

    public void productEdit(Products selectedProduct) {
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
