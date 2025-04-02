//2140. Solving Questions With Brainpower
// Appraoch-A: Top-Down DP
// TC = O(N), SC = O(N) where N is the number of questions
import java.util.Arrays;

class Apr1_A{
	private long findMaxPoints(int[][] questions, int pos, long[] mem){
		if(pos >= questions.length){ //out of bounds
			return 0;
		}
		if(mem[pos] != -1){//Memoization Check
			return mem[pos]; //subproblem already solved
		}

		long exclude = findMaxPoints(questions, pos+1, mem);
		long include = questions[pos][0] + findMaxPoints(questions, pos + questions[pos][1] + 1,mem);
		mem[pos] = Math.max(exclude, include); //store the result in memoization array
		return mem[pos]; 

	}

	public long mostPoints(int[][] questions){  //Main fxn
		int n = questions.length;
		long[] mem = new long[n];  //Space for memoization
		Arrays.fill(mem,-1);
		return findMaxPoints(questions,0, mem);
	}
}


