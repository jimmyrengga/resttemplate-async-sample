/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jimmyrengga.sample.asynctask;

import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jimmy
 */
@Service
public class SimpleService {

    @Autowired
    private RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //contoh memakai async    
    @Async
    public Future<Page> findPageAsync(String page) throws InterruptedException {
        logger.info("Looking up " + page);
        long start = System.currentTimeMillis();
        logger.info("Start [at] " + start);
        Page results = restTemplate.getForObject("http://graph.facebook.com/" + page, Page.class);
        Thread.sleep(1000L);
        logger.info("time " + page + " : " + (System.currentTimeMillis() - start));
        return new AsyncResult<Page>(results);
    }
    
    
    //Contoh tidak memakai async
    public Page findPageSync(String page) throws InterruptedException {
        System.out.println("Looking up " + page);
        long start = System.currentTimeMillis();
        Page results = restTemplate.getForObject("http://graph.facebook.com/" + page, Page.class);
        Thread.sleep(1000L);
        logger.info("time " + page + " : " + (System.currentTimeMillis() - start));
        return results;
    }
    
}
