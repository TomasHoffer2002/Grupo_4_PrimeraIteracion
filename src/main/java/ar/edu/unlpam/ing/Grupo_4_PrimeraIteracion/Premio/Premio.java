package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Premio {
    private int idPremio;
    private String nombre;
    private String descripcion;
    private int puntos_necesarios;
    private int cantidad;
    @JsonProperty("Comercio_idComercio")
    private int Comercio_idComercio;
    private String categoria;
}
