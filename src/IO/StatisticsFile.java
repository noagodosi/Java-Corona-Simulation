package IO;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class StatisticsFile {
    public StatisticsFile(JTable table) {
        File file=loadFileFunc();
        TableModel model = table.getModel();
        try (PrintWriter writer = new PrintWriter(file)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < model.getColumnCount(); i++) {
                sb.append(model.getColumnName(i) + ",");
            }
            sb.append("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j).toString() + ",");
                }
                sb.append("\n");
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File loadFileFunc() {
        FileDialog fd = new FileDialog((Frame) null,"Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        System.out.println(f.getPath());
        return f;
    }
}
