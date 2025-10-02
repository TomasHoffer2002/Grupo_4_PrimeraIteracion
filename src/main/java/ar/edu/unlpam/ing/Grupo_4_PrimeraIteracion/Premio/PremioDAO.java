package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class PremioDAO {
    public boolean insert(Premio premio) {
         String sql = "INSERT INTO premio (nombre, descripcion, puntos_necesarios, Comercio_idComercio, cantidad) " +
                 "VALUES (:nombre, :descripcion, :puntos_necesarios, :Comercio_idComercio, :cantidad)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("nombre", premio.getNombre())
               .addParameter("descripcion", premio.getDescripcion())
               .addParameter("puntos_necesarios", premio.getPuntos_necesarios())
               .addParameter("Comercio_idComercio", premio.getComercio_idComercio())
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
    //Metodo para buscar premio por id
    public Premio findById(int idPremio) {
        String sql = "SELECT * FROM premio WHERE idPremio = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", idPremio)
                      .executeAndFetchFirst(Premio.class);
        }
    }
    //Metodo para actualizar la cantidad de premios
    public void updateCantidad(int idPremio, int nuevaCantidad) {
        String sql = "UPDATE premio SET cantidad = :cantidad WHERE idPremio = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("cantidad", nuevaCantidad)
               .addParameter("id", idPremio)
               .executeUpdate();
        }
    }
}
