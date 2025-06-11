public class Secuencial {

    public static int binarySearch(Data[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        // Mientras el límite izquierdo no supere al derecho
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Si encontramos el objetivo
            if (arr[mid].isEqualTo(target))
                return mid;

            // Si el objetivo está en la mitad derecha
            if (arr[mid].getValue() < target)
                left = mid + 1;

            // Si está en la mitad izquierda
            else
                right = mid - 1;
        }
        return -1; // Elemento no encontrado
    }

}

