package fr.esilv.myapplication2.mobiledev.app.Contract;

import java.util.List;

/**
 * Created by louis on 29/01/2016.
 */
public class Contract {
    private String name;

    private String commercial_name;

    public String getName() {
        return name;
    }

    public String getCommercial_name() {
        return commercial_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommercial_name(String commercial_name) {
        this.commercial_name = commercial_name;
    }

    public Contract(String name, String commercial_name) {
        this.name = name;

        this.commercial_name = commercial_name;
    }
    
}
