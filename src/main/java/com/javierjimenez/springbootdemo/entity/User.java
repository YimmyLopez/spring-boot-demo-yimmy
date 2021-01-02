package com.javierjimenez.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="`user`",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User extends AbstractEntity {

    public enum Role {USER, ADMIN, USER_MANAGER}

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @JsonIgnore
    @ToString.Exclude
    @NotEmpty
    @NotNull
    private String password;
   
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;   
}
