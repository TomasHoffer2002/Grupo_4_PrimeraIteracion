package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Usuario;

import lombok.Data;

@Data
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private int puntos_asociados;
}
