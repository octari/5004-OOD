package view;

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

/* 1.4 example used by DialogDemo.java. */
public class RainbowDialog extends JDialog
        implements ActionListener,
        PropertyChangeListener {
  private String height = null;
  private String width = null;
  private JTextField textFieldHeight;
  private JTextField textFieldWidth;
  // private DialogDemo dd;
  private JComboBox vorh;
  //private String magicWord;
  private JOptionPane optionPane;
  private String[] vh = {"vertical", "horizontal"};
  private String btnString1 = "Enter";
  private String btnString2 = "Cancel";
  private ArrayList<String> argumentsHolder;

 // private String[] countries = {"France", "Greece", "Switzerland"};

  /**
   * Returns null if the typed string was invalid;
   * otherwise, returns the string as the user entered it.
   */
//  public String getValidatedText() {
//    return typedText;
//  }

  /** Creates the reusable dialog. */
  public RainbowDialog(Frame aFrame) {
    super(aFrame, true);
    argumentsHolder = new ArrayList<>();
    //dd = parent;

    // magicWord = aWord.toUpperCase();
    setTitle("Generate a rainbow");
    // String[] countries = {"France", "Greece", "Switzerland"};
    textFieldHeight = new JTextField(10);
    textFieldWidth = new JTextField(10);
    vorh = new JComboBox(vh);
    //Create an array of the text and components to be displayed.
    String msgStringHeight = "Enter an integer as height";
    String msgStringWidth = "Enter an integer as width";
    String msgStringVOrH = "Select vertical or horizontal";
//    String msgString2 = "(The answer is \"" + magicWord
//            + "\".)";
    Object[] array = {msgStringHeight, textFieldHeight, msgStringWidth,
            textFieldWidth,  msgStringVOrH, vorh};

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
        textFieldHeight.requestFocusInWindow();
      }
    });

    //Register an event handler that puts the text into the option pane.
    textFieldHeight.addActionListener(this);
    textFieldWidth.addActionListener(this);

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
        height = textFieldHeight.getText();
        width = textFieldWidth.getText();
        //String ucText = typedText.toUpperCase();
        try{
          Integer.parseInt(height);
          Integer.parseInt(width);
          clearAndHide();

          argumentsHolder.add(height);
          argumentsHolder.add(width);
          argumentsHolder.add(vh[vorh.getSelectedIndex()]);

        }
        catch (NumberFormatException error){
          //text was invalid
          textFieldHeight.selectAll();
          JOptionPane.showMessageDialog(
                  RainbowDialog.this,
                  "Sorry, the input "
                          + "isn't a valid response.\n"
                          + "Please enter "+
                          "an integer.",
                  "Try again",
                  JOptionPane.ERROR_MESSAGE);
          height = null;
          width = null;
          textFieldHeight.requestFocusInWindow();

        }

      } else {
        height = null;
        width = null;
        argumentsHolder = null;
        clearAndHide();
      }
    }
  }

  /** This method clears the dialog and hides it. */
  public void clearAndHide() {
    textFieldHeight.setText(null);
    textFieldWidth.setText(null);
    setVisible(false);
  }

  public ArrayList<String> getArgumentsHolder() {
    return argumentsHolder;
  }
}
