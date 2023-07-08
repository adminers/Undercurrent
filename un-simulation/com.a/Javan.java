<!DOCTYPE html>
<html>
<head>
  <title>Highlight.js Example</title>
  <!-- 引入 highlight.js 样式文件 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css">
  <style>
    /* 增加代码行之间的距离 */
    pre {
      line-height: 1.5em;
    }
  </style>
  <!-- 引入 highlight.js 脚本文件 -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

  <h1>HTML 代码示例</h1>

  <pre><code class="java">
 // 在Java集合中,随机取一个元素。\n

// 给你一个整数数组 nums ，请你在该数组中找出由三个数组成的最大乘积，并输出这个乘积。\n
// 示例 1：\n
// 输入：nums = [1,2,3]\n
// 示例 2：\n
// 输入：nums = [1,2,3,4]\n
// 示例 3：\n
// 输入：nums = [-1,-2,-3]\n
// 示例 4：\n
// 输入：nums = [-1]\n
// 输出：-1\n
// 解释：数组中没有元素。\n
// 示例 5：\n
// 输入：nums = [0]\n
// 输出：0\n
// 示例 6：\n
// 输入：nums = [-1,-2,-3,0]\n
// 输出：0\n
// 示例 7：\n
// 输入：nums = [-1,-2,-3,0,1]\n
// 输出：1\n
// 示例 8：\n
// = [-1,-2,-3,0,1,2,3]\n
// 输出：6\n
/// 提示：\n
// 3 <= nums.length <= 10^4\n
// -1000 <= nums[i] <= 1000\n
// -10^4 <= sum(nums[i]) <= 10^4\n


package com.vinci.code.leetcode.editor.cn;

public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) {
         Solution solution = new MaximumProductOfThreeNumbers().new Solution();
    }
    

class Solution {
    public int maximumProduct(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[0] * nums[1] * nums[len - 1], nums[len - 3] * nums[len - 2] * nums[len - 1]);
    }
}

}   
  </code></pre>

</body>
</html>