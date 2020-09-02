package com.example.aidl_client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.IBinder.DeathRecipient
import android.os.RemoteException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.firstprogram.IBinderPool
import com.example.firstprogram.ipc.IBookManagerAidlInterface
import java.util.concurrent.CountDownLatch

//单例模式实现
class BinderPool(context: Context)  {

    private var context: Context? = null
    //countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行
    //是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，
    // 当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
    private val countDownLatch: CountDownLatch? = null
    private var binderPool: BinderPool? = null
    private var iBinderPool: IBinderPool? = null

    init {
        this.context = context
    }

    fun getInstance(): BinderPool? {
        if (binderPool == null) {
            synchronized(String::class.java) {
                if (binderPool == null) binderPool = BinderPool(context!!)
            }
        }
        Log.d("BinderPool", "---BinderPool")
        // 连接远程线程池服务
        connectBinderPoolService()
        return binderPool
    }

    @Synchronized
    private fun connectBinderPoolService() {
        // countDownLatch = new CountDownLatch(1);
        val mIntent = Intent()
        mIntent.setAction("com.example.firstprogram.service.BinderPoolService")
        mIntent.setPackage("com.example.firstprogram")
        mIntent.putExtra("extra", "i am from com.example.student");
        mIntent.putExtra("我来自com.example", 100);
        context?.bindService(mIntent, conn, AppCompatActivity.BIND_AUTO_CREATE)
        Log.d("BinderPool", "---BinderPool--connect-- start")
//        try {
//            // countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private val conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            iBinderPool = IBinderPool.Stub.asInterface(iBinder)
            // countDownLatch.countDown();
            Log.d("BinderPool", "---BinderPool--connect-- success")
            val bookManager = IBookManagerAidlInterface.Stub.asInterface(queryBinder(1))
//                val studentManager = StudentBeanAidlInterface.Stub.asInterface(binderPool?.queryBinder(2))
            Log.d("BinderPool", "---${bookManager.books[0].name}")
            if (null != iBinderPool) {
                try {
                    iBinderPool!!.asBinder().linkToDeath(deathRecipient, 0)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {}
    }

    private val deathRecipient: DeathRecipient = object : DeathRecipient {
        override fun binderDied() {
            Log.d("BinderPool", "---BinderPool--deathRecipient-")
            if (iBinderPool != null) {
                iBinderPool!!.asBinder().unlinkToDeath(this, 0)
                iBinderPool = null
                // 重连
                connectBinderPoolService()
            }
        }
    }

    fun queryBinder(binderCode: Int): IBinder? {
        Log.d("BinderPool", "---queryBinder-$binderCode")
        return try {
            if (iBinderPool != null) iBinderPool!!.queryBinder(binderCode) else null
        } catch (e: RemoteException) {
            e.printStackTrace()
            null
        }
    }
}