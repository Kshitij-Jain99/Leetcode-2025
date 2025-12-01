//Find the repeating and missing numbers
// Approach-4: XOR
// TC = O(4*N) , SC = O(1)
/*
 1. Find the XOR of the repeating number(X) and the missing number(Y)
      (X^Y) = (a[0]^a[1]^.....^a[n-1]) ^ (1^2^........^N)
 2. Find the first different bit from right between the repeating and the missing number i.e. the first set bit from right in (X^Y)
    By convention, the repeating and the missing number must be different and since they are different they must contain different bits. Now, our task is to find the first different bit from the right i.e. the end. We know, the XOR of two different bits always results in 1. The position of the first different bit from the end will be the first set bit(from the right) in (X^Y) that we have found in step 1.
 3. Based on the position of the different bits we will group all the elements ( i.e. all array elements + all elements between 1 to N) into 2 different groups
    If particular pos of bit is 0, we will insert the element into the 1st group otherwise, we will insert it into the 2nd group. 
 4. Finally, we have two variables i.e. two numbers zero and one. Among them, one is repeating and the other is missing. It’s time to identify them. 
    We will traverse the entire array and check how many times variable zero appears. 
    If it appears twice, it will be the repeating number, otherwise, it will be the missing. Now, based on variable zero’s identity, we can easily identify in which category, variable one belongs.
 */
public class E4 {
     public static int[] findMissingRepeatingNumbers(int[] a) {
        int n = a.length; // size of the array
        int xr = 0;

        for(int i = 0; i<n; i++){
            xr = xr^a[i]; //XOR Of all array elements
            xr = xr ^ (i+1); //XOR of integer 1 to N
        }         // result-> xr = (X^Y)

        int number = (xr & ~(xr-1)); 
        
        //Group the numbers: array ele
        int zero = 0;
        int one = 0;
        for(int i =0; i<n; i++){
            if((a[i] & number) != 0) one = one^a[i];
            else zero = zero^a[i];
        }
        //Group the numbers: 1 to N
        for(int i = 1; i<=n; i++){
          if((i & number) != 0) one = one^i;
          else zero = zero^i;
        }
         //Identify the numbers:
        int cnt = 0;
        for(int i =0; i<n; i++){
            if(a[i] == zero) cnt++;  
        }

        if(cnt == 2) return new int[]{zero, one}; 
        return new int[]{one, zero};
    }

   
}
