package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.Food;
import cn.iflyapi.ihungry.util.JSONResult;

import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-12-08 2:08 PM
 */
public interface FoodService {

    JSONResult addFood(Food food);

    List<Food> listFood();

}
