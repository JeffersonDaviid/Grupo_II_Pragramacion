package PkgUserInterface.Ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import PkgBusinnessLogic.CategoriaProductoBL;
import PkgBusinnessLogic.IvaBL;
import PkgBusinnessLogic.ProductoBL;
import PkgBusinnessLogic.Entities.CategoriaProducto;
import PkgBusinnessLogic.Entities.Iva;
import PkgBusinnessLogic.Entities.Producto;
import PkgFramework.AppException;
import PkgFramework.Validadores;
import PkgUserInterface.UI_Component.CustomButton;
import PkgUserInterface.UI_Component.CustomJPanel;
import PkgUserInterface.UI_Component.CustomText;

public class VRegistrarProducto extends JPanel {
    DefaultTableModel modelo;
    ProductoBL productoRegistrado = new ProductoBL();
    JTable tblProductos;
    ArrayList<Producto> lsProductosRegistrados;

    /**
     * Constructor de la interfaz Registrar Producto donde se generan todos los
     * componentes a mostrar
     * y su funcionalidad
     * 
     * @throws Exception : indica las excepciones que se pueden lanzar durante la
     *                   ejecución
     */
    public VRegistrarProducto() throws Exception {
        setOpaque(false);
        lsProductosRegistrados = new ArrayList<Producto>();

        /*
         * Se crean los paneles necesarios para organizar los componentes del formulario
         * de registro de productos
         */
        setLayout(new BorderLayout(0, 0));

        CustomJPanel panelPrincipal = new CustomJPanel("images/img_fondoRegistro.jpeg");
        panelPrincipal.setOpaque(false);
        add(panelPrincipal);
        panelPrincipal.setLayout(new BorderLayout(0, 0));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setOpaque(false);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelLateralIzquierdo = new JPanel();
        panelLateralIzquierdo.setOpaque(false);
        panelPrincipal.add(panelLateralIzquierdo, BorderLayout.WEST);
        panelLateralIzquierdo.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(0, 0));

        JPanel panelIngresoDatos = new JPanel();
        panelIngresoDatos.setOpaque(false);
        panelLateralIzquierdo.add(panelIngresoDatos);
        panelIngresoDatos.setLayout(new GridLayout(10, 2, 10, 10));

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
         * Ahora, se crean los labels que acompañan a cada áreas de texto y combo boxes
         * utilizados y el título
         * También se crean las áreas de texto donde se ingresa la información del
         * producto por teclado.
         * Además se crean los combo boxes, en orden de aparición para que estén
         * ordenados en el Grid Layaout
         */

        JLabel lbTittle = new JLabel("REGISTRAR PRODUCTO");
        lbTittle.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        panelSuperior.add(lbTittle);

        JLabel lbId = new JLabel("ID:");
        lbId.setHorizontalAlignment(SwingConstants.RIGHT);
        lbId.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbId);

        CustomText txtId = new CustomText("");
        txtId.setColumns(10);
        panelIngresoDatos.add(txtId);

        JLabel lbNombreProducto = new JLabel("Nombre:");
        lbNombreProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbNombreProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbNombreProducto);

        CustomText txtNombreProducto = new CustomText("");
        txtNombreProducto.setColumns(10);
        panelIngresoDatos.add(txtNombreProducto);

        JLabel lbCodigoProducto = new JLabel("Código:");
        lbCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbCodigoProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbCodigoProducto);

        CustomText txtCodigoProducto = new CustomText("");
        txtCodigoProducto.setColumns(10);
        panelIngresoDatos.add(txtCodigoProducto);

        JLabel lbDescripcion = new JLabel("Descripción:");
        lbDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lbDescripcion.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbDescripcion);

        CustomText txtDescripcion = new CustomText("");
        txtDescripcion.setColumns(10);
        panelIngresoDatos.add(txtDescripcion);

        JLabel lbCategoriaProducto = new JLabel("Categoría:");
        lbCategoriaProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbCategoriaProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbCategoriaProducto);

        JComboBox<String> cbxCategoriaProducto = new JComboBox<>();
        CategoriaProductoBL p = new CategoriaProductoBL();
        cargarComboItems(cbxCategoriaProducto, p.getAllCategoriaNombre());
        // productoRegistrado.getConsultarCategorias(cbxCategoriaProducto);
        panelIngresoDatos.add(cbxCategoriaProducto);

        // txt nueva categoria

        JLabel lbStockProducto = new JLabel("Stock:");
        lbStockProducto.setHorizontalAlignment(SwingConstants.RIGHT);
        lbStockProducto.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        panelIngresoDatos.add(lbStockProducto);

        CustomText txtStockProducto = new CustomText("");
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

        CustomText txtPrecioCompra = new CustomText("");
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

        CustomText txtPrecioVenta = new CustomText("");
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

        JComboBox<String> cbxIvaProducto = new JComboBox<>();
        IvaBL iva = new IvaBL();
        cargarComboItems(cbxIvaProducto, iva.getAllIvaNombre());
        // iva la expresión almacenado en
        // "NOMBRE"
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
        tblProductos.setCellEditor(null);
        tblProductos.setEnabled(false);
        scroll.setViewportView(tblProductos);

        /*
         * Creación de los botones Agregar y Limpiar
         * 
         * btnAgregar: se encarga de agregar los datos del producto a la Base de Datos
         * 
         * btnLimpiar: se encarga de limpiar los datos ingresados en los txt y cbx de la
         * interfaz
         */
        CustomButton btnAgregar = new CustomButton("Agregar", "images/iconos/save.png");
        btnAgregar.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        btnAgregar.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoDatos.add(btnAgregar);
        btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                String idProducto = txtId.getText();
                String codigoProducto = txtCodigoProducto.getText();
                String nombreProducto = txtNombreProducto.getText();

                try {
                    String productoDuplicado = productoRegistrado.getValidarProducto(idProducto, codigoProducto,
                            nombreProducto);
                    if (productoDuplicado != null) {
                        JOptionPane.showMessageDialog(null,
                                "El " + productoDuplicado + " del producto ya se encuentra registrado");
                        return;
                    }

                    boolean camposValidos = !idProducto.isEmpty() && !nombreProducto.isEmpty()
                            && !codigoProducto.isEmpty() && !txtDescripcion.getText().isEmpty()
                            && !"Seleccione".equals(cbxCategoriaProducto.getSelectedItem())
                            && !txtStockProducto.getText().isEmpty() && !txtPrecioCompra.getText().isEmpty()
                            && !txtPrecioVenta.getText().isEmpty()
                            && !"Seleccione".equals(cbxIvaProducto.getSelectedItem());

                    if (camposValidos) {
                        Date fechaHoraActual = new Date();
                        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Producto nuevoProducto = new Producto();
                        nuevoProducto.setIdProducto(txtId.getText());
                        nuevoProducto.setProducto(txtNombreProducto.getText());
                        nuevoProducto.setCodigoProducto(txtCodigoProducto.getText());

                        CategoriaProducto categoria = new CategoriaProducto();
                        int idCategoria = (cbxCategoriaProducto.getSelectedIndex());
                        categoria.setIdCategoriaProducto(idCategoria);
                        nuevoProducto.setFkCategoriaProducto(categoria);

                        nuevoProducto.setDescripcion(txtDescripcion.getText());
                        nuevoProducto.setStock(Integer.parseInt(txtStockProducto.getText()));
                        nuevoProducto.setPrecioCompra(Float.parseFloat(txtPrecioCompra.getText()));
                        nuevoProducto.setPrecioVenta(Float.parseFloat(txtPrecioVenta.getText()));

                        Iva iva = new Iva();
                        System.out.println(cbxIvaProducto.getSelectedIndex());
                        int idIva = (cbxIvaProducto.getSelectedIndex());
                        iva.setId(idIva);
                        nuevoProducto.setFkIva(iva);
                        nuevoProducto.setFechaIngreso(formatoFechaHora.format(fechaHoraActual));

                        lsProductosRegistrados.add(nuevoProducto);
                        llenarTabla();
                        productoRegistrado.getRegistrarProducto(nuevoProducto);
                        JOptionPane.showMessageDialog(null, "Producto Registrado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar producto, campos vacíos o incorrectos");
                    }
                } catch (AppException e) {
                    JOptionPane.showMessageDialog(null, "Error al verificar el producto en la base de datos");
                    e.printStackTrace();
                }
            }
        });

        CustomButton btnLimpiar = new CustomButton("Limpiar", "images/iconos/limpiar.png");
        btnLimpiar.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
        btnLimpiar.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoDatos.add(btnLimpiar);
        btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                txtId.setText("");
                txtNombreProducto.setText("");
                txtCodigoProducto.setText("");
                txtDescripcion.setText("");
                cbxCategoriaProducto.setSelectedIndex(0);
                txtStockProducto.setText("");
                txtPrecioCompra.setText("");
                txtPrecioVenta.setText("");
                cbxIvaProducto.setSelectedIndex(0);
            }
        });

        /*
         * Validadores que desactivan la opción de copiar, pegar y cortar de los
         * componentes deseados
         */
        Validadores.deshabilitarCVX(txtId);
        Validadores.deshabilitarCVX(txtNombreProducto);
        Validadores.deshabilitarCVX(txtCodigoProducto);
        Validadores.deshabilitarCVX(txtDescripcion);
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
        modelo.addColumn("DESCRIPCIÓN");
        modelo.addColumn("CATEGORÍA");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRECIO COMPRA");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("IVA");
        modelo.addColumn("FECHA REGISTRO");
    }

    /**
     * Permite llenar la tabla de registro de productos con los datos ingresados en
     * el formulario
     */
    public void llenarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        for (Producto nuevoProducto : lsProductosRegistrados) {
            Object[] fila = new Object[10];
            fila[0] = nuevoProducto.getIdProducto();
            fila[1] = nuevoProducto.getProducto();
            fila[2] = nuevoProducto.getCodigoProducto();
            fila[3] = nuevoProducto.getDescripcion();
            fila[4] = nuevoProducto.getFkCategoriaProducto().getIdCategoriaProducto();
            fila[5] = nuevoProducto.getStock();
            fila[6] = nuevoProducto.getPrecioCompra();
            fila[7] = nuevoProducto.getPrecioVenta();
            fila[8] = nuevoProducto.getFkIva().getId();
            fila[9] = nuevoProducto.getFechaIngreso();
            modelo.addRow(fila);
        }
    }

    /**
     * Método para validar que las entradas ingresadas sean solo números
     * 
     * @param evt evento tipo KeyEvent que se activa cada vez que se teclea en la
     *            caja de texto
     */
    private void validateNumericKeyEvent(KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Error, solo se admiten entradas numéricas.");
        }
    }

    /**
     * Método para validar que las entradas ingresadas sean solo números enteros, en
     * este caso, ignora entradas como "." y "," entre otros.
     * 
     * @param evt : evento tipo KeyEvent que se activa cada vez que se teclea en la
     *            caja de texto
     */
    private void validarNumeroEntero(KeyEvent evt) {
        char validarEntero = evt.getKeyChar();
        if (validarEntero < '0' || validarEntero > '9') {
            evt.consume(); // Ignora la entrada no válida
        }
    }

    /**
     * Método que permite cargar items en un comboBox
     * 
     * @param cbxContenedor : elemento donde se insertarán los items
     * @param lsItem        : lista iterable con los nombres de los items
     */
    private void cargarComboItems(JComboBox<String> cbxContenedor, ArrayList<String> lsItem) {
        cbxContenedor.addItem("Seleccione");
        lsItem.forEach(cbxContenedor::addItem);
    }

}