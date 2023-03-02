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
import org.apache.maven.cantinappdesktop.viewmodel.ScaleDetailsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScaleDetailsScreen {

    private ScaleDetailsViewModel viewModel = new ScaleDetailsViewModel();
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
        dayTextField.setEditable(true);

        selectClassComboBox.setItems(FXCollections.observableArrayList(classList));
        selectPeriodComboBox.setItems(FXCollections.observableArrayList(periodList));
    }

    @FXML
    private void onSelectEmployee() {

    }

    @FXML
    private void onSelectClass() {
        selectEmployeeComboBox.getItems().clear();
        employeeComboBoxList.clear();
        employeeTableViewList.clear();
        employeeListTableView.getItems().clear();
        employeeListTableView.refresh();
        getEmployeesFromSelectedClass(selectClassComboBox.getSelectionModel().getSelectedItem());

    }

    private void getEmployeesFromSelectedClass(String _class) {

        Callback<List<Employee>> getEmployeesCallback = new Callback<List<Employee>>() {
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

        viewModel.getEmployeesWithClass(_class, getEmployeesCallback);
    }

    public void switchToScaleEditScreen(Scale selectedScale) {
        dayEditLabel.setText("Detalhes de Escala");
        dayTextField.setEditable(false);
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
        classTextField.setText(myScale.get_class());
        periodTextField.setText(myScale.getPeriod());
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
        viewModel.deleteScale(myScale.getId(), deleteScaleCallback);
        Stage stage = (Stage) closeScaleDetailsScreenButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerScale() throws ParseException {
        if (checkDayFormat() != null) {
            if (!selectPeriodComboBox.getSelectionModel().isEmpty()) {
                if (!selectClassComboBox.getSelectionModel().isEmpty()) {
                    if (!employeeTableViewList.isEmpty()) {
                        String day = checkDayFormat();
                        System.out.println(day);
                        String period = selectPeriodComboBox.getSelectionModel().getSelectedItem();
                        String _class = selectClassComboBox.getSelectionModel().getSelectedItem();
                        List<Integer> idList = getEmployeeIdList(employeeTableViewList);
                        viewModel.addScale(day, period, _class, idList, scaleCallback);
                        Stage stage = (Stage) scaleRegisterButton.getScene().getWindow();
                        stage.close();
                    } else {
                        selectEmployeeComboBox.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Adicione ao menos um aluno!");
                        alert.setTitle("Registro de escala");
                        alert.show();
                    }
                } else {
                    selectClassComboBox.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Selecione uma turma!");
                    alert.setTitle("Registro de escala");
                    alert.show();
                }
            } else {
                selectPeriodComboBox.requestFocus();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Selecione um turno!");
                alert.setTitle("Registro de escala");
                alert.show();
            }
        } else {
            dayTextField.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Informe um dia válido!");
            alert.setTitle("Registro de escala");
            alert.show();
        }
    }

    String checkDayFormat() throws ParseException {
        SimpleDateFormat firstFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat secondFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = secondFormat.parse(dayTextField.getText());
        String formattedDate = firstFormat.format(date);
        return formattedDate;
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

            employeeIdList.add(employee.getId());

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

}
