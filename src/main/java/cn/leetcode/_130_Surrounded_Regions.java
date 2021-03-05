package cn.leetcode;

/**
 * 130. 被围绕的区域
 * <p>
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * @author oudaming
 * @date 2021-03-05 17:45
 */
public class _130_Surrounded_Regions {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        new _130_Surrounded_Regions().solve(board);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    process(board, i, j, N, M);
                }
            }
        }
        printArray(board);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
        printArray(board);
    }

    private void printArray(char[][] board) {
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("========================");
    }

    /**
     * 从(i,j)出发，将所有’O‘的点变成A
     *
     * @param board
     * @param i
     * @param j
     * @param N
     * @param M
     */
    public void process(char[][] board, int i, int j, int N, int M) {
        if (i >= 0 && i < N && j >= 0 && j < M && board[i][j] == 'O') {
            board[i][j] = 'A';
            process(board, i - 1, j, N, M);
            process(board, i + 1, j, N, M);
            process(board, i, j - 1, N, M);
            process(board, i, j + 1, N, M);
        }
    }
}
