package UI;

import Country.Settlement;
import IO.CareTakerLogFile;
import IO.LogFile;
import IO.Memento;
import IO.Originator;
import Simulation.Clock;
import Simulation.Simulation;
import Virus.VirusManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.awt.BorderLayout.*;


public class mainFrame extends JFrame implements ActionListener {
    public StatisticsFrame statisticsFrame;
    public Simulation loadMap;
    public volatile Settlement[] settlements;
    public MutationWindow mutationWindow;
    static private volatile mainFrame menu;
    private final JMenuItem[] mi;
    private JSpinner spinner;
    public PanelDrawing mapDrawing;
    public JSlider speedSlider;
    private Thread thread;
    private File file=null;
    private CareTakerLogFile careTakerLogFile;
    private Originator originatorMemento;


    public static void main(String[] args) {
        menu = getInstance();
        menu.setBackground(new Color(255, 255, 255));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(850, 650);
        menu.setVisible(true);
    }

    public mainFrame() {
        super("Main Menu");

        JMenuBar mb = new JMenuBar();
        mb.setBackground(new Color(40, 40, 40));
        mb.setForeground(Color.white);
        JMenu m1 = new JMenu("File");
        m1.setForeground(Color.WHITE);
        JMenu m2 = new JMenu("Simulation");
        m2.setForeground(Color.WHITE);
        JMenu m3 = new JMenu("Help");
        m3.setForeground(Color.WHITE);
        String[] names = {"Load", "Statistic", "Edit Mutations","LogFile Location","Restore LogFile Location","Exit", "Play", "Pause", "Stop", "Set Ticks Per Day", "Help", "About"};
        mi = new JMenuItem[names.length];
        originatorMemento= new Originator();
        careTakerLogFile=new CareTakerLogFile();


        speedSlider = new JSlider(1, 100, 50);
        speedSlider.setToolTipText("Speed");
        speedSlider.setPreferredSize(new Dimension(150, 30));
        speedSlider.setBackground(new Color(50, 50, 50));

        this.add(speedSlider, SOUTH);


        for (int i = 0; i < names.length; i++) {
            mi[i] = new JMenuItem(names[i]);
            mi[i].addActionListener(this);
            mi[i].setBackground(new Color(255, 255, 255));
            mi[i].setForeground(Color.BLACK);
        }

        mi[1].setEnabled(false);
        mi[2].setEnabled(false);
        mi[3].setEnabled(false);
        mi[4].setEnabled(false);
        mi[5].setEnabled(false);
        mi[6].setEnabled(false);
        mi[7].setEnabled(false);
        mi[8].setEnabled(false);


        m1.add(mi[0]);
        m1.addSeparator();
        m1.add(mi[1]);
        m1.addSeparator();
        m1.add(mi[2]);
        m1.addSeparator();
        m1.add(mi[3]);
        m1.addSeparator();
        m1.add(mi[4]);
        m1.addSeparator();
        m1.add(mi[5]);


        m2.add(mi[6]);
        m2.addSeparator();
        m2.add(mi[7]);
        m2.addSeparator();
        m2.add(mi[8]);
        m2.addSeparator();
        m2.add(mi[9]);

        m3.add(mi[10]);
        m3.addSeparator();
        m3.add(mi[11]);


        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        setJMenuBar(mb);

        this.getContentPane().setBackground(Color.WHITE);
        this.pack();
        this.setVisible(true);
    }

    public static mainFrame getInstance(){//singelton
        if (menu==null){
            synchronized (mainFrame.class) {
                if (menu == null)
                    menu = new mainFrame();
            }
        }
        return menu;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mi[0]) {//load file
            synchronized (this){
                new Clock(0);
                this.thread=null;
                if(mapDrawing!=null){
                    menu.remove(mapDrawing);
                }
                loadMap = new Simulation(speedSlider);
                settlements = loadMap.getSettlementArray();
                MouseListener mouseListener = new MouseListener();
                if (this.loadMap != null) {
                    mi[1].setEnabled(true);
                    mi[2].setEnabled(true);
                    mi[3].setEnabled(true);
                    mi[5].setEnabled(true);
                    mi[6].setEnabled(true);
                    mi[7].setEnabled(true);
                    mi[8].setEnabled(true);
                }
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(800, 600);
                mapDrawing=new PanelDrawing(menu);

                //mapDrawing.validate();
                mapDrawing.addMouseListener(mouseListener);
                add(mapDrawing, CENTER);
                statisticsFrame = new StatisticsFrame(menu);

                setVisible(true);
                mapDrawing.repaint();
            }

        } else if (e.getSource() == mi[1]) {//statistic table
            if (loadMap != null) {
                statisticsFrame = new StatisticsFrame(menu);
                statisticsFrame.MyJFrame.setVisible(true);

            } else
                printLoadFile();

        } else if (e.getSource() == mi[2]) {//mutations window
            mutationWindow = new MutationWindow(new MutationModel(), menu);
            mutationWindow.repaint();
            mutationWindow.setVisible(true);
            mutationWindow.model.fireTableDataChanged();
            for(int i=0;i<VirusManager.values().length; i++){
                for (int j=0; j<VirusManager.values().length;j++){
                    if(mutationWindow.model.getValueAt(i, j)){
                        mutationWindow.model.setValueAt(true, i, j);
                        VirusManager virusManager1=helpMethode(mutationWindow.model.getColumnName(i));
                        VirusManager virusManager2=helpMethode(mutationWindow.model.getColumnName(j));
                        //System.out.println(virusManager1.getName());
                        //System.out.println(virusManager2.getName());
                        virusManager1.addVariant(virusManager2.getVirus());
                    }
                    else {
                        mutationWindow.model.setValueAt(false, i, j);
                        VirusManager virusManager1=helpMethode(mutationWindow.model.getColumnName(i));
                        VirusManager virusManager2=helpMethode(mutationWindow.model.getColumnName(j));
                        virusManager1.removeVariant(virusManager2.getVirus());
                    }
                }
            }
        }


        else if (e.getSource() == mi[3]) {//log file save
            file = LogFile.SaveFileLogFile(menu, "Log File.log");
            originatorMemento.setState(file.getAbsolutePath());
            careTakerLogFile.addMemento(originatorMemento.createMemento());

            try {
                FileHandler handler = new FileHandler(file.getAbsolutePath());
                handler.setFormatter(new SimpleFormatter());
                Settlement.setLOGGER(Logger.getLogger(settlements.getClass().getName()));
                Logger.getLogger(settlements.getClass().getName()).addHandler(handler);
                mi[4].setEnabled(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else if(e.getSource()==mi[4]){//restore log file
            if(careTakerLogFile.statesList.size()>2){
                Memento memento=careTakerLogFile.getMemento(careTakerLogFile.statesList.size()-2);
                originatorMemento.setMemento(memento);
                JOptionPane.showMessageDialog(menu, originatorMemento.getState());
            }else{
                JOptionPane.showMessageDialog(menu, "No additional location to save a log file has been selected yet ");
            }


        } else if (e.getSource() == mi[5])
            destroy();
        else if (e.getSource() == mi[6]) {// play
             if(null != this.thread) {
                 loadMap.map.play();
                 loadMap.speedSlider = speedSlider;
                 menu.repaint();
             } else {
                 if(file==null){
                     printLogFile();
                     return;
                 }
                 mi[0].setEnabled(false);
                 loadMap.stop = false;
                 loadMap.speedSlider = speedSlider;
                 mapDrawing.repaint();
                 thread = new Thread(() -> loadMap.simulationRepeat(menu));
                 thread.start();
                 menu.repaint();
             }
        } else if (e.getSource() == mi[7]) {//pause
            loadMap.map.pause();
            menu.repaint();
        } else if (e.getSource() == mi[8]) {//stop
            mi[0].setEnabled(true);
            loadMap.map.stop();
            menu.repaint();
        } else if (e.getSource() == mi[9]) {
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            spinner.getEditor().getComponent(0).setBackground(new Color(255, 164, 118, 255));
            JDialog dialog = new JDialog();
            dialog.setPreferredSize(new Dimension(130, 70));
            dialog.setTitle("Ticks Per Day");
            dialog.add(spinner, CENTER);
            dialog.setLocation(getLocationOnScreen());
            dialog.pack();
            dialog.setVisible(true);
            spinner.addChangeListener(e1 -> Clock.setTicks_per_day((Integer) spinner.getValue()));

        } else if (e.getSource() == mi[10]) {
            new HelpDialog(this);
        } else if (e.getSource() == mi[11]) {
            new AboutDialog(this);
        }
    }

    public VirusManager helpMethode(String s){
        if(s == "Chines Variant"){
            return VirusManager.CHINES;
        }
        if(s == "British Variant"){
            return VirusManager.BRITISH;
        }
        else {
            return VirusManager.SOUTH_AFRICAN;
        }

    }

    public void destroy() {
        System.exit(0);
    }//to exit!

    public void printLogFile() {
        JOptionPane.showMessageDialog(this, "Please choose a path to save the log file!");
    }

    public void printLoadFile() {
        JOptionPane.showMessageDialog(this, "Please load a file");
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            if (mapDrawing.shapeList()!= null) {
                for (Shape shape:mapDrawing.shapeList()) {
                    if (shape.contains(event.getPoint())) {
                        int index = mapDrawing.shapeList().indexOf(shape);
                        statisticsFrame.MyTable.setRowSelectionInterval(index, index);
                        mapDrawing.repaint();
                        statisticsFrame.MyJFrame.repaint();
                        statisticsFrame.MyJFrame.setVisible(true);
                        menu.repaint();
                    }
                }
            }
        }
    }
}


