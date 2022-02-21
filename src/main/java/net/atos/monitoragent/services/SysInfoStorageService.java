package net.atos.monitoragent.services;

import net.atos.monitoragent.models.SysInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Service storing system information measurements history
 */
@Component
public class SysInfoStorageService {
    private List<SysInfo> sysInfoList;
    private final SysInfoService sysInfoService;

    public SysInfoStorageService(@Autowired SysInfoService sysInfoService) {
        this.sysInfoService = sysInfoService;
        this.reset();
    }

    /**
     * Take a new measurement and add it to history
     */
    public void takeMeasure() {
        this.sysInfoList.add(this.sysInfoService.getMeasure());
    }

    /**
     * Reset the measurement history
     */
    public void reset() {
        this.sysInfoList = new ArrayList<>();
    }

    /**
     * The measurement current history
     * @return measurement history
     */
    public List<SysInfo> getMeasures() {
        return this.sysInfoList;
    }

}
