package by.bsuir.java.command.executor;

import by.bsuir.java.command.AddingOperation;

import java.util.ArrayList;
import java.util.List;

public class AddingOperationExecutor {
    private final List<AddingOperation> addingOperations
            = new ArrayList<>();

    public void executeOperation(AddingOperation addingOperation,Object obj) {
        addingOperations.add(addingOperation);
        addingOperation.execute(obj);
    }
}
