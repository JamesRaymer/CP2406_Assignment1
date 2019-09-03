package TrafficSimulator;

public class Simulator {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("Bus");
        Road road1 = new Road(6, "straight");
        Road road2 = new Road(5, "Straight");
        TrafficLight trafficLight = new TrafficLight(true);
        int firstEndPoint = road1.getSegments();
        int secondEndPoint = road2.getSegments();
        byte roadSpeed = 50;

        while(firstEndPoint != 0) {
            --firstEndPoint;
            System.out.println("The " + vehicle.getType() + " is traveling at a speed of " + roadSpeed + "km/hr + \nand is " + 25 * firstEndPoint + " meters away from the end of the road.");
        }

        trafficLight.changeStatus();

        for(boolean condition = trafficLight.getStatus(); !condition; condition = trafficLight.getStatus()) {
            trafficLight.redLight();
            trafficLight.changeStatus();
        }

        trafficLight.greenLight();

        while(secondEndPoint != 0) {
            --secondEndPoint;
            System.out.println("The " + vehicle.getType() + " is traveling at a speed of " + roadSpeed + "km/hr + \nand is " + 25 * secondEndPoint + " meters away from the end of the road.");
        }

        System.out.println("Car has fell off the world and has ceased to exist.");
    }
}
