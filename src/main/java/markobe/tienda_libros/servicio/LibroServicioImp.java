package markobe.tienda_libros.servicio;

import markobe.tienda_libros.modelo.Libro;
import markobe.tienda_libros.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibroServicioImp implements ILibroServicio{

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Override
    public List<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    @Override
    public Libro buscarLibroPorId(Integer idLibro) {
        //Si encuentra el libro regresa el objeto de tipo libro, sino regresa null
        Libro libro = libroRepositorio.findById(idLibro).orElse(null);
        return libro;
    }

    @Override
    public void guardarLibro(Libro libro) {
        //Si el ID del libro no existe en la BD se hace una insercción, en caso contrario hace una actualización
        libroRepositorio.save(libro);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepositorio.delete(libro);
    }

}
