package net.atos.monitoragent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysInfoController {

    @GetMapping("/monitor-agent")
    public SysInfo getSysInfo() {
        return new SysInfo();
    }
}
