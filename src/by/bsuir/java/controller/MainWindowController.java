package by.bsuir.java.controller;


import by.bsuir.java.database.dao.impl.EmployeeDao;
import by.bsuir.java.database.dao.impl.ProjectDao;
import by.bsuir.java.database.dao.impl.TaskDao;
import by.bsuir.java.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainWindowController implements Initializable {

    public TableView<Wrapper> tasksTableView;
    public TableColumn<Wrapper,String> projectNameColumn;
    public TableColumn<Wrapper,String> taskNameColumn;
    public TableColumn<Wrapper,String> beginColumn;
    public TableColumn<Wrapper,String> endColumn;
    public TableColumn<Wrapper,String> employeeColumn;
    public TableColumn<Wrapper,String> laborColumn;
    public Text remainingTimeText;
    public Text currentLaborText;
    public Text neededLaborColumn;

    public LineChart tasksChart;
    public TabPane tasksTabPane;
    public Tab mainTab;
    public Tab addTab;
    public Tab graphTab;

    public AnchorPane mainTabAnchorPane;
    public AnchorPane addTabAnchorPane;
    public AnchorPane graphTabAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTasksTable();
        remainingTimeText.setText("");
        currentLaborText.setText("");
        neededLaborColumn.setText("");

        showTasksChart();
    }


    private void fillTasksTable()
    {
        ObservableList<Task> tasks = FXCollections.observableArrayList(new TaskDao().getAll());
        ObservableList<Wrapper> wrappers = FXCollections.observableArrayList();
        Wrapper wrapper;

        for (Task task:tasks
        ) {
            wrapper = new Wrapper();
            wrapper.setTask(task);
            Employee employee = new EmployeeDao().get(task.getEmployeeID());
            wrapper.setEmployee(employee);
            Project project = new ProjectDao().get(task.getProjectID());
            wrapper.setProject(project);
            wrappers.add(wrapper);
        }

        projectNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getProject().getName())));
        taskNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getTask().getName())));
        beginColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTask().getStartDate().toString()));
        endColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTask().getEndDate().toString()));
        employeeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmployee().getSurname() + " " + cellData.getValue().getEmployee().getName()));
        laborColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getEmployee().getLaborInput())));
        tasksTableView.setItems(wrappers);
    }

    public void tasksTableClicked(MouseEvent mouseEvent) {
        Wrapper wrapper = tasksTableView.getSelectionModel().getSelectedItem();
        currentLaborText.setText(String.valueOf(wrapper.getEmployee().getLaborInput()));
        long remaining = wrapper.getTask().getEndDate().getTime() - wrapper.getTask().getStartDate().getTime();

        remainingTimeText.setText(formatInterval(remaining));
        long days = TimeUnit.MILLISECONDS.toHours(remaining)/24;
        neededLaborColumn.setText(String.valueOf(days/new ProjectDao().getAll().size()));
    }

    private static String formatInterval(final long l)
    {
        final long hr = TimeUnit.MILLISECONDS.toHours(l);
        final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        return String.format("%02d days:%02d minutes:%02d seconds", hr, min, sec);
    }

    private void showTasksChart() {

        ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
        EmployeeDao dao = new EmployeeDao();
        List<Employee> employees = FXCollections.observableArrayList(dao.getAll());

        for (Employee employee: employees)
        {
            boolean flag = false;
            ObservableList<XYChart.Data> aList = FXCollections.observableArrayList();
            aList.add(new XYChart.Data<>(employee.getSurname(),employee.getLaborInput()));
            for (XYChart.Series series:seriesList)
            {
                if (employee.getSurname().equals(series.getName()))
                {
                    flag = true;
                }
            }
            if (!flag)
            {
                seriesList.add(new XYChart.Series(employee.getSurname(), aList));
            }
        }
        tasksChart.getXAxis().setLabel("Worker");
        tasksChart.getYAxis().setLabel("WorkLabor");
        tasksChart.setData(seriesList);
    }

    public void showGraphTab(Event event) {
        showTasksChart();
    }

    public void showMainTab(Event event) {
        fillTasksTable();
    }
}
