package org.ej3b.repositories;

import org.ej3b.config.DBConfig;
import org.ej3b.models.Usuarios;

import java.sql.*;

public class RepositoryUsuario {

    public RepositoryUsuario(){

    }

    public Usuarios findByUser(Usuarios u)throws SQLException{
        String sql = "SELECT * FROM usuarios WHERE nom = ?";
        try(Connection conn = DBConfig.getDataSource(). getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql);) {
            stmt.setString(1,u.getNom());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Usuarios user = new Usuarios();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPwsd(rs.getString("pswd"));
                return user;
            }
        }
        return null;
    }
    public void save(Usuarios u)throws SQLException{
        String sql = "INSERT INTO usuarios (nom,pswd) VALUES (?,?)";
        try(Connection conn = DBConfig.getDataSource(). getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            stmt.setString(1,u.getNom());
            stmt.setString(2,u.getPwsd());
            int filasAfectadas = stmt.executeUpdate();
            //verficar
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar el registro");
            }
            //obtener las claves
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                u.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("No se pudo obtener el id generado");
            }
        }
    }
}
