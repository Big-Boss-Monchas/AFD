/*
 * Lenguajes y Autómatas I
 * Práctica #5
 * Escribir un programa que simule el autómata finito propuesto.
 * Angela Aguirre, Guadalupe Camacho, Josué Torres
 */
package pacmanghosts;

import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

public final class DFAGhost {

    String sigma;       // Alfabeto 012
    int nStates;        // # estados 4
    int initialState;   // inicia en 0
    int nSymbols;       // 3 diferentes
    Set<Integer> acceptStates;  // Estados finales (4)
    int[][] delta;            // Transiciones de estados
    // cada par es un estado no válido, por ejemplo:
    // el estado 0 no tiene transición para el alfabeto 2
    int[] avoid = {0, 2, 2, 2, 3, 1, 3, 2}; 
    boolean finish;     // indica si ha terminado

    public DFAGhost() {
        setSigma();
        setnStates();
        setInitialState();
        setnSymbols();
        setAcceptStates();
        setDelta();
        setFinish();
        calculateDelta();
    }

    public boolean getFinish() {
        return finish;
    }

    public void setFinish() {
        this.finish = false;
    }

    public String getSigma() {
        return sigma;
    }

    public void setSigma() {
        this.sigma = "012";
    }

    public int getnStates() {
        return nStates;
    }

    public void setnStates() {
        this.nStates = 4;
    }

    public int getInitialState() {
        return initialState;
    }

    public void setInitialState() {
        this.initialState = 0;
    }

    public int getnSymbols() {
        return nSymbols;
    }

    public void setnSymbols() {
        this.nSymbols = getSigma().length();
    }

    public Set<Integer> getAcceptStates() {
        return acceptStates;
    }

    public void setAcceptStates() {
        this.acceptStates = new TreeSet<>();
        this.acceptStates.add(4);
    }

    public int[][] getDelta() {
        return delta;
    }

    public void setDelta() {
        this.delta = new int[getnStates()][getnSymbols()];
    }

    // Método para ver las transiciones disponibles
    public void showDelta() {
        for (int i = 0; i < nStates; i++) {
            for (int j = 0; j < nSymbols; j++) {
                boolean cont = true;
                for (int k = 0; k < 8; k = k + 2) 
                    if (avoid[k] == i && avoid[k + 1] == j) cont = false;
                
                if (!cont) continue;
                
                System.out.println("Transition for state " + i + ", "
                   + getSigma().substring(j, j + 1) + ": State " + delta[i][j]);
            }
        }
    }
    
    // Transiciones
    private void calculateDelta() {
        delta[0][0] = 1;
        delta[0][1] = 2;
        delta[1][0] = 4;
        delta[1][1] = 2;
        delta[1][2] = 0;
        delta[2][0] = 0;
        delta[2][1] = 3;
        delta[3][0] = 0;
    }

    // Determina el estado que le corresponde al estado actual y el alfabeto 
    // mediante la matriz delta, y el estado le es asignado a q
    // Una vez determinado el estado, muestra la acción correspondiente.
    public int validateString(String cadenaValidar, int q, String ghostName) {
        int s = sigma.indexOf(cadenaValidar);
        q = delta[q][s];

        if (acceptStates.contains(q)) {
            this.finish = true;
            System.out.println("\n\nSe comió a Pac-Man!!! :(");
            return q;
        }
        System.out.println("");
        switch (q) {
            case 0:
                System.out.println(ghostName + " se mueve en el laberinto!");
                break;
            case 1:
                System.out.println(ghostName + " está persiguiendo a Pacman");
                break;
            case 2:
                System.out.println(ghostName + " huye de Pac-Man!!! Corre " 
                        + ghostName + ", corre!!");
                break;
            case 3:
                System.out.println("Aww te han pillado, " + ghostName 
                        + " regresa a su base");
                break;
            default:
                System.out.println("Error");
                break;
        }
        return q;
    }

    // Permite seleccionar al fantasmita con el cual simulará el autómata
    public String intro() {
        Scanner input = new Scanner(System.in);
        int selectCharacter;
        System.out.println("Bienvenido a Pacman!");
        System.out.println("Esta es la historia de un fantasmita.");
        System.out.println("Seleccione su fantasmita favorito:");
        System.out.println("0) Inky\n1) Blinky\n2) Pinky"
                + "\n3) Clyde\n4) Boo");
        System.out.print("Selección: ");
        do {
            while (!input.hasNextInt()) {
                input.next();
                System.out.print("Seleccione de las opciones mostradas: ");
            }
            selectCharacter = input.nextInt();
            if (selectCharacter < 0 || selectCharacter > 4) {
                System.out.print("Seleccione de las opciones mostradas: ");
            }
        } while (selectCharacter < 0 || selectCharacter > 4);

        switch (selectCharacter) {
            case 0:
                return "Inky";
            case 1:
                return "Blinky";
            case 2:
                return "Pinky";
            case 3:
                return "Clyde";
            case 4:
                return "Boo";
        }
        return "";
    }
    
    public int mainMenu(int state, String ghostName) {
        Scanner input = new Scanner(System.in);
        int selection = -1;
        
        // Dependiendo del estado en el que se encuentre, muestra las diferentes
        // opciones que le son disponibles y solamente le permite seleccionar
        // de esos posibles escenarios.
        switch (state) {
            case 0:
                System.out.println("\n0) " + ghostName + " mira a Pac-Man"
                        + "\n1) Pac-Man se come una pelotilla de poder");
                System.out.print("Selección: ");
                do {
                    while (!input.hasNextInt()) {
                        input.next();
                        System.out.print("Seleccione de las opciones mostradas: ");
                    }
                    selection = input.nextInt();
                    if (selection < 0 || selection > 1) {
                        System.out.print("Seleccione de las opciones mostradas: ");
                    }
                } while (selection < 0 || selection > 1);
                break;
            case 1:
                System.out.println("\n0) " + ghostName + " alcanza a Pac-Man"
                        + "\n1) Pac-Man se come una pelotilla de poder"
                        + "\n2) " + ghostName + " pierde de vista a Pac-Man");
                System.out.print("Selección: ");
                do {
                    while (!input.hasNextInt()) {
                        input.next();
                        System.out.print("Seleccione de las opciones mostradas: ");
                    }
                    selection = input.nextInt();
                    if (selection < 0 || selection > 2) {
                        System.out.print("Seleccione de las opciones mostradas: ");
                    }
                } while (selection < 0 || selection > 2);
                break;
            case 2:
                System.out.println("\n0) Se termina el poder de Pac-Man"
                        + "\n1) Pac-Man se come a " + ghostName + "!! :(");
                System.out.print("Selección: ");
                do {
                    while (!input.hasNextInt()) {
                        input.next();
                        System.out.print("Seleccione de las opciones mostradas: ");
                    }
                    selection = input.nextInt();
                    if (selection < 0 || selection > 1) {
                        System.out.print("Seleccione de las opciones mostradas: ");
                    }
                } while (selection < 0 || selection > 1);
                break;
            case 3:
                System.out.println("\n0) " + ghostName + " llega a la base");
                System.out.print("Selección: ");
                do {
                    while (!input.hasNextInt()) {
                        input.next();
                        System.out.print("Seleccione la opción mostrada: ");
                    }
                    selection = input.nextInt();
                    if (selection != 0) {
                        System.out.print("Seleccione la opción mostrada: ");
                    }
                } while (selection != 0);
                break;
        }
        state = this.validateString(Integer.toString(selection), state, ghostName);
        return state;
    }

    @Override
    public String toString() {
        return "\nFelicidades, ha ganado!!!!!";
    }
}
