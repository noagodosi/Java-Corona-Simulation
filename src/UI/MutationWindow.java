package UI;

import Virus.VirusManager;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


public class MutationWindow extends JDialog {

    static String[] columnNames=new String[VirusManager.values().length];
    public final JTable table;
    public MutationModel model;

    public MutationWindow(MutationModel model, JFrame frame){

        super(frame,"Edit Mutation Window",true);
        for (int i =0; i<VirusManager.values().length;i++){
            columnNames[i]=VirusManager.values()[i].getName();
        }
        this.model=model;
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setGridColor(new Color(206, 206, 206));
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);


        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) headerRenderer;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(new RowedTableScroll(table, columnNames));

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(500,500);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(frame);
    }
}