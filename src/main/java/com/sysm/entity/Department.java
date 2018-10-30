package com.sysm.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-15 12:02
 * @Team : 系统集成部
 * @description :
 **/

@Entity
@Table(name = "t_department")
public class Department implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
