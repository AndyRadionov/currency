package aradionov.currencycollector.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class provides service scheduling
 * @author Andrey Radionov
 */
public class Scheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private ScheduledExecutorService scheduledExecutor;
    private Runnable service;

    /**
     * @param service the task to execute
     * @param period the period between successive executions
     * @param timeUnit the time unit of the initialDelay and period parameters
     */
    public Scheduler(Runnable service, long period, TimeUnit timeUnit) {
        LOGGER.debug(service.getClass().getSimpleName() + " Service Starting");
        this.service = service;

        Date midnight = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        long initialDelay = new Date(midnight.getTime() - System.currentTimeMillis()).getTime();

        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(service, initialDelay, period, timeUnit);
    }

    public void shutDownService() {
        LOGGER.debug(service.getClass().getSimpleName() + " Service Shutting Down");
        scheduledExecutor.shutdown();
    }
}
