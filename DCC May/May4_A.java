//1128. Number of Equivalent Domino Pairs
// Approach A: Brute Force-TLE
// TC =O (N^2), SC = O(1)
public class May4_A {
    public int numEquivDominoPairs(int[][] dominoes) {
        int n = dominoes.length;
        int count = 0;
  
        for(int i = 0; i<=n-2; i++ ){
          for(int j = i+1; j<= n-1; j++){
              if(dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1] ||
                 dominoes[i][1] == dominoes[j][0] && dominoes[i][0] == dominoes[j][1]){
                  count++;
                 }
          }
        }
        return count;
      } 
}
