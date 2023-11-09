package markobe.tienda_libros.repositorio;

import markobe.tienda_libros.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {


}
