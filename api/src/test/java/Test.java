public class Test {

    public static void main(String[] args) {
        int[] numbers = {
                1, 11, 111, 1111
        };

        for(int number : numbers){
            System.out.println("Formateado: \t" + String.format("%05d", number));
        }
    }

}
