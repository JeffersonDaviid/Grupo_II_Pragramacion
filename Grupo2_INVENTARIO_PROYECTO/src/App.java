import javax.swing.ImageIcon;
import javax.swing.JFrame;

import DataAccess.DataHelper;
import UserInterface.UI_Interfaz.Disconected;
import UserInterface.UI_Interfaz.IniciarSesion;

public class App extends JFrame {

    // public static void main(String[] args) throws Exception {
    // DataHelper conexion = new DataHelper();

    // if (conexion.getConexion() == null) {
    // Disconected d = new Disconected();
    // d.setVisible(true);
    // } else {
    // // // sudo /opt/lampp/lampp start -> inicar
    // // // sudo /opt/lampp/lampp stop -> parar
    // IniciarSesion app = new IniciarSesion();
    // app.setVisible(true);
    // }

    // }

    public App() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("logoEmpresa.png")).getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
