package org.apache.maven.cantinappdesktop.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.model.Scale;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ScaleDetailsScreen {

    private Scale myScale;
    @FXML
    private Button scaleDeleteButton;
    @FXML
    private Button scaleEditButton;
    @FXML
    private Button scaleRegisterButton;
    @FXML
    private Button closeScaleDetailsScreenButton;
    @FXML
    private Label dayEditLabel;
    @FXML
    private TextField dayTextField;
    @FXML
    private ComboBox<String> selectClassComboBox;
    @FXML
    private ComboBox<String> selectTurnComboBox;
    @FXML
    private ComboBox<String> selectEmployeeComboBox;
    @FXML
    private TableView<String> employeeListTableView;
    @FXML
    private TableColumn<Employee, String> employeeListTableColumn;


    public void swichToScaleAddScreen() {
        scaleDeleteButton.setVisible(false);
        scaleDeleteButton.setManaged(false);
        scaleEditButton.setVisible(false);
        scaleEditButton.setManaged(false);
    }

    public void switchToScaleEditScreen(Scale selectedScale) {
        dayEditLabel.setText("Edição de Produto");
        scaleRegisterButton.setVisible(false);
        scaleRegisterButton.setManaged(false);

        myScale = selectedScale;

        /// ArrayList used to store the employee names for the comboBox
        ArrayList<String> employeeNameList = new ArrayList<>();

        for (Employee employee: myScale.getEmployeeList()) {
            employeeNameList.add(employee.getName());
        }

        System.out.println(employeeNameList);

        dayTextField.setText(myScale.getTurn().getDate());
        selectClassComboBox.setItems(FXCollections.observableArrayList(myScale.getTurn().getClasS()));
        selectEmployeeComboBox.setItems(FXCollections.observableArrayList(employeeNameList));

    }

    @FXML
    void closeDetailsScreen(ActionEvent event) {
        Stage stage = (Stage) closeScaleDetailsScreenButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addEmployeeToScale(){

    }
    @FXML
    void editScale(){

    }
    @FXML
    void deleteScale(){

    }
    @FXML
    void registerScale(){

    }

}
