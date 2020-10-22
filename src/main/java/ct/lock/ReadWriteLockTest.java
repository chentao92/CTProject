package ct.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: CTProject
 * @description: 读写锁测试类,通过多个锁来控制多个list的读写
 * 单个锁参考ct.list.MultithreadListOperation.class
 * @author: chentao
 * @create: 2020-08-24 14:56
 **/

public class ReadWriteLockTest {

    static Map<String, ReentrantReadWriteLock> mapLock = new HashMap<String, ReentrantReadWriteLock>();

    static Map<String, List<String>> mapList = new HashMap<String, List<String>>();

    public static void main(String[] args) {

        ReadWriteLockTest t = new ReadWriteLockTest();
        mapLock.put("1",new ReentrantReadWriteLock());
        mapLock.put("2",new ReentrantReadWriteLock());

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        mapList.put("1",list1);

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        mapList.put("2",list2);

        final ReadWriteLockTest m = new ReadWriteLockTest();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            public void run() {
                m.traversal("1");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal("2");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.addE("3","2");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal("1");
            }
        });
        service.execute(new Runnable() {
            public void run() {
                m.traversal("2");
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

    // 增加元素
    public void addE(String value,String key){

        ReentrantReadWriteLock lock =  mapLock.get(key);
        lock.writeLock().lock();
        System.out.println("写锁"+key);
        List<String> list = mapList.get(key);
        try {
            list.add(value);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
            System.out.println("释放写锁"+key);
        }
    }

    // 根据下标删除某元素
    public void deleteE(int index,String key){
        ReentrantReadWriteLock lock =  mapLock.get(key);
        lock.writeLock().lock();
        System.out.println("写锁"+key);
        List<String> list = mapList.get(key);
        try {
            list.remove(index);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁"+key);
            lock.writeLock().unlock();
        }
    }

    // 删除某元素
    public void deleteE(String value, String key){
        ReentrantReadWriteLock lock =  mapLock.get(key);
        lock.writeLock().lock();
        System.out.println("写锁"+key);
        List<String> list = mapList.get(key);
        try {
            list.remove(value);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁"+key);
            lock.writeLock().unlock();
        }
    }

    // 根据下标修改某元素
    public void updateE(int index, String value, String key){
        ReentrantReadWriteLock lock =  mapLock.get(key);
        lock.writeLock().lock();
        System.out.println("获得写锁"+key);
        List<String> list = mapList.get(key);
        try {
            list.set(index,value);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁"+key);
            lock.writeLock().unlock();
        }
    }

    // 根据值修改元素
    public void updateE(String oldValue, String newValue, String key){
        ReentrantReadWriteLock lock =  mapLock.get(key);
        lock.writeLock().lock();
        System.out.println("获得写锁"+key);
        List<String> list = mapList.get(key);
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
            System.out.println("释放写锁"+key);
            lock.writeLock().unlock();
        }
    }
    // 遍历
    public void traversal( String key ){

        ReentrantReadWriteLock lock =  mapLock.get(key);
        lock.readLock().lock();

        System.out.println("读锁"+key);
        List<String> list = mapList.get(key);
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
            System.out.println("释放读锁"+key);
            lock.readLock().unlock();
        }
    }

}
