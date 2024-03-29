package org.apache.maven.cantinappdesktop.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.viewmodel.EmployeeDetailsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetailsScreen {

    private Employee myEmployee = null;
    private ObservableList<String> classList = FXCollections.observableArrayList("INF4AM", "INF4AT", "REFRI4AM");
    private EmployeeDetailsViewModel viewModel = new EmployeeDetailsViewModel();
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
    public void switchToEmployeeAddScreen() {
        employeeDeleteButton.setVisible(false);
        employeeDeleteButton.setManaged(false);
        employeeEditButton.setVisible(false);
        employeeEditButton.setManaged(false);

        cbEmployeeClass.setItems(classList);
    }

    public void switchToEmployeeEditScreen(Employee selectedEmployee) {
        employeeNameField.setText("Edição de Produto");
        employeeRegisterButton.setVisible(false);
        employeeRegisterButton.setManaged(false);

        myEmployee = selectedEmployee;
        employeeNameField.setText(selectedEmployee.getName());
        cbEmployeeClass.setItems(classList);
        switch (selectedEmployee.getClasS()) {
            case "INF4AM":
                cbEmployeeClass.getSelectionModel().select(0);
                break;
            case "INF4AT":
                cbEmployeeClass.getSelectionModel().select(1);
                break;
            case "REFRI4AM":
                cbEmployeeClass.getSelectionModel().select(2);
                break;
        }
    }

    @FXML
    void registerEmployee() {
        if (!employeeNameField.getText().isEmpty()) {
            if (!cbEmployeeClass.getSelectionModel().isEmpty()) {
                String employeeName = employeeNameField.getText();
                String employeeClass = cbEmployeeClass.getSelectionModel().getSelectedItem().toString();
                viewModel.addEmployee(employeeName, employeeClass, addEmployeeCallback);
                Stage stage = (Stage) employeeRegisterButton.getScene().getWindow();
                stage.close();

            } else {
                cbEmployeeClass.requestFocus();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Selecione uma turma!");
                alert.setTitle("Registro de empregado");
                alert.show();
            }
        } else {
            employeeNameField.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Digite um nome!");
            alert.setTitle("Registro de empregado");
            alert.show();
        }
    }

    @FXML
    void editEmployee() {
        if (!employeeNameField.getText().equals(myEmployee.getName()) ||
                !cbEmployeeClass.getSelectionModel().getSelectedItem().equals(myEmployee.getClasS())) {
            myEmployee.setName(employeeNameField.getText());
            myEmployee.set_class(cbEmployeeClass.getSelectionModel().getSelectedItem().toString());
            viewModel.editEmployee(myEmployee, employeeEditCallback);
        }
        Stage stage = (Stage) employeeRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteEmployee() {
        viewModel.deleteEmployee(myEmployee.getId(), deleteEmployeeCallback);
        Stage stage = (Stage) employeeRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void closeEmployeeEditScreen() {
        Stage stage = (Stage) cancelEmployeeEditButton.getScene().getWindow();
        stage.close();
    }

    Callback<Employee> addEmployeeCallback = new Callback<Employee>() {
        @Override
        public void onResponse(Call<Employee> call, Response<Employee> response) {
            if (!response.isSuccessful()) {
                System.out.println("falhou a response");
            } else {
                System.out.println("sucesso");
            }
        }

        @Override
        public void onFailure(Call<Employee> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    Callback<Employee> employeeEditCallback = new Callback<Employee>() {
        @Override
        public void onResponse(Call<Employee> call, Response<Employee> response) {
            if (response.isSuccessful()) {
                System.out.println("sucesso na response");
            } else {
                System.out.println("falha na response");
            }
        }

        @Override
        public void onFailure(Call<Employee> call, Throwable throwable) {
            System.out.println("falha");
            System.out.println(throwable.getMessage());
        }
    };

    Callback<Void> deleteEmployeeCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                System.out.println("sucesso na response");
            } else {
                System.out.println("falha na response " + response.code());
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable throwable) {
            System.out.println("falha " + throwable.getMessage());
        }
    };

}
