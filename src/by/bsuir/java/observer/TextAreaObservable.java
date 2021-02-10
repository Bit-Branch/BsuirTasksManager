package by.bsuir.java.observer;

import by.bsuir.java.observer.impl.FieldImpl;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class TextAreaObservable {
    private List<Field> textFields = new ArrayList<>();

    public void addObserver(TextField textField) {
        this.textFields.add(new FieldImpl(textField));
    }

    public void clear() {
        for (Field textField : this.textFields) {
            textField.update();
        }
    }
}