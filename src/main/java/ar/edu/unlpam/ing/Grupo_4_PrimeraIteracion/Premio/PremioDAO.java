package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces.DAOPremio;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.GenericDAO;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class PremioDAO extends GenericDAO<Premio> implements DAOPremio{
    @Override
    public boolean insert(Premio premio) {
        return super.insert(premio, "Premio"); // se llama al metodo de la clase abstracta GenericDAO
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
