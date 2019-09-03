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

    void greenLight() {
        System.out.println("The traffic light is green. The car is moving!");
    }

    void redLight() {
        System.out.println("The traffic light is red. The car is halted.");
    }
}
