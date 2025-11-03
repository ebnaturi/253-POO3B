package org.ej3b.routes;

import io.javalin.Javalin;
import io.javalin.http.UnauthorizedResponse;
import org.ej3b.controllers.ControllerAlumno;
import org.ej3b.controllers.JwtMiddleware;
import org.ej3b.controllers.UsuarioController;

public class UsuarioRouter {
    private final UsuarioController usuarioController;
    private final JwtMiddleware jwtMiddleware;
    public UsuarioRouter(UsuarioController controllerAlumno, JwtMiddleware jwtMiddleware){
        this.usuarioController = controllerAlumno;
        this.jwtMiddleware = jwtMiddleware;
    }

    public void register(Javalin app){
        app.post("/signup",usuarioController::registrarUsuario);
        app.post("/login",usuarioController::verificarUsuario);
        jwtMiddleware.apply(app);
        app.exception(UnauthorizedResponse.class,jwtMiddleware::noautorizado);
    }
}
