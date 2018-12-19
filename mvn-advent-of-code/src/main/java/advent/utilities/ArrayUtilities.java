package advent.utilities;

public interface ArrayUtilities {
    static void print(Object[][] input) {
        for (int c = 0; c != input.length; c++) {
            for (int r = 0; r != input[c].length; r++) {
                System.out.print(input[c][r]);
            }
            System.out.println();
        }
    }
}
