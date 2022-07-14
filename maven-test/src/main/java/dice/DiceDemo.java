package dice;

import java.util.Arrays;

/**
 * 骰子有6个面，现在用1，2，3，4，5，6分别代表一个骰子的左，右，前，后，上，下的初始位置，用R代表向右滚动一次，用L代表向左滚动一次，
 * 可以向前翻转（用F表示向前翻转1次），可以向后翻转（用B表示向右翻转1次），可以逆时针旋转（用A表示逆时针旋转90度），
 * 可以顺时针旋转（用C表示逆时针旋转90度），现从初始状态开始，根据输入的动作序列，计算得到最终的状态。
 */

public class DiceDemo {

    public static void main(String[] args) {
        //初始状态
        String[] initStatus = {"1", "2", "3", "4", "5", "6"};
        //动作序列
        String action = "RLFBAC";
        //获取最终状态
        String[] finalStatus = getFinalStatus(initStatus, action);
        Arrays.asList(finalStatus).forEach(System.out::print);
//        int max = 0;
//        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
//        for(int i=0; i<words.length; i++){
//            for(int j=0; j!=i && j<words.length; j++){
//                boolean flag = true;
//                char[] wa = words[j].toCharArray();
//                for(int k=0; k<wa.length; k++){
//                    if(words[i].contains(wa.toString())){
//                        flag = false;
//                        break;
//                    }
//                }
//                if(flag && words[i].length() * words[j].length() > max){
//                    max = words[i].length() * words[j].length();
//                }
//            }
//        }
    }

    private static String[] getFinalStatus(String[] initStatus, String action) {
        if (action.length() == 0) {
            return initStatus;
        }
        String currentAction = action.substring(0, 1);
        String L = null, R = null, F = null, B = null, U = null, D = null;
        if ("R".equals(currentAction)) {
            L = initStatus[5];
            R = initStatus[4];
            F = initStatus[2];
            B = initStatus[3];
            U = initStatus[0];
            D = initStatus[1];
        } else if ("L".equals(currentAction)) {
            L = initStatus[4];
            R = initStatus[5];
            F = initStatus[2];
            B = initStatus[3];
            U = initStatus[1];
            D = initStatus[0];
        } else if ("F".equals(currentAction)) {
            L = initStatus[0];
            R = initStatus[1];
            F = initStatus[5];
            B = initStatus[4];
            U = initStatus[2];
            D = initStatus[3];
        } else if ("B".equals(currentAction)) {
            L = initStatus[0];
            R = initStatus[1];
            F = initStatus[4];
            B = initStatus[5];
            U = initStatus[3];
            D = initStatus[2];
        } else if ("A".equals(currentAction)) {
            L = initStatus[3];
            R = initStatus[2];
            F = initStatus[0];
            B = initStatus[1];
            U = initStatus[4];
            D = initStatus[5];
        } else if ("C".equals(currentAction)) {
            L = initStatus[2];
            R = initStatus[3];
            F = initStatus[1];
            B = initStatus[0];
            U = initStatus[4];
            D = initStatus[5];
        }
        String[] nextStatus = {L, R, F, B, U, D};
        return getFinalStatus(nextStatus, action.substring(1));
    }

}
