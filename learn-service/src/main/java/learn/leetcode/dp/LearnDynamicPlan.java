package learn.leetcode.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 吴飞群
 * @createTime 2022/06/04
 */
public class LearnDynamicPlan {

    /**
     * leetCode: 518
     * 给一些零钱, 加入无限个, 求组成某一个值的组合个数
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     */
    public int change2(int[] coins, int target){
        int coinCount = coins.length;
        int[][] dp = new int[coinCount+1][target+1];
        dp[0][0] = 1;
        for (int i=1; i<=coinCount; i++){
            for (int j=0; j<=target; j++){
                for (int k=0; k*coins[i-1]<=j;k++){
                    dp[i][j] += dp[i-1][j-coins[i-1]*k];
                }
            }
        }
        return dp[coinCount][target];
    }

    /**
     * 问题同上, 暴力破解
     */
    public int change2_1(int[] coins, int target){
        List<Integer> everyMethodCoinCount = new ArrayList<>();
        int a=coins[0], b=coins[1], c=coins[2];

        for (int i = 0; i < target/a+1; i++) {
            for (int j = 0; j < target/b+1; j++) {
                for (int k = 0; k < target/c+1; k++) {
                    if (i*a + j*b + k*c == target){
                        everyMethodCoinCount.add(i+j+k);
                    }
                }
            }
        }

        return everyMethodCoinCount.size();
    }

    public int change2_2(int[] coins, int target){
       int[] dp = new int[target+1];
       dp[0] = 1;
       for (int coin: coins){
           for (int i = coin; i <= target; i++) {
                dp[i] = dp[i] + dp[i-coin];
           }
       }
       return dp[target];
    }

    public int change2_3(int[] coins, int target){
        int coinsCount = coins.length;
        int[][] dp = new int[coinsCount+1][target+1];
        dp[0][0] = 1;
        for (int i = 1; i <= coinsCount; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i-1][j] + (j>=coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }

        return dp[coinsCount][target];
    }


    public static void main(String[] args) {
        LearnDynamicPlan obj = new LearnDynamicPlan();
        int[] coins = new int[]{1,2,5};
        int target = 5;
        System.out.println(obj.change2(coins, target));
        System.out.println(obj.change2_1(coins, target));
        System.out.println(obj.change2_2(coins, target));
        System.out.println(obj.change2_3(coins, target));
    }
}
