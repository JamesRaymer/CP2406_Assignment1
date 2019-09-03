package TrafficSimulator;

class Road {
    private int segments;
    private String type;

    Road(int segments, String type) {
        this.segments = segments;
        this.type = type;
    }

    int getSegments() {
        return this.segments;
    }

    void setType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }
}
