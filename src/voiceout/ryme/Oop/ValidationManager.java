package voiceout.ryme.Oop;

import javax.swing.*;
import java.util.*;

public abstract class ValidationManager {

    protected final List<String> errorMessages = new ArrayList<>();

    public void addCustomError(String message) {
        errorMessages.add(message);
    }

    public boolean validateFields(JTextField... fields) {
        errorMessages.clear();
        Arrays.stream(fields)
                .filter(field -> field.getText().trim().isEmpty())
                .forEach(field -> addCustomError("• " + field.getName() + " must not be blank!"));
        return displayErrors();
    }

    public boolean displayErrors() {
        if (errorMessages.isEmpty()) {
            return true;
        }
        JOptionPane.showMessageDialog(null, String.join("\n", errorMessages), "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
