package markobe.tienda_libros.servicio;

import markobe.tienda_libros.modelo.Libro;

import java.util.List;

public interface ILibroServicio {

    public List<Libro> listarLibros();

    public Libro buscarLibroPorId(Integer idLibro);

    //Este método nos sirve para actualizar o agregar un nuevo libro, depende si el id del libro ya existe
    public void guardarLibro(Libro libro);

    public void eliminarLibro(Libro libro);
}
