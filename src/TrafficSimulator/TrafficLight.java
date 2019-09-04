package TrafficSimulator;

import java.util.Random;

class TrafficLight {
    private Boolean condition;

    TrafficLight(Boolean condition) {
        this.condition = condition;
    }

    Boolean getStatus() {
        return this.condition;
    }

    void changeStatus() {
        Random random = new Random();
        this.condition = random.nextBoolean();
    }
}
