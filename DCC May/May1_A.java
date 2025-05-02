// 2071. Maximum Number of Tasks You Can Assign
// Approach A: Binary Search + Greedy + TreeSet(Balanced BST-> RedBlack Tree)
/*  TC = O(W.LogW.LogW{BS->Loop->BS; W->Workers} + MLogM{Workers Sorting} + NLogN{Tasks Sorting}), 
    SC = O(W{MultiSet/Map})
  */

import java.util.*;

public class May1_A {
    private boolean canAssign(int mid, List<Integer> workers, List<Integer> tasks, int pills, int strength){
       //3
       TreeSet<Integer> usableWorkers = new TreeSet<>();
       int n = workers.size();
       for(int i = n - mid; i<n; i++){
        usableWorkers.add(workers.get(i));
       }
         //4
       for(int i = mid-1; i>= 0; --i){ //Iterating from R->L
          int task = tasks.get(i);     // hardest task among the selected mid
          Integer currWorker = usableWorkers.last(); //// strongest remaining worker

          if(currWorker < task){
             if(pills <= 0){
                return false;
             }
             // Find the weakest worker who can do the task with pill
             Integer weakestWorker = usableWorkers.ceiling(task-strength); //apply lower bound
            if( weakestWorker == null){ //goes out of bound
                return false;
            }
            
            pills--;
            usableWorkers.remove(weakestWorker);
        } else{
            usableWorkers.remove(currWorker);
                }
       }
       return true;
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength){
        //1: To assign the hardest tasks to the strongest workers (greedy logic)
        Arrays.sort(tasks);  
        Arrays.sort(workers);

        List<Integer> taskList = new ArrayList<>();
        List<Integer> workerList = new ArrayList<>();
        for(int task: tasks) taskList.add(task);
        for(int worker: workers) workerList.add(worker);

        //2
        int low = 0;
        int high = Math.min(tasks.length, workers.length);
        int assigned = 0; //track max number of tasks that can be done
        // Binary Search over assigned tasks
        while( low<= high){
            int mid = low + (high -low)/2;
            if(canAssign(mid, workerList, taskList, pills, strength)){
                assigned = mid;
                low = mid+1;
            } else{
                high = mid - 1;
            }
        }
        return assigned;
    }
}
/*
 1. Why LinkedHashMap (OrderedHashMap) wouldn't work:
     It Maintains insertion order, not sorted order.
     It Does not support ceiling(), floor(), last() efficiently. -> O(LogN){TreeSet}, O(N){LinkedHashMap}
 2. “I’m giving my hardest task to the strongest guy left. If he can’t do it, I look for the weakest guy who can — and give him a pill.”
 
     */