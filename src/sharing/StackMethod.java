package sharing;

public class StackMethod {
    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        int s = sum(1, 2);
        int ss = inside(1, 2);
    }

    private static int sum(int a, int b) {
        int e =  a + b;
        return e;
    }

    private static int inside(int a, int b) {
        int s = a + b + sum(a, b);
        return s;
    }
}
