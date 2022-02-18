package net.atos.monitoragent.models;

import lombok.Getter;
import net.atos.monitoragent.SysInfoService;

@Getter
public class SysInfoMemory {

    private final long committedVirtualSize;
    private final long totalSwapSize;
    private final long freeSwapSize;
    private final long freeMemory;
    private final long totalMemory;

    public SysInfoMemory() {
        SysInfoService sys = SysInfoService.getInstance();
        this.committedVirtualSize = sys.getCommittedVirtualMemorySize();
        this.totalSwapSize = sys.getTotalSwapSpaceSize();
        this.freeSwapSize = sys.getFreeSwapSpaceSize();
        this.freeMemory = sys.getFreeMemorySize();
        this.totalMemory = sys.getTotalMemorySize();
    }


}
