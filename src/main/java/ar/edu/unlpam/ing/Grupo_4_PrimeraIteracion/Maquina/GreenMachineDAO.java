package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Maquina;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces.DAOGreenMachine;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util.Sql2oDAO;

@Repository
public class GreenMachineDAO implements DAOGreenMachine{
 @Override
    public List<GreenMachine> listarAltoNivel(int umbralCalculado) {
        String sql = "SELECT * FROM greenMachines WHERE cantidadActual >= :nivel";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                .addParameter("nivel", umbralCalculado)
                .executeAndFetch(GreenMachine.class);
        }   
    }

 public int getCantidadMaxima() {
    return 1000; // Supongo que la capacidad máxima es 1000 unidades, aunque si cada maquina tiene una capacidad diferente, tendriamos que verlo en la bd supongo
 }
}
