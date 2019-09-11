package model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


/**
 * This class represents a dialog for user to enter an integer input. If the input is invalid, a
 * notification will pop up.
 */
public class CustomDialog extends JDialog
        implements ActionListener,
        PropertyChangeListener {
  private String typedText = null;
  private JTextField textField;
  // private DialogDemo dd;

  //private String magicWord;
  private JOptionPane optionPane;

  private String btnString1 = "Enter";
  private String btnString2 = "Cancel";


  /**
   * Returns null if the typed string was invalid; otherwise, return the integer in String as the
   * user entered it.
   *
   * @return the valid input.
   */
  public String getValidatedText() {
    return typedText;
  }

  /**
   * Creates the reusable dialog. The dialog should be based on the given frame
   *
   * @param title  the given title for the dialog.
   * @param aFrame the frame the dialog should be on.
   */
  public CustomDialog(String title, Frame aFrame) {
    super(aFrame, true);

    setTitle(title);

    textField = new JTextField(10);

    //Create an array of the text and components to be displayed.
    String msgString1 = "Enter an integer as " + title;

    Object[] array = {msgString1, textField};

    //Create an array specifying the number of dialog buttons
    //and their text.
    Object[] options = {btnString1, btnString2};

    //Create the JOptionPane.
    optionPane = new JOptionPane(array,
            JOptionPane.QUESTION_MESSAGE,
            JOptionPane.YES_NO_OPTION,
            null,
            options,
            options[0]);

    //Make this dialog display it.
    setContentPane(optionPane);

    //Handle window closing correctly.
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      /**
       * Instead of directly closing the window,
       * we're going to change the JOptionPane's
       * value property.
       * @param we the event to operate in the window
       */
      public void windowClosing(WindowEvent we) {

        optionPane.setValue(JOptionPane.CLOSED_OPTION);
      }
    });

    //Ensure the text field always gets the first focus.
    addComponentListener(new ComponentAdapter() {
      /**
       * Ensure the text field always gets the first focus.
       * @param ce focus on the text filed.
       */
      public void componentShown(ComponentEvent ce) {
        textField.requestFocusInWindow();
      }
    });

    //Register an event handler that puts the text into the option pane.
    textField.addActionListener(this);

    //Register an event handler that reacts to option pane state changes.
    optionPane.addPropertyChangeListener(this);

    this.pack();
  }

  /**
   * This method handles events for the text field.
   *
   * @param e the event for the text field.
   */
  public void actionPerformed(ActionEvent e) {
    optionPane.setValue(btnString1);
  }

  /**
   * This method reacts to state changes in the option pane. If the user entered a invalid input, a
   * notification pop up had reset the input.
   *
   * @param e the event to change the property of the dialog.
   */
  public void propertyChange(PropertyChangeEvent e) {
    String prop = e.getPropertyName();

    if (isVisible()
            && (e.getSource() == optionPane)
            && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
            JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
      Object value = optionPane.getValue();

      if (value == JOptionPane.UNINITIALIZED_VALUE) {
        //ignore reset
        return;
      }

      //Reset the JOptionPane's value.
      //If you don't do this, then if the user
      //presses the same button next time, no
      //property change event will be fired.
      optionPane.setValue(
              JOptionPane.UNINITIALIZED_VALUE);

      if (btnString1.equals(value)) {
        typedText = textField.getText();

        try {
          Integer.parseInt(typedText);
          clearAndHide();
        } catch (NumberFormatException error) {
          //text was invalid
          textField.selectAll();
          JOptionPane.showMessageDialog(
                  CustomDialog.this,
                  "Sorry, \"" + typedText + "\" "
                          + "isn't a valid response.\n"
                          + "Please enter " +
                          "an integer.",
                  "Try again",
                  JOptionPane.ERROR_MESSAGE);
          typedText = null;
          textField.requestFocusInWindow();

        }
      }
      // When the user click the cancel button.
      else {
        typedText = null;
        clearAndHide();
      }
    }
  }

  /**
   * This method clears the dialog and hides it.
   */
  public void clearAndHide() {
    textField.setText(null);
    setVisible(false);
  }
}
