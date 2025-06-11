// 4 Sum
// Approach-4: Better than optimal
// TC = O(N^2{i, j loop} + NlogN{Sorting}) = O(N^2),
// SC = O(logN){sorting re. stack} + O(1){per quadruplet} -> O(n^2){if all possible quadruplets are stored}
/*
 Optimizations::
 1. Lazy Initialization (Memory Efficiency):
   - uses AbstractList and lazy initialization, meaning it only computes the results when they are actually needed 
   - Appraoch-3 immediately computes all possible quadruplets, even if the caller only needs a subset of them
 2. Avoids Unnecessary Computations:
   - If the caller only checks ans.isEmpty(), appraoch-4 computes only until the first valid quadruplet is found (if any).
   - Appraoch-4  always computes all possible quadruplets before returning, even if the caller doesn’t need them all.
 3. Cleaner Abstraction:
   - It encapsulates the logic inside createList(), making it more modular and easier to maintain.
 4. Handles Edge Cases Early:
   -  immediately returns an empty list if arr.length < 4, avoiding unnecessary sorting and looping.
   */
import java.util.*;

public class B4 {
    public List<List<Integer>> fourSum(int[] arr, int target) {
		if (arr.length < 4)
			return Collections.emptyList();
		return new AbstractList<List<Integer>>() {
			List<List<Integer>> ans;

			@Override
			public int size() {
				if (ans == null)
					ans = createList(arr, target);
				return ans.size();
			}

			@Override
			public List<Integer> get(int index) {
				if (ans == null)
					ans = createList(arr, target);
				return ans.get(index);
			}
		};
	}

	private List<List<Integer>> createList(int[] arr, int target) {
		List<List<Integer>> ans = new ArrayList<>();

		Arrays.sort(arr);

		int len = arr.length;

		for (int i = 0; i < len - 3; i++) {
			if (i > 0 && arr[i] == arr[i - 1])
				continue;

			for (int j = i + 1; j < len - 2; j++) {
				if (j > i + 1 && arr[j] == arr[j - 1])
					continue;

				int left = j + 1;
				int right = len - 1;

				while (left < right) {
					long sum = (long) arr[i] + arr[j] + arr[left] + arr[right];

					if (sum == target) {
						ans.add(Arrays.asList(arr[i], arr[j], arr[left], arr[right]));

						left++;
						right--;

						while (left < right && arr[left] == arr[left - 1])
							left++;
						while (left < right && arr[right] == arr[right + 1])
							right--;
					} else if (sum < target) {
						left++;
					} else {
						right--;
					}

				}

			}
		}

		return ans;

	}
}
