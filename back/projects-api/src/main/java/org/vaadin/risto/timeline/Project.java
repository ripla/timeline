package org.vaadin.risto.timeline;

import com.google.common.collect.ImmutableList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
public class Project {

    private static List<String> PROJECT_COLORS = ImmutableList.of("#3090F0",
            "#EC6464", "#98DF58", "#F9DD51", "#24DCD4", "#EC64A5", "#685CB0",
            "#FF7D42", "#336190", "#AA514D", "#7FB053", "#BBA85B", "#247981",
            "#963970", "#4B56A8", "#9A593D");

    private final static Random random = ThreadLocalRandom.current();

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    private String projectId;

    @NotNull
    private String name;

    @NotNull
    private String color;

    private Project() {
    }

    public Project(String name) {
        this(name, PROJECT_COLORS.get(random.nextInt(PROJECT_COLORS.size())));
    }

    public Project(String name, String color) {
        this(UUID.randomUUID().toString(), name, color);
    }

    public Project(String projectId, String name, String color) {
        this.projectId = projectId;
        this.name = name;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
