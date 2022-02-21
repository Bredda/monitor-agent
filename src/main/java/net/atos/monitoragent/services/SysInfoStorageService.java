package net.atos.monitoragent.services;

import net.atos.monitoragent.models.SysInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Service storing system information measurements history
 */
@Component
public class SysInfoStorageService {
    @Value("${agent.name}")
    private String agentName;
    private List<SysInfo> sysInfoList;

    public SysInfoStorageService() {
        this.reset();
    }

    /**
     * Take a new measurement and add it to history
     */
    public void takeMeasure() {
        this.sysInfoList.add(new SysInfo(agentName));
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
