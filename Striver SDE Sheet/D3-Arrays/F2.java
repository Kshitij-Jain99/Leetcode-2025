//Reverse Pairs
//Approach-2: Modified Merge Sort
// TC = O(logN*(N+N)){81 ms LC}, SC = O(N) 
import java.util.*;

public class F2 {
    public static void merge(int[] arr, int low, int mid, int high){ //O(N)
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low; //S(i) of left half
        int right = mid+1; //S(i) of right half
        
        //storing ele. in temp array in sorted manner
        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                temp.add(arr[left]);
                left++;
            } else{
                temp.add(arr[right]);
                right++;
            }
        }
          // if elements on the right half are still remaining
          while(right <= high){
            temp.add(arr[right]);
            right++;
          }
          // if elements on the left half are still remaining
          while(left <= mid){
            temp.add(arr[left]);
            left++;
          }
          // transfering all elements from temporary to arr 
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }

    }

    public static int countPairs(int[] arr, int low, int mid, int high){
        int right = mid+1;
        int cnt = 0;
        for(int i = low; i<= mid; i++){  //  less than O(N) due to optimizations, nearly logN
            while(right <= high && arr[i] > 2*arr[right]) right++;
            cnt += (right - (mid + 1));
        }
        return cnt;
    }

    public static int mergeSort(int[] arr, int low, int high){ //O(LogN)
        int cnt = 0;
        if(low >= high) return cnt;
        int mid = (low+high)/2;
        //every merge we are calling will return us a count so add all
        cnt += mergeSort(arr, low, mid); //left half{lower levels cnt}
        cnt += mergeSort(arr, mid+1, high); //right half{lower levels cnt}
        cnt += countPairs(arr, low, mid, high); //modification: Before merge
        merge(arr, low, mid, high);
        return cnt;
    }

    public static int team(int[] skill, int n){
        return mergeSort(skill, 0, n-1);
    }
}
