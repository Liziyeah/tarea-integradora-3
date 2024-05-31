package model;

import java.util.ArrayList;

public class Collaborator {
  protected String id;
  protected String name;
  protected String email;
  protected String extension;
  protected ArrayList<String> projects;

  public Collaborator(String id, String name, String email, String extension){
    this.id = id;
    this.name = name;
    this.email = email;
    this.extension = extension;
    this.projects = new ArrayList<>();
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public ArrayList<String> getProjects() {
    return projects;
  }

  public void setProjects(ArrayList<String> projects) {
    this.projects = projects;
  }

  public void addProject(String project){
    projects.add(project);
  }
}
