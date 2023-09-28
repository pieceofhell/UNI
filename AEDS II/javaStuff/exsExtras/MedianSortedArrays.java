/*
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

 */

class MedianSortedArrays extends Solution {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int i = 0, j = 0, k = 0, totalLength = nums1.length + nums2.length;
    int[] mergedArray = new int[totalLength];
    double median = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] < nums2[j]) mergedArray[k++] =
        nums1[i++]; else mergedArray[k++] = nums2[j++];
    }
    while (i < nums1.length) mergedArray[k++] = nums1[i++];
    while (j < nums2.length) mergedArray[k++] = nums2[j++];

    if (totalLength % 2 == 0) {
        median = (mergedArray[totalLength / 2 - 1] + mergedArray[totalLength / 2]) / 2.0;
    } else {
        median = mergedArray[totalLength / 2];
    }
    

    return median;
  }
}
