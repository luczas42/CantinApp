package org.apache.maven.cantinappdesktop.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetailsScreen {

    private Employee myEmployee = null;
    private ObservableList<String> classList = FXCollections.observableArrayList("INF4AM",
            "INF4AT",
            "REFRI4AM");
    private RetrofitInit retrofitInit = new RetrofitInit();
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
        String employeeName = employeeNameField.getText();
        String employeeClass = cbEmployeeClass.getSelectionModel().getSelectedItem().toString();

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), employeeName);
        RequestBody clasS = RequestBody.create(MediaType.parse("text/plain"), employeeClass);

        retrofitInit.addEmployee(addEmployeeCallback, name, clasS);
        Stage stage = (Stage) employeeRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editEmployee() {
        myEmployee.setName(employeeNameField.getText());
        myEmployee.setClasS(cbEmployeeClass.getSelectionModel().getSelectedItem().toString());

        retrofitInit.editEmployee(employeeEditCallback, myEmployee);
        Stage stage = (Stage) employeeRegisterButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteEmployee() {
        retrofitInit.deleteEmployee(deleteEmployeeCallback, myEmployee.getEmployee_Id());
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
            if(response.isSuccessful()){
                System.out.println("sucesso na response");
            }else{
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
            if(response.isSuccessful()){
                System.out.println("sucesso na response");
            }else{
                System.out.println("falha na response " + response.code());
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable throwable) {
            System.out.println("falha "+throwable.getMessage());
        }
    };

}
