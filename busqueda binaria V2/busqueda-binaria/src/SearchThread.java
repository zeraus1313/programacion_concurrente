public class SearchThread extends Thread {
    Data[] arr;         // Arreglo en el que se realizará la búsqueda
    int target;        // Elemento que estamos buscando
    int start, end;    // Índices de inicio y fin del segmento que le toca al hilo
    int result = -1;   // Resultado de la búsqueda (-1 si no se encuentra)

    // Constructor: recibe el arreglo, el objetivo a buscar, y los límites del segmento
    public SearchThread(Data[] arr, int target, int start, int end) {
        this.arr = arr;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    // Método que se ejecuta cuando el hilo comienza
    public void run() {
        int left = start;
        int right = end;

        // Bucle de búsqueda binaria clásico dentro del segmento asignado
        while (left <= right) {
            int mid = left + (right - left) / 2; // Evita desbordamiento

            // Si encontramos el elemento, lo guardamos y terminamos
            if (arr[mid].isEqualTo(target)) {
                result = mid;
                return; // Salimos del hilo
            }

            // Si el objetivo es mayor, buscamos en la mitad derecha
            if (arr[mid].getValue() < target) {
                left = mid + 1;
            } 
            // Si es menor, buscamos en la mitad izquierda
            else {
                right = mid - 1;
            }
        }
    }

    // Método para obtener el resultado de búsqueda del hilo
    public int getResult() {
        return result;
    }
}


    

