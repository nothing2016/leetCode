package cn.leetcode;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 *
 * @author oudaming
 * @date 2021-03-02 11:52
 */
public class _85_Maximal_Rectangle {
    public static void main(String[] args) {
//        System.out.println(new _85_Maximal_Rectangle().maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
//        System.out.println(new _85_Maximal_Rectangle().maximalRectangle(new char[][]{{'1'}}));
//        System.out.println(new _85_Maximal_Rectangle().maximalRectangle(new char[][]{{'0'}}));
//        System.out.println(new _85_Maximal_Rectangle().maximalRectangle(new char[][]{{}}));
        System.out.println(new _85_Maximal_Rectangle().maximalRectangle(new char[][]{{'1', '0'}, {'1', '0'}}));
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nums[i][j] = matrix[i][j] == '1' ? 1 : 0;
            }
        }
        NumMatrix numMatrix = new NumMatrix(nums);
        int ans = 0;
        for (int s1 = 0; s1 < N; s1++) {
            for (int e1 = 0; e1 < M; e1++) {
                // 如果当前位置是0，那么就跳过这个节点，从后面节点算起
                if (nums[s1][e1] == 0) {
                    continue;
                }

                for (int s2 = s1; s2 < N; s2++) {
                    boolean ok = true;
                    for (int e2 = e1; e2 < M && ok; e2++) {
                        // 如果当前位置是0，那么这一列之后就一定不会满足条件
                        if (nums[s2][e2] == 0) {
                            ok = false;
                            continue;
                        }
                        int sum = numMatrix.sumRegion(s1, e1, s2, e2);
                        if (sum == (s2 - s1 + 1) * (e2 - e1 + 1)) {
                            ans = Math.max(sum, ans);
                        }

                    }
                }

            }
        }
        return ans;
    }

    class NumMatrix {
        private int[][] matrix;
        private int[][] sum;
        private int[][] all;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            this.matrix = matrix;
            int N = matrix.length;
            int M = matrix[0].length;
            sum = new int[N][M];

            for (int i = 0; i < N; i++) {
                int total = 0;
                for (int j = 0; j < M; j++) {
                    total += matrix[i][j];
                    sum[i][j] = total;
                }
            }
            all = new int[N][M];
            for (int j = 0; j < M; j++) {
                int total = 0;
                for (int i = 0; i < N; i++) {
                    total += sum[i][j];
                    all[i][j] = total;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (all == null) {
                return 0;
            }
            int N = matrix.length;
            int M = matrix[0].length;
            if (row1 < 0 || row1 >= N
                    || row2 < 0 || row2 >= N
                    || col1 < 0 || col1 >= M
                    || col2 < 0 || col2 >= M) {
                return 0;
            }

            int sub1 = row1 - 1 >= 0 ? all[row1 - 1][col2] : 0;
            int sub2 = col1 - 1 >= 0 ? all[row2][col1 - 1] : 0;
            int ans = all[row2][col2] - sub1 - sub2;

            if (row1 - 1 >= 0 && col1 - 1 >= 0) {
                ans += all[row1 - 1][col1 - 1];
            }
            return ans;
        }
    }
}
