package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/premios")
public class PremioControlador {
    private final PremioService premioService;

    public PremioControlador(PremioService premioService) {
        this.premioService = premioService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarPremio(@RequestBody Premio premio) {
        String resultado = premioService.agregarPremio(premio);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }
}
