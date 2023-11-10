package markobe.tienda_libros.presentacion;

import markobe.tienda_libros.modelo.Libro;
import markobe.tienda_libros.servicio.LibroServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LibroForm extends JFrame {

    LibroServicioImp libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroForm(LibroServicioImp libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void iniciarForma() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth() / 2);
        int y = (tamanioPantalla.height - getHeight() / 2);
        setLocation(x, y);
    }

    //Metodo de la personalizacion de la tabla
    private void createUIComponents() {
        // TODO: place custom component creation code here
        tablaModeloLibros = new DefaultTableModel(0, 5);
        String [] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        tablaModeloLibros.setColumnIdentifiers(cabeceros);

        //Instanciar el objeto JTable
        tablaLibros = new JTable(tablaModeloLibros);

        listarLibros();
    }

    private void listarLibros(){
        //Limpiar la tabla
        tablaModeloLibros.setRowCount(0);
        //Obtener los libros
        var libros = libroServicio.listarLibros();
        for (Libro libro : libros) {
            Object [] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            //Agregamos los datos recuperados a la tabla
            tablaModeloLibros.addRow(renglonLibro);
        }
    }

}
