// StudentBeanAidlInterface.aidl
package com.example.firstprogram.ipc;

// Declare any non-default types here with import statements

import com.example.firstprogram.ipc.StudentBean;
import com.example.firstprogram.ICallback;

/**
*
in 表示数据只能由客户端流向服务端
out 表示数据只能由服务端流向客户端
inout 表示数据可在服务端与客户端之间双向流通

*/

interface StudentBeanAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString);

      List<StudentBean> getStudentBeanList();

      void addStudentBeanInOut(inout StudentBean bean);

      void addStudentBeanIn(in StudentBean bean);

      void addStudentBeanOut(out StudentBean bean);

      void addCallback(ICallback callback);

}
