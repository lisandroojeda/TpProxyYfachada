package ar.unrn.main;

import ar.unrn.db.JdbcFachada;
import ar.unrn.model.DbFachada;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String sqlConsulta1= "SELECT * FROM personas";
        String sqlconsulta2 = "SELECT * FROM telefonos";

        DbFachada fachada = new JdbcFachada();

        fachada.open();

        List<Map<String, String>> listaMap = fachada.queryResultAsAsociation(sqlConsulta1);

        List<String[]> listaStrings = fachada.queryResultAsArray(sqlconsulta2);

        fachada.close();

        System.out.println("--------------------------");
        System.out.println("-----MAP LIST STRINGS-----");


        for (Map map : listaMap) {
            System.out.println("" + map.toString());
        }


        System.out.println("\n--------------------------");
        System.out.println("-------LISTA STRINGS------");
        System.out.println("--------------------------");

        /*for (String[] strings : listaStrings) {
            for (String string : strings)
                System.out.println("Valor: " + string);
        }*/

        int i = 0;
        for (String[] strings : listaStrings) {

            for (String string : strings) {
                if (i == 0)
                    System.out.println("Id del telefono: " + string);
                if (i == 1)
                    System.out.println("Numero de telefono: " + string);
                if (i == 2)
                    System.out.println("Id de persona propietaria: " + string);
                i++;
            }

            if (i == 3) {
                i = 0;
            }
        }
    }
}
