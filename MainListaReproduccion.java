package ListaReproduccion;

import java.util.*;

public class MainListaReproduccion {

    public static void main(String[] args) {

        //Abumes y Canciones de cada Album
        Album zeit = new Album("Zeit", "Rammstein");
        zeit.addSong("Armee der Tristen", 3.25);
        zeit.addSong("Zeit", 5.21);
        zeit.addSong("Schwarz", 4.18);
        zeit.addSong("Giftig", 3.08);
        zeit.addSong("Zick Zack", 4.04);
        zeit.addSong("OK", 4.03);
        zeit.addSong("Meine Tränen", 3.44);
        zeit.addSong("Angst", 3.38);
        zeit.addSong("Dicke Titten", 3.49);
        zeit.addSong("Lügen", 4.39);
        zeit.addSong("Adieu", 4.03);

        Album nevermind = new Album("Nevermind", "Nirvana");
        nevermind.addSong("Smells Like Teen Spirit", 5.01);
        nevermind.addSong("In Bloom", 4.14);
        nevermind.addSong("Come as You Are", 3.39);
        nevermind.addSong("Breed", 3.03);
        nevermind.addSong("Lithium", 4.17);
        nevermind.addSong("Polly", 2.57);
        nevermind.addSong("Territotial Pissing", 2.22);
        nevermind.addSong("Drain You", 3.43);
        nevermind.addSong("Lounge Act", 2.36);
        nevermind.addSong("Stay Away", 3.32);
        nevermind.addSong("On a Plain", 3.16);
        nevermind.addSong("Something in the Way", 3.52);
        nevermind.addSong("Endless, Nameless", 6.44);

        //ArrayList de Albumes
        ArrayList<Album> albumes = new ArrayList<>();
        albumes.add(nevermind);
        albumes.add(zeit);

        //LinkedList de Lista de Reproducción con las canciones añadidas con los métodos addToPlayList
        LinkedList<Cancion> ListaDeReproduccion = new LinkedList<>();

        zeit.addToPlayList("Armee der Tristen", ListaDeReproduccion);
        nevermind.addToPlayList("Smells Like Teen Spirit", ListaDeReproduccion);
        zeit.addToPlayList(1, ListaDeReproduccion);
        nevermind.addToPlayList(2, ListaDeReproduccion);
        zeit.addToPlayList("Adieu", ListaDeReproduccion);

        //Llamada al Método play para usar la PlayList
        play(ListaDeReproduccion);

    }

    public static void imprimirMenu (){
        System.out.println("0 - Salir de la lista de Reproducción.");
        System.out.println("1 - Reproducir siguiente canción en la lista.");
        System.out.println("2 - Reproducir previa canción en la lista.");
        System.out.println("3 - Repetir cancion actual.");
        System.out.println("4 - Imprimir la lista de canciones en la PlayList.");
        System.out.println("5 - Imprimir el menú.");
        System.out.println("6 - Eliminar canción actual de la Lista de Reproducción");
    }

    public static void play (LinkedList<Cancion> ListaDeReproduccion){

        ListIterator<Cancion> it = ListaDeReproduccion.listIterator();

        boolean salir = false;
        imprimirMenu();
        int reproduciendo = 0; //Si es 1 es que se reproduce hacia delante, si es 2 hacia atrás

        while (salir == false){

            System.out.println("Elige una opcion");
            int eleccion = CapturaExcepcionesInt();

            switch (eleccion){
                case 0:
                    salir = true;
                    break;
                case 1:
                    //Reproduce la siguiente cancion
                    if(it.hasNext()){
                        if(reproduciendo == 2){ //Si hay siguiente y la lista está yendo para atrás avanzamos uno y luego imprimimos el siguiente
                            it.next();
                            System.out.println(it.next());
                        }
                        else { //Si hay siguiente y la lista está yendo hacia delante imprimimos el siguiente
                            System.out.println(it.next());
                        }
                        reproduciendo = 1; //variable de dirección indica que la lista está yendo hacia delante
                    }
                    else {
                        System.out.println("Has llegado al final de la lista");
                    }
                    break;
                case 2:
                    //Reproduce la canción previa
                    if(it.hasPrevious()){ //Si hay previo
                        if(reproduciendo == 1){ // Si hay previo y la lista está yendo hacia delante
                            it.previous();      // avanzamos al previo
                            if(it.hasPrevious()){   // y si hay otro previo lo imprimimos
                                System.out.println(it.previous());
                            }
                            else {                  //Sino quiere decir que estamos en el principio de la lista
                                System.out.println("Estás en el principio de la lista");
                            }

                        }
                        else { //Si hay previo y la lista está yendo hacia astrás
                            System.out.println(it.previous());
                        }
                        reproduciendo = 2; //variable de dirección indica que la lista está yendo hacia atrás
                    }
                    else {
                        System.out.println("Estás en el principio de la lista");
                    }
                    break;
                case 3:
                    //Reproduce la canción actual;
                    if(reproduciendo == 1){  //Si la lista va hacia delante imprimo la anterior (el iterador de mueve hacia atrás)
                        System.out.println(it.previous());
                        it.next();          // El iterador se mueve hacia delante para poder volver a imprimir el mismo
                    }
                    if(reproduciendo == 2){ //Si la lista va hacia atrás imprimo la siguiente (el iterador de mueve hacia delante)
                        System.out.println(it.next());
                        it.previous();      // El iterador se mueve hacia atrás para poder volver a imprimir el mismo
                    }
                    if (reproduciendo == 0) { //En caso de que aún no se haya reproducido nada
                        System.out.println("Debes reproducir una canción para poder repetirla");
                    }
                    break;
                case 4:
                    printList(ListaDeReproduccion);
                    break;
                case 5:
                    imprimirMenu();
                    break;
                case 6:
                    if (it.hasNext() && reproduciendo == 1){    //si tiene siguiente y va hacia delante lo borramos y pasamos a la canción siguiente
                        it.remove();
                        System.out.println(it.next());
                    }
                    else if (reproduciendo == 1 && it.hasPrevious()){ // si lo de antes no se cumple, si tiene anterior y va hacia delante borramos y pasamos a la canción anterior
                        it.remove();                                  // este es para cuando estamos al final de la lista y borramos la última canción
                        System.out.println(it.previous());
                    }
                    if (it.hasPrevious() && reproduciendo == 2) { //si tiene previa y va hacia atrás lo borramos y pasamos a la canción previa
                        it.remove();
                        System.out.println(it.previous());
                    }
                    else if (it.hasNext() && reproduciendo == 2){ // si lo de antes no se cumple, si tiene siguiente y va hacia atrás borramos y pasamos a la canción siguiente
                        it.remove();                              // este es para cuando estamos al principio de la lista y borramos la primera canción
                        System.out.println(it.next());
                    }
                    break;

            }
        }

    }

    public static void printList(LinkedList<Cancion> ListaReproduccion) {
        Iterator<Cancion> it = ListaReproduccion.iterator();
        int numero = 1;
        while (it.hasNext()) {
            System.out.println("Canción " + numero + ": " + it.next());
            numero++;
        }
    }

    public static int CapturaExcepcionesInt(){
        Scanner sc = new Scanner(System.in);

        int eleccion = 0;
        boolean aceptado = false;

        do {
            try{
                eleccion = sc.nextInt();
                if(eleccion >= 0 && eleccion <= 6){
                    aceptado = true;
                }else {
                    System.out.println("El número insertado debe estar entre 0 y 6");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Solo puedes introducir número del 0 al 6");
                sc.next();
            }
        }
        while (aceptado == false);

        return eleccion;
    }

}
