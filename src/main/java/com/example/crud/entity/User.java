package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "tbl_users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;
    private String name;
    private String state;
    private String lastname;
    private String username;
    private String city;
    private int zip;

}
