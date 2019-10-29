package TrafficSimulator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;

public class MainFrame extends JFrame{
    private JLabel header, statusLabel, status, mode;
    private JButton startButton, exitButton, editButton, menuButton;
    private boolean editable;
    private boolean[] spots = {false, false, false, false, false};
    private Road[] roadArray = {null, null, null, null, null};
    private TrafficLight[] trafficLights ={new TrafficLight(true), new TrafficLight(true), new TrafficLight(true), new TrafficLight(true), new TrafficLight(true)};
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    static Timer systemTimer;
    Random random = new Random();
    private int timeRate = 500;
    JPanel grid1 = new JPanel();
    JPanel[] roadSegments1 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel grid2 = new JPanel();
    JPanel[] roadSegments2 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel grid3 = new JPanel();
    JPanel[] roadSegments3 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel grid4 = new JPanel();
    JPanel[] roadSegments4 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel grid5 = new JPanel();
    JPanel[] roadSegments5 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};

    MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        file.add(load);
        file.add(save);
        menuBar.add(file);
        header = new JLabel("Traffic Simulator");
        JPanel menuPanel = new JPanel();
        JPanel mainGrid = new JPanel();
        JPanel space1 = new JPanel();
        JPanel space2 = new JPanel();
        JPanel space3 = new JPanel();
        JPanel space4 = new JPanel();
        JPanel space5 = new JPanel();
        JPanel newExitPanel = new JPanel();
        JPanel trafficGrid = new JPanel();
        JPanel bottomPanel = new JPanel();
        JLabel mode = new JLabel("Mode: ");
        JLabel status = new JLabel("");
        JLabel statusLabel = new JLabel("Status: ");
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");
        JButton editButton = new JButton("Edit");
        JButton menuButton = new JButton("Menu");
        menuPanel.setLayout(new GridLayout(5,1));
        grid5.setBackground(Color.WHITE);
        grid4.setBackground(Color.WHITE);
        grid3.setBackground(Color.WHITE);
        grid2.setBackground(Color.WHITE);
        grid1.setBackground(Color.WHITE);
        mainGrid.setLayout(new GridLayout(3,1));
        mainGrid.add(space1);
        mainGrid.add(trafficGrid);
        mainGrid.add(space2);
        trafficGrid.setLayout(new GridLayout(1,5));
        trafficGrid.add(grid1);
        trafficGrid.add(grid2);
        trafficGrid.add(grid3);
        trafficGrid.add(grid4);
        trafficGrid.add(grid5);
        grid1.setLayout(new GridLayout(1, 5));
        grid2.setLayout(new GridLayout(1, 5));
        grid3.setLayout(new GridLayout(1, 5));
        grid4.setLayout(new GridLayout(1, 5));
        grid5.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++){
            roadSegments1[i].setVisible(false);
            grid1.add(roadSegments1[i]);
            roadSegments2[i].setVisible(false);
            grid2.add(roadSegments2[i]);
            roadSegments3[i].setVisible(false);
            grid3.add(roadSegments3[i]);
            roadSegments4[i].setVisible(false);
            grid4.add(roadSegments4[i]);
            roadSegments5[i].setVisible(false);
            grid5.add(roadSegments5[i]);
        }
        bottomPanel.setLayout(new GridLayout(1,5));
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        statusLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        mode.setFont(new Font("Serif", Font.PLAIN, 25));
        status.setFont(new Font("Serif", Font.PLAIN, 25));
        startButton.setFont(new Font("Serif", Font.PLAIN, 25));
        exitButton.setFont(new Font("Serif", Font.PLAIN, 25));
        add(header, BorderLayout.NORTH);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(space3);
        menuPanel.add(startButton);
        menuPanel.add(space4);
        menuPanel.add(exitButton);
        menuPanel.add(space5);
        bottomPanel.add(mode);
        mode.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(editButton);
        bottomPanel.add(menuButton);
        bottomPanel.add(statusLabel);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(status);
        add(mainGrid);
        add(menuPanel,BorderLayout.WEST);
        add(bottomPanel,BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        grid1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(editable) {
                    System.out.println("Worked");
                    if(!spots[0]){
                        Road road = new Road(5, "straight", 50);
                        roadArray[0] = road;
                        spots[0] = true;
                        grid1.setBackground(Color.DARK_GRAY);
                    }
                    else {

                    }

                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print(roadArray);
                dispose();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                run();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editable = !editable;
            }
        });
    }
    public void run() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                tickTask();
                }
            };
        systemTimer = new Timer();
        systemTimer.scheduleAtFixedRate(timerTask, 1000, timeRate);
        }

    public void tickTask() {
        System.out.println("Worked");
        if (random.nextFloat() > .8f) {
            Vehicle vehicle = new Vehicle("Car");
            for (int i = 0; i < spots.length; i++) {
                if (spots[i]) {
                    vehicle.setPosition(roadArray[i].getSegments());
                    vehicle.setRoadNumber(i);
                    vehicles.add(vehicle);
                    System.out.println("Worked");
                    roadSegments1[0].setVisible(true);
                    break;
                }
            }
        }
        for(int i = 0; i < vehicles.size(); i ++){
            if (vehicles.get(i).position <= 4) {
                switch (vehicles.get(i).getRoadNumber()){
                    case 0 :
                        roadSegments1[0].setVisible(true);
                        break;
                    case 1 :
                        roadSegments2[0].setVisible(true);
                        break;
                    case 2 :
                        roadSegments3[0].setVisible(true);
                        break;
                    case 3 :
                        roadSegments4[0].setVisible(true);
                        break;
                    case 4 :
                        roadSegments5[0].setVisible(true);
                        break;
                }
                ++vehicles.get(i).position;
            }
            else if(trafficLights[vehicles.get(i).getRoadNumber()].getStatus()){
                if (i == 4 || spots[i + 1]){
                    vehicles.get(i).setRoadNumber(i + 1);
                    vehicles.get(i).setPosition(0);
                }
            }
        }
    }
}
