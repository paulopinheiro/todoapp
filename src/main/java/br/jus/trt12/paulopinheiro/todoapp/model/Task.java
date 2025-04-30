package br.jus.trt12.paulopinheiro.todoapp.model;

import java.sql.Timestamp;
import java.time.Instant;

public class Task {
    private Integer taskid;
    private Integer userid;
    private String task;
    private final Timestamp dateCreated;
    private String description;

    public Task() {
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public Task(Integer taskid, Integer userid, String task, String description, Timestamp dateCreated) {
        this.taskid = taskid;
        this.userid = userid;
        this.task = task;
        this.description = description;
        this.dateCreated = dateCreated;
    }
    public Task(Integer userid, String task, String description) {
        this.userid = userid;
        this.task = task;
        this.description = description;
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTaskid() {
        return taskid;
    }

    @Override
    public String toString() {
        return task;
    }
}
