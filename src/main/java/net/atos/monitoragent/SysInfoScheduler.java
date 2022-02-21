package net.atos.monitoragent;

import net.atos.monitoragent.services.SchedulerState;
import net.atos.monitoragent.services.SysInfoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class SysInfoScheduler {

    private final SysInfoStorageService sysInfoStorageService;
    private final SchedulerState schedulerState;

    public SysInfoScheduler(
            @Autowired SysInfoStorageService sysInfoStorageService,
            @Autowired SchedulerState schedulerState
    ) {
        this.sysInfoStorageService = sysInfoStorageService;
        this.schedulerState = schedulerState;
    }

    @Scheduled(fixedDelayString = "${scheduler.delay}")
    public void reportSystemInfo() {
        if (schedulerState.isExecuting()) {
            this.sysInfoStorageService.takeMeasure();
        }
    }
}
