package com.example.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@AerospikeRecord(namespace="test", set="people")
public class SandBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SandBox(long id) {
        this.id = id;
    }
    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "Taluk")
    private String Taluk;

    @Column(name = "duration")
    private String duration;
    @Column(name = "Summary")
    private String Summary;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaluk() {
        return Taluk;
    }

    public void setTaluk(String taluk) {
        Taluk = taluk;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public SandBox() {
        this.city = city;
        this.state = state;
        Taluk = taluk;
        this.duration = duration;
        Summary = summary;
    }


}
