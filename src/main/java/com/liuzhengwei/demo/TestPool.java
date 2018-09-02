package com.liuzhengwei.demo;

import java.util.Date;

/**
 * @Author ：请叫我伟哥.
 * @Date ：Created in 15:40 2018/8/18 0018
 * @Description：${description}
 * @Modified By：
 * @Version: $version$
 */
public class TestPool {


    public static void main(String[] args) {
        System.out.println ("第五次修改");
        System.out.println ("第七次修改");
        long startTime = new Date().getTime();//开始时间

        StudentDao studentDao=new StudentDao();
        for(int i=0;i<20000;i++){
            studentDao.save("测试"+i, "男", 25.0, "测试地址"+i);
        }
        long endTime = new Date ().getTime();//完成时间
        System.out.println("完成时间："+(endTime-startTime)+"毫秒");
    }
}
