public class Main {
    public static void main(String[] args) {
        System.out.println(FieldSample.age);
        callLateInit();
        callConst();
        MethodSample.increment(10);
    }

    private static void callConst() {
        System.out.println(ConstSample.VERSION);
    }

    static void callLateInit() {
        System.out.println(LateInitSample.userName);
        //System.out.println(LateInitSample.password); compilation error
    }
}
