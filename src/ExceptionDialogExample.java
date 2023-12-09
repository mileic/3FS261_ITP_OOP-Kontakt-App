import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExceptionDialogExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                throwException();
            } catch (Exception e) {
                showExceptionDialog(e, "Error", "Exception Details");
            }
        });
    }

    private static void throwException() throws Exception {
        // Simulate an exception
        throw new Exception("This is a sample exception message.");
    }

    private static void showExceptionDialog(Exception e, String title, String message) {
        // Create text area to display exception stack trace
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(getStackTraceAsString(e));

        // Create scroll pane and add text area to it
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create toggle button
        JToggleButton toggleButton = new JToggleButton("Show Details");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggleButton.isSelected()) {
                    scrollPane.setVisible(true);
                } else {
                    scrollPane.setVisible(false);
                }
            }
        });

        // Create panel to hold the toggle button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(toggleButton);

        // Create panel to hold the scroll pane and button panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show option pane with the main panel
        JOptionPane.showMessageDialog(null, mainPanel, title, JOptionPane.ERROR_MESSAGE);
    }

    private static String getStackTraceAsString(Exception e) {
        // Convert the stack trace to a string
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            stringBuilder.append(element.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
