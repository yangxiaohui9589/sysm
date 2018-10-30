package com.sysm.entity;

import javax.persistence.*;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-05-18 9:34
 * @Team : 系统集成部
 * @description :
 **/
@Entity
@Table(name="t_role")
public class Role implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    /** 状态 是否禁用*/
    private boolean disabled;
    /** 描述 */
    private String description;

    public Role() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
