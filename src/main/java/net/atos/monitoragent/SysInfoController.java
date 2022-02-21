package net.atos.monitoragent;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import net.atos.monitoragent.models.SysInfo;
import net.atos.monitoragent.services.SchedulerService;
import net.atos.monitoragent.services.SysInfoStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SysInfoController {

    @Value("${scheduler.delay}")
    private String delay;
    @Value("${agent.name}")
    private String agentName;
    private static final Logger log = LoggerFactory.getLogger(SysInfoController.class);
    private final SysInfoStorageService sysInfoStorageService;
    private final SchedulerService schedulerService;

    public SysInfoController(
            @Autowired SysInfoStorageService sysInfoStorageService,
            @Autowired SchedulerService schedulerService
    ) {
        this.sysInfoStorageService = sysInfoStorageService;
        this.schedulerService = schedulerService;
    }


    @GetMapping("/monitor-agent/one")
    public SysInfo getSysInfo() {
        return new SysInfo(agentName);
    }

    @GetMapping("/monitor-agent/start")
    public boolean startSysInfoScheduler() {
        this.sysInfoStorageService.reset();
        this.schedulerService.start();
        log.info("Starting measurement every {} milliseconds", this.delay);
        return true;
    }
    @GetMapping("/monitor-agent/stop")
    public boolean stopSysInfoScheduler() {
        log.info("Stopping measurements");
        this.schedulerService.stop();
        return false;
    }
    @GetMapping("/monitor-agent/resume")
    public boolean resumeSysInfoScheduler() {
        this.schedulerService.start();
        log.info("Resuming measurement every {} milliseconds", this.delay);
        return true;
    }
    @GetMapping(value = "/monitor-agent/measures", produces = "text/csv")
    public void getSysInfoSchedulerMeasure(HttpServletResponse servletResponse) {
        try {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(servletResponse.getWriter())
                    .withSeparator(';')
                    .build();
            beanToCsv.write(this.sysInfoStorageService.getMeasures());
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
