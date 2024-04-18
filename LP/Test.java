public class Test {

    public static int sizeofIntArray(int[] a) {
        return a.length * Integer.BYTES;
    }

    public static int sizeofCharArray(char[] a) {
        return a.length * Character.BYTES;
    }

    public static int sizeofByteArray(byte[] a) {
        return a.length * Byte.BYTES;
    }

    public static int sizeofShortArray(short[] a) {
        return a.length * Short.BYTES;
    }

    public static int sizeofLongArray(long[] a) {
        return a.length * Long.BYTES;
    }

    public static int sizeofDoubleArray(double[] a) {
        return a.length * Double.BYTES;
    }

    public static int sizeofFloatArray(float[] a) {
        return a.length * Float.BYTES;
    }

    public static int sizeofInt() {
        return Integer.BYTES;
    }

    public static int sizeofChar() {
        return Character.BYTES;
    }

    public static int sizeofByte() {
        return Byte.BYTES;
    }

    public static int sizeofShort() {
        return Short.BYTES;
    }

    public static int sizeofLong() {
        return Long.BYTES;
    }

    public static int sizeofDouble() {
        return Double.BYTES;
    }

    public static int sizeofFloat() {
        return Float.BYTES;
    }

    public static void main(String[] args) {
        // int[] a = new int[24];
        // String a = "crazygyatt";
        // System.out.println(a.length());

        int a[] = new int[24];
        System.out.println(sizeofIntArray(a));
        System.out.println(sizeofInt());
    }

}