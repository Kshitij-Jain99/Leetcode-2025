//278. First Bad Version
// Approach: Binary Search
// TC = O(), SC = O()
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
/*
 1. One bad version could leads to subsequent versions to be bad.
 2. Similar to finding pivot in rotated sorted array.
 3. Since all bad are on one side and all godd on other so we can apply Binary Search.
 */
import java.util.*;

public class May1_Additional extends VersionControl {
    public int firstBadVersion(int n){
        int l = 0, h = n;
        while(l<=h){
            int mid = l+(h-l)/2;
            if(isBadVersion(mid)) h = mid-1;
            else l = mid+1;
        }
         return l;
    }
}
