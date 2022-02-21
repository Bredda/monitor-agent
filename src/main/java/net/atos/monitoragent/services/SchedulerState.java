package net.atos.monitoragent.services;

import org.springframework.stereotype.Component;

@Component
public class SchedulerState {

    private boolean executing;

    public SchedulerState() { this.executing = false;}

    public void start() { this.executing = true; }
    public void stop() { this.executing = false; }
    public boolean isExecuting() { return this.executing; }
}
