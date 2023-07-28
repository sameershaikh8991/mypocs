package com.crud.dto;


//import org.springframework.data.relational.core.mapping.Table;



import org.springframework.data.annotation.Id;

public class StudentDto {
    @Id
    private int id;
    private String name;

    public StudentDto() {
    }

    public StudentDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
