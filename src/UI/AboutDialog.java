package UI;

import javax.swing.*;

public class AboutDialog extends JDialog {
    AboutDialog(mainFrame frame){
        super(frame,"About",false);
        JOptionPane.showMessageDialog(this,"Home Work 2 @GUI  \n Authors: Noa Fadida & Adi Godosi \n Date Of Writing:4/5/21");
    }

}
