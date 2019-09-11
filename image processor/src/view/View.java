package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.CustomDialog;
import model.FlagDialog;

/**
 * This class represents a view of the program for the user interaction. It implements all the
 * methods in the interface view.IView.
 */
public class View extends JFrame implements IView {
  private Menu jmb;
  private JPanel output;
  private JPanel contentPane;
  private JTextArea commands;
  private JLabel imageLabel;

  private ImageIcon display;

  /**
   * Construct a JFrame the holds all the required components.
   */
  public View() {

    super();

    display = new ImageIcon();
    setTitle("model.Model Tool");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jmb = new Menu();

    contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);

    //Create a scrolled text area.
    output = new JPanel();
    imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);

    imageLabel.setIcon(display);

    scrollPane.setPreferredSize(new Dimension(600, 600));
    output.add(scrollPane);
    output.setBorder(BorderFactory.createTitledBorder("model.Model Display"));

    output.setMaximumSize(null);

    contentPane.add(output, BorderLayout.CENTER);

    commands = new JTextArea(10, 20);
    JScrollPane scrollText = new JScrollPane(commands);
    scrollText.setBorder(BorderFactory.createTitledBorder("Batch Script"));
    contentPane.add(scrollText, BorderLayout.SOUTH);

    setContentPane(contentPane);
    setJMenuBar(jmb.getJmb());

    this.pack();

  }


  /**
   * Display the result image.
   *
   * @param image teh image to displayed.
   */
  public void display(java.awt.Image image) {

    display.setImage(image);
    imageLabel.setIcon(new ImageIcon(display.getImage()));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.revalidate();

  }

  /**
   * Get the absolute path the user choose when loading a file.
   *
   * @return the absolute loading path.
   */
  public String choosePath() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PNG Images", "jpg", "png");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(View.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {

      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  /**
   * Get the absolute path the user choose when saving a image.
   *
   * @return the absolute saving path.
   */
  public String savePath() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(View.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  /**
   * Set the a listener for the components on the frame to react.
   *
   * @param clicked the action listener.
   */
  public void setListeners(ActionListener clicked) {
    jmb.setListeners(clicked);
  }

  /**
   * Return the image being displayed.
   *
   * @return the image being displayed.
   */
  public java.awt.Image getDisplay() {
    return display.getImage();
  }

  /**
   * When clicked the button for the operation which needs an input as parameter, pop up a dialog
   * for the input.
   *
   * @param title the title of the dialog.
   * @return the single input from a dialog.
   */
  public String setSingleInput(String title) {
    CustomDialog sizeSelector = new CustomDialog(title,
            this);
    sizeSelector.setVisible(true);
    return sizeSelector.getValidatedText();
  }

  /**
   * When click the button for generating a flag, which needs to choose country and enter a ratio,
   * pop up a dialog for the setting.
   *
   * @return an array list of the all the settings from the user.
   */
  public ArrayList<String> setFlag() {
    FlagDialog flagSelector = new FlagDialog(this);
    flagSelector.setVisible(true);
    return flagSelector.getArgumentsHolder();
  }

  /**
   * When click the button for generating a rainbow, which needs to choose vertical or horizontal
   * and enter height and width, pop up a dialog for the setting.
   *
   * @return an array list of all the settings from the user.
   */
  public ArrayList<String> setRainbow() {
    RainbowDialog rainbowSelector = new RainbowDialog(this);
    rainbowSelector.setVisible(true);
    return rainbowSelector.getArgumentsHolder();
  }

  /**
   * When fail to execute the operation, a notification will a appear in the view.
   *
   * @param command the command fail to executed.
   */
  public void cantDo(String command) {
    JOptionPane.showMessageDialog(this, command,
            "Notification", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Get the all the input in the text area.
   *
   * @return teh all the input in the text area.
   */
  public String getText() {
    return commands.getText();
  }
}

