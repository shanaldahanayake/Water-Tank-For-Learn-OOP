import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

interface WaterLevelObserver{
    public void update(int waterLevel);
}

class DisplayWindow extends JFrame implements WaterLevelObserver{
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
    public void update(int waterLevel){
        this.displayLabel.setText(Integer.toString(waterLevel));
    }

}

class AlarmWindow extends JFrame implements WaterLevelObserver{
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
    public void update(int waterLevel){
        this.alarmLabel.setText(waterLevel>50?"On":"Off");
    }

}

class SplitterWindow extends JFrame implements WaterLevelObserver{
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
    public void update(int waterLevel){
        this.splitterLabel.setText(waterLevel>75?"On":"Off");
    }
}

class WatarTankWindow extends JFrame{
    private JSlider waterLevelSlider;
    private WaterTankController waterTankController;
   
    WatarTankWindow(WaterTankController waterTankController){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Water Tank Window");
        this.waterTankController=waterTankController;

        waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);
        waterLevelSlider.setMajorTickSpacing(10);
        waterLevelSlider.setPaintLabels(true);
        add(waterLevelSlider);
        setVisible(true);

        waterLevelSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e){
                int waterLevel=waterLevelSlider.getValue();
                waterTankController.setWaterLevel(waterLevel);
            }
        });
    }
   
}

class WaterTankController{
   WaterLevelObserver observers[]=new WaterLevelObserver[0];

    private int waterLevel;

    public void addWaterLevelObserver(WaterLevelObserver waterLevelObserver){
        WaterLevelObserver[] temp=new WaterLevelObserver[observers.length+1];
        for(int i=0;i<observers.length;i++){
            temp[i]=observers[i];
        }
        temp[temp.length-1]=waterLevelObserver;
        observers=temp;
    }

    public void setWaterLevel(int waterLevel){
        if(this.waterLevel!=waterLevel){
            this.waterLevel=waterLevel;
            notifyObject();
        }
    }
    public void notifyObject(){
        for(WaterLevelObserver waterLevelObserver:observers){
            waterLevelObserver.update(waterLevel);
        } 
    }
   
    
}


class Demo{
    public static void main(String args[]){
        WaterTankController wtc=new WaterTankController();
        wtc.addWaterLevelObserver(new AlarmWindow());
        wtc.addWaterLevelObserver(new DisplayWindow());
        wtc.addWaterLevelObserver(new SplitterWindow()); 

        WatarTankWindow wateWatarTankWindow=new WatarTankWindow(wtc);
    }

}
