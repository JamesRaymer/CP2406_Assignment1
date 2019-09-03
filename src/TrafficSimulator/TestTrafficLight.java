package TrafficSimulator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestTrafficLight {
    public TestTrafficLight() {
    }

    @Test
    public void checkTrafficLight() {
        TrafficLight trafficLight = new TrafficLight(true);
        Boolean condition = true;
        Assertions.assertEquals(trafficLight.getStatus(), condition);
    }
}
