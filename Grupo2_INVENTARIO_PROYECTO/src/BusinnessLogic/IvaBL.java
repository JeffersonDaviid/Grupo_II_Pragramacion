package BusinnessLogic;

import java.sql.ResultSet;
import java.util.ArrayList;

import BusinnessLogic.Entities.Iva;
import DataAccess.IvaDAC;
import Framework.APP;
import Framework.AppException;

public class IvaBL {

    /**
     * Obtiene el iva por id en la tabla Iva de la BD
     * 
     * @param id : se envia como parametro el id que identifica si tiene o no tiene
     *           iva
     * @return : retorna iva con todos sus atributos
     * @throws Exception : indica las excepciones que se pueden lanzar durante la
     *                   ejecución
     */
    public Iva getBLIvaById(int id) throws Exception {
        try {
            IvaDAC ivaDac = new IvaDAC();
            ResultSet rsIva = ivaDac.getIvaById(id);
            while (rsIva.next()) {
                Iva iva = new Iva(id, rsIva.getString(APP.BASE_DATOS_MYSQL.NOMBRE));
                return iva;
            }
        } catch (Exception e) {
            throw new AppException(e, getClass(), "Error en getBLIvaById(int id): " + e.getMessage());
        }
        return null;
    }

    /**
     * Toma solo los valores de nombre que le corresponde a iva
     * 
     * @return ArrayList de String que contiene los nombres presentes en la tabla
     *         Iva
     * @throws Exception : indica las excepciones que se pueden lanzar durante la
     *                   ejecución
     */
    public ArrayList<String> getAllIvaNombre() throws Exception {
        try {
            ArrayList<String> lsEstado = new ArrayList<String>();
            IvaDAC ivaDAC = new IvaDAC();
            ResultSet rsEstado = ivaDAC.getIvaNombre();
            while (rsEstado.next()) {
                lsEstado.add(rsEstado.getString(APP.BASE_DATOS_MYSQL.NOMBRE));
            }
            return lsEstado;
        } catch (Exception e) {
            throw new AppException(e, getClass(), "Error en getAllIvaNombre(): " + e.getMessage());
        }
    }

}
