package UI;

import Virus.VirusManager;

import javax.swing.table.AbstractTableModel;


public class MutationModel extends AbstractTableModel {

    static boolean data [][];


    public MutationModel(){
        data=new boolean[VirusManager.values().length][VirusManager.values().length];
        boolean check=false;
        for (int i=0;i<data.length;i++){
            for (int j=0; j<data.length;j++){
                check=VirusManager.values()[i].CheckIfExist(VirusManager.values()[j].getVirus());
                data[i][j]=check;
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Boolean.class;
    }

    @Override
    public Boolean getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column){
        return MutationWindow.columnNames[column];
    }

    @Override
    public int getRowCount() {
        return VirusManager.values().length;
    }

    @Override
    public int getColumnCount() {
        return VirusManager.values().length;
    }

    @Override
    public void setValueAt(Object aValue,int row,int col){
        data[row][col]=(Boolean) aValue;
    }

    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }

}
