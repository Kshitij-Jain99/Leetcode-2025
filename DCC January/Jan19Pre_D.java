//42. Trapping Rain Water
// Approach D: Monotonic deque (explicit handling of leftmost)
//TC = O(n) SC = O(n)

import java.util.ArrayDeque;
import java.util.Deque;

public class Jan19Pre_D {
 public int trap(int[] height) {
        Deque<Integer> dq = new ArrayDeque<>();
        int result = 0;

        for(int i=0; i<height.length; i++){
            int curr = height[i];
            if(dq.isEmpty() && curr==0){
                continue;
            }
            if(dq.isEmpty() || curr <= dq.peekLast()){
                dq.offerLast(height[i]);
                continue;
            }
            
            int count = 0;
            while(!dq.isEmpty() && curr > dq.peekLast()){
                int diff = Math.min(curr, dq.peekFirst()) - dq.peekLast();
                result += diff;
                dq.removeLast();
                count++;
            }
            if(!dq.isEmpty() && curr < dq.peekFirst()){
                for(int j=0;j<count;j++){
                    dq.offerLast(curr);
                }
            }
            while(!dq.isEmpty() && curr >= dq.peekFirst()){
                dq.removeFirst();
            }
            dq.offerLast(curr);
        }
        
        return result;
    }   
}
