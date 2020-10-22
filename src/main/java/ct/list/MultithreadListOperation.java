package ct.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: CTProject
 * @description: 多线程数组操作
 * 读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的
 * @author: chentao
 * @create: 2020-08-20 09:52
 **/

public class MultithreadListOperation {

    List<String> list = new ArrayList<String>();

    ReentrantReadWriteLock  lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final MultithreadListOperation m = new MultithreadListOperation();
        m.buildList();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.addE("add");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.deleteE("5");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.updateE(5,"update");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.updateE("5","update");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal();
            }
        });

        try {
            System.out.println("通知线程结束");
            service.shutdown();
            System.out.println(System.currentTimeMillis());
            //获取线程结束的结果，设置超时时间。超时时强制结束所有线程。
            if(!service.awaitTermination(5000, TimeUnit.MILLISECONDS)){
                System.out.println(System.currentTimeMillis());
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            service.shutdownNow();
        }

    }

    // 初始化在list中加入元素
    public void buildList(){
        for (int i = 0 ; i < 10 ;i++){
            list.add(i+"");
        }
    }

    // 增加元素
    public void addE(String value){
        lock.writeLock().lock();
        System.out.println("写锁");
        try {
            list.add(value);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
            System.out.println("释放写锁");
        }
    }

    // 根据下标删除某元素
    public void deleteE(int index){
        lock.writeLock().lock();
        System.out.println("写锁");
        try {
            list.remove(index);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁");
            lock.writeLock().unlock();
        }
    }

    // 删除某元素
    public void deleteE(String value){
        lock.writeLock().lock();
        System.out.println("写锁");
        try {
            list.remove(value);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁");
            lock.writeLock().unlock();
        }
    }

    // 根据下标修改某元素
    public void updateE(int index, String value){
        System.out.println("准备获取写锁");
        lock.writeLock().lock();
        System.out.println("获得写锁");
        try {
            list.set(index,value);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁");
            lock.writeLock().unlock();
        }
    }

    // 根据值修改元素
    public void updateE(String oldValue, String newValue){
        System.out.println("准备获取写锁");
        lock.writeLock().lock();
        System.out.println("获得写锁");
        try {
            int size = list.size();
            for (int i = 0 ; i < size ;i++){
                if (list.get(i) == oldValue){
                    list.set(i,newValue);
                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁");
            lock.writeLock().unlock();
        }
    }

    // 遍历
    public void traversal(){
        lock.readLock().lock();
        System.out.println("读锁");
        int size = list.size();
        try {
            for (int i = 0 ; i < size ;i++){
                Thread.sleep(10);
                System.out.println(list.get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 释放读锁
            System.out.println("释放读锁");
            lock.readLock().unlock();
        }
    }
}



