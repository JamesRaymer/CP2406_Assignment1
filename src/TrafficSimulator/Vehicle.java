package TrafficSimulator;

class Vehicle {
    private String type;
    private double length;
    private double breadth;
    private int roadNumber;
    int position;

    Vehicle(String type) {
        this.setType(type);
        this.setLength(this.length);
        this.setBreadth(this.breadth);
        this.setPosition(0);
    }

    void setType(String type) {
        this.type = type;
    }

    void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
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

    int getRoadNumber() { return this.roadNumber;}
    void setRoadNumber(int roadNumber) { this.roadNumber = roadNumber;}
}
