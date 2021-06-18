package ar.unrn.model;

import java.util.Set;

public interface IPersona {

    Persona personaPorId(int id);


    Set<Telefono> obtenerTelefonoXiD(int idPersona);
}
