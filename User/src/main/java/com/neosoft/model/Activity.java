package com.neosoft.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "activity")
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String activityInfo;
}
