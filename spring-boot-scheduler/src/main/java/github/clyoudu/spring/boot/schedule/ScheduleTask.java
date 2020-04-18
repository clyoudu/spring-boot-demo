package github.clyoudu.spring.boot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leichen
 * @date 2020/3/24 3:09 下午
 */
@Component
@Slf4j
public class ScheduleTask {

    @Scheduled(cron = "0/10 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("task1");
        Thread.sleep(1000L);
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void task2() throws InterruptedException {
        log.info("task2");
        Thread.sleep(15000L);
    }

    /**
     * 2020-03-24 16:39:30.002  INFO 4106 --- [    task-pool-2] g.c.spring.boot.schedule.ScheduleTask    : task1
     * 2020-03-24 16:39:30.002  INFO 4106 --- [    task-pool-1] g.c.spring.boot.schedule.ScheduleTask    : task2
     * 2020-03-24 16:39:40.002  INFO 4106 --- [    task-pool-2] g.c.spring.boot.schedule.ScheduleTask    : task1
     * 2020-03-24 16:39:50.002  INFO 4106 --- [    task-pool-2] g.c.spring.boot.schedule.ScheduleTask    : task1
     * 2020-03-24 16:39:50.002  INFO 4106 --- [    task-pool-3] g.c.spring.boot.schedule.ScheduleTask    : task2
     */
}
