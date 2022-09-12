package algorithm;

/**
 * 先递增后递减数组取最大值
 */
public class MaxValue {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,5,4,3};
        int maxValue = 0;
        int left = 0;
        int rignt = a.length - 1;
        int count = 0;
        while (left <= rignt) {
            count++;
            int middleIndex = (left + rignt) >>> 1;
            maxValue = a[middleIndex];

            if (a[middleIndex - 1] < maxValue && a[middleIndex + 1] < maxValue) {
                break;
            }

            if (a[middleIndex + 1] > maxValue) {
                left = middleIndex + 1;
            } else if (a[middleIndex + 1] < maxValue){
                rignt = middleIndex;
            } else {
                break;
            }
        }
        System.out.println(maxValue);
        System.out.println(count);
    }
}
