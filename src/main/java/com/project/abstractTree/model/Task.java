package com.project.abstractTree.model;

import java.time.Instant;

/**
 * Class for tasks in time manager
 *
 * @author Sergey
 * @version 1.0.0
 */

public class Task {
    private int id;
    /**
     * Field for name of task
     */
    private String name;
    /**
     * Field for display active task
     */
    private Instant timeStart;
    /**
     * Field for storing the ending of active tasks
     */
    private Instant timeStop;
    private long timeDayActivity;

    public Task(int id, String name) {
        timeDayActivity = 0;
        this.id = id;
        this.name = name;
    }

    public void Activate() {
        timeStart = Instant.now();
    }

    public void Deactivate() {
        timeStop = Instant.now();
        timeDayActivity += timeStop.getEpochSecond() - timeStart.getEpochSecond();
        timeStop = null;
        timeStart = null;
    }

    public boolean isActive() {
        return timeStart != null && timeStop == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeDayActivity() {
        return timeDayActivity;
    }
}
