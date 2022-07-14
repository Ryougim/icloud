package algorithm.bus;

import java.time.LocalDateTime;

/**
 * 行车动作记录
 */
public class DriveAction {
    private LocalDateTime actionTime; //动作时间
    private String actionDesc; //动作描述

    public DriveAction(String actionDesc) {
        this.actionTime = LocalDateTime.now();
        this.actionDesc = actionDesc;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public String getActionDesc() {
        return actionDesc;
    }
}
