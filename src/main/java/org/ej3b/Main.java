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
        app.get("/inicio/", ctx -> ctx.result("Hello World"));
        app.get("/saludame/", ctx -> ctx.result("Hello Elias"));
        app.post("/saludame/",ctx -> {
            String  nom = ctx.body();
            ctx.result("Hello "+nom);
        });

        Alumno[] lista = new Alumno[10];

        app.get("/sobjeto/",ctx->{
            Alumno[] als = new Alumno[3];

            als[0] = new Alumno();
            als[0].setNombre("Elias");
            als[0].setEdad(20);
            als[0].setMatricula(123456789);

            Alumno al2 = new Alumno();
            al2.setNombre("Juan");
            al2.setEdad(21);
            al2.setMatricula(987654321);
            // crear un arreglo asignando valores directatamente
            //Alumno[] als = {al,al2};
            //otra foram crear arreglos

            //als[0] = al;
            als[1] = al2;

            ctx.json(als);
        });

        app.get("/enteros/",ctx->{
            //int[][] num = {{1,2,3},{4,5,6}};

            int[][] num = new int[2][3];
            num[1][1]=5;
            num[0][2]=3;

            ctx.json(num);
        });

        app.post("/alumno/",ctx->{
            Alumno[] al = ctx.bodyAsClass(Alumno[].class);
            if (al != null || al.length > 0) {
                String mat ="";
                for(Alumno b:al) {
                    mat += b.getMatricula() + " ";
                    for (int i = 0; i <lista.length; i++) {
                        if(lista[i] == null){
                            lista[i] = b;
                            i =lista.length;
                        }
                    }
                }
                ctx.result("se ha agregado el alumno con la "
                        + "matricula " + mat);
            }
        });

        app.get("/alumno/",ctx->{
            ctx.json(lista);
        });

        app.get("/alumno/{id}",ctx->{
            String matricula = ctx.pathParam("id");
            Alumno alr=null;
            for (Alumno alumno : lista) {
                if(alumno == null){
                    continue;
                }
                System.out.println(alumno.getMatricula() == Integer.parseInt(matricula));
                if(alumno.getMatricula() == Integer.parseInt(matricula)){
                    alr = alumno;
                }
            }
            if(alr != null){
                ctx.json(alr);
            }
            else {
                ctx.result(
                        "No se encontro el alumno con la matricula "
                                + Integer.parseInt(matricula));
            }
        });



        Init.intAlumnos().register(app);
    }
}