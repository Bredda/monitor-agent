package net.atos.monitoragent.models;

import lombok.Getter;
import net.atos.monitoragent.services.SysInfoService;

@Getter
public class SysInfoCpu {

    private final int availableProcessors;
    private final double cpuLoad;
    private final double cpuAverageLoad;


    public SysInfoCpu() {
        SysInfoService sys = SysInfoService.getInstance();
        this.availableProcessors = sys.getAvailableProcessors();
        this.cpuAverageLoad = sys.getProcessCpuLoad();
        this.cpuLoad = sys.getProcessCpuLoad();
    }
}
