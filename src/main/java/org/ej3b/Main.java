package org.ej3b;

import io.javalin.Javalin;
import org.ej3b.config.Init;
import org.ej3b.models.Alumno;
import org.ej3b.repositories.ReposityAlumno;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*configuracion*/)
                .start(7000);

        Init.intAlumnos().register(app);
    }
}