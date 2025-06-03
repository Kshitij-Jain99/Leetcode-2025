//Merge two Sorted Arrays Without Extra Space
//Approach-3: Gap Method
// TC = O((n+m)*log(n+m)) ->(Inner Loop)*(Outer loop), SC = O(1)

public class C3 {
    public static void swapIfGreater(long[] arr1, long[] arr2, int idx1, int idx2 ){
        if(arr1[idx1] > arr2[idx2]){
            //sawp
            long temp = arr1[idx1];
            arr1[idx1] = arr2[idx2];
            arr2[idx2] = temp;
        }
    }

    public static void merge(long[] arr1, long[] arr2, int n, int m){
        // len of imaginary single array
        int len = n+m;
        // gap initially
        int gap = (len/2) + len%2;
            //Or  ceil(len / 2.0); Using ceil() function (not preferred in performance-critical code):
            //Or  (len + 1) / 2; widely used in competitive programming and low-level code for computing ceiling division by 2.

        while(gap>0){
            //place 2 pointers
            int left = 0;
            int right = left + gap;
            while(right < len){
                //case 1:Left in arr1[], right in arr2[]
                if(left <n && right>=n) swapIfGreater(arr1, arr2, left, right-n);

                //case 2: both pointers in arr2[]
                else if(left >= n) swapIfGreater(arr2, arr2, left-n, right-n);

                //case 3: both pointers in arr1[]
                else swapIfGreater(arr1, arr1, left, right);
            
                left++; right++;
            }
            
        //break if iteration gap = 1 is completed
        if(gap == 1) break;

        //Otherwise, calculate new gap
        gap = (gap/2) + (gap%2);
        }  
    }
}
