//1007. Minimum Domino Rotations For Equal Row
// Appraoch-B: HashMap 
// TC = O(6n) , SC = O(1)

public class May3_B {
    public int minDominoesRotations(int[] top, int[] bottom){
        int n = top.length, res = Integer.MAX_VALUE;
        int[] face = new int[7];
        //how many times i appears across both rows.
        for(int i =0; i<n; i++){
            face[top[i]]++;
            if(bottom[i] != top[i]) face[bottom[i]]++;
        }

        for(int x = 1; x<= 6; x++){
            if(face[x] < n) continue;
            int maintainTop = 0, maintainBottom = 0;
            boolean possible = true;
            for(int i =0; i<n; i++){
                if(top[i] != x) maintainTop++;
                if(bottom[i] != x) maintainBottom++;
            }
            if(possible) res = Math.min(res, Math.min(maintainTop, maintainBottom));
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
