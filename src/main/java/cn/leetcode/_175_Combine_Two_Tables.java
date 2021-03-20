package cn.leetcode;

/**
 * 175. 组合两个表
 * SQL架构
 * 表1: Person
 * <p>
 * +-------------+---------+
 * | 列名         | 类型     |
 * +-------------+---------+
 * | PersonId    | int     |
 * | FirstName   | varchar |
 * | LastName    | varchar |
 * +-------------+---------+
 * PersonId 是上表主键
 * 表2: Address
 * <p>
 * +-------------+---------+
 * | 列名         | 类型    |
 * +-------------+---------+
 * | AddressId   | int     |
 * | PersonId    | int     |
 * | City        | varchar |
 * | State       | varchar |
 * +-------------+---------+
 * AddressId 是上表主键
 * <p>
 * <p>
 * 编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：
 * <p>
 * FirstName, LastName, City, State
 *
 * @author oudaming
 * @date 2021/3/20 0020 08:53
 */
public class _175_Combine_Two_Tables {
//    select FirstName, LastName, City, State
//    from Person left join Address on Person.PersonId = Address.PersonId
}
