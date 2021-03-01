package cn.leetcode;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * @author oudaming
 * @date 2021-03-01 14:54
 */
public class _79_Word_Search {

    public static void main(String[] args) {
        System.out.println(new _79_Word_Search().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCCED"));
        System.out.println(new _79_Word_Search().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "SEE"));
        System.out.println(new _79_Word_Search().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCB"));
        System.out.println(new _79_Word_Search().exist(new char[][]{
                {'A'}}, "A"));
        System.out.println(new _79_Word_Search().exist(new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'E', 'S'},
                        {'A', 'D', 'E', 'E'}},
                "ABCESEEEFS"));
    }

    public boolean exist(char[][] board, String word) {
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean[][] visit = new boolean[N][M];
                boolean ok = proccess(board, i, j, 0, visit, word);
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从i,j位置出发，正在拼凑word.charAt(cur)的值
     *
     * @param board
     * @param i
     * @param j
     * @param cur
     * @param word
     * @return
     */
    private boolean proccess(char[][] board, int i, int j, int cur, boolean[][] visit, String word) {
        if (cur == word.length()) {
            return true;
        }
        int N = board.length;
        int M = board[0].length;
        // 超出了界限
        if (i < 0 || i == N || j < 0 || j == M) {
            return false;
        }
        if (visit[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(cur)) {
            return false;
        }

        visit[i][j] = true;
        boolean ans = proccess(board, i - 1, j, cur + 1, visit, word)
                || proccess(board, i + 1, j, cur + 1, visit, word)
                || proccess(board, i, j - 1, cur + 1, visit, word)
                || proccess(board, i, j + 1, cur + 1, visit, word);
        visit[i][j] = false;
        return ans;
    }
}
