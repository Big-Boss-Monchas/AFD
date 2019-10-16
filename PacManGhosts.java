/*
 * Lenguajes y Autómatas I
 * Práctica #5
 * Escribir un programa que simule el autómata finito propuesto.
 * Angela Aguirre, Guadalupe Camacho, Josué Torres
 */
package pacmanghosts;

public class PacManGhosts {

    public static void main(String[] args) {
        int state = 0;      

        DFAGhost ghostPacman = new DFAGhost();

        String ghostName = ghostPacman.intro();
        
        System.out.println("");
        System.out.println(ghostName + " se mueve en el laberinto!");
        
        // Itera hasta que se llega a la condición final
        do {            
            state = ghostPacman.mainMenu(state, ghostName);
        } while (!ghostPacman.getFinish());
        
        System.out.println(ghostPacman.toString());
    }
}
