package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
@RequestMapping("/premios")
public class PremioControlador {
    private static final Logger registraLog = LoggerFactory.getLogger(PremioControlador.class);
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

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Premio>> obtenerPremiosPorCategoria(@PathVariable String categoria) {
        List<Premio> premios = premioService.obtenerPremiosPorCategoria(categoria);
        if (premios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 sin contenido
        }
        registraLog.info("Se encontraron {} premios en la categoría '{}'", premios.size(), categoria);
        registraLog.debug("Premios encontrados: {}", premios);
        return ResponseEntity.ok(premios);
    }

}
