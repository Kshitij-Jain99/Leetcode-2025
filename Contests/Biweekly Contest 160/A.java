//3602. Hexadecimal and Hexatrigesimal Conversion

public class A {
  // Approach-1: Manual Base Conversion
  // TC = O(2.log n), SC = O(2.log n) //logn length string build up

  public String concatHex36_1(int n){
    long sqr = n*n;
    long cube = n*n*n;

    String getHexaDecimal = getConversion(sqr, 16);
    String getHexTri = getConversion(cube, 36);
    return getHexaDecimal + getHexTri;
  }    

  String getConversion(long val, int base){
    StringBuilder sb = new StringBuilder();
    String digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    int rem = 0;
    while(val > 0){
         rem = (int)(val % base);
         sb.append(digit.charAt(rem));
         val = val/base;
    }
    return sb.reverse().toString();
  }

    // Approach-2: Using built-in conversion methods
    // TC = O(log n), SC = O(log n)
    public String concatHex36_2(int n) {
        long n2 = (long) n * n;
        long n3 = (long) n * n * n;

        String hexPart = Long.toHexString(n2).toUpperCase();     // Base-16
        String base36Part = Long.toString(n3, 36).toUpperCase(); // Base-36

        return hexPart + base36Part;
    }


    // Approach-3(Better than 1): Fixed Len. char[]
    // TC = O(2.log n), SC = O(2.log n)
    public String concatHex36_3(int n) {
        int n2 = n*n, n3 = n*n*n;

        int hex16Len = n2 == 0 ? 1: (int)(Math.log(n2) / Math.log(16)) + 1;
        int hex36Len = n3 == 0 ? 1: (int)(Math.log(n3) / Math.log(36)) + 1;

        char[] hex16Chars = new char[hex16Len];
        char[] hex36Chars = new char[hex36Len];

        for(int i = hex16Len - 1; i>= 0; i--){
            int rem = n2%16;
            hex16Chars[i] = (char)(rem < 10 ? '0' + rem : 'A' + rem - 10);
            n2 /= 16;
        }

        for(int i = hex36Len-1; i>=0; i--){
            int rem = n3%36;
            hex36Chars[i] = (char)(rem < 10 ? '0' + rem : 'A' + rem - 10);
            n3/= 36;
        }
        return new String(hex16Chars) + new String(hex36Chars);
    }
}

