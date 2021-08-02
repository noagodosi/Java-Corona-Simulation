package UI;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class RowedTableScroll extends JScrollPane {

    private static class RowHeaderRenderer extends JLabel
            implements ListCellRenderer<String> {
        RowHeaderRenderer(JTable table) {
            setOpaque(true);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(CENTER);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(234, 94, 255));
            header.setForeground(Color.WHITE);
            setForeground(new Color(255, 255, 255));
            setBackground(new Color(0xFF74FF));
            setFont(header.getFont());
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends String> list,
                                                      String value, int index, boolean isSelected, boolean cellHasFocus){
            setText((value == null) ? "" : value);
            return this;
        }
    }
    public RowedTableScroll(final JTable table, final String[] rowHeaders) {
        super(table);
        final JList<String> rowHeader = new JList<>(new AbstractListModel<>() {
            public int getSize() {
                return Math.min(rowHeaders.length, table.getRowCount());
            }

            public String getElementAt(int index) {
                return rowHeaders[index];
            }
        });
        rowHeader.setFixedCellWidth(150);
        rowHeader.setFixedCellHeight(table.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(table));
        this.setBackground(new Color(0xF8C8F7));
        this.setRowHeaderView(rowHeader);
    }
}