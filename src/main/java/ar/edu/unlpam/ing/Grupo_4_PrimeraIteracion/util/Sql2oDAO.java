package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util;

import org.sql2o.Sql2o;

public class Sql2oDAO {
    static Sql2o sql2o;

    public static Sql2o getSql2o() {
        if (sql2o == null) {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/proyecto", "root", "universidad");
        }
        return sql2o;
    }
}
