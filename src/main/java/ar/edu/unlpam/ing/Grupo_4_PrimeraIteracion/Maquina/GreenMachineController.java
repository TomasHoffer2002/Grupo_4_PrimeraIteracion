package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Maquina;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maquinas")
public class GreenMachineController {

    @Autowired
    private GreenMachineService greenMachineService;
    @GetMapping("/completas")
    public ResponseEntity<List<GreenMachine>> listarCompletas() {
        List<GreenMachine> maquinas = greenMachineService.listarAltoNivel();
        return new ResponseEntity<>(maquinas, HttpStatus.OK);
    }
}