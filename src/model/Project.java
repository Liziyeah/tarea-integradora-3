package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.plaf.nimbus.State;

public class Project {
  protected String id;
  protected String name;
  protected StatusRequest state;
  protected Date clasifingDate;
  protected PriorityProject priorityProject;
  protected String leader;
  protected Date closingDate;
  protected Date estimatedClosingDate;
  protected int projectType;

  public Project(String id, String name, int state, Date clasifingDate, int priorityProject, Date estimatedClosingDate,
  String leader, int projectType) {
    this.id = id;
    this.name = name;
    this.state = setStateReturn(state);
    this.clasifingDate = clasifingDate;
    this.priorityProject = setPriorityProject(priorityProject);
    this.estimatedClosingDate = estimatedClosingDate;
    this.leader = leader;
    this.projectType = projectType;
  }

  ArrayList<Collaborator> collaboratorList = new ArrayList<>();
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

  public StatusRequest getState() {
    return state;
  }

  public Date getClasifingDate() {
    return clasifingDate;
  }

  public void setClasifingDate(Date clasifingDate) {
    this.clasifingDate = clasifingDate;
  }

  public PriorityProject getPriorityProject() {
    return priorityProject;
  }

  public String getLeader() {
    return leader;
  }

  public void setLeader(String leader) {
    this.leader = leader;
  }
  public PriorityProject setPriorityProject(int priorityProject) {
    return PriorityProject.values()[priorityProject];
  }

  public void setState(int state) {
    this.state = StatusRequest.values()[state];
  }

  public StatusRequest setStateReturn(int state) {
    return StatusRequest.values()[state];
  }

  public void setClosingDate(Date closingDate) {
    this.closingDate = closingDate;
  }

  public Date getClosingDate() {
    return closingDate;
  }

  public Date getEstimatedClosingDate() {
    return estimatedClosingDate;
  }

  public void setEstimatedClosingDate(Date estimatedClosingDate) {
    this.estimatedClosingDate = estimatedClosingDate;
  }

  public int getProjectType() {
    return projectType;
  }

  public void setProjectType(int projectType) {
    this.projectType = projectType;
  }
  
}
