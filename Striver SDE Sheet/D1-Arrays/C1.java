//31. Next Permutation
// Appraoch-1 : Optimal
// TC = O((3N) = 3*O(N) approx., SC = O(1)
import java.util.*;

public class C1 {
    public static List< Integer > nextGreaterPermutation(List< Integer > A){
        int n = A.size();
        int ind = -1; //break point
        for(int i = n-2; i>= 0; i--){
            if(A.get(i) <A.get(i+1)){
                ind = i;
                break;
            }
        }
        // brak point not found, reverse the array
        if(ind == -1){
            Collections.reverse(A);
            return A;
        }
        // find the next greater element to the right of the break point
        // and swap it with the break point
        for(int i = n-1; i>ind; i--){
            if(A.get(i) > A.get(ind)){
                int tmp = A.get(i);
                A.set(i, A.get(ind));
                A.set(ind, tmp);
                break;
            }
        }
        // reverse the right half
        List<Integer> sublist = A.subList(ind+1, n);
        Collections.reverse(sublist);
        return A;
    }

    public static void main(String args[]) {
        List<Integer> A = Arrays.asList(new Integer[] {2, 1, 5, 4, 3, 0, 0});
        List<Integer> ans = nextGreaterPermutation(A);

        System.out.print("The next permutation is: [");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println("]");

    }
}
