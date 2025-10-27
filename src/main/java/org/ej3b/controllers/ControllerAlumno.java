package org.ej3b.controllers;

import org.ej3b.services.ServicieAlumno;
import org.ej3b.models.Alumno;

import java.sql.SQLException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;

public class ControllerAlumno {
    private  final ServicieAlumno serviceAlumno;

    public ControllerAlumno(ServicieAlumno serviceAlumno){
        this.serviceAlumno = serviceAlumno;
    }

    public void getAll(Context ctx){
        try{
            List<Alumno> als = this.serviceAlumno.findAll();
            ctx.json(als);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener los alumnos");
        }
    }

    public void saveAlumno(Context ctx){
        try{
            Alumno al = ctx.bodyAsClass(Alumno.class);
            //todo el codigo ara verfiicar la información es usable en la app
            serviceAlumno.save(al);
            ctx.status(201).result("Alumno guardado correctamente");
        }catch(SQLException e) {
            ctx.status(500).result("Error al guardar los alumnos");
        }
    }

    public void deleteAlumno(Context ctx){
        try{
            String matricula = ctx.pathParam("matricula");
            serviceAlumno.delete(Integer.parseInt(matricula));
            ctx.status(201).result("Alumno eliminado correctamente");
        }catch(SQLException e){
            ctx.status(404).result("No se encontro el alumno " +
                    "con la matricula " + ctx.pathParam("matricula"));
        }

    }
    public void updateAlumno(Context ctx){
        try{
            Alumno al = ctx.bodyAsClass(Alumno.class);
            serviceAlumno.update(al);
            ctx.status(201).result("Alumno actualizado correctamente");
        }catch(SQLException e) {
            ctx.status(500).result("Error al actualizar los alumnos");
        }
    }
}
