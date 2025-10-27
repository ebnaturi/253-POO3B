package org.ej3b.services;

import org.ej3b.repositories.ReposityAlumno;
import org.ej3b.models.Alumno;
import java.sql.SQLException;
import java.util.List;

public class ServicieAlumno {
    private final ReposityAlumno reposityAlumno;

    public ServicieAlumno(ReposityAlumno reposityAlumno) {
        this.reposityAlumno = reposityAlumno;
    }

    public List<Alumno> findAll() throws SQLException {
        //validacion de reglas de negocio y operación
        return reposityAlumno.findAll();
    }

    public void save(Alumno alumno)throws SQLException{
        //todo el codigo necesario para validar las reglas de negocio
        reposityAlumno.save(alumno);
    }

    public void delete(int matricula) throws SQLException{
        //todo el codigo necesario para validar las reglas de negocio
        reposityAlumno.delete(matricula);
    }
    public void update(Alumno alumno) throws SQLException{
        //todo el codigo necesario para validar las reglas de negocio
        reposityAlumno.update(alumno);
    }
}
