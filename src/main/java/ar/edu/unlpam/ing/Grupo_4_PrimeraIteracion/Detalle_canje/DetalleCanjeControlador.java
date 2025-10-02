package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Detalle_canje;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/premios")
public class DetalleCanjeControlador {
    private final DetalleCanjeService canjeService;

    public DetalleCanjeControlador(DetalleCanjeService canjeService) {
        this.canjeService = canjeService;
    }

    @PostMapping("/canjear/{idUsuario}/{idPremio}")
    public ResponseEntity<Map<String, Object>> canjearPremio(
            @PathVariable int idUsuario,
            @PathVariable int idPremio) {

        Map<String, Object> comprobante = canjeService.canjearPremio(idUsuario, idPremio);

        if (comprobante.containsKey("error")) {
            return ResponseEntity.badRequest().body(comprobante);
        }
        return ResponseEntity.ok(comprobante);
    }
}
