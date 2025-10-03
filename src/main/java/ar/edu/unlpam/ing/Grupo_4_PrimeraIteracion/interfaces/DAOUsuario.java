package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Usuario.Usuario;

public interface DAOUsuario {
    public Usuario findById(int idUsuario);
    public void updatePuntos(int idUsuario, int nuevosPuntos);
}
