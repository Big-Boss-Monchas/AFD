package afd;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AFD {
    Scanner input = new Scanner (System.in);
    String sigma;       // Alfabeto
    int nStates;        // # estados
    int initialState;
    int nSymbols;
    Set<Integer> acceptStates;  // Estados finales
    int [][] delta ;            // Transiciones

    public AFD(String sigma, int nStates, Set<Integer> acceptStates) {
        this.sigma = sigma;
        this.nStates = nStates;
        this.initialState = 0;
        this.nSymbols = sigma.length();
        this.acceptStates = acceptStates;
        delta = new int [nStates][nSymbols];
        calculateDelta();
    }

    public String getSigma() {
        return sigma;
    }

    public void setSigma(String sigma) {
        this.sigma = sigma;
    }

    public int getnStates() {
        return nStates;
    }

    public void setnStates(int nStates) {
        this.nStates = nStates;
    }

    public int getInitialState() {
        return initialState;
    }

    public void setInitialState(int initialState) {
        this.initialState = initialState;
    }

    public int getnSymbols() {
        return nSymbols;
    }

    public void setnSymbols(int nSymbols) {
        this.nSymbols = nSymbols;
    }

    public Set<Integer> getAcceptStates() {
        return acceptStates;
    }

    public void setAcceptStates(Set<Integer> acceptStates) {
        this.acceptStates = acceptStates;
    }

    public int[][] getDelta() {
        return delta;
    }

    public void setDelta(int[][] delta) {
        this.delta = delta;
    }
    
    // Recorre la cantidad de estados y de simbolos del alfabeto
    //  a cada uno le asigna un estado que le sigue
    private void calculateDelta(){
        for (int i = 0; i < nStates; i++) {
            for (int j = 0; j < nSymbols; j++) {
                System.out.print("Transition for state " +  i  + ", "
                        + sigma.substring(j, j + 1) + ": ");
                delta[i][j]= input.nextInt();
            }
        }
    }
    
    // Recorre cada caracter de la cadena a validar.
    // Asigna un numero al caracter que pertenece en el alfabeto.
    // Hace la coincidencia de la matriz y le asigna el estado a una variable.
    // Si la variable coincide con uno de los estados finales, la cadena 
    // es aceptada como valida.
    public String validateString(String cadenaValidar){
        int q, s;
        q = 0;
        for (int i = 0; i < cadenaValidar.length(); i++) {
            s = sigma.indexOf(cadenaValidar.substring(i, i + 1));
            q = delta[q][s];
        }
        if (acceptStates.contains(q)) {
            return "La cadena es valida";
        }
        return "La cadena no es valida";
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int num;
        System.out.print("Introduzca el alfabeto: ");
        String alfabeto = input.nextLine();
        
        System.out.print("Introduzca la cantidad de estados: ");
        int estados = input.nextInt();
        
        // Acepta las entradas del usuario hasta que entre un valor menor a 0
        System.out.print("Introduzca los estados finales: ");
        Set<Integer> aceptado = new TreeSet<>();
        while((num = input.nextInt()) > -1){
            aceptado.add(num);
        }
         
        // Para terminar de introducir las cadenas a validar, basta con usar ~
        AFD automata = new AFD(alfabeto, estados, aceptado);
        System.out.print("Introduzca la cadena: ");
        String cadena; 
        while((cadena = input.next()) != null){
            if (cadena.equals("~")) {
                return;
            }
            System.out.println(automata.validateString(cadena));
            System.out.print("Introduzca la cadena: ");
        }
    }
}
