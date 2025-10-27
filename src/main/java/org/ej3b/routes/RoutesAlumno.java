package org.ej3b.routes;

import org.ej3b.controllers.ControllerAlumno;
import io.javalin.Javalin;
public class RoutesAlumno {
    private final ControllerAlumno controllerAlumno;

    public RoutesAlumno(ControllerAlumno controllerAlumno){
        this.controllerAlumno = controllerAlumno;
    }

    public void register(Javalin app){
        app.get("/alumnos",controllerAlumno::getAll);
        app.post("/alumnos",controllerAlumno::saveAlumno);
        app.delete("/alumnos/{matricula}",
                controllerAlumno::deleteAlumno);
        app.patch("/alumnos",controllerAlumno::updateAlumno);
    }
}
