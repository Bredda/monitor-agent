package net.atos.monitoragent.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysInfoMemory {

    private long committedVirtualSize;
    private long totalSwapSize;
    private long freeSwapSize;
    private long freeMemory;
    private long totalMemory;

}
