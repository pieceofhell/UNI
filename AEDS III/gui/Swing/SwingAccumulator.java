import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
 
// A Swing GUI application inherits the top-level container javax.swing.JFrame
public class SwingAccumulator extends JFrame {
   private JTextField tfInput, tfOutput;
   private int sum = 0;    // accumulated sum, init to 0
 
   // Constructor to setup the GUI components and event handlers
   public SwingAccumulator() {
      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container cp = getContentPane();
      cp.setLayout(new GridLayout(2, 2, 5, 5));  // The content-pane sets its layout
 
      cp.add(new JLabel("Enter an Integer: "));
      tfInput = new JTextField(10);
      cp.add(tfInput);
      cp.add(new JLabel("The Accumulated Sum is: "));
      tfOutput = new JTextField(10);
      tfOutput.setEditable(false);  // read-only
      cp.add(tfOutput);
 
      // Allocate an anonymous instance of an anonymous inner class that
      //  implements ActionListener as ActionEvent listener
      tfInput.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            // Get the String entered into the input TextField, convert to int
            int numberIn = Integer.parseInt(tfInput.getText());
            sum += numberIn;      // accumulate numbers entered into sum
            tfInput.setText("");  // clear input TextField
            tfOutput.setText(sum + ""); // display sum on the output TextField
         }
      });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Swing Accumulator"); // "super" Frame sets title
      setSize(350, 120);  // "super" Frame sets initial size
      setVisible(true);   // "super" Frame shows
   }
 
   // The entry main() method
   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new SwingAccumulator(); // Let the constructor do the job
         }
      });
   }
}