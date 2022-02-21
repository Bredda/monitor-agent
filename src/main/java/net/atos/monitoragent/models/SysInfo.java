package net.atos.monitoragent.models;

import com.opencsv.bean.CsvRecurse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysInfo {

    private String agent;
    private String date;
    @CsvRecurse
    private SysInfoSys system;
    @CsvRecurse
    private SysInfoCpu cpu;
    @CsvRecurse
    private SysInfoProcess process;
    @CsvRecurse
    private SysInfoMemory memory;


}
