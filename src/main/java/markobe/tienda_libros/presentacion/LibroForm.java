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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {

    LibroServicioImp libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
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
                agregarLibro();
            }
        });
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarLibro();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
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

    private void agregarLibro(){
        //Leer los valores del formulario
        if(libroTexto.getText().equals("")){
            mostrarMensaje("Proporciona el nombre del Libro");
            libroTexto.requestFocusInWindow();
            return;
        }
        String nombreLibro = libroTexto.getText();
        String autor = autorTexto.getText();
        Double precio = Double.parseDouble(precioTexto.getText());
        Integer existencias = Integer.parseInt(existenciasTexto.getText());

        // Crear el objeto Libro
        Libro libro = new Libro(null, nombreLibro, autor, precio, existencias);
        //libro.setNombreLibro(nombreLibro);
        //libro.setAutor(autor);
        //libro.setPrecio(precio);
        //libro.setExistencias(existencias);

        libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el Libro correctamente");
        limpiarFormulario();
        listarLibros();
    }

    private void cargarLibroSeleccionado(){
        //Los indices de las columnas inician en 0
        int renglon = tablaLibros.getSelectedRow(); //Regresa -1 si no se selecciono ningun registro
        if(renglon != -1){
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            idTexto.setText(idLibro);

            String nombreLibro = tablaLibros.getModel().getValueAt(renglon, 1).toString();
            libroTexto.setText(nombreLibro);

            String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
            autorTexto.setText(autor);

            String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
            precioTexto.setText(precio);

            String existencias = tablaLibros.getModel().getValueAt(renglon, 4).toString();
            existenciasTexto.setText(existencias);
        }
    }

    private void modificarLibro(){
        if(idTexto.getText().equals("")){
            mostrarMensaje("Selecciona un registro");
        }
        else{
            //Verificamos que nombre del libro no sea null
            if(libroTexto.getText().equals("")){
                mostrarMensaje("Proporciona el nombre del Libro");
                libroTexto.requestFocusInWindow();
                return;
            }
            //Llenamos el objeto libro a actualizar
            int idLibro = Integer.parseInt(idTexto.getText());
            String nombreLibro = libroTexto.getText();
            String autor = autorTexto.getText();
            Double precio = Double.parseDouble(precioTexto.getText());
            Integer existencias = Integer.parseInt(existenciasTexto.getText());

            Libro libro = new Libro(idLibro, nombreLibro, autor, precio, existencias);

            libroServicio.guardarLibro(libro);
            mostrarMensaje("Se modifico Libro correctamente");
            limpiarFormulario();
            listarLibros();
        }
    }

    private void eliminarLibro(){
        if(idTexto.getText().equals("")){
            mostrarMensaje("Selecciona un registro a eliminar");
        }
        else{
            if(libroTexto.getText().equals("")){
                mostrarMensaje("Proporciona el nombre del Libro");
                libroTexto.requestFocusInWindow();
                return;
            }
            int idLibro = Integer.parseInt(idTexto.getText());
            String nombreLibro = libroTexto.getText();
            String autor = autorTexto.getText();
            Double precio = Double.parseDouble(precioTexto.getText());
            Integer existencias = Integer.parseInt(existenciasTexto.getText());

            Libro libro = new Libro(idLibro, nombreLibro, autor, precio, existencias);

            libroServicio.eliminarLibro(libro);
            mostrarMensaje("Se elimino Libro correctamente");
            limpiarFormulario();
            listarLibros();
        }
    }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    //Metodo de la personalizacion de la tabla
    private void createUIComponents() {
        // TODO: place custom component creation code here
        //Creamos el elemento idTexto oculto
        idTexto = new JTextField("");
        idTexto.setVisible(false);

        tablaModeloLibros = new DefaultTableModel(0, 5){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        String [] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        tablaModeloLibros.setColumnIdentifiers(cabeceros);

        //Instanciar el objeto JTable
        tablaLibros = new JTable(tablaModeloLibros);

        //Evitar que se seleccionen varios registros
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
