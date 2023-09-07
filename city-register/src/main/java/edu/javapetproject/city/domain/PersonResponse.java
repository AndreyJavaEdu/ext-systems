package edu.javapetproject.city.domain;

public class PersonResponse {
    private boolean registered; //(Yes or No)
    private boolean temporal; // (Default false)

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "registered=" + registered +
                ", temporal=" + temporal +
                '}';
    }
}
