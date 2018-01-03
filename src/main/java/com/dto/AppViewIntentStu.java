package com.dto;

import com.entity.Topics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppViewIntentStu {

    private List<TopicStudent> topics = new ArrayList<TopicStudent>();

    private int batch = 0;

    private int choice = 0;

    public List<TopicStudent> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicStudent> topics) {
        this.topics = topics;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
