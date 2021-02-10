package by.bsuir.java.command.impl;

import by.bsuir.java.command.AddingOperation;
import by.bsuir.java.database.dao.impl.EmployeeDao;
import by.bsuir.java.model.Employee;

public class AddEmployeeOperation implements AddingOperation<Employee> {
    @Override
    public void execute(Employee employee) {
            new EmployeeDao().save(employee);
    }
}
