package org.ej3b.repositories;

import org.ej3b.models.Alumno;
import org.ej3b.config.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class ReposityAlumno {

    private static final Logger log = LoggerFactory.getLogger(ReposityAlumno.class);

    public List<Alumno> findAll() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";

        try(
                Connection conn = DBConfig.getDataSource().
                                getConnection();
                PreparedStatement stmt =conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
                while (rs.next()){
                    Alumno al = new Alumno();
                    al.setNombre(rs.getString("nombre"));
                    al.setEdad(rs.getInt("edad"));
                    al.setMatricula(rs.getInt("matricula"));
                    alumnos.add(al);
                }
        }
        return alumnos;
    }
    public void save(Alumno alumno) throws SQLException{
        String sql = "INSERT INTO alumno (nombre,edad,matricula) " +
                "VALUES (?,?,?)";
        try(
                Connection conn = DBConfig.getDataSource().
                        getConnection();
                PreparedStatement stmt =conn.prepareStatement(sql);
        ){
            stmt.setString(1,alumno.getNombre());
            stmt.setInt(2,alumno.getEdad());
            stmt.setInt(3,alumno.getMatricula());
            stmt.executeUpdate();
        }
    }
    public void delete(int matricula)throws SQLException{
            String sql = "DELETE FROM alumno WHERE matricula = ?";
            try(
                    Connection conn = DBConfig.getDataSource().
                            getConnection();
                    PreparedStatement stmt =conn.prepareStatement(sql);
            ){
                stmt.setInt(1,matricula);
                stmt.executeUpdate();
            }
    }
    public void update(Alumno alumno)throws SQLException{
        String sql = "UPDATE alumno SET nombre = ?, " +
                "edad = ? WHERE matricula = ?";
        try(
                Connection conn = DBConfig.getDataSource().
                        getConnection();
                PreparedStatement stmt =conn.prepareStatement(sql);
        ){
            stmt.setString(1,alumno.getNombre());
            stmt.setInt(2,alumno.getEdad());
            stmt.setInt(3,alumno.getMatricula());
            stmt.executeUpdate();
        }
    }
}
