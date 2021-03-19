package cn.leetcode;

/**
 * 1603. 设计停车系统
 * 请你给一个停车场设计一个停车系统。停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位。
 * <p>
 * 请你实现 ParkingSystem 类：
 * <p>
 * ParkingSystem(int big, int medium, int small) 初始化 ParkingSystem 类，三个参数分别对应每种停车位的数目。
 * bool addCar(int carType) 检查是否有 carType 对应的停车位。
 * carType 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示。
 * 一辆车只能停在  carType 对应尺寸的停车位中。如果没有空车位，请返回 false ，否则将该车停入车位并返回 true 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
 * [[1, 1, 0], [1], [2], [3], [1]]
 * 输出：
 * [null, true, true, false, false]
 * <p>
 * 解释：
 * ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
 * parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
 * parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
 * parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
 * parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= big, medium, small <= 1000
 * carType 取值为 1， 2 或 3
 * 最多会调用 addCar 函数 1000 次
 *
 * @author oudaming
 * @date 2021-03-19 9:25
 */
public class _1603_Design_Parking_System {
}

class ParkingSystem {
    private int bigLimit;
    private int mediumLimit;
    private int smallLimit;

    private int bigSize = 0;
    private int mediumSize = 0;
    private int smallSize = 0;

    public ParkingSystem(int big, int medium, int small) {
        this.bigLimit = big;
        this.mediumLimit = medium;
        this.smallLimit = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1) {
            if (bigSize < bigLimit) {
                bigSize++;
                return true;
            } else {
                return false;
            }
        } else if (carType == 2) {
            if (mediumSize < mediumLimit) {
                mediumSize++;
                return true;
            } else {
                return false;
            }
        } else {
            if (smallSize < smallLimit) {
                smallSize++;
                return true;
            } else {
                return false;
            }
        }
    }
}