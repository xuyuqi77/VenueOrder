package com.xyq.vo.task;

import com.xyq.vo.service.SportService;
import com.xyq.vo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * Created by yqxu2 on 2017/4/29.
 */
@Component
public class TaskManager {

    private UserService userService;
    private SportService sportService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    /**
     * 每天24：00更新数据库中所有用户的预定状态 以及场馆项目数量
     * cron公式格式: 秒/分/时/日期/月份/周几
     * *:every
     * ?:ignore
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void updateSQL() {
        // 将所有用户的预定状态置为 未预定
        userService.updateAllUserOrder();
        // 将所有场馆项目的数量恢复
        sportService.updateAllSportNum();
    }
}
