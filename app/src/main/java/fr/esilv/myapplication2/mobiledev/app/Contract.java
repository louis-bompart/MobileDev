package fr.esilv.myapplication2.mobiledev.app;

import java.util.List;

/**
 * Created by louis on 29/01/2016.
 */
public class Contract {
    private String name;
    private List<String> cities;
    private String commercialName;
    private String countryCode;

    public Contract(String name, List<String> cities, String commercialName, String countryCode) {
        this.name = name;
        this.cities = cities;
        this.commercialName = commercialName;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public List<String> getCities() {
        return cities;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
