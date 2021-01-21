package com.project.abstract_tree.model;

import java.util.Date;

/**
 * Class for tasks in time manager
 *
 * @author Sergey
 * @version 1.0.0
 */
public class Task {
    /**
     * Field for name of task
     */
    private String name;
    /**
     * Field for display active task
     */
    private boolean active;
    /**
     * Field for storing the beginning of active tasks
     */
    private Date timeStart;
    /**
     * Field for storing the ending of active tasks
     */
    private Date timeStop;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public Date getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(Date timeStop) {
        this.timeStop = timeStop;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Task(String name) {
        this.name = name;
    }
}
