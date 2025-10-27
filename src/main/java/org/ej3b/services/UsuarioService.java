package org.ej3b.services;

import org.ej3b.models.Usuarios;
import org.ej3b.repositories.RepositoryUsuario;

import java.sql.SQLException;

public class UsuarioService {
    private final RepositoryUsuario repositoryUsuario;
    public UsuarioService(RepositoryUsuario repositoryUsuario){
    this.repositoryUsuario = repositoryUsuario;
    }

    public void save(Usuarios u)throws SQLException {
        repositoryUsuario.save(u);
    }

    public Usuarios findByUsername(Usuarios u)throws SQLException {
        return  repositoryUsuario.findByUser(u);
    }
}
