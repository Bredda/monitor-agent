package net.atos.monitoragent;

import lombok.Getter;
import net.atos.monitoragent.models.SysInfoCpu;
import net.atos.monitoragent.models.SysInfoMemory;
import net.atos.monitoragent.models.SysInfoProcess;
import net.atos.monitoragent.models.SysInfoSys;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class SysInfo {

    private final String agent;
    private final String date;
    private final SysInfoSys system;
    private final SysInfoCpu cpu;
    private final SysInfoProcess process;
    private final SysInfoMemory memory;

    public SysInfo() {
        this.agent = System.getProperty("agentName") == null ? "agentName not set" : System.getProperty("agentName") ;
        this.system = new SysInfoSys();
        this.cpu = new SysInfoCpu();
        this.process = new SysInfoProcess();
        this.memory = new SysInfoMemory();
        Date currDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = dateFormat.format(currDate);
    }
}
