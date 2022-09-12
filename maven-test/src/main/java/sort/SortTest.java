package sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        //定义数组
        int[] arr = {3, 6, 2, 9, 5, 4, 1, 5, 8, 7, 0};
        //排序
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
        quickSort(arr, 0, arr.length -1);
        //查找指定元素下标
        int tatget = 5;
        int index = binarySearch(arr, tatget);
        System.out.println("元素" + tatget + "所在的下标为：" + index);
    }

    /**
     * 冒泡排序法
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSwapped = false; //表示在每一轮冒泡中是否有交换元素位置
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSwapped = true;
                }
            }
            System.out.println(Arrays.toString(arr));
            if (!isSwapped) {
                break;
            }
        }
    }

    /**
     * 选择排序法
     *
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int s = i; //s代表最小值元素的索引
            for (int j = s + 1; j < arr.length; j++) {
                if (arr[s] > arr[j]) {
                    s = j;
                }
            }
            if (s != i) {
                swap(arr, i, s);
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 插入排序法
     *
     * @param arr
     */
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            boolean flag = false;
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j]; //j下标所在的元素后移一位
                } else {
                    arr[j + 1] = temp; //找到所在的位置了插进去
                    flag = true;
                    break;
                }
            }
            //如果i没有插入到指定位置证明其是最小的，插入到0所在的位置
            if (!flag) {
                arr[0] = temp;
            }
        }
    }

    /**
     * 快速排序法  双边循环快排
     *
     * @param arr
     * @param l
     * @param h
     */
    private static void quickSort(int[] arr, int l, int h) {
        if (l >= h) {
            return;
        }
        int p = partition(arr, l, h);
        quickSort(arr, l, p - 1); //左边分区
        quickSort(arr, p + 1, h); //右边分区
    }

    /**
     * 分区
     *
     * @param arr
     * @param l
     * @param h
     * @return 返回新的基准点索引
     */
    private static int partition(int[] arr, int l, int h) {
        int pv = arr[l]; //基准点元素
        int i = l; //i从左向右
        int j = h; //j从右向左
        while (i < j) {
            // j从右向左找比基准点小的元素
            while (i < j && arr[j] > pv) {
                j--;
            }
            // i从左向右找比基准点大的元素
            while (i < j && arr[i] <= pv) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, l, j);
        System.out.println(Arrays.toString(arr));
        return j;
    }

    /**
     * 二分查找法
     *
     * @param arr
     * @param target
     * @return
     */
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 交换数组中两个元素
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
