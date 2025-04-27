package voiceout.ryme.Oop;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseValidator implements IValidation {
    // Holds error messages; protected so subclass can access.
    protected final List<String> errorMessages = new ArrayList<>();

    // Helper method to add errors from within the class hierarchy.
    protected void addError(String message) {
        errorMessages.add(message);
    }

    @Override
    public void addCustomError(String message) {
        errorMessages.add(message);
    }

    // Default error display: shows a popup.
    @Override
    public boolean displayErrors() {
        if (!errorMessages.isEmpty()) {
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
