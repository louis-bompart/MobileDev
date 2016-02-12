package fr.esilv.myapplication2.mobiledev.app.Station;

/**
 * Created by louis on 05/02/2016.
 */
public class Station {
    String name;
    String address;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Station(String name, String address, Position position) {

        this.name = name;
        this.address = address;
        this.position = position;
    }

    Position position;

    public double getLat() {
        return position.getLat();
    }

    public void setLng(double lng) {
        position.setLng(lng);
    }

    public void setLat(double lat) {
        position.setLat(lat);
    }

    public double getLng() {
        return position.getLng();
    }

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
    public class Position {
        double lat;
        double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public Position(double lat, double lng) {

            this.lat = lat;
            this.lng = lng;
        }
    }
}
