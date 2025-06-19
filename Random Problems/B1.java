//Spiral matrix
// Approach-1:
// TC = O(m*n), SC = O(1)

import java.util.ArrayList;
import java.util.List;

public class B1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int sRow = 0, sCol = 0, eRow = m-1, eCol = n-1;
        List<Integer> ans = new ArrayList<>(m*n);

        while(sRow <= eRow && sCol <= eCol){
            for(int j = sCol; j <= eCol; j++) ans.add(matrix[sRow][j]); //top
            for(int i = sRow+1; i<= eRow; i++) ans.add(matrix[i][eCol]); //right
            for(int j = eCol-1; j>= sCol; j--){                          //botom
                if(sRow == eRow) break;
                ans.add(matrix[eRow][j]);
            }
            for(int i = eRow-1; i>= sRow+1; i--){     //left
                if(sCol == eCol) break;
                ans.add(matrix[i][sCol]);
            }
              sRow++; eRow--; sCol++; eCol--;

        }
          return ans;
    }
}
