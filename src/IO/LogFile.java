package IO;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LogFile {
    public  static File SaveFileLogFile(Frame frame, String fileName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(frame);
        return new File(fileChooser.getSelectedFile(), fileName);
    }
}
