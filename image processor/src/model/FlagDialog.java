package model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * This class represents a dialog for user to enter an integer input as the ratio of flag and choose the country by combo box. If the input is invalid, a
 * notification will pop up.
 */
public class FlagDialog extends JDialog
        implements ActionListener,
        PropertyChangeListener {
  private String typedText = null;
  private JTextField textField;
  private JComboBox country;
  private JOptionPane optionPane;

  private String btnString1 = "Enter";
  private String btnString2 = "Cancel";

  private String[] countries = {"France", "Greece", "Switzerland"};
  private ArrayList<String> argumentsHolder;

  /**
   * Returns null if the typed string was invalid;
   * otherwise, returns the integer string as the user entered it.
   * @return the integer string as the user entered it.
   */
  public String getValidatedText() {
    return typedText;
  }

  /** Creates the reusable dialog. */
  public FlagDialog(Frame aFrame) {
    super(aFrame, true);
    //dd = parent;

    // magicWord = aWord.toUpperCase();
    setTitle("Generate a flag");
    argumentsHolder = new ArrayList<>();
   // String[] countries = {"France", "Greece", "Switzerland"};
    textField = new JTextField(10);
    country = new JComboBox(countries);
    //Create an array of the text and components to be displayed.
    String msgString1 = "Enter an integer as ratio";
    String msgString2 = "Select a country";
//    String msgString2 = "(The answer is \"" + magicWord
//            + "\".)";
    Object[] array = {msgString1, textField, msgString2, country};

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
      public void windowClosing(WindowEvent we) {
        /*
         * Instead of directly closing the window,
         * we're going to change the JOptionPane's
         * value property.
         */
        optionPane.setValue(JOptionPane.CLOSED_OPTION);
      }
    });

    //Ensure the text field always gets the first focus.
    addComponentListener(new ComponentAdapter() {
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

  /** This method handles events for the text field. */
  public void actionPerformed(ActionEvent e) {
    optionPane.setValue(btnString1);
  }

  /** This method reacts to state changes in the option pane. */
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
        //String ucText = typedText.toUpperCase();
        try{
          Integer.parseInt(typedText);
          clearAndHide();
          argumentsHolder.add(countries[country.getSelectedIndex()]);
          argumentsHolder.add(getValidatedText());
        }
        catch (NumberFormatException error){
          //text was invalid
          textField.selectAll();
          JOptionPane.showMessageDialog(
                  FlagDialog.this,
                  "Sorry, \"" + typedText + "\" "
                          + "isn't a valid response.\n"
                          + "Please enter "+
                          "an integer.",
                  "Try again",
                  JOptionPane.ERROR_MESSAGE);
          typedText = null;
          textField.requestFocusInWindow();

        }
      } else {
        typedText = null;
        argumentsHolder = null;
        clearAndHide();
      }
    }
  }

  /** This method clears the dialog and hides it. */
  public void clearAndHide() {
    textField.setText(null);
    setVisible(false);
  }

  public ArrayList<String> getArgumentsHolder(){
    return argumentsHolder;
  }
}
