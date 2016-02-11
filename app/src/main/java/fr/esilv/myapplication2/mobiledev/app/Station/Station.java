package fr.esilv.myapplication2.mobiledev.app.Station;

/**
 * Created by louis on 05/02/2016.
 */
public class Station {
    String name;
    String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Station(String name, String address) {

        this.name = name;
        this.address = address;
    }
}
