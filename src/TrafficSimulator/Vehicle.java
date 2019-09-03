package TrafficSimulator;

class Vehicle {
    private String type;
    private double length;
    private double breadth;

    Vehicle(String type) {
        this.setType(type);
        this.setLength(this.length);
        this.setBreadth(this.breadth);
    }

    private void setType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }

    private void setLength(double length) {
        this.length = length;
        length = 2.0;
        String type = this.type;
        byte count = -1;
        switch(type) {
            case "Bus":
                count = 1;
                break;
            case "Car":
                count = 0;
                break;
            case "Motorbike":
                count = 2;
        }

        switch(count) {
            case 0:
                this.length = length;
                break;
            case 1:
                this.length = length * 3.0;
                break;
            case 2:
                this.length = length * 0.5;
        }

    }

    double getLength() {
        return this.length;
    }

    double getBreadth() {
        this.calculateBreadth();
        return this.breadth;
    }

    private void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    private void calculateBreadth() {
        this.breadth = this.length / 2.0;
    }
}
