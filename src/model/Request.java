package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Request {
  private Date registeredDate ;
  private StatusRequest state;
  private String description;
  private String nameCollaborator;
  private String requestArea;
  private String id;
  private String idCollaborator;

  public Request(Date registeredDate, String description, int state, String nameCollaborator, String requestArea, String id, String idCollaborator){
    this.registeredDate = registeredDate;
    this.state = setStateStatusRequest(state);
    this.description = description;
    this.nameCollaborator = nameCollaborator;
    this.requestArea = requestArea;
    this.id = id;
    this.idCollaborator = idCollaborator;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public StatusRequest getState() {
    return state;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNameCollaborator() {
    return nameCollaborator;
  }

  public void setNameCollaborator(String nameCollaborator) {
    this.nameCollaborator = nameCollaborator;
  }

  public String getRequestArea() {
    return requestArea;
  }

  public void setRequestArea(String requestArea) {
    this.requestArea = requestArea;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdCollaborator() {
    return idCollaborator;
  }

  public void setIdCollaborator(String idCollaborator) {
    this.idCollaborator = idCollaborator;
  }

  public StatusRequest setStateStatusRequest(int state) {
    return StatusRequest.values()[state];
  }

  public void setState(int state) {
    this.state =  StatusRequest.values()[state];
  }

}
