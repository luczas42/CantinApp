package org.apache.maven.cantinappdesktop.view;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.maven.cantinappdesktop.model.Employee;

public class EmployeeDetailsScreen {

    private Employee myEmployee = null;
    @FXML
    private ComboBox cbEmployeeClass;
    @FXML
    private TextField employeeNameField;
    @FXML
    private Button employeeEditButton;
    @FXML
    private Button employeeRegisterButton;
    @FXML
    private Button employeeDeleteButton;
    @FXML
    private Button cancelEmployeeEditButton;
    ////
    //// ALTERNATE BETWEEN EDIT AND ADD POPUP
    ////
    public void employeeAdd() {
        employeeDeleteButton.setVisible(false);
        employeeDeleteButton.setManaged(false);
        employeeEditButton.setVisible(false);
        employeeEditButton.setManaged(false);
    }

    public void employeeEdit(Employee selectedEmployee) {
        employeeNameField.setText("Edição de Produto");
        employeeRegisterButton.setVisible(false);
        employeeRegisterButton.setManaged(false);

        myEmployee = selectedEmployee;
        employeeNameField.setText(selectedEmployee.getName());
        cbEmployeeClass.setItems(new ImmutableObservableList("INF4AM", "INF4AT", "REFRI4AM"));
    }

}
