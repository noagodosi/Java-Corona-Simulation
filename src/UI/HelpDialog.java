package UI;

import javax.swing.*;

public class HelpDialog extends JDialog{
    HelpDialog(mainFrame frame){
        super(frame,"Help",true);
        JOptionPane.showMessageDialog(this, "The purpose of the system is to present the user with information about the Chinese virus and its variations.\n" +
                "On the main screen, he is shown a map where he can see the color of the localities and their location\n" +
                "At the bottom of the main screen he can select the speed of the simulation\n" +
                "In addition, at the top it is presented with a varied menu that includes: statistics window, mutation editing window and the like.");

    }
}

