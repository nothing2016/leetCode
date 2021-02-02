package cn.leetcode;

/**
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * 空白格用'.'表示。
 *
 * @author oudaming
 * @date 2021-02-02 17:20
 */
public class _37SudokuSolver {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new _37SudokuSolver().solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        // row[3][6]=0 表示 第1行中6出现过
        boolean[][] row = new boolean[9][10];
        // col[2][5]=0 表示 第2行中5出现过
        boolean[][] col = new boolean[9][10];
        // 通过3 * (i / 3) + (j / 3); 将大的方块变成九个小方块，编号从[0,8],如下
        // 0 1 2
        // 3 4 5
        // 6 7 8
        boolean[][] bucket = new boolean[9][10];
        //将row,col,bucket上的值全部初始化一遍
        initMaps(board, row, col, bucket);
        // 为空白的地方填写数字
        process(board, 0, 0, row, col, bucket);
    }

    /**
     * 将row,col,bucket上的值全部初始化一遍
     *
     * @param board
     * @param row
     * @param col
     * @param bucket
     */
    public static void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

    /**
     * 回溯的方法为空白的地方填写数字
     *
     * @param board
     * @param i
     * @param j
     * @param row
     * @param col
     * @param bucket
     * @return
     */
    public static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) {
            // 如果能走到这里，表示肯定是合法的
            return true;
        }
        int nexti = j != 8 ? i : i + 1;
        int nextj = j != 8 ? j + 1 : 0;
        // 如果该位置已经填了值，那么跳过，到下一个位置去填值
        if (board[i][j] != '.') {
            return process(board, nexti, nextj, row, col, bucket);
        } else {
            int bid = 3 * (i / 3) + (j / 3);
            // 到了空白的位置，使用[1,9]尝试去填
            for (int num = 1; num <= 9; num++) {
                if ((!row[i][num]) && (!col[j][num]) && (!bucket[bid][num])) {
                    // 比如num合法，就填充值num
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                    board[i][j] = (char) (num + '0');
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        // 如果填了num后，后面的值都是合法的，就直接返回true，整个独数就是合法的
                        return true;
                    }
                    // 如果后面的值都是非法的，那么需要回溯，使用num+1再去尝试一下
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bid][num] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }
}
