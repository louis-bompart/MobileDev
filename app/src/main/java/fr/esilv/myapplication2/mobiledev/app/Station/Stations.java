package fr.esilv.myapplication2.mobiledev.app.Station;

import fr.esilv.myapplication2.mobiledev.app.Contract.Contract;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by louis on 05/02/2016.
 */
public class Stations extends ArrayList<Station> {
    public Stations(Collection<? extends Station> collection) {
        super(collection);
    }

    public Stations(int capacity) {
        super(capacity);
    }

    public Stations() {
    }
}
