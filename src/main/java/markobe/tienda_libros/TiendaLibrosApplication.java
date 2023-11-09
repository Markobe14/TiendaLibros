package markobe.tienda_libros;

import markobe.tienda_libros.presentacion.LibroForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TiendaLibrosApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TiendaLibrosApplication.class, args);

		//Configuramos el contexto de Spring para que el proyecto NO se ejecute como una app web
		ConfigurableApplicationContext contextSpring = new SpringApplicationBuilder(TiendaLibrosApplication.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);

		//Ejecutamos el código para cargar el formulario
		java.awt.EventQueue.invokeLater(()->{
			//Obtenemos el objeto form a traves de Spring
			LibroForm libroForm = contextSpring.getBean(LibroForm.class);
			libroForm.setVisible(true);
		});

	}

}
