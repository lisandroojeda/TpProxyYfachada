package ar.unrn.persistencia;

import ar.unrn.model.IPersona;
import ar.unrn.model.Persona;
import ar.unrn.model.PersonaProxy;
import ar.unrn.model.Telefono;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao implements IPersona {

    private Connection obtenerConexion() {
        try {
            String url = "jdbc:mysql://localhost:3306/dbpatronproxy?useSSL=false";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public Persona personaPorId(int id) {
        try (
                Connection conexion = this.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement("SELECT * FROM personas where id = ?");
        ) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonos = new PersonaProxy(this,id);
            String nombrePersona = null;

            while (result.next()) {
                nombrePersona = result.getString(2);
            }

            return new Persona(id, nombrePersona, telefonos);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Telefono> obtenerTelefonoXiD(int idPersona) {
        try (
                Connection conexion = this.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement("SELECT * FROM telefonos where idPersona = ?");
        ) {
            statement.setInt(1, idPersona);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonos = new HashSet<Telefono>();
            while (result.next()) {
                telefonos.add(new Telefono(result.getString(2)));
            }
            return telefonos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
