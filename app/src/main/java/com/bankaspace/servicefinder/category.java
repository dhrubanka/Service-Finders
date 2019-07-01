package com.bankaspace.servicefinder;

public class category {
    private int userid;
    private String name;
    private String category;
    private  String price;
    private String locality;
    private String imagepath;

    public category(int id,String name,String category,String price,String imagepath,String locality){
        this.userid=id;
        this.name=name;
        this.category=category;
        this.price=price;
        this.imagepath=imagepath;
        this.locality=locality;

    }

    public int getUserId() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getLocality() {
        return locality;
    }

    public String getImagepath() {
        return imagepath;
    }
}
