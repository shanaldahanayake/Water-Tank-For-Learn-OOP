import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

class DisplayWindow extends JFrame{
    private JLabel displayLabel;

    DisplayWindow(){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Display Window");

        displayLabel=new JLabel();
        displayLabel.setFont(new Font("",1,20));
        add(displayLabel);
        setVisible(true);
    }
    public void setDisplayLabel(int waterLevel){
        this.displayLabel.setText(Integer.toString(waterLevel));
    }

}

class AlarmWindow extends JFrame{
    private JLabel alarmLabel;

    AlarmWindow(){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Alarm Window");
        setVisible(true);

        alarmLabel=new JLabel();
        alarmLabel.setFont(new Font("",1,20));
        add(alarmLabel);
        setVisible(true);
    }
    public void setAlarmLabel(int waterLevel){
        this.alarmLabel.setText(waterLevel>50?"On":"Off");
    }

}

class SplitterWindow extends JFrame{
    private JLabel splitterLabel;

    SplitterWindow(){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Splitter Window");
        setVisible(true);

        splitterLabel=new JLabel();
        splitterLabel.setFont(new Font("",1,20));
        add(splitterLabel);
        setVisible(true);
    }
    public void setSplitterLabel(int waterLevel){
        this.splitterLabel.setText(waterLevel>75?"On":"Off");
    }
}

class WatarTankWindow extends JFrame{
    private JSlider waterLevelSlider;
   
    private DisplayWindow displayWindow;
    private AlarmWindow alarmWindow;
    private SplitterWindow splitterWindow;

    WatarTankWindow(){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Water Tank Window");

        waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);
        waterLevelSlider.setMajorTickSpacing(10);
        waterLevelSlider.setPaintLabels(true);
        add(waterLevelSlider);
        setVisible(true);

        waterLevelSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e){
                int waterLevel=waterLevelSlider.getValue();
                displayWindow.setDisplayLabel(waterLevel);
                alarmWindow.setAlarmLabel(waterLevel);
                splitterWindow.setSplitterLabel(waterLevel);
            }
        });

        this.alarmWindow= new AlarmWindow();
        this.displayWindow=new DisplayWindow();
        this.splitterWindow=new SplitterWindow();
    }
}

class Demo{
    public static void main(String args[]){
        new WatarTankWindow();
    }
}
