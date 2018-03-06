package com.timer;

import com.service.TimerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("autoSelect")
public class AutoSelect {

    private Logger logger = Logger.getLogger(AutoSelect.class);

    @Autowired
    private TimerService timerService;
    public void  execute()  {
        synchronized(this) {
            logger.info("------------start auto select-----------------------");
            timerService.start();
            logger.info("------------end auto select-----------------------");
        }


    }
}
