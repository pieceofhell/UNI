import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private JLabel inputLabel;
    private int currentFunction;
    private int caesarShift;

    public Main() {
        // Set the title of the window
        setTitle("Button Grid");

        // Set the layout of the frame to be a BorderLayout
        setLayout(new BorderLayout());

        // Create a text area for displaying messages
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane (with the text area) to the top of the frame
        add(scrollPane, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        // Create and add buttons to the button panel
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("Button " + i);
            button.addActionListener(new ButtonClickListener(i));
            buttonPanel.add(button);
        }

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Create a panel for the input field and label
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // Create the input label
        inputLabel = new JLabel("Enter your input: ");
        inputPanel.add(inputLabel, BorderLayout.WEST);

        // Create the input field
        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Add the input panel to the bottom of the frame
        add(inputPanel, BorderLayout.SOUTH);

        // Add action listener to the input field
        inputField.addActionListener(new InputFieldListener());

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        setSize(400, 500);

        // Center the window
        setLocationRelativeTo(null);
    }

    private class ButtonClickListener implements ActionListener {
        private int buttonNumber;

        public ButtonClickListener(int buttonNumber) {
            this.buttonNumber = buttonNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            executeFunction(buttonNumber);
        }
    }

    private class InputFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            switch (currentFunction) {
                case 8:
                    if (caesarShift == 0) {
                        try {
                            caesarShift = Integer.parseInt(input);
                            appendText("Caesar encoder shift: " + caesarShift);
                            requestInput("Enter the string to be encrypted:");
                        } catch (NumberFormatException ex) {
                            appendText("Invalid input. Please enter a number.");
                            caesarShift = 0;  // Reset shift
                            requestInput("Enter the shift number:");
                        }
                    } else {
                        String encryptedText = caesarCipher(input, caesarShift);
                        appendText("Encrypted text: " + encryptedText);
                        caesarShift = 0;  // Reset shift after use
                    }
                    break;
                case 9:
                    try {
                        int number = Integer.parseInt(input);
                        int result = number + 5;
                        appendText("Sum 5 function executed. Input: " + number + ", Result: " + result);
                    } catch (NumberFormatException ex) {
                        appendText("Invalid input. Please enter a number.");
                    }
                    break;
                default:
                    appendText("Unknown function.");
            }
            // Clear the input field
            inputField.setText("");
        }
    }

    private void executeFunction(int buttonNumber) {
        // Define actions for each button
        switch (buttonNumber) {
            case 1:
                appendText("Function 1 executed.");
                break;
            case 2:
                appendText("Function 2 executed.");
                break;
            case 3:
                appendText("Function 3 executed.");
                break;
            case 4:
                appendText("Function 4 executed.");
                break;
            case 5:
                appendText("Function 5 executed.");
                break;
            case 6:
                appendText("Function 6 executed.");
                break;
            case 7:
                appendText("Function 7 executed.");
                break;
            case 8:
                appendText("Function 8 executed. Please enter the shift number:");
                requestInput("Enter the shift number:");
                currentFunction = 8;
                break;
            case 9:
                appendText("Function 9 executed. Please enter a number:");
                requestInput("Enter a number:");
                currentFunction = 9;
                break;
            default:
                appendText("Unknown function.");
        }
    }

    private void requestInput(String prompt) {
        inputLabel.setText(prompt);
        inputField.requestFocus();
    }

    private void appendText(String text) {
        textArea.append(text + "\n");
    }

    private String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
