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
    }
}
