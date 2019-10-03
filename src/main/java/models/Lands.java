package models;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.swing.*;
import java.util.List;


public class Lands {
    private String name;
    private String email;
    private String property;
    private String purpose;
    private String location;
    private String plot;
    private String meansofpayment;
    private String price;
    private int id;

    public Lands(String name, String email, String property, String purpose, String location, String plot, String meansofpayment, String price) {
        this.name = name;
        this.email = email;
        this.property = property;
        this.purpose = purpose;
        this.location = location;
        this.plot = plot;
        this.meansofpayment = meansofpayment;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getMeansofpayment() {
        return meansofpayment;
    }

    public void setMeansofpayment(String meansofpayment) {
        this.meansofpayment = meansofpayment;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save(){
        try (Connection con =  DB.sql2o.open()){
            String sql = "INSERT INTO lands (name, email, property, location, meansofpayment, plot, price, purpose) VALUES (:name, :email, :property, :location, :meansofpayment, :plot, :price, :purpose)";
            this.id =(int) con.createQuery(sql, true)
                .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("property", this.property)
                    .addParameter("location", this.location)
                    .addParameter("meansofpayment", this.meansofpayment)
                    .addParameter("plot", this.plot)
                    .addParameter("price", this.price)
                    .addParameter("purpose", this.purpose)
                    .executeUpdate()
                    .getKey();
        }

    }

    public static List<Lands> allLands(){
        String sql = "SELECT * FROM lands;";
        try (Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Lands.class);
        }
    }
}




