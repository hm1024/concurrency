package com.minghai.concurrency.example.singleton;

import com.minghai.concurrency.annotation.NotRecommend;
import com.minghai.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式  =》》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
@NotRecommend
public class SingletonExample4 {

    // 私有的构造函数
    private SingletonExample4(){

    }

    /*
      实例化一个对象经历的步骤 instance = new SingletonExample4();
      1、memory = allocate() 分配对象的内存空间
      2、ctorInstance() 初始化对象
      3、instance = memory 这只 instance 指向刚分配的内存

      JVM 和 CPU 优化，发生了指令重排
      1、memory = allocate() 分配对象的内存空间
      3、instance = memory 这只 instance 指向刚分配的内存
      2、ctorInstance() 初始化对象
     */



    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    public  static SingletonExample4 getInstance(){
        if(instance == null){   // 双重检测机制
            synchronized (SingletonExample1.class){ // 同步锁
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
