package TrafficSimulator;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Simulator {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setSize(800,800);
        frame.setVisible(true);
        Road road2 = new Road(5, "straight", 50);
        Road road1 = new Road(6, "Straight", 60, road2);
        Vehicle vehicle = new Vehicle("Bus");
        TrafficLight trafficLight = new TrafficLight(true);
        //int secondEndPoint = road2.getSegments(); << Leaving in case change isn't correct coding practice.

        vehicle.setPosition(road1.getSegments());
        while(vehicle.position != 0) {
            --vehicle.position;
            System.out.println("The " + vehicle.getType() + " is traveling at a speed of " + road1.getSpeedLimit() + "\n" +
                    "km/hr and is " + 25 * vehicle.position + " meters away from the end of the road.");
        }

        trafficLight.changeStatus();

        for(boolean condition = trafficLight.getStatus(); !condition; condition = trafficLight.getStatus()) {
            System.out.println("The traffic light is red. The car is halted.");
            trafficLight.changeStatus();
        }

        System.out.println("The traffic light is green. The car is moving!");
        vehicle.setPosition(road2.getSegments());
        while(vehicle.position != 0) {
            --vehicle.position;
            System.out.println("The " + vehicle.getType() + " is traveling at a speed of " + road2.getSpeedLimit() + "\n" +
                    "km/hr and is " + 25 * vehicle.position + " meters away from the end of the road.");
        }

        System.out.println("Car has fell off the world and has ceased to exist.");
    }
}
