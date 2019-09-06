package TrafficSimulator;

class Road {
    private int segments;
    private String type;
    private Road nextRoad;
    private int speedLimit;

    Road(int segments, String type, int speedLimit) {
        this.segments = segments;
        this.type = type;
        this.speedLimit = speedLimit;
    }

    Road(int segments, String type, int speedLimit, Road nextRoad) {
        this.segments = segments;
        this.type = type;
        this.speedLimit = speedLimit;
        this.nextRoad = nextRoad;
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

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Road getNextRoad() {
        return nextRoad;
    }

    public void setNextRoad(Road nextRoad) {
        this.nextRoad = nextRoad;
    }
}
