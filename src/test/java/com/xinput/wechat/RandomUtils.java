package com.xinput.wechat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-22 10:03
 */
public class RandomUtils {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    @Test
    public void ran() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        System.out.println(RandomStringUtils.randomAlphanumeric(10));
                    }
                }
            });
        }
        Thread.sleep(3000000);
    }

    @Test
    public void tt() {
        System.out.println(RandomStringUtils.randomAlphanumeric(-1));
    }
}
