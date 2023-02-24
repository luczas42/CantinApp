package org.apache.maven.cantinappdesktop.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.model.Scale;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
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
    private TextField classTextField;
    @FXML
    private TextField periodTextField;
    @FXML
    private TableColumn<Employee, String> employeeListTableColumn;
    @FXML
    private Button btAddEmployeeToScale;


    public void swichToScaleAddScreen() {
        scaleDeleteButton.setVisible(false);
        scaleDeleteButton.setManaged(false);
        periodTextField.setVisible(false);
        periodTextField.setDisable(true);
        classTextField.setDisable(true);
        classTextField.setVisible(false);

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
        dayEditLabel.setText("Detalhes de Escala");
        scaleRegisterButton.setVisible(false);
        scaleRegisterButton.setManaged(false);
        selectClassComboBox.setDisable(true);
        selectClassComboBox.setVisible(false);
        selectPeriodComboBox.setDisable(true);
        selectPeriodComboBox.setVisible(false);
        selectEmployeeComboBox.setDisable(true);
        selectEmployeeComboBox.setVisible(false);
        btAddEmployeeToScale.setDisable(true);
        btAddEmployeeToScale.setVisible(false);

        myScale = selectedScale;

        dayTextField.setText(myScale.getDay());
        classTextField.setText(myScale.getClasS());
        periodTextField.setText(transformPeriodToString(myScale.getPeriod()));
        if (myScale.getEmployeeList() != null) {
            getEmployeeFromScale();
        }
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
    void getEmployeeFromScale() {
        employeeTableViewList.addAll(myScale.getEmployeeList());

        ObservableList<Employee> employeeObservableList = FXCollections.observableList(employeeTableViewList);
        employeeListTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        employeeListTableView.setItems(employeeObservableList);
    }

    @FXML
    void deleteScale() {
        retrofitInit.deleteScale(deleteScaleCallback, myScale.getTurn_id());
        Stage stage = (Stage) closeScaleDetailsScreenButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerScale() {
        if (!dayTextField.getCharacters().isEmpty()) {
            if (!selectClassComboBox.getSelectionModel().isEmpty()) {
                if (!selectPeriodComboBox.getSelectionModel().isEmpty()) {
                    if (!employeeTableViewList.isEmpty()) {
                        String day = dayTextField.getText();
                        int period = transformPeriodToInt(selectPeriodComboBox.getSelectionModel().getSelectedItem());
                        String clasS = selectClassComboBox.getSelectionModel().getSelectedItem();
                        List<Integer> idList = getEmployeeIdList(employeeTableViewList);
                        retrofitInit.addScale(scaleCallback, day, period, clasS, idList);
                        Stage stage = (Stage) scaleRegisterButton.getScene().getWindow();
                        stage.close();
                    } else {
                        selectEmployeeComboBox.isFocused();
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Erro: Preencha o campo de empregados!");
                        a.show();
                    }
                }
            }
        }

    }

    Callback<Scale> scaleCallback = new Callback<Scale>() {
        @Override
        public void onResponse(Call<Scale> call, Response<Scale> response) {
            if (response.isSuccessful()) {
                System.out.println("sucesso na response inserir");
            } else {
                System.out.println("falha na response inserir");
            }
        }

        @Override
        public void onFailure(Call<Scale> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
            System.out.println("erroooo inserir");
        }
    };

    Callback<Void> deleteScaleCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                System.out.println("sucesso na response deletar");
            } else {
                System.out.println("falha na response deletar");
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable throwable) {
            System.out.println(throwable.getMessage());
            System.out.println("erorooooo deletar");
        }
    };

    private List<Integer> getEmployeeIdList(List<Employee> oldList) {
        List<Integer> employeeIdList = new ArrayList<>();

        for (Employee employee : oldList) {

            employeeIdList.add(employee.getEmployee_Id());

        }
        return employeeIdList;
    }

    private int transformPeriodToInt(String selectedItem) {
        if (selectedItem.equals("Manhã")) {
            return 1;
        } else if (selectedItem.equals("Tarde")) {
            return 2;
        } else {
            return 3;
        }
    }

    private String transformPeriodToString(int period){
        if (period==1) {
            return "Manhã";
        } else if (period==2) {
            return "Tarde";
        } else {
            return "Noite";
        }
    }

}
