package net.atos.monitoragent.models;

import lombok.Getter;
import net.atos.monitoragent.SysInfoService;

@Getter
public class SysInfoProcess {

    private final double processCpuLoad;
    private final long processCpuTime;
    private final long processAllocatedMemory;
    private final long processTotalMemory;
    private final long processPresumableFreeMemory;
    private final float processMemoryLoad;

    public SysInfoProcess() {
        SysInfoService sys = SysInfoService.getInstance();
        this.processAllocatedMemory = sys.getProcessAllocatedMemory();
        this.processCpuTime = sys.getProcessCpuTime();
        this.processMemoryLoad = sys.getProcessMemoryLoad();
        this.processTotalMemory = sys.getProcessTotalMemory();
        this.processPresumableFreeMemory = sys.getProcessPresumableFreeMemory();
        this.processCpuLoad = sys.getProcessCpuLoad();
    }

}
