package cn.iflyapi.ihungry.model;

/**
 * @author: qfwang
 * @date: 2018-12-08 2:09 PM
 */
public class Food {

    private Integer id;
    private String name;
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
