package ar.unrn.main;

import ar.unrn.model.Persona;
import ar.unrn.model.Telefono;
import ar.unrn.persistencia.PersonaDao;

public class Main {

    public static void main(String args[]) {
        PersonaDao dao = new PersonaDao();
        Persona p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            System.out.println(telefono);
        }
    }

}
