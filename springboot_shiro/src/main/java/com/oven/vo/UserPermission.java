package com.oven.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserPermission implements Serializable {

    @Id
    @GeneratedValue
    private Integer uid;
    private Integer mid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
                "uid=" + uid +
                ", mid=" + mid +
                '}';
    }

}
