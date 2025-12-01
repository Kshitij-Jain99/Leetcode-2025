//Reverse Pairs
//Approach-X: General Theory
 
/*
 1. The general principles behind these solutions and its possible application to a number of similar problems.
 2. The fundamental idea is very simple: break down the array and solve for the subproblems.
 3. A breakdown of an array naturally reminds us of subarrays. To smoothen our following discussion, let's assume the input array is nums, with a total of n elements.
     Let nums[i, j] denote the subarray starting from index i to index j (both inclusive), T(i, j) as the same problem applied to this subarray (for example, for Reverse Pairs, T(i, j) will represent the total number of important reverse pairs for subarray nums[i, j]).
 4. With the definition above, it's straightforward to identify our original problem as T(0, n - 1). Now the key point is how to construct solutions to the original problem from its subproblems. 
    This is essentially equivalent to building recurrence relations for T(i, j). Since if we can find solutions to T(i, j) from its subproblems, we surely can build solutions to larger subarrays until eventually the whole array is spanned.
 5. While there may be many ways for establishing recurrence relations for T(i, j), here I will only introduce the following two common ones:
   I. Sequential recurrence relation: T(i, j) = T(i, j - 1) + C, i.e., elements will be processed sequentially and C denotes the subproblem for processing the last element of subarray nums[i, j].
   II. Partition recurrence relation: T(i, j) = T(i, m) + T(m + 1, j) + C where m = (i+j)/2, i.e., subarray nums[i, j] will be further partitioned into two parts and C denotes the subproblem for combining the two parts. 
 6. For either case, the nature of the subproblem C will depend on the problem under consideration, and it will determine the overall time complexity of the original problem.
    So usually it's crucial to find efficient algorithm for solving this subproblem in order to have better time performance. Also pay attention to possibilities of overlapping subproblems, in which case a dynamic programming (DP) approach would be preferred.
 7. Apply these two recurrence relations to this problem:
    I -- Sequential recurrence relation:
    A. set i = 0, i.e., the subarray always starts from the beginning: T(0, j) = T(0, j - 1) + C
    B.  subproblem C now becomes "find the number of important reverse pairs with the first element of the pair coming from subarray nums[0, j - 1] while the second element of the pair being nums[j]
    C. for a pair (p, q) to be an important reverse pair, it has to satisfy the following two conditions:
       p < q AND nums[p] > 2 * nums[q]
    D. the first condition is met automatically; so we only need to consider the second condition.
    E. The straightforward way of searching would be a linear scan of the subarray -> Brute Force Approach
    F. To improve the searching efficiency, a key observation is that the order of elements in the subarray does not matter, since we are only interested in the total number of important reverse pairs. This suggests we may sort those elements and do a binary search instead of a plain linear scan
       If the searching space (formed by elements over which the search will be done) is "static" (it does not vary from run to run), placing the elements into an array would be perfect for us to do the binary search.
       However, this is not the case here. After the j-th element is processed, we need to add it to the searching space so that it becomes searchable for later elements, which renders the searching space expanding as more and more elements are processed.
       Therefore we'd like to strike a balance between searching and insertion operations.
    G. This is where data structures like binary search tree (BST) or binary indexed tree (BIT) prevail, which offers relatively fast performance for both operations.
        
    II -- Partition recurrence relation
    A. setting i = 0, j = n - 1, m = (n-1)/2, we have: T(0, n - 1) = T(0, m) + T(m + 1, n - 1) + C
    B. subproblem C now reads "find the number of important reverse pairs with the first element of the pair coming from the left subarray nums[0, m] while the second element of the pair coming from the right subarray nums[m + 1, n - 1]".
    C. So here is the Merge-sort-based solution, where the function "reversePairsSub" will return the total number of important reverse pairs within subarray nums[l, r]. 
    D. The two-pointer searching process is represented by the nested while loop involving variable p, while the rest is the standard merging algorithm.
  
8.  Lastly let me name a few leetcode problems that fall into the patterns described above and thus can be solved with similar ideas.
   A. For leetcode 315, applying the sequential recurrence relation (with j fixed), the subproblem C reads: find the number of elements out of visited ones that are
      smaller than current element, which involves searching on "dynamic searching space"; applying the partition recurrence relation, we have a subproblem C: for each
      element in the left half, find the number of elements in the right half that are smaller than it, which can be embedded into the merging process by noting that 
      these elements are exactly those swapped to its left during the merging process.
   B. For leetcode 327, applying the sequential recurrence relation (with j fixed) on the pre-sum array, the subproblem C reads: find the number of elements out of 
      visited ones that are within the given range, which again involves searching on "dynamic searching space"; applying the partition recurrence relation, we have a
      subproblem C: for each element in the left half, find the number of elements in the right half that are within the given range, which can be embedded into the 
      merging process using the two-pointer technique.
 */