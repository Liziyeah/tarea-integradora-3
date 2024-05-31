package ui;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import model.Controller;

public class Main {

	// Variables globales
	static Scanner reader;
	static Controller controller = new Controller();

	public static void main(String[] args) {

		reader = new Scanner(System.in);
		menu();


	}

	public static void menu()
	{

		int respuesta = 1;

		while (respuesta!=0) {

			System.out.println("\n \n Bienvenido al menú del Área de Transformación y Mejoramiento, ingresa la opción de la que quieras mostrar el menú:: \n");
			System.out.println("1. Menú de registro de solicitudes y creación de proyectos \n" + "2. Menú de consultas \n" + "3. Menu de operaciones y cálculos\n");

			respuesta = reader.nextInt();

			reader.nextLine();

			switch (respuesta) {
					case 1:
						menuRegister();
						break;
					case 2:
						menuConsult();
						break;
          case 3:
						menuCalculate();
					break;

					default:
						System.out.print("Por favor ingrese una opcion válida: ");
						continue;
			}

		}

	}

	public static void menuRegister() {
		System.out.println("(1). Registro de solicitudes de proyectos\n"+
		"(2). Cambiar de estado a una solicitud\n"+
		"(3). Finalizar o cerrar un proyecto\n");
		int respuesta = reader.nextInt();

		reader.nextLine();

		switch (respuesta) {

				case 1:
					registerRequest();
					break;

				case 2:
          			updateRequestStatus();
					break;

				case 3:
					closureProject();
					break;

				default:
					System.out.print("Por favor ingrese una opción valida");
		}
	}

	public static void menuConsult() {
		System.out.println("(1). Visualizar la información de proyectos.\n"+
		"(2). Consultar la información detallada de un proyecto.\n"+
		"(3). Consultar proyectos por tipo y prioridad.\n"+
		"(4). Consultar proyectos por colaborador.\n"+
		"(5). Consultar cantidad de solicitudes recibidas y aprobadas\n" +
		"(6). Generar objetos de prueba.\n");
		int respuesta = reader.nextInt();

			reader.nextLine();

			switch (respuesta) {
					case 1:
						consultInformation();
						break;
					case 2:
						consultInfoProject();
						break;
					case 3:
						consultTypeProjects();
						break;
					case 4:
						consultProjectsByColab();
						break;
					case 5:
						consultarCantidadSolicitudes();
						break;
					default:
						System.out.print("Por favor ingrese una opcion valida");
			}
		}


		public static void menuCalculate() {
			System.out.println("(1). Consultar eficiencia de un colaborador\n"+
			"(2). Consultar eficiencia de un Proyecto\n"+
			"(3). Consultar eficiencia de las solicitudes\n");
			int respuesta = reader.nextInt();
	
			reader.nextLine();
	
			switch (respuesta) {
					case 1:
						eficienciaColaborador();
						break;
					case 2:
						eficienciaProyecto();
						break;
					case 3:
						eficienciaSolicitud();
						break;
					default:
						System.out.print("Por favor ingrese una opcion valida");
			}
		}

		public static void registerRequest() {
			System.out.println("Ingrese el id de la solicitud: ");
			String id = reader.nextLine();
			System.out.println("Ingresa la fecha de registro de la solicitud en formate dd-mm-yyyy");
			String date = reader.nextLine();
			Date registerDate = switchStringToDate(date);
			System.out.println("Ingresa una breve descripción de lo que se trata la solicitud");
			String description = reader.nextLine();
			int state = 3;
			System.out.println("¿Quieres asociar la solicitud a un colaborador existente o agregar un colaborador?");
			System.out.println("1. Asociar a un colaborador existente");
			System.out.println("2. Agregar un colaborador");
			int collaborator = reader.nextInt();
			reader.nextLine();
			System.out.println("Ingresa el nombre del área solicitante");
			String requestArea = reader.nextLine();

			String idCollaborator = "";
			String nameCollab = "";
			String[] collaboratorInfo = new String[2];

			switch (collaborator) {
				case 1:
					System.out.println("Ingresa el id del colaborador: ");
					idCollaborator = reader.nextLine();
					System.out.println("Ingresa el nombre del colaborador: ");
					nameCollab = reader.nextLine();
					System.out.println(controller.createRequest(registerDate, description, state, nameCollab, idCollaborator, requestArea,id));
					break;
				case 2:
					collaboratorInfo = createCollaborator();
					System.out.println(controller.createRequest(registerDate, description, state, collaboratorInfo[0], collaboratorInfo[1], requestArea,id));
					break;
				default:
					break;
			}
		}

		public static String[] createCollaborator() {
			System.out.println("Ingresa el id del colaborador: ");
			String id = reader.nextLine();
			System.out.println("Ingresa el nombre del colaborador: ");
			String name = reader.nextLine();
			System.out.println("Ingresa el correo del colaborador: ");
			String email = reader.nextLine();
			System.out.println("Ingresa la extensión del colaborador: ");
			String extension = reader.nextLine();
			System.out.println(controller.createCollaborator(id, name, email, extension));
			
			String[] collaborator = new String[2];
			collaborator[0] = id;
			collaborator[1] = name;

			return collaborator;
		}

		public static void updateRequestStatus() {
			String id = "";
			System.out.println("Ingrese el id de la solicitud que deseas cambiar el estado: ");
			id = reader.nextLine();
			System.out.println("¿A qué estado deseas cambiar la solicitud? \n" +
			"1. Proyecto denegado \n" +
			"2. Proyecto aprobado \n" +
			"3. Proyecto postergado \n");
			int stateRequest = reader.nextInt();
			stateRequest -= 1;
			reader.nextLine();
			if(stateRequest == 1) {
				createProjects(id);
			}
			controller.changeState(stateRequest, id);
		}

		public static void createProjects(String id) {
			String dateRequest;
			System.out.println("Ingresa la fecha en la que se aprueba la solicitud: ");
			dateRequest = reader.nextLine();
			Date newDate = switchStringToDate(dateRequest);
			System.out.println("¿De qué desea crear el proyecto? \n" +
			"1. Gestión de conocimiento \n" +
			"2. Transformación de Mejoramiento");
			int typeProject = reader.nextInt();
			reader.nextLine();
			switch (typeProject) {
				case 1:
				createKnowledgeManagementProject(newDate, id);
					break;
				case 2:
				createProcessTransformationProject(newDate, id);
					break;
				default:
					break;
			}
		}

		public static Date switchStringToDate(String dateRequest) {
			DateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			Calendar calendario = new GregorianCalendar();
			Date datePriority = null;
			try {

				datePriority = formateador.parse(dateRequest);

				calendario.setTime(datePriority);

				} catch (ParseException error) {
					error.printStackTrace();
			}
			return datePriority;
		}

		public static void createKnowledgeManagementProject(Date dateRequest, String id) {
			System.out.println("Ingresa el nombre del proyecto: ");
			String projectName = reader.nextLine();
			int projectState = 1;
			System.out.println("¿Cuál es el tipo de prioridad del proyecto? (ingresa el número) \n" +
			"1. Urgente \n" +
			"2. Alta \n" +
			"3. Media \n" +
			"4. Baja");
			int priority = reader.nextInt();
			priority -= 1;
			reader.nextLine();
			System.out.println("Ingresa el identificador del lider del proyecto (Colaborador): ");
			String leader = reader.nextLine();
			System.out.println("Ingresa el nombre sugerido del proyecto con el que se va a gestionar: ");
			String projectSuggestName = reader.nextLine();
			System.out.println("Ingresa a qué comunidad afecta el proyecto \n" +
			"1. Estudiantes \n" +
			"2. Profesores \n" +
			"3. Personal administrativo");
			int community = reader.nextInt();
			reader.nextLine();
			controller.createKnowledgeProject(id, projectName, projectState, dateRequest, priority, leader, projectSuggestName, community);
		}

		public static void createProcessTransformationProject(Date dateRequest, String id) {
			System.out.println("Ingresa el nombre del proyecto: ");
			String projectName = reader.nextLine();
			System.out.println("¿Cuál es el tipo de prioridad del proyecto? (ingresa el número) \n" +
			"1. Urgente \n" +
			"2. Alta \n" +
			"3. Media \n" +
			"4. Baja");
			int priority = reader.nextInt();
			priority -= 1;
			reader.nextLine();
			System.out.println("Ingresa el ID del lider del proyecto (Colaborador): ");
			String leader = reader.nextLine();
			System.out.println("Ingresa  el código del proceso que se va a mejorar: ");
			String processCode = reader.nextLine();
			Date estimatedDate = calculatePriorityDate(priority, dateRequest);
			System.out.println(controller.processTransformation(id, projectName, 1, dateRequest, priority, estimatedDate, leader, processCode));
	}

	public static Date calculatePriorityDate(int priority, Date estimatedDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(estimatedDate);
		switch (priority) {
			case 0:
				calendar.add(Calendar.DAY_OF_MONTH, 5);
				break;
			case 1:
				calendar.add(Calendar.DAY_OF_MONTH, 10);
				break;
			case 2:
				calendar.add(Calendar.DAY_OF_MONTH, 30);
				break;
			case 3:
				calendar.add(Calendar.DAY_OF_MONTH, 60);
				break;
			default:
				break;
		}
		return calendar.getTime();
	}

	public static void closureProject() {
		System.out.println("Ingresa el id del proyecto que deseas cerrar: ");
		String id = reader.nextLine();
		System.out.println("Ingresa la fecha de cierre del proyecto: ");
		String date = reader.nextLine();
		Date dateClosure = switchStringToDate(date);
		System.out.println(controller.closeProject(id, dateClosure));
	}

	public static void consultInformation() {
		String stringDate;
		System.out.println("Ingresa la fecha de inicio de la consulta: ");
		stringDate = reader.nextLine();
		Date date = switchStringToDate(stringDate);
		String[][] information = controller.consultInformation(date);
		System.out.println("Fecha de Consultas: " + date);
		for(int i = 0; i < information.length; i++) {
			System.out.println("---------------------------------");
			for(int j = 0; j < information[i].length; j++) {
				System.out.println(information[i][j] + "|");
			}
		}
		System.out.println("---------------------------------");
		System.out.println("Desea ver la información de algún proyecto en específico? (1 = si, 2 = no)");
		int option = reader.nextInt();
		reader.nextLine();
		if(option == 1) {
			consultInfoProject();
		}
	}

	public static void consultInfoProject(){
		System.out.println("Ingresa el id del proyecto que deseas ver: ");
		String id = reader.nextLine();
		System.out.println(controller.consultProject(id));
	}

	public static void consultTypeProjects() {
		System.out.println("Ingresa el tipo de proyecto que deseas ver: \n" +
		"1. Gestión de conocimiento \n" +
		"2. Transformación de Mejoramiento");
		int type = reader.nextInt();
		reader.nextLine();

		int[] info = controller.consultTypeProjects(type);
		System.out.println("Cantidad de proyectos: " + info[4]);
		System.out.println("Cantidad de proyectos con prioridad urgente: " + info[0]);
		System.out.println("Cantidad de proyectos con prioridad alta: " + info[1]);
		System.out.println("Cantidad de proyectos con prioridad media: " + info[2]);
		System.out.println("Cantidad de proyectos con prioridad baja: " + info[3]);
	}

	public static void consultProjectsByColab() {
		System.out.println("Ingresa el id del colaborador que deseas ver: ");
		String id = reader.nextLine();
		String[] projects = controller.consultProjectsByColab(id);
		for(int i = 0; i < projects.length; i++) {
			System.out.println(projects[i]);
		}
	}

	public static void consultarCantidadSolicitudes() {
		System.out.println("Ingrese la fecha desde donde se toman en cuenta los proyectos:");
		String dateMinS = reader.nextLine();
		System.out.println("Ingrese la fecha hasta donde se toman en cuenta los proyectos:");
		String dateMaxS = reader.nextLine();
		Date dateMin = switchStringToDate(dateMinS);
		Date dateMax = switchStringToDate(dateMaxS);
		String[] info = controller.consultarCantidadSolicitudes(dateMin, dateMax);
		
		System.out.println("Cantidad de solicitudes recibidas: " + info[0]);
		System.out.println("Cantidad de solicitudes aprobadas (Proyectos): " + info[1]);
	}

	public static void eficienciaColaborador() {
		System.out.println("Ingresa el id del colaborador que deseas ver: ");
		String id = reader.nextLine();
		System.out.println("Ingrese la fecha desde donde se toman en cuenta los proyectos:");
		String dateMinS = reader.nextLine();
		System.out.println("Ingrese la fecha hasta donde se toman en cuenta los proyectos:");
		String dateMaxS = reader.nextLine();
		Date dateMin = switchStringToDate(dateMinS);
		Date dateMax = switchStringToDate(dateMaxS);
		System.out.println(controller.consultarEficienciaColab(id, dateMin, dateMax));
	}

	public static void eficienciaProyecto() {
		String id;
		System.out.println("Ingresa el id del proyecto que deseas ver: ");
		id = reader.nextLine();
		double eficiencia = controller.consultarEficienciaProyecto(id);

		System.out.println("La eficiencia del proyecto es: " + eficiencia);
	}

	public static void eficienciaSolicitud() {
		String id;
		System.out.println("Ingresa el id de la solicitud que deseas ver: ");
		id = reader.nextLine();
		double eficiencia = controller.consultarEficienciaProyecto(id);

		System.out.println("La eficiencia de la solicitud es: " + eficiencia);
	}
}