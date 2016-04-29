package com.example.aylin.customdatatable;

/**
 * Created by aylin on 29.04.2016.
 */

public class Model {

    private String id;
    private String aramaDurum;
    private String avukat;
    private String sicil;

    public Model(String id, String aramaDurum, String avukat, String sicil) {
        this.id = id;
        this.aramaDurum = aramaDurum;
        this.avukat = avukat;
        this.sicil = sicil;
    }

    public String getsNo() {
        return id;
    }

    public String getProduct() {
        return aramaDurum;
    }

    public String getCategory() {
        return avukat;
    }

    public String getPrice() {
        return sicil;
    }
}
