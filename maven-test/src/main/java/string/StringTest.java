package string;

public class StringTest {
    public static void main(String[] args) {
        String parentStr = "aaaaaaca";
        String childStr = "aaaac";
//        int index = bruteForceCompare(parentStr, childStr);
        int index = KMPCompare(parentStr, childStr);
        System.out.println(index);
    }

    /**
     * 暴力比较法
     *
     * @param parentStr
     * @param childStr
     * @return
     */
    private static int bruteForceCompare(String parentStr, String childStr) {
        char[] parentCharArr = parentStr.toCharArray();
        char[] childCharArr = childStr.toCharArray();
        int actionIdx = 0; //父串比较起始指针位置
        int comIdx = 0; //子串比较指针位置
        while (actionIdx <= parentCharArr.length - childCharArr.length) {
            if (parentCharArr[actionIdx + comIdx] != childCharArr[comIdx]) {
                actionIdx++;
                comIdx = 0;
            } else {
                if (comIdx == childCharArr.length - 1) {
                    return actionIdx;
                } else {
                    comIdx++;
                }
            }
        }
        return -1;
    }

    /**
     * KMP比较法
     *
     * @param parentStr
     * @param childStr
     * @return
     */
    private static int KMPCompare(String parentStr, String childStr) {
        char[] parentCharArr = parentStr.toCharArray();
        char[] childCharArr = childStr.toCharArray();
        //获取next数组
        int[] next = getNext(childCharArr);
        int actionIdx = 0; //主串指针
        int comIdx = 0; //子串比较指针位置
        while (actionIdx <= parentCharArr.length - childCharArr.length) {
            if (parentCharArr[actionIdx] != childCharArr[comIdx]) {
                //主串指针不回溯，字串指针移动后的位置=部分匹配值
                comIdx = next[comIdx];
            } else {
                if (comIdx == childCharArr.length - 1) {
                    return actionIdx + 1 - childCharArr.length;
                } else {
                    actionIdx++;
                    comIdx++;
                }
            }
        }
        return -1;
    }

    /**
     * 获取next数组（即最大公共前后缀，也是子串下一次移动后的位置）
     *
     * @param childCharArr
     * @return
     */
    private static int[] getNext(char[] childCharArr) {
        int[] next = new int[childCharArr.length];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < childCharArr.length; ++i) {
            while (k != -1 && childCharArr[k + 1] != childCharArr[i]) {
                k = next[k];
                // 因为前一个的最长串的下一个字符不与最后一个相等，需要找前一个的次长串，
                // 问题就变成了求0到next(k)的最长串，如果下个字符与最后一个不等，
                // 继续求次长串，也就是下一个next(k)，直到找到，或者完全没有
                // 最好结合前面的图来看
            }
            if (childCharArr[k + 1] == childCharArr[i]) {
                ++k; // 字符串相等则看下一个
            }
            next[i] = k; // 数组赋值
        }
        return next;
    }
}
