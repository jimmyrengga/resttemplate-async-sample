/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jimmyrengga.sample.asynctask;

import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author jimmy
 */
@Configuration
@EnableAsync
@ComponentScan
public class Runner {
    
    @Autowired
    private SimpleService sampleService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Scheduled(cron = "*/30 * * * * ?")
    public void scheduledHitUrl() throws Exception {
        
        logger.info("Scheduler is start");
        
        runAsync();
//        runSync();
        
        logger.info("Scheduler is finish");
        
    }
    
    private void runAsync() throws Exception {
        long start = System.currentTimeMillis();
        
        Future<Page> page1 = sampleService.findPageAsync("GoPivotal");
        Future<Page> page2 = sampleService.findPageAsync("CloudFoundry");
        Future<Page> page3 = sampleService.findPageAsync("SpringFramework");
        
        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
            Thread.sleep(10); //millisecond pause between each check
        }

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info(page1.get().toString());
        logger.info(page2.get().toString());
        logger.info(page3.get().toString());
    }
    
    private void runSync() throws Exception {
        long start = System.currentTimeMillis();
        
        Page page1 = sampleService.findPageSync("GoPivotal");
        Page page2 = sampleService.findPageSync("CloudFoundry");
        Page page3 = sampleService.findPageSync("SpringFramework");
        
        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info(page1.toString());
        logger.info(page2.toString());
        logger.info(page3.toString());
    }
    
}
