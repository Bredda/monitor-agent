package net.atos.monitoragent.models;

import lombok.Getter;
import net.atos.monitoragent.services.SysInfoService;

@Getter
public class SysInfoSys {
    private final String arch;
    private final String version;
    private final String name;

    public SysInfoSys() {
        SysInfoService sys = SysInfoService.getInstance();
        this.name = sys.getName();
        this.version = sys.getVersion();
        this.arch = sys.getArch();
    }
}
