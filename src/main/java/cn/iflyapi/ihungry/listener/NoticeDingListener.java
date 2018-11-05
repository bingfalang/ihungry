package cn.iflyapi.ihungry.listener;

import cn.iflyapi.ihungry.task.NoticeBeginManager;
import cn.iflyapi.ihungry.task.NoticeEndManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:05 PM
 */
public class NoticeDingListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new NoticeBeginManager();
        new NoticeEndManager();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
