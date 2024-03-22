import java.awt.*; // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;

// A Swing GUI application inherits the top-level container javax.swing.JFrame
public class SwingPlayground extends JFrame {

  private JTextField tfInput, tfOutput;
  private int sum = 0;
  private JButton btn1; // Using Swing's JButton instead of AWT's Button

  // Constructor to setup the GUI components and event handlers
  public SwingPlayground() {
    // Retrieve the content-pane of the top-level container JFrame
    // All operations done on the content-pane
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(3, 3, 5, 5));

    btn1 = new JButton("Button");
    cp.add(btn1);

    // cp.add(new JLabel("Enter an Integer: "));
    tfInput = new JTextField(10);
    cp.add(tfInput);
    cp.add(new JLabel("Text box: "));
    tfOutput = new JTextField(10);
    tfOutput.setEditable(false); // read-only
    cp.add(tfOutput);

    btn1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
         tfOutput.setText("penis");
      }
   });

    // Allocate an anonymous instance of an anonymous inner class that
    //  implements ActionListener as ActionEvent listener
    tfInput.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          // Get the String entered into the input TextField, convert to int
          int numberIn = Integer.parseInt(tfInput.getText());
          sum += numberIn; // accumulate numbers entered into sum
          // tfInput.setText(""); // clear input TextField
          tfOutput.setText(sum + ""); // display sum on the output TextField
        }
      }
    );

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program if close-window button clicked
    setTitle("GUI Testing"); // "super" Frame sets title
    setSize(350, 120); // "super" Frame sets initial size
    setVisible(true); // "super" Frame shows
  }
  public static void main(String[] args) {
    // Run the GUI construction in the Event-Dispatching thread for thread-safety
    SwingUtilities.invokeLater(
      new Runnable() {
        @Override
        public void run() {
          new SwingPlayground(); // Let the constructor do the job
        }
      }
    );
  }
}
