package UI;

import Country.Settlement;
import IO.StatisticsFile;
import Population.Person;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class StatisticsFrame implements ActionListener {

    public JFrame MyJFrame;
    protected JPanel centerPanel;
    protected JPanel northPanel;
    protected JPanel southPanel;
    public Settlement[] settlements;
    public JTable MyTable;
    private String selectedSettlement;
    private int rowSelected;


    public StatisticsFrame(mainFrame Frame) // constructor
    {
        JButton c1, c2, c3;
        this.settlements = Frame.settlements;
        MyJFrame = new JFrame("Statistics Window");
        MyJFrame.setPreferredSize(new Dimension(700, 195));
        MyJFrame.setBackground(new Color(248, 248, 248));
        northPanel = new JPanel();
        northPanel.setBackground(new Color(255, 231, 255));
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 255));
        southPanel = new JPanel();
        southPanel.setBackground(new Color(255, 255, 255));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        MyJFrame.add(northPanel, BorderLayout.NORTH);


        String[] ColNames = {"Select Column", "Settlement Name", "Settlement Type", "Ramzor Color"};
        JComboBox columnSelect = new JComboBox(ColNames);
        columnSelect.setBackground(new Color(246, 184, 244, 255));
        columnSelect.setForeground(new Color(255, 255, 255, 255));
        columnSelect.setPreferredSize(new Dimension(150, 20));
        columnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnSelect.getSelectedIndex();
            }
        });

        northPanel.add(columnSelect, BorderLayout.EAST);

        JTextField tbFilterText = new JTextField();
        tbFilterText.setPreferredSize(new Dimension(250, 20));
        tbFilterText.setBackground(new Color(252, 182, 245));
        tbFilterText.setToolTipText("Filter Column Name:");
        northPanel.add(tbFilterText, BorderLayout.WEST);

        //create table
        String[] columnNamesTable = new String[]{"Settlement Name", "Settlement Type", "Ramzor Color", "Number Of Sick",
                "Vaccine Doses", "Number Of Resident"};
        DefaultTableModel tableModel = new DefaultTableModel();
        this.MyTable = new JTable(tableModel);
        TableRowSorter<DefaultTableModel> sorter;
        MyTable.setRowSorter(sorter = new TableRowSorter<DefaultTableModel>(tableModel));

        for (String col : columnNamesTable) {
            tableModel.addColumn(col);
        }
        for (Settlement settlement : settlements) {
            tableModel.addRow(new String[]{settlement.getName(),
                    settlement.getClass().toString().substring(14),
                    settlement.getRamzorColor().toString(),
                    String.valueOf(settlement.getSickPeople().size()),
                    String.valueOf(settlement.getNumberOfVaccinesDoses()),
                    String.valueOf(settlement.getPeople().size() + settlement.getSickPeople().size())
            });
        }
        centerPanel.add(new JScrollPane(MyTable));

        MyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    rowSelected = MyTable.getSelectedRow();
                    selectedSettlement = MyTable.getValueAt(rowSelected != -1 ? rowSelected : 0, 0).toString();

            }
        });
        tbFilterText.setToolTipText("Filter Name Of Column:");
        tbFilterText.getDocument().addDocumentListener(new DocumentListener() {


            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    int indexCol = columnSelect.getSelectedIndex();
                    if (indexCol != 0) {
                       sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), indexCol - 1));
                       MyTable.setRowSorter(sorter);
                    }
                } catch (java.util.regex.PatternSyntaxException e3) {
                    e3.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    int indexCol = columnSelect.getSelectedIndex();
                    if (indexCol != 0) {
                        sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), indexCol));
                        MyTable.setRowSorter(sorter);
                    }
                } catch (java.util.regex.PatternSyntaxException e1) {
                    e1.printStackTrace();
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    int indexCol = columnSelect.getSelectedIndex();
                    if (indexCol != 0) {
                        sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), indexCol));
                        MyTable.setRowSorter(sorter);
                    }
                } catch (java.util.regex.PatternSyntaxException e3) {
                    e3.printStackTrace();
                }
            }

        });

        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        southPanel.setPreferredSize(new Dimension(500, 25));
        southPanel.setBackground(new Color(255, 231, 255));
        southPanel.add(c1 = new JButton("Save"));
        c1.setBackground(new Color(247, 224, 255, 255));
        c1.setForeground(new Color(247, 115, 255, 255));
        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.fireTableDataChanged();
                new StatisticsFile(MyTable);
                JOptionPane.showMessageDialog(MyJFrame,"saved!");
            }
        });
        southPanel.add(c2 = new JButton("Add Sick"));
        c2.setBackground(new Color(247, 224, 255, 255));
        c2.setForeground(new Color(247, 115, 255, 255));
        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Settlement settlement:settlements){
                    if(selectedSettlement.equals(settlement.getName())){
                        int numOfPerson;
                        int numOfSick;
                        Random rand = new Random();
                        Random randVirus = new Random();
                        IVirus[] randomVirusArr = new IVirus[3];
                        BritishVariant b = new BritishVariant();
                        SouthAfricanVariant s = new SouthAfricanVariant();
                        ChineseVariant c = new ChineseVariant();
                        randomVirusArr[0] = b;
                        randomVirusArr[1] = s;
                        randomVirusArr[2] = c;
                        numOfPerson = settlement.getPeople().size();
                        numOfSick = (int) (0.1*numOfPerson) ;
                        for (int j = 0; j < numOfSick; j++) {
                            numOfPerson = settlement.getPeople().size();
                            int randomIndex = rand.nextInt(numOfPerson);
                            Person current = settlement.getPeople().remove(randomIndex);
                            int rVIndex = randVirus.nextInt(3);
                            settlement.addPerson(current.contagion(randomVirusArr[rVIndex]));
                            MyTable.setValueAt(settlement.getSickPeople().size(), rowSelected,3);
                        }
                    }
                }
            }
        });

        southPanel.add(c3 = new JButton("Vaccinate"));
        c3.setBackground(new Color(247, 224, 255, 255));
        c3.setForeground(new Color(247, 115, 255, 255));
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Settlement settlement:settlements) {
                    if (selectedSettlement.equals(settlement.getName())) {
                        String num=JOptionPane.showInputDialog(MyJFrame, "Enter the number of vaccine doses:\n only positive number");
                        if(Integer.parseInt(num)>0){
                            settlement.setNumberOfVaccinesDoses(Integer.parseInt(num));
                            MyTable.setValueAt(num, rowSelected,4);
                        }
                    }
                }

            }
        });
        MyJFrame.repaint();
        MyJFrame.add(centerPanel, BorderLayout.CENTER);
        MyJFrame.add(southPanel, BorderLayout.SOUTH);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setSize(new Dimension(MyTable.getSize()));
        MyJFrame.pack();
        MyJFrame.setVisible(false);
        MyJFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}







