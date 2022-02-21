package net.atos.monitoragent.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysInfoCpu {

    private int availableProcessors;
    private double cpuLoad;
    private double cpuAverageLoad;

}
