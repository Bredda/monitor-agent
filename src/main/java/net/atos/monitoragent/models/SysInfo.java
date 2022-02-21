package net.atos.monitoragent.models;

import com.opencsv.bean.CsvRecurse;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class SysInfo {

    private final String agent;
    private final String date;
    @CsvRecurse
    private final SysInfoSys system;
    @CsvRecurse
    private final SysInfoCpu cpu;
    @CsvRecurse
    private final SysInfoProcess process;
    @CsvRecurse
    private final SysInfoMemory memory;

    public SysInfo(String agentName) {
        this.agent = agentName ;
        this.system = new SysInfoSys();
        this.cpu = new SysInfoCpu();
        this.process = new SysInfoProcess();
        this.memory = new SysInfoMemory();
        Date currDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = dateFormat.format(currDate);
    }

}
