package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.interfaces;

import ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.Premio.Premio;

public interface DAOPremio {
    public boolean insert(Premio premio);
    public boolean existeComercio(int idComercio);
    public Premio findById(int idPremio);
    public void updateCantidad(int idPremio, int nuevaCantidad);
}
