/**
 * Un objeto de esta clase
 * guarda una lista de números enteros
 * 
 * La clase incluye una serie de métodos de instancia
 * para hacer operaciones sobre la lista
 * y dos  métodos estáticos para trabajar con
 * arrays de dos dimensiones
 *
 * @author - Carlos Arevalo
 */
import java.util.Random;
import java.util.Arrays;
public class ListaNumeros {
    public static final int DIMENSION = 10;
    public static final int ANCHO_FORMATO = 6;
    public static final char CAR_CABECERA = '-';
    private static final Random generador = new Random();
    private int lista[];
    private int pos;
    public int segundoMaximo;
    /**
     * Constructor de la clase ListaNumeros
     * Crea e inicializa adecuadamente los
     * atributos
     *
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros() {
        pos = 0;
        int i = 0;
    }

    /**
     * Añade un valor al final de la lista 
     * siempre que no esté completa
     *
     * @param numero el valor que se añade.  
     * @return true si se ha podido añadir, false en otro caso
     */
    public void addElemento(int numero) {
        if(pos<lista.length){
            lista[pos] = numero;
            pos++;
        }
        this.pos = pos;
    }

    /**
     * @return true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
       return pos == lista.length;
    }

    /**
     * @return true si la lista está vacía, false en otro caso.
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * @return el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
        pos = 0;
    }

    /**
     * @return una cadena con representación textual de la lista 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
        //TODO
        String listaString = "";
        for(int i = 0; i<pos;i++){
            if(pos>0){
                listaString += Utilidades.centrarNumero(lista[i], ANCHO_FORMATO);
            }
        }
        listaString = representacion(listaString);
        return listaString;
    }

    private String representacion(String listaString){
        int guiones = pos * ANCHO_FORMATO;
        String cabecera = "";
        for(int i = 0; i<guiones; i++){
            cabecera += CAR_CABECERA;
        }
        return cabecera + "\n" + listaString + "\n" + cabecera;
    }
    
    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     *  
     * @return el segundo valor máximo en la lista
     * Cuando no haya un segundo máximo el método 
     * devolverá el valor Integer.MIN_VALUE
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} se devuelve 28
     * Si lista = {21, -5, 28, -7, 77} se devuelve 28
     * Si lista = {77, 21} se devuelve 21
     * Si lista = {21} se devuelve Integer.MIN_VALUE
     * Si lista = {21, 21, 21, 21} se devuelve Integer.MIN_VALUE
     * 
     * No se puede usar ningún otro array auxiliar ni hay que ordenar previamente
     * la lista
     */
    public int segundoMaximo() {       
        int primerMaximo = 0;
        int segundoMaximo = 0;
        int numero;
        for(int i=0;i<lista.length; i++){
            numero = lista[i];
            if(numero>primerMaximo){
                primerMaximo = numero;
            }
            if(segundoMaximo<primerMaximo && segundoMaximo<numero && numero<primerMaximo){
                segundoMaximo = numero;
            }
        }
        return segundoMaximo;
    }
    
    /**
     * El método coloca los valores que son segundos máximos al principio de
     * la lista respetando el orden de aparición del resto de elementos
     * 
     * No se puede usar ningún otro array auxiliar ni hay que ordenar previamente
     * la lista
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} 
     * lista queda  {28, 28, 28, 28, 21, -5, -7, 77, 77, -17, 21, 15, 77} y se devuelve true
     * 
     * Si lista = {77, 21} lista queda {21, 77} y se devuelve true
     * Si lista = {21} lista queda igual y se devuelve false
     * Si lista = {21, 21, 21, 21} lista queda igual y se devuelve false
     * 
     * @return true si se han colocado los segundos máximos
     *          false si no se han colocado los segundos máximos porque no había ninguno
     */
    public boolean segundosMaximosAlPrincipio() {
        int numero = 0;
        int segundo = 0;
        segundo = segundoMaximo;
        int c = 0;
        for(int i=0;i<lista.length;i++)
        {
            numero = lista[i];
            if(segundo == numero)
            {
                for(int x=lista.length - 1;x>0;x--){
                    numero = lista[x];
                    lista[x-1] = numero;
                }
                c++;
                for(int a = 0; c<a;c++){
                    lista[a] = segundo;
                }
            }
        }  
        return true;
    }
    
    /**
     * @param numero búsqueda binaria de  numero en lista
     * @return devuelve -1 si no se encuentra o la posición en la que aparece
     *  
     * El array original lista no se modifica
     * Para ello crea  previamente una copia
     * de lista y trabaja  con la copia
     *  
     * Usa exclusivamente métodos de la clase Arrays
     */
    public int buscarBinario(int numero) {
         int[] listaCopia = new int [lista.length];
         listaCopia = lista;
         Arrays.sort(listaCopia);
         return Arrays.binarySearch(listaCopia, numero);
    }

    /**
     * 
     * @return devuelve un array bidimensional de enteros de tamaño DIMENSION
     * inicializado con valores aleatorios entre 0 y 10 inclusive
     * 
     * Estos valores van a representar el brillo de una zona del espacio
     * 
     */
    public void crearBrillos() {
       int tabla[][] = new int [DIMENSION][DIMENSION];
       for(int filas = 0; filas<tabla.length; filas++){
           for(int columnas = 0; columnas < tabla[filas].length; columnas++){
               tabla[filas][columnas] = (int)(Math.random() * 10 + 1);
            }
        }
    }

    /**
     * @param  un array bidimensional brillos 
     * @return un nuevo array bidimensional de valores booleanos
     *          de las mismas dimensiones que el array brillos con
     *          valores true en las posiciones donde hay estrellas
     * 
     * Una posición f,c del array brillos es una estrella 
     * si la suma del valor de los brillos de sus cuatro vecinos 
     * (arriba, abajo, derecha e izquierda) es mayor que 30
     * 
     * Nota -  No hay estrellas en los bordes del array brillos
     */
    public void detectarEstrellas() {
        //TODO
        
    }

}
 