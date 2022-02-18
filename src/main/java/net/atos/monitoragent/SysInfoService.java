package net.atos.monitoragent;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class SysInfoService {

    private static final SysInfoService instance = new SysInfoService();

    private final OperatingSystemMXBean operatingSystemMXBean;
    private final Runtime runtime;

    private SysInfoService() {
        operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        runtime = Runtime.getRuntime();
    }

    public static SysInfoService getInstance() {
        return instance;
    }

    public long getCommittedVirtualMemorySize() {
        return operatingSystemMXBean.getCommittedVirtualMemorySize();
    }

    public long getTotalSwapSpaceSize() {
        return operatingSystemMXBean.getTotalSwapSpaceSize();
    }

    public long getFreeSwapSpaceSize() {
        return operatingSystemMXBean.getFreeSwapSpaceSize();
    }

    public long getFreeMemorySize() {
        return operatingSystemMXBean.getFreeMemorySize();
    }

    public long getTotalMemorySize() {
        return operatingSystemMXBean.getTotalMemorySize();
    }

    /**
     * A value between 0 and 1 representing system's overall CPU usage.
     * Beware that the first readings will return 0 (see https://stackoverflow.com/a/20457130/778272).
     */
    public double getSystemCpuLoad() {
        return operatingSystemMXBean.getCpuLoad();
    }

    /**
     * On Linux, this is the average system load calculated over a period of 1 minute (matches the one reported by top).
     * Not sure about other systems.
     *
     * See this excellent article on the Linux average system load metric:
     * http://www.brendangregg.com/blog/2017-08-08/linux-load-averages.html
     */
    public double getSystemLoadAverage() {
        return operatingSystemMXBean.getSystemLoadAverage();
    }

    /**
     * A value between 0 and 1 representing this process' CPU usage.
     * Beware that the first readings will return 0 (see https://stackoverflow.com/a/20457130/778272).
     */
    public double getProcessCpuLoad() {
        return operatingSystemMXBean.getProcessCpuLoad();
    }

    public long getProcessCpuTime() {
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
    public long getProcessAllocatedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    /**
     * This is the maximum amount this JVM will ever get from the operating system (as set by the `-Xmx` parameter).
     * See https://stackoverflow.com/a/18366283/778272
     */
    public long getProcessTotalMemory() {
        return runtime.maxMemory();
    }

    /**
     * This is the total amount of memory still available for use by this process.
     * See https://stackoverflow.com/a/18366283/778272
     */
    public long getProcessPresumableFreeMemory() {
        return runtime.maxMemory() - getProcessAllocatedMemory();
    }

    /**
     * A value between 0 and 1 representing the amount of memory available to the JVM that is currently being used.
     */
    public float getProcessMemoryLoad() {
        return getProcessAllocatedMemory() / (float) getProcessTotalMemory();
    }

    /**
     * Returns the number of processors available. A machine with 6 cores and hyper-threading enabled will report 12
     * processors.
     */
    public int getAvailableProcessors() {
        return operatingSystemMXBean.getAvailableProcessors();
    }

    /**
     * The architecture of the machine (e.g.: "x86_64").
     */
    public String getArch() {
        return operatingSystemMXBean.getArch();
    }

    /**
     * The version of the operating system (e.g.: "10.14.6").
     */
    public String getVersion() {
        return operatingSystemMXBean.getVersion();
    }

    /**
     * The name of the operating system (e.g.: "Mac OS X").
     */
    public String getName() {
        return operatingSystemMXBean.getName();
    }


}
