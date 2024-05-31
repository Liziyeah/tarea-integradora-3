package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class KnowledgeManagement extends Project {
  private String suggestName;
  private PeopleCommunityType impactedCommunity;

  public KnowledgeManagement(String id, String name, int state, Date clasifingDate,
  int priorityProject, Date estimatedClosingDate, String leader, String suggestName, int impactedCommunity, int projectType) {
    super(id, name, state, clasifingDate, priorityProject, estimatedClosingDate, leader, projectType);
    this.suggestName = suggestName;
    this.impactedCommunity = setPeopleCommunityType(impactedCommunity);
  }

  public String getSuggestName() {
    return suggestName;
  }

  public void setSuggestName(String suggestName) {
    this.suggestName = suggestName;
  }

  public PeopleCommunityType getImpactedCommunity() {
    return impactedCommunity;
  }

  public PeopleCommunityType setPeopleCommunityType(int community) {
    return PeopleCommunityType.values()[community];
  }

  public void setState(int community) {
    this.impactedCommunity =  PeopleCommunityType.values()[community];
  }

}

