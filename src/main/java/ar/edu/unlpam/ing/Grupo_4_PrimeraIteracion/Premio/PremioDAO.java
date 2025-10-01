package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class PremioDAO {
    public boolean insert(Premio premio) {
        String sql = "INSERT INTO premio (nombre, descripcion, puntos_necesarios, Comercio_idComercio, cantidad) " +
                     "VALUES (:nombre, :descripcion, :puntosNecesarios, :comercioIdComercio, :cantidad)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("nombre", premio.getNombre())
               .addParameter("descripcion", premio.getDescripcion())
               .addParameter("puntosNecesarios", premio.getPuntosNecesarios())
               .addParameter("comercioIdComercio", premio.getComercioIdComercio())
               .addParameter("cantidad", premio.getCantidad())
               .executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error insertando premio: " + e.getMessage());
            return false;
        }
    }
    // Verifica si el comercio existe
    public boolean existeComercio(int idComercio) {
        String sql = "SELECT COUNT(*) FROM comercio WHERE idComercio = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            int count = con.createQuery(sql)
                       .addParameter("id", idComercio)
                       .executeScalar(Integer.class);
            return count > 0;
        }
    }

}
