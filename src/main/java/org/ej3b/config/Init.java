package org.ej3b.config;

import org.ej3b.controllers.ControllerAlumno;
import org.ej3b.controllers.JwtMiddleware;
import org.ej3b.controllers.TokenManager;
import org.ej3b.controllers.UsuarioController;
import org.ej3b.repositories.RepositoryUsuario;
import org.ej3b.repositories.ReposityAlumno;
import org.ej3b.routes.RoutesAlumno;
import org.ej3b.routes.UsuarioRouter;
import org.ej3b.services.ServicieAlumno;
import org.ej3b.services.UsuarioService;

public class Init {

    public static RoutesAlumno intAlumnos(){
        ReposityAlumno reposityAlumno = new ReposityAlumno();
        ServicieAlumno servicieAlumno = new ServicieAlumno(reposityAlumno);
        ControllerAlumno controllerAlumno = new ControllerAlumno(servicieAlumno);
        RoutesAlumno routesAlumno = new RoutesAlumno(controllerAlumno);
        return routesAlumno;
    }

    public static UsuarioRouter initUsuarios(){
        RepositoryUsuario repositoryUsuario = new RepositoryUsuario();
        UsuarioService usuarioService = new UsuarioService(repositoryUsuario);
        TokenManager tokenManager = new TokenManager();
        JwtMiddleware jwtMiddleware = new JwtMiddleware(tokenManager);
        UsuarioController usuarioController = new UsuarioController(usuarioService,tokenManager);
        UsuarioRouter usuarioRouter = new UsuarioRouter(usuarioController,jwtMiddleware);
        return usuarioRouter;

    }

}
