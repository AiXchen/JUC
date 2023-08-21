package com.aixchen.lock;

import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendEmail(){
        //try{ TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){e.printStackTrace();}
        System.out.println("--------sendEmail");
    }

    public synchronized void sendSms(){
        System.out.println("--------sendSms");
    }
    public void hello(){
        System.out.println("--------hello");
    }
}

/**
 * 1。一个对象里面只要有一个synchronized方法，某一个时刻内，只要一个线程去调用其中一个synchronized方法，其他的线程就得等待。
 * 锁的是当前对象this，被锁定后，其他线程都不能进入到当前对象的其他synchronized方法，普通方法不影响
 * 2。换成两个对象之后，锁对象不同了，相互不影响
 * 3。对于普通同步方法，锁的是当前实例对象，通常是指this,即phone
 *    对于静态同步方法，锁的是当前的Class类，即Phone.class
 *    对于静态同步代码块，锁的是synchronized内的对象
 * 4。所有的同步方法用的都是用一把锁，即对象锁this，也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须要
 * 等待获取锁的资源释放了之后才能使用。
 *
 * 所有的静态同步方法用的也是同一把锁，Class类本身，
 * 具体实例对象this和Class类本身是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞争的
 *
 * synchronized同步代码块是靠一个monitorenter和两个monitorexit指令，第一个monitorexit指令是正常的推出
 * 第二个monitorexit指令是确保发生了异常也能正常退出
 *
 * synchronized普通同步方法：调用指令将会检查方法的ACC_SYNCHRONIZED标识是否被设置了
 * 如果被设置了，执行线程之前将持有monitor的先执行，执行完之后释放monitor
 *
 * synchronized静态方法：ACC_STATIC和ACC_SYNCHRONIZED标识区分是否使用了静态同步方法
 *
 * 为什么每个对象都能成为一个锁？
 *    执行线程之前都要求成功持有管程，然后才能执行方法，最后完成方法后（无论方法正常完成还是非正常完成），都将释放管程。
 * 在方法执行期间，执行线程持有了管程，其他任何线程都无法再获取到同一个管程。
 *    每个对象天生都持有一个Monitor
 */
public class LockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        new Thread(()->{
            phone.sendSms();
        },"b").start();
    }

}
