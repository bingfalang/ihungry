package cn.iflyapi.ihungry.service;

import cn.iflyapi.ihungry.model.FavoriteCount;
import cn.iflyapi.ihungry.util.JSONResult;

import java.util.List;

/**
 * @author: qfwang
 * @date: 2018-12-08 3:37 PM
 */
public interface FavoriteService {

    JSONResult addFavorite(String[] foodIds, String userIP);

    List<FavoriteCount> countTodayFavorite();
}
