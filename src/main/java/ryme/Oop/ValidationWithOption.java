package ryme.Oop;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ValidationWithOption extends BaseValidator {
    // displayOption: 
    // true  -> Show errors via a popup (normal way).
    // false -> Print errors to the terminal.
    // null  -> Do both.
    private Boolean displayOption;

    public ValidationWithOption(Boolean displayOption) {
        this.displayOption = displayOption;
    }

    @Override
    public boolean validateFields(JTextField... fields) {
        // Clear any previous errors.
        errorMessages.clear();

        // Check each field for emptiness.
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                addError("• " + field.getName() + " must not be blank!");
            }
        }
        // Display errors based on the displayOption flag.
        return displayErrors();
    }

    @Override
    public boolean displayErrors() {
        if (!errorMessages.isEmpty()) {
            String errors = String.join("\n", errorMessages);

            if (displayOption == null) {
                // Do both: display a popup and print to the terminal.
                System.out.println("Validation Errors:");
                System.out.println(errors);
                JOptionPane.showMessageDialog(
                    null,
                    errors,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else if (displayOption) {
                // Graphical output only.
                JOptionPane.showMessageDialog(
                    null,
                    errors,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                // Terminal output only.
                System.out.println("Validation Errors:");
                System.out.println(errors);
            }
            return false;
        }
        return true;
    }
}
