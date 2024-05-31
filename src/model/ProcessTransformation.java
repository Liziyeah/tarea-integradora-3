package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProcessTransformation extends Project{
  private String processCode;

  public ProcessTransformation(String id, String name, int state, Date clasifingDate,
  int priorityProject, Date estimatedClosingDate, String leader, String processCode, int projectType) {
    super(id, name, state, clasifingDate, priorityProject, estimatedClosingDate, leader, projectType);
    this.processCode = processCode;
  }

  public String getProcessCode() {
    return processCode;
  }

  public void setProcessCode(String processCode) {
    this.processCode = processCode;
  }

}
