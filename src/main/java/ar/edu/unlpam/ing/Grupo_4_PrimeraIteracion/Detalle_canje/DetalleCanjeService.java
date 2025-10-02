package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Detalle_canje;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio.Premio;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio.PremioDAO;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Usuario.Usuario;
import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Usuario.UsuarioDAO;

@Service
public class DetalleCanjeService {
    private final UsuarioDAO usuarioDAO;
    private final PremioDAO premioDAO;
    private final DetalleCanjeDAO detalleCanjeDAO;

    public DetalleCanjeService(UsuarioDAO usuarioDAO, PremioDAO premioDAO, DetalleCanjeDAO detalleCanjeDAO) {
        this.usuarioDAO = usuarioDAO;
        this.premioDAO = premioDAO;
        this.detalleCanjeDAO = detalleCanjeDAO;
    }

    public Map<String, Object> canjearPremio(int idUsuario, int idPremio) {
        Usuario usuario = usuarioDAO.findById(idUsuario);
        Premio premio = premioDAO.findById(idPremio);

        if (usuario == null) {
            return Map.of("error", "Usuario no encontrado");
        }
        if (premio == null) {
            return Map.of("error", "Premio no encontrado");
        }
        if (usuario.getPuntos_asociados() < premio.getPuntos_necesarios()) {
            return Map.of("error", "Puntos insuficientes");
        }
        if (premio.getCantidad() <= 0) {
            return Map.of("error", "Premio sin stock");
        }

        // Actualizar puntos y stock
        int nuevosPuntos = usuario.getPuntos_asociados() - premio.getPuntos_necesarios();
        usuarioDAO.updatePuntos(idUsuario, nuevosPuntos);

        int nuevaCantidad = premio.getCantidad() - 1;
        premioDAO.updateCantidad(idPremio, nuevaCantidad);

        // Guardar detalle del canje
        LocalDateTime fecha = LocalDateTime.now();
        detalleCanjeDAO.insert(idUsuario, idPremio, fecha);

        // Comprobante JSON
        return Map.of(
            "mensaje", "Canje realizado con éxito",
            "usuario", usuario.getNombre() + " " + usuario.getApellido(),
            "premio", premio.getNombre(),
            "puntos_canjeados", premio.getPuntos_necesarios(),
            "puntos_restantes", nuevosPuntos,
            "comercio", premio.getComercio_idComercio(),
            "fecha", fecha.toString()
        );
    }
}
