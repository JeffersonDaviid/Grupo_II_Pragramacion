package UserInterface.Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import BusinnessLogic.CategoriaProductoBL;
import BusinnessLogic.IvaBL;
import BusinnessLogic.ProductoBL;
import BusinnessLogic.Entities.CategoriaProducto;
import BusinnessLogic.Entities.Iva;
import BusinnessLogic.Entities.Producto;
import Framework.APP;
import Framework.Validadores;
import UserInterface.UI_Component.CustomButton;
import UserInterface.UI_Component.CustomText;

public class RegistrarProducto extends JPanel {
    DefaultTableModel modelo;
    ProductoBL productoRegistrado = new ProductoBL();
    JTable tblProductos;
    ArrayList<Producto> lsProductosRegistrados;
    
    /**
     * @throws Exception para manejar las excepciones
     */
    public RegistrarProducto() throws Exception {
        lsProductosRegistrados = new ArrayList<Producto>();
        //Asignando la fecha "actual"
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); // asigna el formato requerido para la fecha
        Date fecha = new Date(); // obtiene la fecha actual
        String fechaActual = formatoFecha.format(fecha);

        /*
        * Se crean los paneles necesarios para organizar los componentes del formulario de registro de productos
        */
        setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelPrincipal = new JPanel();
    	add(panelPrincipal);
    	panelPrincipal.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelSuperior = new JPanel();
    	panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelLateralIzquierdo = new JPanel();
        panelPrincipal.add(panelLateralIzquierdo, BorderLayout.WEST);
        panelLateralIzquierdo.setLayout(new GridLayout(0,1, 0, 0));
        
        JPanel panelCentral = new JPanel();
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(0, 0));
        
        JPanel panelIngresoDatos = new JPanel();
        panelLateralIzquierdo.add(panelIngresoDatos);
        panelIngresoDatos.setLayout(new GridLayout(9, 2, 10, 10));
        
        
        /*
        * Labels de ayuda para dar un mejor aspecto a la ubicación de la tabla
        */
        JLabel lbCentralSuperior = new JLabel("      ");
        panelCentral.add(lbCentralSuperior, BorderLayout.NORTH);
        
        JLabel lbCentralInferior = new JLabel("      ");
        panelCentral.add(lbCentralInferior, BorderLayout.SOUTH);
        
        JLabel lbCentralIzquierdo = new JLabel("      ");
        panelCentral.add(lbCentralIzquierdo, BorderLayout.WEST);
        
        JLabel lbCentralDerecho = new JLabel("      ");
        panelCentral.add(lbCentralDerecho, BorderLayout.EAST);
        
        /*
        * Ahora, se crean los labels que acompañan a cada áreas de texto y combo boxes utilizados y el título
        * También se crean las áreas de texto donde se ingresa la información del producto por teclado.
        * Además se crean los combo boxes, en orden de aparición para que estén ordenados en el Grid Layaout
        */
        
        JLabel lbTittle = new JLabel("REGISTRAR PRODUCTO");
        lbTittle.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        panelSuperior.add(lbTittle);

        JLabel lbId = new JLabel("ID:");
        lbId.setHorizontalAlignment(SwingConstants.RIGHT);
        lbId.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbId);
         
        CustomText txtId = new CustomText("", "Campo vacío");
        txtId.setColumns(10);
        panelIngresoDatos.add(txtId);

        JLabel lbNombreProducto = new JLabel("Nombre:");
        lbNombreProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbNombreProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbNombreProducto);
         
        CustomText txtNombreProducto = new CustomText("", "Campo vacío");
        txtNombreProducto.setColumns(10);
        panelIngresoDatos.add(txtNombreProducto);
 
        JLabel lbCodigoProducto = new JLabel("Código:");
        lbCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbCodigoProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbCodigoProducto);
         
        CustomText txtCodigoProducto = new CustomText("", "Campo vacío");
        txtCodigoProducto.setColumns(10);
        panelIngresoDatos.add(txtCodigoProducto);
 
        JLabel lbCategoriaProducto = new JLabel("Categoría:");
        lbCategoriaProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbCategoriaProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbCategoriaProducto);
        
        JComboBox cbxCategoriaProducto = new JComboBox();
        productoRegistrado.getConsultarCategorias(cbxCategoriaProducto);
        panelIngresoDatos.add(cbxCategoriaProducto);
        
        JLabel lbStockProducto = new JLabel("Stock:");
        lbStockProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbStockProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbStockProducto);
        
        CustomText txtStockProducto = new CustomText("", "Campo vacío");
        txtStockProducto.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent evt) {
                validateNumericKeyEvent(evt);
                validarNumeroEntero(evt);
        	}

        });
        txtStockProducto.setColumns(10);
        panelIngresoDatos.add(txtStockProducto);
        
        JLabel lbPrecioCompraProducto = new JLabel("Precio Compra:");
        lbPrecioCompraProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbPrecioCompraProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbPrecioCompraProducto);
        
        CustomText txtPrecioCompra = new CustomText("", "Campo vacío");
        txtPrecioCompra.addKeyListener(new KeyAdapter() {
            @Override
        	public void keyTyped(KeyEvent evt) {
                validateNumericKeyEvent(evt);
        	}
        });
        txtPrecioCompra.setColumns(10);
        panelIngresoDatos.add(txtPrecioCompra);
        
        JLabel lbPrecioVentaProducto = new JLabel("Precio Venta:");
        lbPrecioVentaProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbPrecioVentaProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbPrecioVentaProducto);
        
        CustomText txtPrecioVenta = new CustomText("", "Campo vacío");
        txtPrecioVenta.addKeyListener(new KeyAdapter() {
            @Override
        	public void keyTyped(KeyEvent evt) {
                validateNumericKeyEvent(evt);
        	}
        });
        txtPrecioVenta.setColumns(10);
        panelIngresoDatos.add(txtPrecioVenta);
        
        JLabel lbIvaProducto = new JLabel("IVA:");
        lbIvaProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbIvaProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbIvaProducto);
        
        JComboBox cbxIvaProducto = new JComboBox();
        productoRegistrado.getConsultarIVA(cbxIvaProducto); //Consulta en la tabla iva la expresión almacenado en "NOMBRE"
        panelIngresoDatos.add(cbxIvaProducto);

        /*
        * Creación de la tabla donde se mostraran los productos recien registrados
        */

        JScrollPane scroll = new JScrollPane();
        panelCentral.add(scroll);
        
        tblProductos = new JTable();
        modelo = new DefaultTableModel();
        tblProductos.setModel(modelo);
        getColumnasTabla();
        scroll.setViewportView(tblProductos);

        /* Creación de los botones Agregar y Limpiar
         * @btnAgregar: se encarga de agregar los datos del producto a la base de datos
         * @btnLimpiar: se encarga de limpiar los datos ingresados en los txt y cbx de la interfaz  
         */
        //TODO Falta hacer que los datos ingresados se registren en la base de datos
        CustomButton btnAgregar = new CustomButton("Agregar","images/iconos/save.png");
        btnAgregar.setBackground(Color.RED);
        btnAgregar.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        btnAgregar.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoDatos.add(btnAgregar);
        btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                //A continuación validamos que los campos del formulario no esten vacíos para un correcto guardado del producto
                if( !txtId.getText().isEmpty() &&
                    !txtNombreProducto.getText().isEmpty() &&
                    !txtCodigoProducto.getText().isEmpty() &&
                    !"".equals(cbxCategoriaProducto.getSelectedItem()) &&
                    !txtStockProducto.getText().isEmpty() &&
                    !txtPrecioCompra.getText().isEmpty() &&
                    !txtPrecioVenta.getText().isEmpty() &&
                    !"".equals(cbxIvaProducto.getSelectedItem()))
                {
                    Producto nuevoProducto = new Producto();
                    nuevoProducto.setIdProducto(txtId.getText());
                    nuevoProducto.setProducto(txtNombreProducto.getText());
                    nuevoProducto.setCodigoProducto(txtCodigoProducto.getText());
                    CategoriaProducto categoria = new CategoriaProducto();
                    int idCategoria = (cbxCategoriaProducto.getSelectedIndex()+1);
                    categoria.setIdCategoriaProducto(idCategoria);
                    nuevoProducto.setFkCategoriaProducto(categoria);
                    nuevoProducto.setStock(Integer.parseInt(txtStockProducto.getText()));
                    nuevoProducto.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText()));
                    nuevoProducto.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText()));
                    Iva iva = new Iva();
                    int idIva = (cbxIvaProducto.getSelectedIndex()+1);
                    iva.setId(idIva);
                    nuevoProducto.setFkIva(iva);
                    nuevoProducto.setFechaIngreso(fechaActual);
                    lsProductosRegistrados.add(nuevoProducto);
                    llenarTabla();
                    productoRegistrado.getRegistrarProducto(nuevoProducto); //TODO revisar esta linea
                    JOptionPane.showMessageDialog(null, "Producto Registrado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al agregar producto, campos vacíos");
                }
                
            }
        });

        CustomButton btnLimpiar = new CustomButton("Limpiar","images/iconos/limpiar.png");
        btnLimpiar.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        btnLimpiar.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoDatos.add(btnLimpiar);
        btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                txtId.setText("");
                txtNombreProducto.setText("");
                txtCodigoProducto.setText("");
                cbxCategoriaProducto.setSelectedIndex(0);
                txtStockProducto.setText("");
                txtPrecioCompra.setText("");
                txtPrecioVenta.setText("");
                cbxIvaProducto.setSelectedIndex(0);
            }
        });

        
        /*
         *Validadores que desactivan la opción de copiar, pegar y cortar de los componentes deseados 
         */
        Validadores.deshabilitarCVX(txtId);
        Validadores.deshabilitarCVX(txtNombreProducto);
        Validadores.deshabilitarCVX(txtCodigoProducto);
        Validadores.deshabilitarCVX(txtStockProducto);
        Validadores.deshabilitarCVX(txtPrecioCompra);
        Validadores.deshabilitarCVX(txtPrecioVenta);
        
    }
    
    /**
     * Método que asigna a cada columna de la tabla un título correspondiente
     */
    private void getColumnasTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("CATEGORÍA");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRECIO COMPRA");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("IVA");
        modelo.addColumn("FECHA REGISTRO");
    }
    
    public void llenarTabla(){
        while(modelo.getRowCount() > 0){
            modelo.removeRow(0);
        }
        for (Producto nuevoProducto : lsProductosRegistrados) {
            Object [] fila = new Object[9];
            fila[0] = nuevoProducto.getIdProducto();
            fila[1] = nuevoProducto.getProducto();
            fila[2] = nuevoProducto.getCodigoProducto();
            fila[3] = nuevoProducto.getFkCategoriaProducto().getIdCategoriaProducto();
            fila[4] = nuevoProducto.getStock();
            fila[5] = nuevoProducto.getPrecioCompra();
            fila[6] = nuevoProducto.getPrecioVenta();
            fila[7] = nuevoProducto.getFkIva().getId();
            fila[8] = nuevoProducto.getFechaIngreso();
            modelo.addRow(fila);
        }
    }
    

    /**
     * Método para validar que las entradas ingresadas sean solo números
     * @param evt evento tipo KeyEvent que se activa cada vez que se teclea en la caja de texto
     */
    private void validateNumericKeyEvent(KeyEvent evt) {
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Error, solo se admiten entradas numéricas.");
        }
    }

    /**
     * Método para validar que las entradas ingresadas sean solo números enteros, en este caso, ignora entradas como "." y "," entre otros.
     * @param evt evento tipo KeyEvent que se activa cada vez que se teclea en la caja de texto
     */
    private void validarNumeroEntero(KeyEvent evt) {
        char validarEntero = evt.getKeyChar();
        if (validarEntero < '0' || validarEntero > '9') {
            evt.consume(); // Ignora la entrada no válida
        }
    }
}
