package net.atos.monitoragent.services;

import com.sun.management.OperatingSystemMXBean;
import net.atos.monitoragent.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Service to poll system informations
 */
@Component
public class SysInfoService {

    @Value("${agent.name}")
    private String agentName;
    private final OperatingSystemMXBean operatingSystemMXBean;
    private final Runtime runtime;

    public SysInfoService() {
        operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        runtime = Runtime.getRuntime();
    }

    public SysInfo getMeasure() {
        SysInfo sys = new SysInfo();

        sys.setAgent(agentName);
        Date currDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sys.setDate(dateFormat.format(currDate));

        SysInfoSys sysInfoSys = new SysInfoSys();
        sysInfoSys.setName(this.getName());
        sysInfoSys.setArch(this.getArch());
        sysInfoSys.setVersion(this.getVersion());
        sys.setSystem(sysInfoSys);

        SysInfoCpu sysInfoCpu = new SysInfoCpu();
        sysInfoCpu.setAvailableProcessors(this.getAvailableProcessors());
        sysInfoCpu.setCpuLoad(this.getSystemCpuLoad());
        sysInfoCpu.setCpuAverageLoad(this.getSystemLoadAverage());
        sys.setCpu(sysInfoCpu);

        SysInfoProcess sysInfoProcess = new SysInfoProcess();
        sysInfoProcess.setProcessAllocatedMemory( getProcessAllocatedMemory());
        sysInfoProcess.setProcessCpuTime(this.getProcessCpuTime());
        sysInfoProcess.setProcessMemoryLoad(this.getProcessMemoryLoad());
        sysInfoProcess.setProcessTotalMemory(this.getProcessTotalMemory());
        sysInfoProcess.setProcessPresumableFreeMemory(this.getProcessPresumableFreeMemory());
        sysInfoProcess.setProcessCpuLoad(this.getProcessCpuLoad());
        sys.setProcess(sysInfoProcess);

        SysInfoMemory memory = new SysInfoMemory();
        memory.setCommittedVirtualSize(this.getCommittedVirtualMemorySize());
        memory.setTotalSwapSize(this.getTotalSwapSpaceSize());
        memory.setFreeSwapSize(this.getFreeSwapSpaceSize());
        memory.setFreeMemory(this.getFreeMemorySize());
        memory.setTotalMemory(this.getTotalMemorySize());
        sys.setMemory(memory);

        return sys;
    }

    private long getCommittedVirtualMemorySize() {
        return operatingSystemMXBean.getCommittedVirtualMemorySize();
    }

    private long getTotalSwapSpaceSize() {
        return operatingSystemMXBean.getTotalSwapSpaceSize();
    }

    private long getFreeSwapSpaceSize() {
        return operatingSystemMXBean.getFreeSwapSpaceSize();
    }

    private long getFreeMemorySize() {
        return operatingSystemMXBean.getFreeMemorySize();
    }

    private long getTotalMemorySize() {
        return operatingSystemMXBean.getTotalMemorySize();
    }

    /**
     * A value between 0 and 1 representing system's overall CPU usage.
     * Beware that the first readings will return 0 (see https://stackoverflow.com/a/20457130/778272).
     */
    private double getSystemCpuLoad() {
        return operatingSystemMXBean.getCpuLoad();
    }

    /**
     * On Linux, this is the average system load calculated over a period of 1 minute (matches the one reported by top).
     * Not sure about other systems.
     *
     * See this excellent article on the Linux average system load metric:
     * http://www.brendangregg.com/blog/2017-08-08/linux-load-averages.html
     */
    private double getSystemLoadAverage() {
        return operatingSystemMXBean.getSystemLoadAverage();
    }

    /**
     * A value between 0 and 1 representing this process' CPU usage.
     * Beware that the first readings will return 0 (see https://stackoverflow.com/a/20457130/778272).
     */
    private double getProcessCpuLoad() {
        return operatingSystemMXBean.getProcessCpuLoad();
    }

    private long getProcessCpuTime() {
        return operatingSystemMXBean.getProcessCpuTime();
    }

    /**
     * The amount of memory allocated by the program. `Runtime.totalMemory()` gives the total amount of memory already
     * obtained by the JVM from the operating system. Of those, `Runtime.freeMemory()` is the amount definitely
     * available for future use. That amount can increase, however. For the total amount of memory still available, see
     * getProcessPresumableFreeMemory().
     *
     * For more details, see https://stackoverflow.com/a/18366283/778272
     */
    private long getProcessAllocatedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    /**
     * This is the maximum amount this JVM will ever get from the operating system (as set by the `-Xmx` parameter).
     * See https://stackoverflow.com/a/18366283/778272
     */
    private long getProcessTotalMemory() {
        return runtime.maxMemory();
    }

    /**
     * This is the total amount of memory still available for use by this process.
     * See https://stackoverflow.com/a/18366283/778272
     */
    private long getProcessPresumableFreeMemory() {
        return runtime.maxMemory() - getProcessAllocatedMemory();
    }

    /**
     * A value between 0 and 1 representing the amount of memory available to the JVM that is currently being used.
     */
    private float getProcessMemoryLoad() {
        return getProcessAllocatedMemory() / (float) getProcessTotalMemory();
    }

    /**
     * Returns the number of processors available. A machine with 6 cores and hyper-threading enabled will report 12
     * processors.
     */
    private int getAvailableProcessors() {
        return operatingSystemMXBean.getAvailableProcessors();
    }

    /**
     * The architecture of the machine (e.g.: "x86_64").
     */
    private String getArch() {
        return operatingSystemMXBean.getArch();
    }

    /**
     * The version of the operating system (e.g.: "10.14.6").
     */
    private String getVersion() {
        return operatingSystemMXBean.getVersion();
    }

    /**
     * The name of the operating system (e.g.: "Mac OS X").
     */
    private String getName() {
        return operatingSystemMXBean.getName();
    }


}
