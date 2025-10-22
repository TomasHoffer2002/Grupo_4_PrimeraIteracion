package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces;

import java.util.List;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Maquina.GreenMachine;

public interface DAOGreenMachine {
    public List<GreenMachine> listarAltoNivel(int cantidadMinima);
}
