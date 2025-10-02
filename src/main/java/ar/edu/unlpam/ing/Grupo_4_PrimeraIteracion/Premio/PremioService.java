package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import org.springframework.stereotype.Service;

@Service
public class PremioService {
    private final PremioDAO premioDAO;

    public PremioService(PremioDAO premioDAO) {
        this.premioDAO = premioDAO;
    }

    public String agregarPremio(Premio premio) {
        if (premio.getNombre() == null || premio.getNombre().isEmpty()) {
            return "Error: El nombre es obligatorio";
        }
        if (premio.getPuntos_necesarios() <= 0) {
            return "Error: Los puntos necesarios deben ser mayores a 0";
        }
        if (!premioDAO.existeComercio(premio.getComercio_idComercio())) {
            return "Error: El comercio no existe";
        }
        if (premio.getCantidad() <= 0) {
            return "Error: La cantidad debe ser mayor a 0";
        }

        boolean insertado = premioDAO.insert(premio);
        return insertado ? "Premio agregado correctamente" : "Error al insertar el premio";
    }
}
