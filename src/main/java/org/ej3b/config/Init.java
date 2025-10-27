package org.ej3b.config;

import org.ej3b.controllers.ControllerAlumno;
import org.ej3b.repositories.ReposityAlumno;
import org.ej3b.routes.RoutesAlumno;
import org.ej3b.services.ServicieAlumno;

public class Init {

    public static RoutesAlumno intAlumnos(){
        ReposityAlumno reposityAlumno = new ReposityAlumno();
        ServicieAlumno servicieAlumno = new ServicieAlumno(reposityAlumno);
        ControllerAlumno controllerAlumno = new ControllerAlumno(servicieAlumno);
        RoutesAlumno routesAlumno = new RoutesAlumno(controllerAlumno);
        return routesAlumno;
    }

}
