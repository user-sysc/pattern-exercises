package despues.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repositorio de persistencia que almacena los registros de atenciones médicas.
 */
public class Repo {

    private final List<String> database = new ArrayList<>();

    public void save(String data) {
        database.add(data);
        System.out.println("    [Repo] Guardado en base de datos: \"" + data + "\"");
    }

    public List<String> findAll() {
        return Collections.unmodifiableList(database);
    }
}
