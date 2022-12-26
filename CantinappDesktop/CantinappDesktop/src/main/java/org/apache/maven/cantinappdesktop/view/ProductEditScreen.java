package org.apache.maven.cantinappdesktop.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductEditScreen {

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

    @FXML
    void addProductImage(ActionEvent event) {

    }

    @FXML
    void closeProductEditScreen(ActionEvent event) {
        Stage stage = (Stage) cancelProductEditButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    @FXML
    void editProduct(ActionEvent event) {

    }

    @FXML
    void registerProduct(ActionEvent event) {

    }

    public void checkIsEdit(Boolean isEdit){
        if (isEdit){
            productDeleteButton.setVisible(false);
            productDeleteButton.setManaged(false);
            productEditButton.setVisible(false);
            productEditButton.setManaged(false);
        }else {
            productEditLabel.setText("Edição de Produto");
            productRegisterButton.setVisible(false);
            productRegisterButton.setManaged(false);
        }
    }

}
