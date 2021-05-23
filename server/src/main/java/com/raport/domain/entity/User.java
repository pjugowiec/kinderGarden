package com.raport.domain.entity;

import com.raport.domain.enums.RoleType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "users")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;
}
