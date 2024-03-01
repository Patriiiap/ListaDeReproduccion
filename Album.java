package ListaReproduccion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {

    private String nombre;
    private String artista;
    private List<Cancion> canciones;

    //Constructor

    public Album(String nombre, String artista){
        this.nombre = nombre;
        this.artista = artista;
        canciones = new ArrayList<>();
    }

    //Métodos

    private Cancion findSong (String titulo){

        for(int i = 0; i < canciones.size(); i++){
            if(canciones.get(i).getTitulo().equalsIgnoreCase(titulo)){
                return canciones.get(i);
            }
        }
        return null;
    }

    public boolean addSong(String titulo, double duracion){

        boolean anadir = true;

        if(findSong(titulo) == null){
            Cancion cancion = new Cancion(titulo, duracion);
            canciones.add(cancion);
            anadir = true;
        }
        else {
            anadir = false;
        }

        return anadir;
    }

    public boolean addToPlayList (int numeroPista, LinkedList<Cancion> listaReproduccion){

        boolean anadirALista = false;

        if(findSong(canciones.get(numeroPista).getTitulo()) != null){
            listaReproduccion.add(canciones.get(numeroPista));
            anadirALista = true;
        }
        else {
            anadirALista = false;
        }

        return anadirALista;
    }

    public boolean addToPlayList (String titulo, LinkedList<Cancion> listaReproduccion){

        boolean anadirALista = false;

        if(findSong(titulo) != null){
            listaReproduccion.add(canciones.get(canciones.indexOf(findSong(titulo))));
            anadirALista = true;
        }
        else {
            anadirALista = false;
        }

        return anadirALista;

    }

    public void printAlbum (){
        for (int i = 0; i < canciones.size(); i++){
            System.out.println(canciones.get(i));
        }
    }

}
