//3410. Maximize Subarray Sum After Removing All Occurrences of One Element
// TC = O(N.LogN), SC = O(N)
//Approach B: Segment Tree + TreeMap and Direct Element Removal Approach
import java.util.*;

class D2 {
    public long maxSubarraySum(int[] nums) {
        // edge cases: all negative or all positive
        boolean allNeg = true;
        boolean allPos = true;
        long totalSum = 0;
        for (int num : nums) {
            if (num > 0) {
                allNeg = false;
            }
            if (num <= 0) {
                allPos = false;
            }
            totalSum += num;
        }
        if (allNeg) {
            if (nums.length == 0) return 0;
            int maxNum = nums[0];
            for (int num : nums) {
                if (num > maxNum) {
                    maxNum = num;
                }
            }
            return maxNum;
        }
        if (allPos) {
            return totalSum;
        }

        List<Integer> processed = preprocess(nums);

        Set<Integer> uniqueNegSet = new HashSet<>();
        for (int num : processed) {
            if (num < 0) {
                uniqueNegSet.add(num);
            }
        }
        List<Integer> uniqueNegatives = new ArrayList<>(uniqueNegSet);
        Collections.sort(uniqueNegatives);

        // coordinate compression
        Map<Integer, Integer> negMap = new HashMap<>();
        for (int i = 0; i < uniqueNegatives.size(); i++) {
            negMap.put(uniqueNegatives.get(i), i);
        }

        int numNegatives = uniqueNegatives.size();

        SegmentTree st = new SegmentTree(numNegatives);
        long res = Long.MIN_VALUE;

        long cumSum = 0;

        for (int v : processed) {
            if (v >= 0) {
                if (numNegatives > 0) {
                    st.updateRangeAddPublic(0, numNegatives - 1, v);
                    int currentMax = st.queryRangeMaxCorrectPublic(0, numNegatives - 1);
                    res = Math.max(res, (long) currentMax);
                    st.updateRangeChmax0Public(0, numNegatives - 1);
                }
                res = Math.max(res, (long) v);

                if (numNegatives == 0) {
                    cumSum += v;
                    res = Math.max(res, cumSum);
                }
            } else {
                int currentIdx = negMap.get(v);
                if (numNegatives > 1) {
                    if (currentIdx > 0) {
                        st.updateRangeAddPublic(0, currentIdx - 1, v);
                    }
                    if (currentIdx < numNegatives - 1) {
                        st.updateRangeAddPublic(currentIdx + 1, numNegatives - 1, v);
                    }
                }
                if (numNegatives > 0) {
                    int currentMax = st.queryRangeMaxCorrectPublic(0, numNegatives - 1);
                    res = Math.max(res, (long) currentMax);
                    st.updateRangeChmax0Public(0, numNegatives - 1);
                }
            }
        }

        return res != Long.MIN_VALUE ? res : 0;
    }

    // Compress adjacent positives, remove negative prefixes and suffixes
    private List<Integer> preprocess(int[] nums) {
        int start = 0;
        while (start < nums.length && nums[start] <= 0) {
            start++;
        }

        int end = nums.length - 1;
        while (end >= start && nums[end] <= 0) {
            end--;
        }

        if (start > end) {
            return new ArrayList<>();
        }

        List<Integer> trimmed = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            trimmed.add(nums[i]);
        }

        List<Integer> compressed = new ArrayList<>();
        int i = 0;
        while (i < trimmed.size()) {
            if (trimmed.get(i) > 0) {
                long sumPos = trimmed.get(i);
                while (i + 1 < trimmed.size() && trimmed.get(i + 1) > 0) {
                    sumPos += trimmed.get(i + 1);
                    i++;
                }
                compressed.add((int) sumPos);
            } else {
                compressed.add(trimmed.get(i));
            }
            i++;
        }

        return compressed;
    }

    class SegmentTree {
        private int n;
        private int size;
        private int[] mx;
        private int[] mn;
        private int[] addLazy;
        private int[] setLazy;
        private boolean[] setFlag;

        public SegmentTree(int n) {
            this.n = n;
            this.size = 1;
            while (size < n) size <<= 1;

            mx = new int[2 * size];
            mn = new int[2 * size];
            addLazy = new int[2 * size];
            setLazy = new int[2 * size];
            setFlag = new boolean[2 * size];

            for (int i = 0; i < n; i++) {
                mx[size + i] = 0;
                mn[size + i] = 0;
            }

            for (int i = size - 1; i >= 1; i--) {
                pushUp(i);
            }
        }

        private void pushUp(int idx) {
            mx[idx] = Math.max(mx[idx << 1], mx[idx << 1 | 1]);
            mn[idx] = Math.min(mn[idx << 1], mn[idx << 1 | 1]);
        }

        private void applySet(int idx, int val) {
            mx[idx] = val;
            mn[idx] = val;
            setLazy[idx] = val;
            setFlag[idx] = true;
            addLazy[idx] = 0;
        }

        private void applyAdd(int idx, int val) {
            if (setFlag[idx]) {
                setLazy[idx] += val;
                mx[idx] += val;
                mn[idx] += val;
            } else {
                addLazy[idx] += val;
                mx[idx] += val;
                mn[idx] += val;
            }
        }

        private void pushDown(int idx) {
            if (setFlag[idx]) {
                applySet(idx << 1, setLazy[idx]);
                applySet(idx << 1 | 1, setLazy[idx]);
                setFlag[idx] = false;
            }
            if (addLazy[idx] != 0) {
                applyAdd(idx << 1, addLazy[idx]);
                applyAdd(idx << 1 | 1, addLazy[idx]);
                addLazy[idx] = 0;
            }
        }

        private void rangeAdd(int l, int r, int val, int idx, int start, int end) {
            if (l > end || r < start) {
                return;
            }
            if (l <= start && end <= r) {
                applyAdd(idx, val);
                return;
            }
            pushDown(idx);
            int mid = (start + end) >> 1;
            rangeAdd(l, r, val, idx << 1, start, mid);
            rangeAdd(l, r, val, idx << 1 | 1, mid + 1, end);
            pushUp(idx);
        }

        private void rangeSet(int l, int r, int val, int idx, int start, int end) {
            if (l > end || r < start) {
                return;
            }
            if (l <= start && end <= r) {
                applySet(idx, val);
                return;
            }
            pushDown(idx);
            int mid = (start + end) >> 1;
            rangeSet(l, r, val, idx << 1, start, mid);
            rangeSet(l, r, val, idx << 1 | 1, mid + 1, end);
            pushUp(idx);
        }

        private void rangeChmax0(int l, int r, int idx, int start, int end) {
            if (l > end || r < start) {
                return;
            }
            if (l <= start && end <= r) {
                if (mn[idx] >= 0) {
                    return;
                }
                if (mx[idx] <= 0) {
                    applySet(idx, 0);
                    return;
                }
            }
            if (start == end) {
                if (mx[idx] < 0) {
                    applySet(idx, 0);
                }
                return;
            }
            pushDown(idx);
            int mid = (start + end) >> 1;
            rangeChmax0(l, r, idx << 1, start, mid);
            rangeChmax0(l, r, idx << 1 | 1, mid + 1, end);
            pushUp(idx);
        }

        private int rangeMaxQueryCorrect(int l, int r, int idx, int start, int end) {
            if (l > end || r < start) {
                return Integer.MIN_VALUE; // No overlap
            }
            if (l <= start && end <= r) {
                return mx[idx];
            }
            pushDown(idx);
            int mid = (start + end) >> 1;
            int resLeft = rangeMaxQueryCorrect(l, r, idx << 1, start, mid);
            int resRight = rangeMaxQueryCorrect(l, r, idx << 1 | 1, mid + 1, end);
            return Math.max(resLeft, resRight);
        }

        public void updateRangeAddPublic(int l, int r, int val) {
            rangeAdd(l, r, val, 1, 0, size - 1);
        }

        public void updateRangeSetPublic(int l, int r, int val) {
            rangeSet(l, r, val, 1, 0, size - 1);
        }

        public void updateRangeChmax0Public(int l, int r) {
            rangeChmax0(l, r, 1, 0, size - 1);
        }

        public int queryRangeMaxCorrectPublic(int l, int r) {
            return rangeMaxQueryCorrect(l, r, 1, 0, size - 1);
        }
    }
}
