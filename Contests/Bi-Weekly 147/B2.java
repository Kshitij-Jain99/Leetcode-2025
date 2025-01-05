//3408. Design Task Manager
// Approach B: Better than A: TreeSet + Hashmap 
// SC = O(N)
/*
 #TC:
 1.Add/Edit/Remove operations: Same TC->(PriorityQueue): O(log N) and (TreeSet): O(log N)
 2. execTop: 
    PQ: O(N log N) in the worst case, as it may need to poll multiple invalid tasks.
    O(log N) as TreeSet.pollFirst() directly returns the highest priority task.
    Approach B is more efficient for the execTop operation, since it directly removes the highest priority task from the TreeSet with a simpler operation.
 */
import java.util.*;
public class B2 {
     private TreeSet<int[]> tasks;
    private Map<Integer, int[]> taskMap;

    public B2(List<List<Integer>> tasks) {
        this.tasks = new TreeSet<>((a,b) -> b[2] == a[2] ? b[1] - a[1] : b[2] - a[2]);
        this.taskMap = new HashMap<>();
        for(List<Integer> task: tasks) {
            int[] t = new int[]{task.get(0), task.get(1), task.get(2)};
            this.tasks.add(t);
            this.taskMap.put(task.get(1), t);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        int[] task = new int[]{userId, taskId, priority};
        this.tasks.add(task);
        this.taskMap.put(taskId, task);
    }
    
    public void edit(int taskId, int newPriority) {
        int[] task = taskMap.get(taskId);
        tasks.remove(task);
        taskMap.remove(taskId);
        int[] newTask = new int[]{task[0], task[1], newPriority};
        tasks.add(newTask);
        taskMap.put(taskId, newTask);
    }
    
    public void rmv(int taskId) {
        this.tasks.remove(this.taskMap.get(taskId));
        this.taskMap.remove(taskId);
    }
    
    public int execTop() {
        if(this.tasks.isEmpty()) return -1;
        int[] task = this.tasks.pollFirst();
        this.taskMap.remove(task[1]);
        return task[0];
    }
}
