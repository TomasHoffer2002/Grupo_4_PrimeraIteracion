package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Usuario;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class UsuarioDAO {
    public Usuario findById(int idUsuario) {
        String sql = "SELECT * FROM usuario WHERE idUsuario = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", idUsuario)
                      .executeAndFetchFirst(Usuario.class);
        }
    }

    public void updatePuntos(int idUsuario, int nuevosPuntos) {
        String sql = "UPDATE usuario SET puntos_asociados = :puntos WHERE idUsuario = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("puntos", nuevosPuntos)
               .addParameter("id", idUsuario)
               .executeUpdate();
        }
    }
}
