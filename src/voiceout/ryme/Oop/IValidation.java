package voiceout.ryme.Oop;

import javax.swing.JTextField;

public interface IValidation {
    // Adds a custom error message.
    void addCustomError(String message);
    // Validates an array of text fields (for example, checks for emptiness).
    boolean validateFields(JTextField... fields);
    // Displays accumulated error messages.
    boolean displayErrors();
}
