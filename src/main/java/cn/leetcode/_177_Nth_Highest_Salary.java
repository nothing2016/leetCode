package cn.leetcode;

/**
 * 177. 第N高的薪水
 * 编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
 * <p>
 * +----+--------+
 * | Id | Salary |
 * +----+--------+
 * | 1  | 100    |
 * | 2  | 200    |
 * | 3  | 300    |
 * +----+--------+
 * 例如上述 Employee 表，n = 2 时，应返回第二高的薪水 200。如果不存在第 n 高的薪水，那么查询应返回 null。
 * <p>
 * +------------------------+
 * | getNthHighestSalary(2) |
 * +------------------------+
 * | 200                    |
 * +------------------------+
 *
 * @author oudaming
 * @date 2021/3/20 0020 08:12
 */
public class _177_Nth_Highest_Salary {

    /**
     * @param args
     */
    public static void main(String[] args) {


//        CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
//        BEGIN
//        declare m INT;
//        set m=N-1;
//        RETURN (
//                # Write your MySQL query statement below.
//                        select ifnull((select distinct Salary from Employee as E order by Salary desc limit m,1),null)
//         );
//        END
    }
}
