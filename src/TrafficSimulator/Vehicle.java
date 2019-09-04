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

    void setType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }

    private void setLength(double length) {
        this.length = length;
        length = 2.0;
        String type = this.type;
        switch(type) {
            case "Bus":
                this.length = length * 3.0;
                break;
            case "Car":
                this.length = length;
                break;
            case "Motorbike":
                this.length = length * 0.5;
                break;
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

    double calculateBreadth() {
        return this.breadth = this.length / 2.0;
    }
}
