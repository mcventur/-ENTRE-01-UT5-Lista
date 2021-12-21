/**
 * Un objeto de esta clase
 * guarda una lista de n�meros enteros
 * 
 * La clase incluye una serie de m�todos de instancia
 * para hacer operaciones sobre la lista
 * y dos  m�todos est�ticos para trabajar con
 * arrays de dos dimensiones
 *
 * @author - Jonathan Del Arco
 */

import java.util.Random;
import java.util.Arrays;

public class ListaNumeros {
    public static final int DIMENSION = 10;
    public static final int ANCHO_FORMATO = 6;
    public static final char CAR_CABECERA = '-';

    private static final Random generador = new Random();
    private static int[] listaNumeros;
    private int pos;
    
    /**
     * Constructor de la clase ListaNumeros
     * Crea e inicializa adecuadamente los
     * atributos
     *
     * @param n el tama�o m�ximo de la lista
     */
    public ListaNumeros(int n) {
        listaNumeros = new int[n];
        pos=0;
    }

    /**
     * A�ade un valor al final de la lista 
     * siempre que no est� completa
     *
     * @param numero el valor que se a�ade.  
     * @return true si se ha podido a�adir, false en otro caso
     */
    public boolean addElemento(int numero) {
        boolean OK = true;
        boolean noOK = false;
        if(pos<listaNumeros.length){
            listaNumeros[pos]=numero;
        } else{
            return noOK;
        }
        pos++;
        return OK;
    }

    /**
     * @return true si la lista est� completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return pos>=listaNumeros.length;
    }

    /**
     * @return true si la lista est� vac�a, false en otro caso.
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos==0;
    }

    /**
     * @return el n� de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vac�a la lista
     */
    public void vaciarLista() {
       for(int i=0; i<pos; i++){
           listaNumeros[i]=0;
       }
       pos=0;
    }

    /**
     * @return una cadena con representaci�n textual de la lista 
     * (leer enunciado)
     * 
     * Si la lista est� vac�a devuelve ""
     */
    public String toString() {
       if(estaVacia()){
           
       }else{
           Utilidades str=null;
           String numero = "";
           String resultado = "";
           String cSup="";
           
           for(int i=0; i<pos; i++){
               cSup+= String.format("%c" + "%c" + "%c" + "%c" + "%c" + "%c", CAR_CABECERA,CAR_CABECERA,CAR_CABECERA,CAR_CABECERA,CAR_CABECERA,CAR_CABECERA);
           }
           
           for(int i=0; i<pos; i++){
               numero = str.centrarNumero(listaNumeros[i], ANCHO_FORMATO);
               resultado += numero;
           }
           
           return cSup + "\n" + resultado + "\n" + cSup;
       }
       
       return "";
    }
    
    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     *  
     * @return el segundo valor m�ximo en la lista
     * Cuando no haya un segundo m�ximo el m�todo 
     * devolver� el valor Integer.MIN_VALUE
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} se devuelve 28
     * Si lista = {21, -5, 28, -7, 77} se devuelve 28
     * Si lista = {77, 21} se devuelve 21
     * Si lista = {21} se devuelve Integer.MIN_VALUE
     * Si lista = {21, 21, 21, 21} se devuelve Integer.MIN_VALUE
     * 
     * No se puede usar ning�n otro array auxiliar ni hay que ordenar previamente
     * la lista
     */
    public int segundoMaximo() {             
        int max=listaNumeros[0];
        int segMax=listaNumeros[0];
        int seRepite=0;
        for(int i=0; i<pos; i++) {
            if(listaNumeros[i] >= max) {
                segMax = max;
                max=listaNumeros[i];
            }else if(listaNumeros[i] > segMax && listaNumeros[i] < max){
                segMax=listaNumeros[i];
            } else if(pos==1){
                seRepite = Integer.MIN_VALUE;
            }
        }
        return segMax;
    }

    /**
     * El m�todo coloca los valores que son segundos m�ximos al principio de
     * la lista respetando el orden de aparici�n del resto de elementos
     * 
     * No se puede usar ning�n otro array auxiliar ni hay que ordenar previamente
     * la lista
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} 
     * lista queda  {28, 28, 28, 28, 21, -5, -7, 77, 77, -17, 21, 15, 77} y se devuelve true
     * 
     * Si lista = {77, 21} lista queda {21, 77} y se devuelve true
     * Si lista = {21} lista queda igual y se devuelve false
     * Si lista = {21, 21, 21, 21} lista queda igual y se devuelve false
     * 
     * @return true si se han colocado los segundos m�ximos
     *          false si no se han colocado los segundos m�ximos porque no hab�a ninguno
     */
    public boolean segundosMaximosAlPrincipio() {
        int contador=0;
        int aux=0;
        int segMax=segundoMaximo();
        
        //Cuenta las veces que se repite
        for(int i=0; i<pos; i++){
            if(listaNumeros[i]==segMax){
                contador++;
            }
        }
        //A�ade el numero tantas veces repetido al principio del Array 
        for(int j=0; j<contador; j++){
                listaNumeros[j]=segMax;
        }
                
        return true;
    }
   

    /**
     * @param numero b�squeda binaria de  numero en lista
     * @return devuelve -1 si no se encuentra o la posici�n en la que aparece
     *  
     * El array original lista no se modifica
     * Para ello crea  previamente una copia
     * de lista y trabaja  con la copia
     *  
     * Usa exclusivamente m�todos de la clase Arrays
     */
    public int buscarBinario(int numero) {
        //Crea la copia
        int[] copiaListaNumeros = Arrays.copyOf(listaNumeros, listaNumeros.length);
        //Ordena la copia
        Arrays.sort(copiaListaNumeros);
        //Asigno a una variable el valor del �ndice
        int index = Arrays.binarySearch(copiaListaNumeros, numero);
        //Si el numero existe en el Array devuelve el �ndice en donde est�
        for(int i=0; i<pos; i++){
            if(numero==copiaListaNumeros[i]){
                return index;
            }
        }
        return -1;
    }

    /**
     * 
     * @return devuelve un array bidimensional de enteros de tama�o DIMENSION
     * inicializado con valores aleatorios entre 0 y 10 inclusive
     * 
     * Estos valores van a representar el brillo de una zona del espacio
     * 
     */
    public static int[][] crearBrillos() {
       int[][] brillos = new int[DIMENSION][DIMENSION];
       
       //A�ade numeros aleatorios al Array
       for(int fila=0; fila<DIMENSION; fila++){
           for(int columna=0; columna<DIMENSION; columna++){
               brillos[fila][columna]= generador.nextInt(11);
           }
       }
       
       return brillos;
    }

    /**
     * @param  un array bidimensional brillos 
     * @return un nuevo array bidimensional de valores booleanos
     *          de las mismas dimensiones que el array brillos con
     *          valores true en las posiciones donde hay estrellas
     * 
     * Una posici�n f,c del array brillos es una estrella 
     * si la suma del valor de los brillos de sus cuatro vecinos 
     * (arriba, abajo, derecha e izquierda) es mayor que 30
     * 
     * Nota -  No hay estrellas en los bordes del array brillos
     */
    public static boolean[][] detectarEstrellas(int[][] brillos) {
        boolean[][] tablaBoolean=new boolean[DIMENSION][DIMENSION];
        
        for(int fila=1; fila<DIMENSION-1; fila++){
           for(int columna=1; columna<DIMENSION-1; columna++){
               if(brillos[fila][columna-1] + brillos[fila-1][columna] + brillos[fila][columna+1] + brillos[fila+1][columna]>30){
                   tablaBoolean[fila][columna]=true;
               } else {
                   tablaBoolean[fila][columna]=false;
               }
           }
        }
       
        return tablaBoolean;
    }
}