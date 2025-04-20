package ryme.Oop;

import javax.swing.JTextField;

public class Validation extends BaseValidator {

    @Override
    public boolean validateFields(JTextField... fields) {
        // Clear any previous errors.
        errorMessages.clear();

        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                addError("• " + field.getName() + " must not be blank!");
            }
        }
        // Use the default display (JOptionPane).
        return displayErrors();
    }
}
