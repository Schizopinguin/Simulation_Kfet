package main.java;

public abstract class Event {
    private final int startingTime;

    public Event(int startingTime){
        this.startingTime = startingTime;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public abstract void run();
}
