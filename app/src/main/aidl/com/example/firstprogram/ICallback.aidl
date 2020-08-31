// ICallback.aidl
package com.example.firstprogram;

// Declare any non-default types here with import statements

import com.example.firstprogram.ipc.StudentBean;

interface ICallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

     void callback(String str,in StudentBean msg);

}
