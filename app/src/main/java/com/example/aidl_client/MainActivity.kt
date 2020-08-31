package com.example.aidl_client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.IBinder.DeathRecipient
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firstprogram.ipc.StudentBean
import com.example.firstprogram.ipc.StudentBeanAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        click.setOnClickListener(this)
        button1_start_service.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.click -> {
                myAidl?.addStudentBeanIn(StudentBean("xyz", 100))
                if (myAidl != null && myAidl?.asBinder()?.isBinderAlive!!){
                    Log.d("MyAidlService", "---service isBinderAlive")
                }
                var list = myAidl?.studentBeanList
                for (bean in list!!) {
                    Log.d("MyAidlService", bean.name)
                    Log.d("MyAidlService", bean.grade.toString())
                }
                testview.text = "dddd"
            }
            R.id.button1_start_service -> {
                val mIntent = Intent()
                mIntent.setAction("com.example.firstprogram.service.MyAidlService")
                mIntent.setPackage("com.example.firstprogram")
                mIntent.putExtra("extra", "i am from com.example.student");
                mIntent.putExtra("我来自com.example", 100);
                bindService(mIntent, serviceConnection, BIND_AUTO_CREATE)
            }
        }
    }


    private var myAidl: StudentBeanAidlInterface? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            Log.d("MyAidlService", "---service connected---${name.packageName}")
            myAidl = StudentBeanAidlInterface.Stub.asInterface(binder)
            binder.linkToDeath(mDeathRecipient,0) //linkToDeath的第二个参数是标记位，直接设置为0即可
//            if (binder.isBinderAlive){
//                Log.d("MyAidlService", "---service isBinderAlive")
//            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d("MyAidlService", "---service dis connected")
        }
    }

    private val mDeathRecipient: DeathRecipient = object : DeathRecipient {
        override fun binderDied() {
            if (myAidl == null) {
                return
            }
            Log.d("MyAidlService", "---service closed")
            //linkToDeath的第二个参数是标记位，直接设置为0即可
            myAidl?.asBinder()?.unlinkToDeath(this, 0)
            myAidl = null
            // 重绑定
        }
    }

}