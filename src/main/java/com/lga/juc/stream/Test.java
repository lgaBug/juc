package com.lga.juc.stream;

import java.util.Arrays;
import java.util.List;

public class Test {

    /**
     * 题目要求：只能用一行代码实现
     * 现在有5个用户！筛选：
     * 1、ID必须是偶数
     * 2、年龄必须大于23岁
     * 3、用户名转为大写字母
     * 4、用户名字母倒着排序
     * 5、只输出一个用户！
     */
    public static void main(String[] args) {

        User user1 = new User(1, "a", 21);
        User user2 = new User(2, "b", 22);
        User user3 = new User(3, "c", 23);
        User user4 = new User(4, "d", 24);
        User user5 = new User(5, "e", 25);
        User user6 = new User(6, "f", 26);
        //存储交给集合
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6);
        //计算交给stream流
        list.stream().filter((u) -> {
            return u.getId() % 2 == 0;
        }).filter(u -> {
            return u.getAge() > 23;
        }).map(u -> {
            return u.getName().toUpperCase();
        }).sorted((uu1, uu2) -> {
            return uu1.compareTo(uu2);
        }).limit(1).forEach((u) -> {
            System.out.println(u);
        });

    }
}
