//Find the duplicate in an array of N+1 integers
// Appraoch-3: Linked List Cycle method
// TC = O(N), SC = O(1)

public class D3 {
    public static int findDuplicate(int[] nums){
        //initial pos
        int slow = nums[0];
        int fast = nums[0];
        //Till I Collision
        do{
            slow = nums[slow];
            fast = nums[nums[fast]]; //2x
        } while(slow != fast);

        // Till II Collision
        fast = nums[0];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
