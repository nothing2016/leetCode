package cn.leetcode;

/**
 * 178. 分数排名
 * SQL架构
 * 编写一个 SQL 查询来实现分数排名。
 * <p>
 * 如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有“间隔”。
 * <p>
 * +----+-------+
 * | Id | Score |
 * +----+-------+
 * | 1  | 3.50  |
 * | 2  | 3.65  |
 * | 3  | 4.00  |
 * | 4  | 3.85  |
 * | 5  | 4.00  |
 * | 6  | 3.65  |
 * +----+-------+
 * 例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：
 * <p>
 * +-------+------+
 * | Score | Rank |
 * +-------+------+
 * | 4.00  | 1    |
 * | 4.00  | 1    |
 * | 3.85  | 2    |
 * | 3.65  | 3    |
 * | 3.65  | 3    |
 * | 3.50  | 4    |
 * +-------+------+
 * 重要提示：对于 MySQL 解决方案，如果要转义用作列名的保留字，可以在关键字之前和之后使用撇号。例如 `Rank`
 * <p>
 * 通过次数86,625提交次数146,352
 *
 * @author oudaming
 * @date 2021/3/21 0021 08:56
 */
public class _178_Rank_Scores {

//    select score, DENSE_RANK()　OVER(ORDER BY Score DESC) as 'Rank'　from Scores;
//
//    值得注意的三个窗口函数。现在给定五个成绩：99，99，85，80，75。
//    DENSE_RANK()。如果使用 DENSE_RANK() 进行排名会得到：1，1，2，3，4。
//    RANK()。如果使用 RANK() 进行排名会得到：1，1，3，4，5。
//    ROW_NUMBER()。如果使用 ROW_NUMBER() 进行排名会得到：1，2，3，4，5。


}
