//3408. Design Task Manager
// Approach A: PQ + Hashmap + Delayed Deletion[PQ]
// SC = O(N)-> Store all tasks objects[ O(N)], HashMap[ O(N)], TaskData objects[O(n)]
import java.util.*;

class B1 {

    class TaskData {
        int userId;
        int taskId;
        long priority;
        boolean removed;
        TaskData(int u, int t, long p) {
            userId = u;
            taskId = t;
            priority = p;
            removed = false; /*V.Imp: TaskData not actually removing from PQ but flagging it false
                               Randomly accessing anything in PQ is very costly as it will take O(N) time ->can use set also[other approach]*/
        }
    }

    PriorityQueue<TaskData> tasksQueue;
    Map<Integer, TaskData> tasksMap;
    
    //Constructor initialization: O(M.LogM) for M initial tasks
    public B1(List<List<Integer>> tasks) {
        tasksQueue = new PriorityQueue<>((a, b) -> { 
            //PQ sorting on 2 parameters
            //First on basis of maxPriority-> If same then on base of taskId
            if (a.priority != b.priority) {
                return Long.compare(b.priority, a.priority);
            }
            return Integer.compare(b.taskId, a.taskId);
        });
        
        tasksMap = new HashMap<>();
        //Adding tasks in HashMap
        for (List<Integer> triple : tasks) {
            add(triple.get(0), triple.get(1), triple.get(2));
        }
    }
    
    public void add(int userId, int taskId, int priority) {  //O(LogN)
        TaskData data = new TaskData(userId, taskId, priority);
        tasksMap.put(taskId, data);
        tasksQueue.offer(data);
    }
    
    public void edit(int taskId, int newPriority) {  //O(LogN)
        TaskData oldData = tasksMap.get(taskId);
        if (oldData != null) { //old data not there, edge case
            oldData.removed = true; 
            //old data is not removed but flagged fasle, it will be useful when edit, etc is called on it
            TaskData newData = new TaskData(oldData.userId, oldData.taskId, newPriority);
            tasksMap.put(taskId, newData);
            tasksQueue.offer(newData);
        }
    }
    
    public void rmv(int taskId) {  //O(1)
        TaskData data = tasksMap.get(taskId);
        if (data != null) {
            data.removed = true;
            //remove from HashMap but not PQ
            tasksMap.remove(taskId);
        }
    }
    
    public int execTop() {  //O(N.LogN)-worst, O(LogN)-avg.
        while (!tasksQueue.isEmpty()) {
            TaskData top = tasksQueue.peek();
            if (!top.removed) {
                tasksQueue.poll();
                tasksMap.remove(top.taskId);
                top.removed = true;
                return top.userId;
            }
            tasksQueue.poll();
        }
        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */