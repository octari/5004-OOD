import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import model.ImageImpl;
import view.View;

/**
 * This class represents a runner of the program.
 */
public class Runner {

  /**
   * This is the main method of the runner. If the arguments are invalid, throw an exception and
   * quit.
   *
   * @param args the user entered to choose how to run the program.
   * @throws FileNotFoundException    when if fails to read the file user entered.
   * @throws IllegalArgumentException when the arguments the user entered is invalid.
   */
  public static void main(String[] args) throws FileNotFoundException {
    if (args[0].equals("-interactive")) {
      try {
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

        //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        //    {
        //       if ("Nimbus".equals(info.getName())) {
        //          UIManager.setLookAndFeel(info.getClassName());
        //         break;
        //    }
        // }
      } catch (UnsupportedLookAndFeelException e) {
        // handle exception
      } catch (ClassNotFoundException e) {
        // handle exception
      } catch (InstantiationException e) {
        // handle exception
      } catch (IllegalAccessException e) {
        // handle exception
      } catch (Exception e) {
      }

      View.setDefaultLookAndFeelDecorated(false);


      View frame = new View();
      new Controller(new ImageImpl()).executeInteractive(frame);

    } else if (args[0].equals("-script")) {

      new Controller(new ImageImpl()).
              executeScript(new InputStreamReader(new FileInputStream(args[1])));
    } else {
      throw new IllegalArgumentException("Invalid arguments");
    }
  }


}

