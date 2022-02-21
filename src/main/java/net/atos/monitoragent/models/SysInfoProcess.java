package net.atos.monitoragent.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysInfoProcess {

    private double processCpuLoad;
    private long processCpuTime;
    private long processAllocatedMemory;
    private long processTotalMemory;
    private long processPresumableFreeMemory;
    private float processMemoryLoad;

}
