package org.ej3b.controllers;

import org.ej3b.models.Usuarios;
import org.ej3b.services.UsuarioService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.sql.SQLException;
import java.util.Map;
import com.password4j.Password;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final TokenManager tokenManager;
    public UsuarioController(UsuarioService usuarioService,TokenManager tokenManager){
        this.usuarioService = usuarioService;
        this.tokenManager = tokenManager;
    }


    public void registrarUsuario(Context ctx){
        try{
            Usuarios ur = ctx.bodyAsClass(Usuarios.class);
            String pswdhased = Password.hash(ur.getPwsd()).withBcrypt().getResult();
            ur.setPwsd(pswdhased);
            usuarioService.save(ur);
            String token = tokenManager.issueToken(""+ur.getId());
            ctx.status(201).json(Map.of(
                    "userId", ur.getId(),
                    "token", token,
                    "success", true,
                    "message","Usuario registrado correctamente"
            ));
        } catch (SQLException e) {
            ctx.status(500).result("Error al registrar el usuario");
        }
    }

    public void verificarUsuario(Context ctx){
        Usuarios ul = ctx.bodyAsClass(Usuarios.class);
        try{
            Usuarios ur = usuarioService.findByUsername(ul);
            if(ur == null) {
                throw new SQLException("Usuario no encontrado");
            }
            boolean rs = Password.check(ul.getPwsd(),ur.getPwsd()).withBcrypt();
            if(rs) {
                //ctx.status(201).result("Usuario verificado correctamente");
                String token = tokenManager.issueToken(ur.getId()+"");
                ctx.json(Map.of(
                        "userId", ur.getId(),
                        "token", token,
                        "success", true,
                        "message","Usuario verificado correctamente"
                ));
            }
            else {
                ctx.status(404).result("Usuario no encontrado");
            }
            }catch (SQLException e){
                ctx.status(403).json(Map.of(
                        "error", "Credenciales inválidas",
                        "success", false,
                        "message",e.getMessage()
                ));
            }

    }
}
