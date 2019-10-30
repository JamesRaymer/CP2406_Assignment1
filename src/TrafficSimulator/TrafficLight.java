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
        this.condition = !this.condition;
    }
}
