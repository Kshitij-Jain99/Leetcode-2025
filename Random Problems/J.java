//3709. Design Exam Scores Tracker: https://leetcode.com/problems/design-exam-scores-tracker/description/

import java.util.ArrayList;

public class J {
    
    //Appraoch-1: Prefix sums(ArrayList) + binary search → fast range sum queries
    //TC = O(logN), SC = O(N)
    /*
    1. Prefix sums to get range sums efficiently.
    2. Binary search to find which exams fall in the time interval [startTime, endTime]
    */
     private ArrayList<Integer> times; //stores all exam timestamps (in increasing order).
     private ArrayList<Long> pref;     //prefix sum array of scores

     public ExamTracker(){
        times = new ArrayList<>();
        pref = new ArrayList<>();
        pref.add(0L);   //pref is one longer than times(avoids edge cases)
     }

    public void record(int time, int score) {
        times.add(time);
        pref.add(pref.get(pref.size()-1) + (long) score);  //new total = previous total + score.
    }
    
    public long totalScore(int startTime, int endTime) {
        int l = lowerBound(times, startTime);  
        int r = upperBound(times, endTime)-1;

        //If the range [startTime, endTime] contains no recorded exams,
        if(l > r) return 0L; //it means there’s no overlap (no elements in the range).
       
        return pref.get(r+1) - pref.get(l);
    }

     private int lowerBound(ArrayList<Integer> arr, int target){
        int left = 0, right = arr.size();
        while(left < right){
            int mid = (left + right)/2;
            if(arr.get(mid) < target) left = mid+1;
            else right = mid;
        }
        return left;
     }

     private int upperBound(ArrayList<Integer> arr, int target){
        int left = 0, right = arr.size();
        while(left < right){
            int mid = (left + right)/2;
            if(arr.get(mid) <= target) left = mid+1;
            else right = mid;
        }
        return left;
     }

     
/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */


   //Appraoch-2: Dynamic Segment Tree
   //TC = O(log(maxTime)) per add/query; SC = (#unique times × log(maxTime))
   /*
    1. To efficiently record scores at given times and query total scores in a time range.
    2. Time values can go up to 10⁹ — too large for a normal array-based segment tree.
    3. We use a dynamic segment tree that creates nodes only where needed, so memory usage stays low.
    4. Each node stores the sum of scores in its range, allowing fast range queries.
    5. Appraoch:
       a. Dynamic Segment Tree:
            Each node covers a range [l, r].
            Leaf nodes store the score for a specific time.
            Internal nodes store the sum of their children.
        b. Add / Record Score
            Traverse the tree to the correct leaf corresponding to time.
            Add the score, and update parent nodes on the way up.
        c. Query Total Score
            Traverse only nodes that overlap with [startTime, endTime].
            Return 0 if no overlap, or the stored sum if fully within range.
    */

   private SegmentTree sg;
    public ExamTracker() {
        sg=new SegmentTree();
    }
    
    public void record(int time, int score) {
        sg.add(time,score);
    }
    
    public long totalScore(int startTime, int endTime) {
        return sg.query(startTime,endTime);
    }


    class SegmentTree{
    private static class Node{
        long sum;
        Node left;
        Node right;
    }

    private static final int minTime=1;
    private static final int maxTime=(int)1e9;
    private final Node root=new Node();

    private void add(Node node , int l , int r, int time, long score){
        if(l==r){
            node.sum+=score;
            return;
        }

        int mid=l+(r-l)/2;
        if(time<=mid){
            if(node.left==null) node.left=new Node();
            add(node.left,l,mid,time,score);
        }
        else{
            if(node.right==null) node.right=new Node();
            add(node.right,mid+1,r,time,score);
        }
        long leftSum= (node.left!=null)? node.left.sum : 0;
        long rightSum=(node.right!=null)? node.right.sum : 0;
        node.sum=leftSum+rightSum;
    }
    public void add(int time, int score){
        add(root,minTime, maxTime,time, (long)score);
    }
    private long query(Node node, int l , int r, int ql, int qr){
        if(node==null || ql>r || qr <l) return 0;
        if(ql<=l && r<=qr) return node.sum;

        int mid=l+(r-l)/2;
        return query(node.left,l,mid,ql,qr) + query(node.right,mid+1,r,ql,qr);
    }
    public long query(int startTime, int endTime){
        return query(root, minTime, maxTime,startTime, endTime);
    }
}
}
