package net.atos.monitoragent.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysInfo {

    private String agent;
    private String date;
    private String arch;
    private String version;
    private String name;
    private int availableProcessors;
    private double cpuLoad;
    private double cpuAverageLoad;
    private double processCpuLoad;
    private long processCpuTime;
    private long processAllocatedMemory;
    private long processTotalMemory;
    private long processPresumableFreeMemory;
    private float processMemoryLoad;
    private long committedVirtualSize;
    private long totalSwapSize;
    private long freeSwapSize;
    private long freeMemory;
    private long totalMemory;


}
