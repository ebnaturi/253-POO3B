package org.ej3b.models;

public class Inscripcion {
    private Alumno alumno;
    private Materias materia;
    private float[] cal;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public float[] getCal() {
        return cal;
    }

    public void setCal(float[] cal) {
        this.cal = cal;
    }
}
