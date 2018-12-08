package cn.iflyapi.ihungry.model;

/**
 * @author: qfwang
 * @date: 2018-12-08 3:52 PM
 */
public class FavoriteCount {

    private String name;
    private String category;
    private Integer total;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
