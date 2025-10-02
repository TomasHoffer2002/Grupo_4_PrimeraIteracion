package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Detalle_canje;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class DetalleCanjeDAO {
    public void insert(int idUsuario, int idPremio, LocalDateTime fecha) {
        String sql = "INSERT INTO detalle_canje (Usuario_idUsuario, Premio_idPremio, fecha_canje) " +
                     "VALUES (:usuario, :premio, :fecha)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("usuario", idUsuario)
               .addParameter("premio", idPremio)
               .addParameter("fecha", fecha)
               .executeUpdate();
        }
    }
}
