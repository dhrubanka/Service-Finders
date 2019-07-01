package com.bankaspace.servicefinder;

/**
 * Created by DC on 27-02-2018.
 */

public class User {
    private String username, email, gender,id,category,name,personalno,pricerange,businessdes,whatsapp,picpath,locality,address;
    public User(String id, String username, String email, String gender, String category, String name, String personalno, String pricerange, String businessdes, String whatsapp, String picpath, String locality, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.category=category;
        this.name=name;
        this.personalno=personalno;
        this.pricerange=pricerange;
        this.businessdes=businessdes;
        this.whatsapp=whatsapp;
        this.picpath=picpath;
        this.locality=locality;
        this.address=address;



    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getPersonalno() {
        return personalno;
    }

    public String getPricerange() {
        return pricerange;
    }

    public String getBusinessdes() {
        return businessdes;
    }

    public String getWhatsapp() {
        return whatsapp;
    }
    public String getPicpath() {
        return picpath;
    }

    public String getLocality() {
        return locality;
    }

    public String getAddress() {
        return address;
    }



    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
