package TrafficSimulator;
public class Simulator {

    public static void main(String[] args) {
        Road road1 = new Road(6, "straight");
        Road road2 = new Road(5, "Straight");
        Vehicle vehicle = new Vehicle("Bus");
        TrafficLight trafficLight = new TrafficLight(true);
        vehicle.setPosition(road1.getSegments());
        //int secondEndPoint = road2.getSegments(); << Leaving in case change isn't correct coding practice.
        byte roadSpeed = 50;

        while(vehicle.position != 0) {
            --vehicle.position;
            System.out.println("The " + vehicle.getType() + " is traveling at a speed of " + roadSpeed + "km/hr + \nand is " + 25 * vehicle.position + " meters away from the end of the road.");
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
            System.out.println("The " + vehicle.getType() + " is traveling at a speed of " + roadSpeed + "km/hr + \nand is " + 25 * vehicle.position + " meters away from the end of the road.");
        }

        System.out.println("Car has fell off the world and has ceased to exist.");
    }
}
