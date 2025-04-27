package voiceout.ryme.Oop;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ValidationWithOption extends BaseValidator {
    // displayOption: 
    //    true  -> Show errors via a popup only.
    //    false -> Print errors to the terminal only.
    //    null  -> Show errors in both popup and terminal.
    private Boolean displayOption;

    public ValidationWithOption(Boolean displayOption) {
        this.displayOption = displayOption;
    }

    @Override
    public boolean validateFields(JTextField... fields) {
        // Clear previous errors.
        errorMessages.clear();
        // Check each field for being blank.
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                addError("• " + field.getName() + " must not be blank!");
            }
        }
        // Display the errors according to the chosen option.
        return displayErrors();
    }

    @Override
    public boolean displayErrors() {
        if (!errorMessages.isEmpty()) {
            String errors = String.join("\n", errorMessages);
            if (displayOption == null) {
                // Both method: print to terminal and show popup.
                System.out.println("Validation Errors:");
                System.out.println(errors);
                JOptionPane.showMessageDialog(
                    null,
                    errors,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else if (displayOption) {
                // Popup only.
                JOptionPane.showMessageDialog(
                    null,
                    errors,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                // Terminal only.
                System.out.println("Validation Errors:");
                System.out.println(errors);
            }
            return false;
        }
        return true;
    }
}
