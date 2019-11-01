package TrafficSimulator;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;

public class MainFrame extends JFrame{
    private JLabel header, statusLabel, mode;
    private int carCount;
    private JLabel status = new JLabel("" + carCount);
    private JButton startButton, exitButton, editButton, menuButton;
    private boolean editable;
    private boolean[] spots = {false, false, false, false, false};
    private Road[] roadArray = {null, null, null, null, null};
    private TrafficLight[] trafficLights ={new TrafficLight(false), new TrafficLight(true), new TrafficLight(true), new TrafficLight(true), new TrafficLight(true), new TrafficLight(true)};
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    static Timer systemTimer;
    Random random = new Random();
    private int timeRate = 250;
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
    JPanel lightGrid1 = new JPanel();
    JPanel[] lightSegments1 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel lightGrid2 = new JPanel();
    JPanel[] lightSegments2 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel lightGrid3 = new JPanel();
    JPanel[] lightSegments3 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel lightGrid4 = new JPanel();
    JPanel[] lightSegments4 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};
    JPanel lightGrid5 = new JPanel();
    JPanel[] lightSegments5 = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),};

    MainFrame() {
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
        final JLabel status = new JLabel("Not running.");
        JPanel menuPanel = new JPanel();
        JPanel mainGrid = new JPanel();
        JPanel space1 = new JPanel();
        JPanel space2 = new JPanel();
        JPanel space3 = new JPanel();
        JPanel space4 = new JPanel();
        JPanel space5 = new JPanel();
        JPanel space6 = new JPanel();
        JPanel trafficGrid = new JPanel();
        JPanel mainLightGrid = new JPanel();
        JPanel bottomPanel = new JPanel();
        JLabel mode = new JLabel("Mode: ");
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
        mainGrid.setLayout(new GridLayout(5,1));
        mainGrid.add(space1);
        mainGrid.add(mainLightGrid);
        mainGrid.add(trafficGrid);
        mainGrid.add(space2);
        mainGrid.add(space6);
        mainLightGrid.setLayout(new GridLayout(1,5));
        mainLightGrid.add(lightGrid1);
        mainLightGrid.add(lightGrid2);
        mainLightGrid.add(lightGrid3);
        mainLightGrid.add(lightGrid4);
        mainLightGrid.add(lightGrid5);
        lightGrid1.setLayout(new GridLayout(1,5));
        lightGrid2.setLayout(new GridLayout(1,5));
        lightGrid3.setLayout(new GridLayout(1,5));
        lightGrid4.setLayout(new GridLayout(1,5));
        lightGrid5.setLayout(new GridLayout(1,5));
        for (int i = 0; i < 5; i++){
            lightSegments1[i].setVisible(false);
            lightGrid1.add(lightSegments1[i]);
            lightSegments2[i].setVisible(false);
            lightGrid2.add(lightSegments2[i]);
            lightSegments3[i].setVisible(false);
            lightGrid3.add(lightSegments3[i]);
            lightSegments4[i].setVisible(false);
            lightGrid4.add(lightSegments4[i]);
            lightSegments5[i].setVisible(false);
            lightGrid5.add(lightSegments5[i]);
        }
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
        menuButton.setEnabled(false);
        startButton.setEnabled(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        grid1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(editable) {
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
        grid2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(editable) {
                    if(!spots[1]){
                        Road road = new Road(5, "straight", 50);
                        roadArray[1] = road;
                        spots[1] = true;
                        grid2.setBackground(Color.DARK_GRAY);
                    }
                    else {

                    }

                }
            }
        });
        grid3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(editable) {
                    if(!spots[2]){
                        Road road = new Road(5, "straight", 50);
                        roadArray[2] = road;
                        spots[2] = true;
                        grid3.setBackground(Color.DARK_GRAY);
                    }
                    else {

                    }

                }
            }
        });
        grid4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(editable) {
                    if(!spots[3]){
                        Road road = new Road(5, "straight", 50);
                        roadArray[3] = road;
                        spots[3] = true;
                        grid4.setBackground(Color.DARK_GRAY);
                    }
                    else {
                    }
                }
                else {
                }
            }
        });
        grid5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(editable) {
                    if(!spots[4]){
                        Road road = new Road(5, "straight", 50);
                        roadArray[4] = road;
                        spots[4] = true;
                        grid5.setBackground(Color.DARK_GRAY);
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
                startButton.setEnabled(false);
                editButton.setEnabled(false);
                status.setText("Running.");
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editable = !editable;
                startButton.setEnabled(false);
                editButton.setEnabled(false);
                menuButton.setEnabled(true);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startButton.setEnabled(true);
                menuButton.setEnabled(false);
                editButton.setEnabled(true);
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
        if (random.nextFloat() > .8f) {
            Vehicle vehicle = new Vehicle("Car");
            for (int i = 0; i < spots.length - 1; i++) {
                if (spots[i]) {
                    //vehicle.setPosition(roadArray[i].getSegments());
                    vehicle.setRoadNumber(i);
                    vehicles.add(vehicle);
                    carCount = vehicles.size();
                    roadSegments1[0].setVisible(true);
                    break;
                }
            }
        }
        for(int i = 0; i < vehicles.size(); i ++){
            if (vehicles.get(i).position < 4) {
                switch (vehicles.get(i).getRoadNumber()){
                    case 0 :
                        if(!roadSegments1[vehicles.get(i).position + 1].isVisible()){
                            ++vehicles.get(i).position;
                            roadSegments1[vehicles.get(i).position].setVisible(true);
                            roadSegments1[vehicles.get(i).position].setBackground(Color.GREEN);
                            if(vehicles.get(i).position != 0){
                                roadSegments1[vehicles.get(i).position - 1].setVisible(false);
                            }
                        }
                        break;
                    case 1 :

                        if(!roadSegments2[vehicles.get(i).position + 1].isVisible()){
                            ++vehicles.get(i).position;
                            roadSegments2[vehicles.get(i).position].setVisible(true);
                            roadSegments2[vehicles.get(i).position].setBackground(Color.GREEN);
                            if(vehicles.get(i).position != 0){
                                roadSegments2[vehicles.get(i).position - 1].setVisible(false);
                            }
                        }
                        break;
                    case 2 :

                        if(!roadSegments3[vehicles.get(i).position + 1].isVisible()){
                            ++vehicles.get(i).position;
                            roadSegments3[vehicles.get(i).position].setVisible(true);
                            roadSegments3[vehicles.get(i).position].setBackground(Color.GREEN);
                            if(vehicles.get(i).position != 0){
                                roadSegments3[vehicles.get(i).position - 1].setVisible(false);
                            }
                        }
                        break;
                    case 3 :

                        if(!roadSegments4[vehicles.get(i).position + 1].isVisible()){
                            ++vehicles.get(i).position;
                            roadSegments4[vehicles.get(i).position].setVisible(true);
                            roadSegments4[vehicles.get(i).position].setBackground(Color.GREEN);
                            if(vehicles.get(i).position != 0){
                                roadSegments4[vehicles.get(i).position - 1].setVisible(false);
                            }
                        }
                        break;
                    case 4 :

                        if(!roadSegments5[vehicles.get(i).position + 1].isVisible()){
                            ++vehicles.get(i).position;
                            roadSegments5[vehicles.get(i).position].setVisible(true);
                            roadSegments5[vehicles.get(i).position].setBackground(Color.GREEN);
                            if(vehicles.get(i).position != 0){
                                roadSegments5[vehicles.get(i).position - 1].setVisible(false);
                            }
                        }
                        break;
                }

            }
            else if(trafficLights[vehicles.get(i).getRoadNumber()].getStatus()){
                if (vehicles.get(i).getRoadNumber() == 4 || spots[vehicles.get(i).getRoadNumber() + 1]){
                    if (vehicles.get(i).getRoadNumber() == 4 ){
                        roadSegments5[4].setVisible(false);
                        vehicles.remove(i);
                        carCount = vehicles.size();
                    }
                    else{
                        switch (vehicles.get(i).getRoadNumber()){
                            case 0 :
                                roadSegments1[4].setVisible(false);
                                break;
                            case 1:
                                roadSegments2[4].setVisible(false);
                                break;
                            case 2:
                                roadSegments3[4].setVisible(false);
                                break;
                            case 3:
                                roadSegments4[4].setVisible(false);
                                break;
                            case 4:
                                roadSegments5[4].setVisible(false);
                                break;
                        }
                        vehicles.get(i).setRoadNumber(vehicles.get(i).getRoadNumber() + 1);
                        vehicles.get(i).setPosition(0);
                    }
                }
                else {
                    switch (vehicles.get(i).getRoadNumber()){
                        case 0 :
                            roadSegments1[4].setVisible(false);
                            break;
                        case 1:
                            roadSegments2[4].setVisible(false);
                            break;
                        case 2:
                            roadSegments3[4].setVisible(false);
                            break;
                        case 3:
                            roadSegments4[4].setVisible(false);
                            break;
                        case 4:
                            roadSegments5[4].setVisible(false);
                            break;
                    }
                }
            }

        }
        if (random.nextFloat() > .8f) {
            for( int i = 0; i < trafficLights.length; i++){
                trafficLights[i].changeStatus();
                if (trafficLights[0].getStatus() == false){
                    lightSegments1[4].setVisible(true);
                    lightSegments1[4].setBackground(Color.RED);
                }
                else {
                    lightSegments1[4].setVisible(true);
                    lightSegments1[4].setBackground(Color.GREEN);
                }
            }
        }
    }
}
