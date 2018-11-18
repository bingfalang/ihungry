package cn.iflyapi.ihungry.model;

import java.util.Date;

/**
 * @author: qfwang
 * @date: 2018-11-17 4:59 PM
 */
public class ApplyRecord {

    private Long userId;
    private String name;
    private Date applyAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getApplyAt() {
        return applyAt;
    }

    public void setApplyAt(Date applyAt) {
        this.applyAt = applyAt;
    }
}
