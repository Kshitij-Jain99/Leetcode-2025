//Find the Majority Element that occurs more than N/2 times
// Appraoch-3: Moore's Voting Algorithm
// TC = O(N+N), SC  = O(1)

public class C3 {
    public static int majorityElement(int[] v){
    int n = v.length;
    int cnt = 0; //for tracking the count of element
    int el = 0; // for which element we are counting

    //applying the algorithm
    //calculate the count and find the expected majority element.
    for(int i = 0; i<n; i++){
        if(cnt == 0){ 
            cnt = 1;
            el = v[i]; //store the current element of the array as Element.
        } else if(el == v[i])  cnt++; // current element and Element are the same increase the Count by 1.
          else cnt--; //current element and Element are different decrease the Count by 1.
    }

    //checking if the standard element is the majority element or not
    //If the question states that the array must contain a majority element, in that case, we do not need the second check.
    int cnt1 = 0;
    for(int i =0; i<n; i++){
        if(v[i] == el) cnt1++;
    }

    if(cnt1 > (n/2)) return el;
    return -1;
  }
}
