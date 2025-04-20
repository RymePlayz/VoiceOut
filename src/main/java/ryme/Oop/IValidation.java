package ryme.Oop;

import javax.swing.JTextField;

public interface IValidation {
    void addCustomError(String message);
    boolean validateFields(JTextField... fields);
    boolean displayErrors();
}
