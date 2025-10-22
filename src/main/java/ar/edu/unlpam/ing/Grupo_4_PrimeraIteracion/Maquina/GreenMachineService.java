package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Maquina;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// GreenMachineService.java
@Service
public class GreenMachineService {

    @Autowired
    private GreenMachineDAO greenMachineDAO;

    // Define el umbral como una constante
    private static final int UMBRAL_RECOLECCION = 80; // 80%

    public List<GreenMachine> listarAltoNivel() {
        return greenMachineDAO.listarAltoNivel(greenMachineDAO.getCantidadMaxima() * UMBRAL_RECOLECCION / 100); //Calculo para sacar el porcentaje usando la capacidad maxima
    }
}