package org.apache.maven.cantinappdesktop.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.model.Scale;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScaleDetailsScreen {

    private RetrofitInit retrofitInit = new RetrofitInit();
    private Scale myScale;
    private List<Employee> employeeComboBoxList = new ArrayList<>();
    private List<String> employeeNameList = new ArrayList<>();
    private List<Employee> employeeTableViewList = new ArrayList<>();
    private ObservableList<String> classList = FXCollections.observableArrayList("INF4AM", "INF4AT", "REFRI4AM");
    private ObservableList<String> periodList = FXCollections.observableArrayList("Manhã", "Tarde", "Noite");
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
    private ComboBox<String> selectPeriodComboBox;
    @FXML
    private ComboBox<String> selectEmployeeComboBox;
    @FXML
    private TableView<Employee> employeeListTableView;
    @FXML
    private TableColumn<Employee, String> employeeListTableColumn;
    @FXML
    private Button btAddEmployeeToScale;


    public void swichToScaleAddScreen() {
        scaleDeleteButton.setVisible(false);
        scaleDeleteButton.setManaged(false);
        scaleEditButton.setVisible(false);
        scaleEditButton.setManaged(false);

        selectClassComboBox.setItems(FXCollections.observableArrayList(classList));
        selectPeriodComboBox.setItems(FXCollections.observableArrayList(periodList));
    }

    @FXML
    private void onSelectEmployee() {

    }

    @FXML
    private void onSelectClass() {
        System.out.println("a");
        selectEmployeeComboBox.getItems().clear();
        employeeComboBoxList.clear();
        employeeTableViewList.clear();
        employeeListTableView.getItems().clear();
        employeeListTableView.refresh();
        getEmployeesFromSelectedClass(selectClassComboBox.getSelectionModel().getSelectedItem());

    }

    private void getEmployeesFromSelectedClass(String clasS) {

        Callback<List<Employee>> employeesCallBack = new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    employeeComboBoxList.addAll(response.body());
                    for (Employee employee : response.body()) {
                        employeeNameList.add(employee.getName());
                    }
                    ObservableList<String> employeeComboBoxObservableList = FXCollections.observableList(employeeNameList);
                    selectEmployeeComboBox.setItems(employeeComboBoxObservableList);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable throwable) {

            }
        };

        retrofitInit.getEmployeesWithClass(employeesCallBack, clasS);
    }

    public void switchToScaleEditScreen(Scale selectedScale) {
        dayEditLabel.setText("Edição de Produto");
        scaleRegisterButton.setVisible(false);
        scaleRegisterButton.setManaged(false);

        myScale = selectedScale;

        /// ArrayList used to store the employee names for the comboBox
        ArrayList<String> employeeNameList = new ArrayList<>();

        for (Employee employee : myScale.getEmployeeList()) {
            employeeNameList.add(employee.getName());
        }

        dayTextField.setText(myScale.getDay().toString());
        selectClassComboBox.setItems(FXCollections.observableArrayList(myScale.getClasS()));
//        selectEmployeeComboBox.setItems(FXCollections.observableArrayList(employeeNameList));

    }

    @FXML
    void closeDetailsScreen(ActionEvent event) {
        Stage stage = (Stage) closeScaleDetailsScreenButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addEmployeeToScale() {
        for (Employee employee : employeeComboBoxList) {
            if (employee.getName().equals(selectEmployeeComboBox.getSelectionModel().getSelectedItem())) {
                employeeTableViewList.add(employee);
            }
        }

        ObservableList<Employee> employeeObservableList = FXCollections.observableList(employeeTableViewList);
        employeeListTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        employeeListTableView.setItems(employeeObservableList);
    }

    @FXML
    void editScale() {

    }

    @FXML
    void deleteScale() {

    }

    @FXML
    void registerScale() {
        if (!dayTextField.getCharacters().isEmpty()) {
            if (!selectClassComboBox.getSelectionModel().isEmpty()) {
                if (!selectPeriodComboBox.getSelectionModel().isEmpty()) {
                    if (!employeeTableViewList.isEmpty()) {
                        String day = dayTextField.getText();
                        int period = transformPeriod(selectPeriodComboBox.getSelectionModel().getSelectedItem());
                        String clasS = selectClassComboBox.getSelectionModel().getSelectedItem();
                        int []employeeArray = arrayConverter(employeeTableViewList);
                        apiCall(day, period, clasS, employeeArray);

                        Stage stage = (Stage) scaleRegisterButton.getScene().getWindow();
                        stage.close();
                    } else {
                        System.out.println("erro");
                    }
                }
            }
        }

    }

    private void apiCall(String string_day, int int_period, String string_class, int[] int_employeeArray) {
//        RequestBody day = RequestBody.create(MediaType.parse("text/plain"), string_day);
//        RequestBody period = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(int_period));
//        RequestBody clasS = RequestBody.create(MediaType.parse("text/plain"), string_class);
//        retrofitInit.addScale(scaleCallback, day, period, clasS, int_employeeArray);
        retrofitInit.addScale(scaleCallback, string_day, int_period, string_class, int_employeeArray);
    }

    Callback<Scale> scaleCallback = new Callback<Scale>() {
        @Override
        public void onResponse(Call<Scale> call, Response<Scale> response) {
            if (response.isSuccessful()){
                System.out.println("sucesso na response");
            }else{
                System.out.println("falha na response");
            }
        }

        @Override
        public void onFailure(Call<Scale> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };

    private int[] arrayConverter(List<Employee> oldList) {
        int i = 0;
        int[] intArray = new int[oldList.size()];
        for (Employee employee :
                oldList) {
            intArray[i] = employee.getEmployeeId();
            System.out.println(intArray[i]);
            i++;

        }
        return intArray;
    }

    private int transformPeriod(String selectedItem) {
        if (selectedItem.equals("Manhã")) {
            return 1;
        } else if (selectedItem.equals("Tarde")) {
            return 2;
        } else {
            return 3;
        }
    }

}
