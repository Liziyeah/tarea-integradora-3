package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Controller {
  ArrayList<Request> requestList;
  ArrayList<Project> projectList;
  ArrayList<Collaborator> collabList;

  public Controller() {
    requestList = new ArrayList<>();
    projectList = new ArrayList<>();
    collabList = new ArrayList<>();
  }

  /**
   * Creates a new request and adds it to the request list.
   * @param registeredDate The date the request was registered.
   * @param description A description of the request.
   * @param state The state of the request.
   * @param nameCollaborator The name of the collaborator making the request.
   * @param requestArea The area of the request.
   * @param id The ID of the request.
   * @param idCollaborator The ID of the collaborator.
   * @return A message indicating the request was successfully registered.
   */
  public String createRequest(Date registeredDate, String description, int state, String nameCollaborator, String requestArea, String id, String idCollaborator) {
    Request request = new Request(registeredDate, description, state, nameCollaborator, requestArea, id, idCollaborator);
    requestList.add(request);
    return "La solicitud se ha registrado exitosamente";
  }

  /**
   * Creates a new collaborator and adds them to the collaborator list.
   * @param id The ID of the collaborator.
   * @param name The name of the collaborator.
   * @param email The email of the collaborator.
   * @param extension The extension of the collaborator.
   * @return A message indicating the collaborator was successfully registered.
   */
  public String createCollaborator(String id, String name, String email, String extension) {
    Collaborator collaborator = new Collaborator(id, name, email, extension);
    collabList.add(collaborator);
    return "El colaborador se ha registrado exitosamente";
  }

  /**
   * Creates a new knowledge management project and adds it to the project list.
   * @param id The ID of the project.
   * @param name The name of the project.
   * @param state The state of the project.
   * @param clasifingDate The classification date of the project.
   * @param priorityProject The priority of the project.
   * @param leader The leader of the project.
   * @param suggestName The suggested name for the project.
   * @param impactedCommunity The impacted community of the project.
   * @return A message indicating the project was successfully created.
   */
  public String createKnowledgeProject(String id, String name, int state, Date clasifingDate, int priorityProject, String leader, String suggestName, int impactedCommunity) {
    KnowledgeManagement knowledgeManagementProject = new KnowledgeManagement(id, name, state, clasifingDate, priorityProject, null, leader, suggestName, impactedCommunity, 1);
    projectList.add(knowledgeManagementProject);
    addProjectToCollaborator(leader, id);
    return "Proyecto de Gestión de conocimiento creado";
  }

  /**
   * Creates a new process transformation project and adds it to the project list.
   * @param id The ID of the project.
   * @param name The name of the project.
   * @param state The state of the project.
   * @param clasifingDate The classification date of the project.
   * @param priorityProject The priority of the project.
   * @param estimatedClosingDate The estimated closing date of the project.
   * @param leader The leader of the project.
   * @param processCode The process code of the project.
   * @return A message indicating the project was successfully created.
   */
  public String processTransformation(String id, String name, int state, Date clasifingDate, int priorityProject, Date estimatedClosingDate ,String leader, String processCode) {
    ProcessTransformation processTransformationProject = new ProcessTransformation(id, name, state, clasifingDate, priorityProject, estimatedClosingDate, leader, processCode, 0);
    projectList.add(processTransformationProject);
    addProjectToCollaborator(leader, id);
    return "Proyecto de Transformación de Mejoramiento creado";
  }

  /**
   * Changes the state of a request based on its ID.
   * @param state The new state of the request.
   * @param id The ID of the request.
   */
  public void changeState(int state, String id) {
    for (int i = 0; i < requestList.size(); i++) {
      if (requestList.get(i).getId().equals(id)) {
        requestList.get(i).setState(state);
      }
    }
  }

  /**
   * Closes a project and sets its closing date.
   * @param id The ID of the project.
   * @param closingDate The closing date of the project.
   * @return A message indicating the project was successfully closed.
   */
  public String closeProject(String id, Date closingDate) {
    for (int i = 0; i < projectList.size(); i++) {
      if (projectList.get(i).getId().equals(id)) {
        projectList.get(i).setState(4);
        projectList.get(i).setClosingDate(closingDate);
      }
    }
    return "Proyecto finalizado exitosamente";
  }

  /**
   * Adds a project to a collaborator's list of projects.
   * @param idCollaborator The ID of the collaborator.
   * @param idProject The ID of the project.
   */
  public void addProjectToCollaborator(String idCollaborator, String idProject) {
    for (int i = 0; i < collabList.size(); i++) {
      if (collabList.get(i).getId().equals(idCollaborator)) {
        collabList.get(i).addProject(idProject);
      }
    }
  }

  /**
   * Consults information about projects led by collaborators after a certain date.
   * @param date The date after which to consult projects.
   * @return A 2D array with information about the projects and their leaders.
   */
  public String[][] consultInformation(Date date) {
    String[][] leadersProjects = new String[collabList.size()][6];
    for (int i = 0; i < collabList.size(); i++) {
      leadersProjects[i][0] = collabList.get(i).getId() + " " + collabList.get(i).getName();
      for (int j = 0; j < projectList.size(); j++) {
        if ((collabList.get(i).getId().equals(projectList.get(j).getLeader())) && (projectList.get(j).getClasifingDate().after(date))) {
          leadersProjects[i][j + 1] = projectList.get(j).getPriorityProject() + " " + projectList.get(j).getClasifingDate();
        }
      }
    }
    return leadersProjects;
  }

  /**
   * Consults projects by a specific collaborator.
   * @param id The ID of the collaborator.
   * @return An array with information about the projects.
   */
  public String[] consultProjectsByColab(String id) {
    String[] collaboratorInfoProjects = new String[projectList.size()];
    int j = 0;
    for (int i = 0; i < collabList.size(); i++) {
      if (collabList.get(i).getId().equals(id)) {
        collaboratorInfoProjects[j] = projectList.get(i).getId() + "\n" + projectList.get(i).getName() + "\n" + projectList.get(i).getState() + "\n" + projectList.get(i).getClasifingDate() + "\n" + projectList.get(i).getPriorityProject() + "\n" + projectList.get(i).getLeader();
        j++;
      }
    }
    return collaboratorInfoProjects;
  }

  /**
   * Consults information about a specific collaborator.
   * @param id The ID of the collaborator.
   * @return A string with the collaborator's information.
   */
  public String consultCollaborator(String id) {
    String collaboratorInfo = "";
    for (int i = 0; i < collabList.size(); i++) {
      if (collabList.get(i).getId().equals(id)) {
        collaboratorInfo = collabList.get(i).getId() + "\n" + collabList.get(i).getName() + "\n" + collabList.get(i).getEmail() + "\n" + collabList.get(i).getExtension();
      }
    }
    return collaboratorInfo;
  }

  /**
   * Consults information about a specific project.
   * @param id The ID of the project.
   * @return A string with the project's information.
   */
  public String consultProject(String id) {
    String projectInfo = "";
    for (int i = 0; i < projectList.size(); i++) {
      if (projectList.get(i).getId().equals(id)) {
        projectInfo = projectList.get(i).getId() + "\n" + projectList.get(i).getName() + "\n" + projectList.get(i).getState() + "\n" + projectList.get(i).getClasifingDate() + "\n" + projectList.get(i).getPriorityProject() + "\n" + projectList.get(i).getLeader();
      }
    }
    return projectInfo;
  }

  /**
   * Consults the number of projects of a specific type and their priority.
   * @param type The type of projects to consult.
   * @return An array with the number of projects by priority and the total count.
   */
  public int[] consultTypeProjects(int type) {
    int[] cantidadDeProyectos = new int[5];
    int j = 0;
    for (int i = 0; i < projectList.size(); i++) {
      if (projectList.get(i).getProjectType() == type) {
        if (projectList.get(i).getPriorityProject().toString().equals("URGENT")) {
          cantidadDeProyectos[0]++;
        } else if (projectList.get(i).getPriorityProject().toString().equals("HIGH")) {
          cantidadDeProyectos[1]++;
        } else if (projectList.get(i).getPriorityProject().toString().equals("MEDIUM")) {
          cantidadDeProyectos[2]++;
        } else if (projectList.get(i).getPriorityProject().toString().equals("LOW")) {
          cantidadDeProyectos[3]++;
        }
        j++;
      }
    }
    cantidadDeProyectos[4] = j;
    return cantidadDeProyectos;
  }

  /**
   * Consults the number of requests received and managed within a date range.
   * @param dateMin The minimum date of the range.
   * @param dateMax The maximum date of the range.
   * @return An array with the count of received and managed requests.
   */
  public String[] consultarCantidadSolicitudes(Date dateMin, Date dateMax) {
    int recibido = 0;
    int gestionada = 0;
    for (int i = 0; i < requestList.size(); i++) {
      if ((requestList.get(i).getRegisteredDate().before(dateMax) && requestList.get(i).getRegisteredDate().after(dateMin))) {
        recibido++;
        if (requestList.get(i).getState().toString().equals("PROJECT") || (projectList.get(i).getState().toString().equals("CLOSED"))) {
          gestionada++;
        }
      }
    }
    String[] cantidadSolicitudes = new String[2];
    cantidadSolicitudes[0] = String.valueOf(recibido);
    cantidadSolicitudes[1] = String.valueOf(gestionada);
    return cantidadSolicitudes;
  }

  /**
   * Consults the efficiency of a collaborator within a date range.
   * @param id The ID of the collaborator.
   * @param dateMin The minimum date of the range.
   * @param dateMax The maximum date of the range.
   * @return The efficiency of the collaborator.
   */
  public double consultarEficienciaColab(String id, Date dateMin, Date dateMax) {
    int liderados = 0;
    int count = 0;
    double efficiency = 0;
    for (int i = 0; i < projectList.size(); i++) {
      if ((projectList.get(i).getClasifingDate().before(dateMax) && projectList.get(i).getClasifingDate().after(dateMin))) {
        if ((projectList.get(i).getState().toString().equals("CLOSED")) && (projectList.get(i).getLeader().equals(id))) {
          liderados++;
        } else if (projectList.get(i).getState().toString().equals("PROJECT")) {
          count++;
        }
      }
    }
    if (count != 0) {
      efficiency = (double) liderados / count;
    } else {
      efficiency = liderados;
    }
    return efficiency;
  }

  /**
   * Consults the efficiency of a project based on its closing date.
   * @param id The ID of the project.
   * @return The efficiency of the project.
   */
  public double consultarEficienciaProyecto(String id) {
    double efficiency = 0;
    Project project = null;
    for (int i = 0; i < projectList.size(); i++) {
      if ((projectList.get(i).getId().equals(id)) && (projectList.get(i).getState().toString().equals("CLOSED"))) {
        project = projectList.get(i);
      }
    }
    LocalDate localDate1 = project.getClosingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDate2 = project.getEstimatedClosingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    efficiency = calcularEficiencia(localDate1, localDate2);
    return efficiency;
  }

  /**
   * Consults the efficiency of a request based on the start date of its associated project.
   * @param id The ID of the request.
   * @return The efficiency of the request.
   */
  public double eficienciaSolicitudes(String id) {
    double efficiency = 0;
    Date dateStartProject = null;
    Date dateRegister = null;
    for (int i = 0; i < requestList.size(); i++) {
      if (requestList.get(i).getId().equals(id)) {
        for (int j = 0; j < projectList.size(); j++) {
          if (projectList.get(j).getId().equals(id)) {
            dateRegister = requestList.get(i).getRegisteredDate();
            dateStartProject = projectList.get(j).getClasifingDate();
          }
        }
      }
    }
    int days = 20;
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dateRegister);
    calendar.add(Calendar.DAY_OF_YEAR, days);
    Date calculatedDate = calendar.getTime();
    LocalDate localDate1 = dateStartProject.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDate2 = calculatedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    efficiency = calcularEficiencia(localDate1, localDate2);
    return efficiency;
  }

  /**
   * Calculates efficiency based on the difference between two dates.
   * @param date1 The first date.
   * @param date2 The second date.
   * @return The calculated efficiency.
   */
  public double calcularEficiencia(LocalDate date1, LocalDate date2) {
    double efficiency = 0;
    long dias = ChronoUnit.DAYS.between(date1, date2);
    efficiency = 1 - (dias / 100.0);
    return efficiency;
  }
}