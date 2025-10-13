package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces.DAOPremio;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class PremioDAO implements DAOPremio{
    @Override
    public boolean insert(Premio premio) {
         String sql = "INSERT INTO premio (nombre, descripcion, puntos_necesarios, Comercio_idComercio, cantidad, categoria) " +
                 "VALUES (:nombre, :descripcion, :puntos_necesarios, :Comercio_idComercio, :cantidad, :categoria)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("nombre", premio.getNombre())
               .addParameter("descripcion", premio.getDescripcion())
               .addParameter("puntos_necesarios", premio.getPuntos_necesarios())
               .addParameter("Comercio_idComercio", premio.getComercio_idComercio())
               .addParameter("cantidad", premio.getCantidad())
               .addParameter("categoria", premio.getCategoria())
               .executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error insertando premio: " + e.getMessage());
            return false;
        }
    }
    // Verifica si el comercio existe
    @Override
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
    @Override
    public Premio findById(int idPremio) {
        String sql = "SELECT * FROM premio WHERE idPremio = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                .addParameter("id", idPremio)
                .executeAndFetchFirst(Premio.class);
        }   
    }
    //Metodo para buscar premio por categoria
    @Override
    public List<Premio> findByCategoria(String categoria) {
        String sql = "SELECT * FROM premio WHERE categoria = :categoria";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return (List<Premio>) con.createQuery(sql)
                .addParameter("categoria", categoria)
                .executeAndFetch(Premio.class);
    } catch (Exception e) {
        System.err.println("Error al buscar premios por categoría: " + e.getMessage());
        return Collections.emptyList();
    }
    }
    //Metodo para actualizar la cantidad de premios
    @Override
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
