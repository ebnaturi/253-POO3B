package org.ej3b.routes;

import io.javalin.Javalin;
import org.ej3b.controllers.ControllerAlumno;
import org.ej3b.controllers.UsuarioController;

public class UsuarioRouter {
    private final UsuarioController usuarioController;
    public UsuarioRouter(UsuarioController controllerAlumno){
        this.usuarioController = controllerAlumno;
    }

    public void register(Javalin app){
        app.post("/signup",usuarioController::registrarUsuario);
        app.post("/login",usuarioController::verificarUsuario);
    }
}
