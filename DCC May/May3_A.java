//1007. Minimum Domino Rotations For Equal Row
// Appraoch-A: GREEDY
// TC = O(2n) = O(n), SC = O(1)
/*
 1. value on each domino can be between 1 - 6
 2. check for each number from 1 to 6 whether it is possible for all tops or bottoms to be that number and count the number of operations needed for the same.
 3. Instead of checking for all values we only check for top[0] and bottom[0] as they are the only two values that can be present in the first domino.
 4. Return mim. count
 */
public class May3_A {
    public int minDominoesRotations(int[] top, int[] bottom){
        int res = getRotation(top, bottom, top[0]);
        if(bottom[0] != top[0]) {
            res = Math.min(res, getRotation(top, bottom, bottom[0]));
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    public int getRotation(int[] top, int[] bottom, int target){
        int swapTop = 0, swapBottom = 0;
        for(int i = 0; i< top.length; i++){
            if(top[i] != target && bottom[i] != target) return Integer.MAX_VALUE;
            if(top[i]!= target) swapTop++;
            if(bottom[i] != target) swapBottom++;
        }
        return Math.min(swapTop, swapBottom);
    }
}
