package net.atos.monitoragent.services;

import net.atos.monitoragent.SysInfoScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Service handling the scheduler activation.
 * It can start or stop it with a given delay.
 */
@Component
public class SchedulerService {

    private static final String SCHEDULED_TASKS = "scheduledTasks";
    private final ScheduledAnnotationBeanPostProcessor postProcessor;
    private final SysInfoScheduler sysInfoScheduler;
    private final SchedulerState schedulerState;

    public SchedulerService(
            @Autowired ScheduledAnnotationBeanPostProcessor postProcessor,
            @Autowired SysInfoScheduler sysInfoScheduler,
            @Autowired SchedulerState schedulerState
    ) {
        this.postProcessor = postProcessor;
        this.sysInfoScheduler = sysInfoScheduler;
        this.schedulerState = schedulerState;
    }

    /**
     * Starts the scheduler
     */
    public void start() {
        this.schedulerState.start();
        postProcessor.postProcessAfterInitialization(sysInfoScheduler, SCHEDULED_TASKS);
    }

    /**
     * Stops the scheduler
     */
    public void stop() {
        this.schedulerState.stop();
        postProcessor.postProcessBeforeDestruction(sysInfoScheduler, SCHEDULED_TASKS);
    }
}
