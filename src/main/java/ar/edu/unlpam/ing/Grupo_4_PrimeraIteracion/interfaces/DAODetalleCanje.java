package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces;

import java.time.LocalDateTime;

public interface  DAODetalleCanje {
    public void insert(int idUsuario, int idPremio, LocalDateTime fecha);
}
