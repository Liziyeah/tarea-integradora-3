package model;

public class InstitutionalArea {
  private String office;
  private String name;
  private String code;

  public InstitutionalArea(String office, String name, String code){
    this.office = office;
    this.name = name;
    this.code = code;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
