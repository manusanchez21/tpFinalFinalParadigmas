import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static News news;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		asegurarArchivosExistentes();

		news = new News();

		boolean running = true;
		while (running) {
			mostrarMenuPrincipal();
			int opcion = leerEntero("Seleccione una opción: ");
			switch (opcion) {
				case 1:
					registrarAutor();
					break;
				case 2:
					registrarLector();
					break;
				case 3:
					cargarNoticia();
					break;
				case 4:
					registrarComentario();
					break;
				case 5:
					listarUltimoAnio();
					break;
				case 6:
					listarUltimoMes();
					break;
				case 7:
					mostrarNoticiaYComentarios();
					break;
				case 8:
					listarPorAutor();
					break;
				case 9:
					listarTodosLosArticulos();
					break;
				case 0:
					running = false;
					System.out.println("Saliendo. Hasta luego.");
					break;
				default:
					System.out.println("Opción inválida");
			}
		}
	}

	private static void asegurarArchivosExistentes() {
		try {
			String[] archivos = {"usuarios.txt", "articulos.txt", "comentarios.txt"};
			for (String path : archivos) {
				File f = new File(path);
				if (!f.exists()) {
					f.createNewFile();
				}
			}
		} catch (IOException e) {
			System.err.println("No se pudieron crear archivos base: " + e.getMessage());
		}
	}

	private static void mostrarMenuPrincipal() {
		System.out.println("\n--- SISTEMA DE NOTICIAS - MENU PRINCIPAL ---");
		System.out.println("1) Registro de autores");
		System.out.println("2) Registro de usuarios (lectores)");
		System.out.println("3) Cargar noticia (por autor)");
		System.out.println("4) Registrar comentario (por usuario)");
		System.out.println("5) Listar noticias publicadas en el último año");
		System.out.println("6) Listar noticias publicadas en el último mes");
		System.out.println("7) Mostrar una noticia y sus comentarios asociados");
		System.out.println("8) Artículos publicados por un autor (DNI)");
		System.out.println("9) Listar todos los artículos");
		System.out.println("0) Salir");
	}

	private static int leerEntero(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				int val = Integer.parseInt(scanner.nextLine().trim());
				return val;
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Ingrese un número entero.");
			}
		}
	}

	private static String leerTexto(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

	private static void registrarAutor() {
		try {
			int dni = leerEntero("DNI del autor: ");
			String nombre = leerTexto("Nombre: ");
			int edad = leerEntero("Edad: ");
			news.agregarAutor(dni, nombre, edad);
			System.out.println("Autor registrado correctamente.");
		} catch (IllegalArgumentException | IOException e) {
			System.out.println("Error al registrar autor: " + e.getMessage());
		}
	}

	private static void registrarLector() {
		try {
			int dni = leerEntero("DNI del usuario: ");
			String nombre = leerTexto("Nombre: ");
			int edad = leerEntero("Edad: ");
			news.agregarLector(dni, nombre, edad);
			System.out.println("Lector registrado correctamente.");
		} catch (IllegalArgumentException | IOException e) {
			System.out.println("Error al registrar lector: " + e.getMessage());
		}
	}

	private static void cargarNoticia() {
		try {
			int dniAutor = leerEntero("DNI del autor que publica: ");
			String titulo = leerTexto("Título: ");
			String detalle = leerTexto("Detalle: ");
			System.out.println("Categorias válidas: Geopolitca, Deportivas, Cientificas, Policiales");
			String categoria = leerTexto("Categoria: ");
			news.agregarArticulo(dniAutor, titulo, detalle, categoria);
			System.out.println("Artículo agregado correctamente.");
		} catch (IllegalArgumentException | IOException e) {
			System.out.println("Error al agregar artículo: " + e.getMessage());
		}
	}

	private static void registrarComentario() {
		try {
			int dniUsuario = leerEntero("DNI del usuario que comenta: ");
			int idArticulo = leerEntero("ID del artículo: ");
			String texto = leerTexto("Texto del comentario: ");
			news.agregarComentario(dniUsuario, idArticulo, texto);
			System.out.println("Comentario registrado correctamente.");
		} catch (IllegalArgumentException | IOException e) {
			System.out.println("Error al agregar comentario: " + e.getMessage());
		}
	}

	private static void listarUltimoAnio() {
		try {
			ArrayList<String> lista = news.listarArticulosUltimoAnio();
			if (lista.isEmpty()) {
				System.out.println("No hay artículos en el último año.");
			} else {
				System.out.println("Artículos publicados en el último año:");
				for (String s : lista) System.out.println(s);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		}
	}

	private static void listarUltimoMes() {
		try {
			ArrayList<String> lista = news.listarArticulosUltimoMes();
			if (lista.isEmpty()) {
				System.out.println("No hay artículos en el último mes.");
			} else {
				System.out.println("Artículos publicados en el último mes:");
				for (String s : lista) System.out.println(s);
			}
		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		}
	}

	private static void mostrarNoticiaYComentarios() {
		try {
			int id = leerEntero("ID del artículo a mostrar: ");
			if (news.articulos == null || !news.articulos.containsKey(id)) {
				System.out.println("No existe el artículo con ese ID.");
				return;
			}
			Articulo articulo = news.articulos.get(id);
			System.out.println("--- NOTICIA ---");
			System.out.println(articulo.toString());
			System.out.println("--- COMENTARIOS ---");
			ArrayList<String> comentarios = news.mostrarComentariosDeUnArticulo(id);
			if (comentarios.isEmpty()) {
				System.out.println("No hay comentarios para este artículo.");
			} else {
				for (String c : comentarios) {
					System.out.println(c);
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static void listarPorAutor() {
		try {
			Integer dni = leerEntero("DNI del autor: ");
			ArrayList<String> lista = news.mostrarArticulosPorAutor(dni);
			if (lista.isEmpty()) {
				System.out.println("No hay artículos para ese autor.");
			} else {
				System.out.println("Artículos del autor:");
				for (String s : lista){
                    System.out.println(s);
                }       
			}
		} catch (IllegalArgumentException | InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static void listarTodosLosArticulos() {
		try {
			ArrayList<String> lista = news.todosLosArticulos();
			if (lista.isEmpty()) {
				System.out.println("No hay artículos cargados.");
			} else {
				System.out.println("Todos los artículos:");
				for (String s : lista) System.out.println(s);
			}
		} catch (Exception e) {
			System.out.println("Error al listar todos los artículos: " + e.getMessage());
		}
	}
}

