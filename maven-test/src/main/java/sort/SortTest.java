package sort;

public class SortTest {
    public static void main(String[] args) {
        //定义数组
        int[] arr = {3, 6, 2, 9, 5, 4, 1, 5, 8, 7, 0};
        //排序
        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
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
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序法
     *
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
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
     * 快速排序法
     *
     * @param arr
     */
    private static void quickSort(int[] arr) {

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
}
