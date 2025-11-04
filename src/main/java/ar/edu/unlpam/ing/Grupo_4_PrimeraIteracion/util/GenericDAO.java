package ar.edu.unlpam.ing.Grupo_4_PrimeraIteracion.util;

import java.lang.reflect.Field;
import java.util.StringJoiner;

import org.sql2o.Connection;

public abstract class GenericDAO<T> {

    public boolean insert(T entity, String tableName) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // construye cadenas de texto separadas por comas
        StringJoiner columns = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");

        try (Connection con = Sql2oDAO.getSql2o().open()) {

            for (Field field : fields) {
                field.setAccessible(true);

                // Se omite el campo que empieza con id, que es autoincremental
                if (field.getName().toLowerCase().startsWith("id")) {
                    continue;
                }
                // se van agregando los nombres de las columnas y los valores
                columns.add(field.getName());
                values.add(":" + field.getName());
            }
            // se crea la consulta SQL dinámica
            String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                tableName, columns.toString(), values.toString()
            );
            // se prepara la consulta
            var query = con.createQuery(sql);

            for (Field field : fields) {
                // esto permite acceder a campos privados
                field.setAccessible(true);
                // Se omite el campo que empieza con id, que es autoincremental
                if (field.getName().toLowerCase().startsWith("id")) {
                    continue;
                }
                // asigna valores a los parámetros nombrados dentro de la consulta SQL.
                // ejemplo: query.addParameter("nombre", "Caja de alfajores");
                query.addParameter(field.getName(), field.get(entity));
            }
            // ejecuta la consulta SQL, que es insert con todos los campos y sus valores
            query.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("Error insertando en tabla " + tableName + ": " + e.getMessage());
            return false;
        }
    }
}