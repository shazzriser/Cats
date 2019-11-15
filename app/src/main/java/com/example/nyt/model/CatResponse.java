package com.example.nyt.model;

public class CatResponse {
    private String id;
    private Weight weight;
    private String name;
    private String description;
    private String temperament;
    private String origin;
    private String life_span;
    private String wikipedia_url;
    private String dog_friendly;

    public String getId() {
        return id;
    }

    public Weight getWeigth() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWeigth(Weight weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public void setDog_friendly(String dog_friendly) {
        this.dog_friendly = dog_friendly;
    }

}
