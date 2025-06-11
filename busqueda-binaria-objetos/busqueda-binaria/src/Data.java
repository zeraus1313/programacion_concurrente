public class Data {
    int value;

    public Data(int value) {
        this.value = value;
    }

    public boolean isEqualTo(int target) {
        try {
            Thread.sleep(0, 1500); // Simula trabajo costoso (1.5 microsegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.value == target;
    }

    public int getValue() {
        return value;
    }
}