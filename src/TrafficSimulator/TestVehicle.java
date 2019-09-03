package TrafficSimulator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestVehicle {
    public TestVehicle() {
    }

    @Test
    public void checkCar() {
        Vehicle vehicle = new Vehicle("Car");
        String type = "Car";
        vehicle.setType("Car");
        vehicle.calculateBreadth();
        Assertions.assertEquals(vehicle.getType(), type);
        Assertions.assertEquals(2.0, vehicle.getLength());
        Assertions.assertEquals(1.0, vehicle.calculateBreadth());
        Assertions.assertEquals(1.0, vehicle.getBreadth());
    }

    @Test
    public void checkBus() {
        Vehicle vehicle = new Vehicle("Bus");
        String type = "Bus";
        vehicle.setType("Bus");
        vehicle.calculateBreadth();
        Assertions.assertEquals(vehicle.getType(), type);
        Assertions.assertEquals(6.0, vehicle.getLength());
        Assertions.assertEquals(3.0, vehicle.calculateBreadth());
        Assertions.assertEquals(3.0, vehicle.getBreadth());
    }

    @Test
    public void checkMotorbike() {
        Vehicle vehicle = new Vehicle("Motorbike");
        String type = "Motorbike";
        vehicle.setType("Motorbike");
        vehicle.calculateBreadth();
        Assertions.assertEquals(vehicle.getType(), type);
        Assertions.assertEquals(1.0, vehicle.getLength());
        Assertions.assertEquals(0.5, vehicle.calculateBreadth());
        Assertions.assertEquals(0.5, vehicle.getBreadth());
    }
}
