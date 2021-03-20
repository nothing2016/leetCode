package cn.leetcode;

/**
 * 176. 第二高的薪水
 * SQL架构
 * 编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
 * <p>
 * +----+--------+
 * | Id | Salary |
 * +----+--------+
 * | 1  | 100    |
 * | 2  | 200    |
 * | 3  | 300    |
 * +----+--------+
 * 例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。
 * <p>
 * +---------------------+
 * | SecondHighestSalary |
 * +---------------------+
 * | 200                 |
 * +---------------------+
 * <p>
 * 答案：
 * SELECT
 * IFNULL(
 * (SELECT DISTINCT Salary
 * FROM Employee
 * ORDER BY Salary DESC
 * LIMIT 1 OFFSET 1),
 * NULL) AS SecondHighestSalary
 *
 * @author oudaming
 * @date 2021/3/20 0020 08:10
 */
public class _176_Second_Highest_Salary {
//   SELECT
//   IFNULL(
//    (SELECT DISTINCT Salary
//            FROM Employee
//            ORDER BY Salary DESC
//            LIMIT 1 OFFSET 1),NULL) AS SecondHighestSalary
}
