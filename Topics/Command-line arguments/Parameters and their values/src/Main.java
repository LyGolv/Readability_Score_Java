
class Problem {
    public static void main(String[] args) {
        for (int i = 0, ptr = 1; i < args.length; i++, ptr *= -1) {
            System.out.print(args[i] + (ptr == 1 ? "=" : "\n"));
        }
    }
}
