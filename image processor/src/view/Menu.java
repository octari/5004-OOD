package view;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;

/**
 * This class represents the menu in the view listener. It will expose all the operation in the
 * model.
 */
public class Menu extends JFrame {

  private JMenuBar jmb;
  private JMenu file, process, run, edit;
  private JMenu subNew;
  private JMenuItem checker, command, rainbow, flag, blur,
          sharpen, greyscale, sepia, dither, mosaic;
  private JMenuItem subLoad, subSave, undo, redo;

  /**
   * Construct the menu.
   */
  public Menu() {

    jmb = new JMenuBar();
    file = new JMenu("File");
    process = new JMenu("Process");
    run = new JMenu("Run");

    edit = new JMenu("Edit");

    subNew = new JMenu("New");
    subLoad = new JMenuItem("Load...");
    subSave = new JMenuItem("Save");

    checker = new JMenuItem("Checkerboard");
    rainbow = new JMenuItem("Rainbow");
    flag = new JMenuItem("Flag");

    command = new JMenuItem("Execute Input");

    blur = new JMenuItem("Blur");
    sharpen = new JMenuItem("Sharpen");
    greyscale = new JMenuItem("Greyscale");
    sepia = new JMenuItem("Sepia tone");
    dither = new JMenuItem("Dither");
    mosaic = new JMenuItem("Mosaic");

    undo = new JMenuItem("Undo");
    redo = new JMenuItem("Redo");

    edit.add(undo);
    edit.add(redo);

    subNew.add(checker);
    subNew.add(rainbow);
    subNew.add(flag);

    file.add(subNew);
    file.add(subLoad);
    file.add(subSave);

    process.add(blur);
    process.add(sharpen);
    process.add(greyscale);
    process.add(sepia);
    process.add(dither);
    process.add(mosaic);
    run.add(command);

    jmb.add(file);
    jmb.add(edit);
    jmb.add(process);
    jmb.add(run);

    subLoad.setActionCommand("Open file");
    checker.setActionCommand("generate checker");
    mosaic.setActionCommand("mosaic");
    flag.setActionCommand("generate flag");
    rainbow.setActionCommand("generate rainbow");
    subSave.setActionCommand("Save file");
    blur.setActionCommand("blur");
    sharpen.setActionCommand("sharpen");
    greyscale.setActionCommand("greyscale");
    sepia.setActionCommand("sepia");
    dither.setActionCommand("dither");
    undo.setActionCommand("undo");
    redo.setActionCommand("redo");
    command.setActionCommand("run command");

  }


  /**
   * Return the menu itself.
   *
   * @return the menu itself.
   */
  public JMenuBar getJmb() {
    return jmb;
  }

  /**
   * Set a action listener for each menu item.
   *
   * @param listener the listener.
   */
  public void setListeners(ActionListener listener) {
    subLoad.addActionListener(listener);
    checker.addActionListener(listener);
    mosaic.addActionListener(listener);
    flag.addActionListener(listener);
    rainbow.addActionListener(listener);
    subSave.addActionListener(listener);
    blur.addActionListener(listener);
    sharpen.addActionListener(listener);
    greyscale.addActionListener(listener);
    sepia.addActionListener(listener);
    dither.addActionListener(listener);
    undo.addActionListener(listener);
    redo.addActionListener(listener);
    command.addActionListener(listener);

  }


}
