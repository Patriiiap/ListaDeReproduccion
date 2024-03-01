package ListaReproduccion;

public class Cancion {

    private String titulo;
    private double duracion;


    //Constructor

    public Cancion (String titulo, double duracion){
        this.titulo = titulo;
        this.duracion = duracion;
    }

    //Métodos

    @Override
    public String toString() {
        return titulo + " : " + duracion ;
    }


    //Getters and Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
