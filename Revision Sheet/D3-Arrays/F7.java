//Reverse Pairs
//Approach-6: BIT-based solution
// TC = O(N.LogN), SC = O(N) 

import java.util.Arrays;

public class F7 {
    //the searching and insertion operations are:
    private int search(int[] bit, int i) {
    int sum = 0;
    
    while (i < bit.length) {
        sum += bit[i];
        i += i & -i;
    }

    return sum;
}

private void insert(int[] bit, int i) {
    while (i > 0) {
        bit[i] += 1;
        i -= i & -i;
    }
}
 
public int reversePairs(int[] nums) {
    int res = 0;
    int[] copy = Arrays.copyOf(nums, nums.length);
    int[] bit = new int[copy.length + 1];
    
    Arrays.sort(copy);
    
    for (int ele : nums) {
        res += search(bit, index(copy, 2L * ele + 1));
        insert(bit, index(copy, ele));
    }
    
    return res;
}

private int index(int[] arr, long val) {
    int l = 0, r = arr.length - 1, m = 0;
    	
    while (l <= r) {
    	m = l + ((r - l) >> 1);
    		
    	if (arr[m] >= val) {
    	    r = m - 1;
    	} else {
    	    l = m + 1;
    	}
    }
    
    return l + 1;
}
}
/*
 More explanation for the BIT-based solution:
1. We want the elements to be sorted so there is a sorted version of the input array which is copy.
2. The bit is built upon this sorted array. Its length is one greater than that of the copy array to account for the root.
3. Initially the bit is empty and we start doing a sequential scan of the input array. For each element being scanned, we first search the bit to find all elements
   greater than twice of it and add the result to res. We then insert the element itself into the bit for future search.
4. Note that conventionally searching of the bit involves traversing towards the root from some index of the bit, which will yield a predefined running total
   of the copy array up to the corresponding index. For insertion, the traversing direction will be opposite and go from some index towards the end of the bit array.
5. For each scanned element of the input array, its searching index will be given by the index of the first element in the copy array that is greater than
   twice of it (shifted up by 1 to account for the root), while its insertion index will be the index of the first element in the copy array that is no less than itself
   (again shifted up by 1). This is what the index function is for.
6. For our case, the running total is simply the number of elements encountered during the traversal process. If we stick to the convention above, 
   the running total will be the number of elements smaller than the one at the given index, since the copy array is sorted in ascending order. However,
   we'd actually like to find the number of elements greater than some value (i.e., twice of the element being scanned), therefore we need to flip the convention. 
   This is what you see inside the search and insert functions: the former traversing towards the end of the bit while the latter towards the root.
 */