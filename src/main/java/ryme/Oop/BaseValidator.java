package ryme.Oop;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseValidator implements IValidation {
    // Protected so that subclasses can access and modify the error list.
    protected final List<String> errorMessages = new ArrayList<>();

    protected void addError(String message) {
        errorMessages.add(message);
    }

    @Override
    public void addCustomError(String message) {
        errorMessages.add(message);
    }

    // Default displayErrors method (can be overridden).
    @Override
    public boolean displayErrors() {
        if (!errorMessages.isEmpty()) {
            // Default: Show an error dialog.
            JOptionPane.showMessageDialog(
                null,
                String.join("\n", errorMessages),
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }
}
