import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        int[] arreglo = new int[1000]; //Nuevo arreglo
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = i * 2; // Arreglo ordenado
        }
        Random random = new Random(); //objetivo random
        int objetivo = random.nextInt(1999);
        while (objetivo %2 != 0){ //Pido numeros existentes en el vector
            objetivo = random.nextInt(1999);
        }
        long inicio = System.nanoTime(); //mido el tiempo con System.nanoTime
        int posicion = Secuencial.binarySearch(arreglo, objetivo);
        long fin = System.nanoTime();
        long duracion = fin - inicio; //Calculo el tiempo para cada algoritmo
        System.out.println("Busqueda Binaria Formato Secuencial: ");
         if (posicion != -1) {
            System.out.println("Elemento encontrado ( " + objetivo + " )  en la posicion: " + posicion + "con un tiempo de: " + duracion + "ns");
        } else {
            System.out.println("Elemento no encontrado." + objetivo);
        }

        inicio = System.nanoTime();
        posicion = parallelBinarySearch(arreglo, objetivo, 2);
        fin = System.nanoTime();
        duracion = fin - inicio;
        System.out.println("Busqueda Binaria Formato Concurrente: ");
         if (posicion != -1) {
            System.out.println("Elemento encontrado ( " + objetivo + " ) en la posicion: " + posicion + "con un tiempo de: " + duracion + "ns");
        } else {
            System.out.println("Elemento no encontrado." + objetivo);
        }
        
    }

    // Método principal para ejecutar la búsqueda binaria en paralelo
    public static int parallelBinarySearch(int[] arr, int target, int numThreads) throws InterruptedException {
        
        int length = arr.length; // Tamaño total del arreglo
        SearchThread[] threads = new SearchThread[numThreads]; // Arreglo de hilos
        int segment = length / numThreads; // Tamaño de cada segmento para cada hilo

        // Crear e iniciar cada hilo con su segmento correspondiente
        for (int i = 0; i < numThreads; i++) {
            int start = i * segment; // Índice inicial del segmento
            int end = (i == numThreads - 1) ? length - 1 : (start + segment - 1); // Índice final del segmento

            // Se crea el hilo y se le asignan los parámetros
            threads[i] = new SearchThread(arr, target, start, end);
            threads[i].start(); // Se inicia el hilo
        }

        // Esperamos a que todos los hilos terminen y revisamos sus resultados
        for (SearchThread thread : threads) {
            thread.join(); // Espera a que el hilo termine

            // Si el hilo encontró el objetivo, devolvemos su resultado
            if (thread.getResult() != -1) {
                return thread.getResult();
            }
        }

        // Si ningún hilo encontró el valor, retornamos -1
        return -1;
    }
}
