package com.javierjimenez.springbootdemo.entity;

import javax.persistence.*;


@Entity
@Table(name="`role`",uniqueConstraints = {@UniqueConstraint(columnNames = "rolename")})
public class Role extends AbstractEntity
{
    
}
