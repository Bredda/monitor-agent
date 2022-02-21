package net.atos.monitoragent;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import net.atos.monitoragent.models.SysInfo;
import net.atos.monitoragent.payload.MessageResponse;
import net.atos.monitoragent.services.SchedulerService;
import net.atos.monitoragent.services.SysInfoService;
import net.atos.monitoragent.services.SysInfoStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class SysInfoController {

    @Value("${scheduler.delay}")
    private String delay;
    private static final Logger log = LoggerFactory.getLogger(SysInfoController.class);
    private final SysInfoStorageService sysInfoStorageService;
    private final SchedulerService schedulerService;
    private final SysInfoService sysInfoService;

    public SysInfoController(
            @Autowired SysInfoStorageService sysInfoStorageService,
            @Autowired SchedulerService schedulerService,
            @Autowired SysInfoService sysInfoService
    ) {
        this.sysInfoStorageService = sysInfoStorageService;
        this.schedulerService = schedulerService;
        this.sysInfoService = sysInfoService;
    }


    @GetMapping("/monitor-agent/one")
    public SysInfo getSysInfo() {
        return sysInfoService.getMeasure();
    }

    @GetMapping("/monitor-agent/start")
    public MessageResponse startSysInfoScheduler() {
        this.sysInfoStorageService.reset();
        this.schedulerService.start();
        String message = String .format("Starting measurement every %s milliseconds", this.delay);
        log.info(message);
        return new MessageResponse(200, message);
    }
    @GetMapping("/monitor-agent/stop")
    public MessageResponse stopSysInfoScheduler() {
        String message = "Stopping measurements";
        log.info(message);
        this.schedulerService.stop();
        return new MessageResponse(200, message);
    }
    @GetMapping("/monitor-agent/resume")
    public MessageResponse resumeSysInfoScheduler() {
        this.schedulerService.start();
        String message = String.format("Resuming measurement every %s milliseconds", this.delay);
        log.info(message);
        return new MessageResponse(200, message);
    }
    @GetMapping(value = "/monitor-agent/measures", produces = "text/csv")
    public void getSysInfoSchedulerMeasure(HttpServletResponse servletResponse) {
        try {
            StatefulBeanToCsv<List<SysInfo>> beanToCsv = new StatefulBeanToCsvBuilder<List<SysInfo>>(servletResponse.getWriter())
                    .withSeparator(';')
                    .build();
            beanToCsv.write(this.sysInfoStorageService.getMeasures());
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
