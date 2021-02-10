package by.bsuir.java.observer.impl;

import by.bsuir.java.observer.Field;
import javafx.scene.control.TextField;

public class FieldImpl implements Field {

    private TextField textField;

    public FieldImpl(TextField textField) {
        this.textField = textField;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void update() {
        textField.clear();
    }
}
