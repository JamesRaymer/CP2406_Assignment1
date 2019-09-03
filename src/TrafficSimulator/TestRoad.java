package TrafficSimulator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestRoad {
    public TestRoad() {
    }

    @Test
    public void checkStraightRoad() {
        Road road = new Road(5, "Straight");
        String type = "Straight";
        int segments = 5;
        road.setType("Straight");
        Assertions.assertEquals(road.getSegments(), segments);
        Assertions.assertEquals(road.getType(), type);
    }
}
