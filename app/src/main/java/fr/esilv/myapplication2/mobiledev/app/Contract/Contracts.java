package fr.esilv.myapplication2.mobiledev.app.Contract;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by louis on 05/02/2016.
 */
public class Contracts extends ArrayList<Contract> {
    public Contracts(Collection<? extends Contract> collection) {
        super(collection);
    }

    public Contracts(int capacity) {
        super(capacity);
    }

    public Contracts() {
    }
}
