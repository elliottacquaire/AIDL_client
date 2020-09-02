// IBookManagerAidlInterface.aidl
package com.example.firstprogram.ipc;


// Declare any non-default types here with import statements

/**
* 1 实体类的包名与aidl文件的包名必须一致
  2 IBookManager中必须import所需实体类
  3 接口中声明的方法参数需要使用in/out/inout
  4 aidl文件以及实体类在两端都要有，并且同个包下
*/
import com.example.firstprogram.ipc.Book;

interface IBookManagerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,  double aDouble, String aString);

//            void addBook(in Book book);

            List<Book> getBooks();

//            void addBookAndNotify(in com.example.firstprogram.ipc.Book book);

//            void registerListener(IOnNewBookAddedListener l);

//            void unregisterListener(IOnNewBookAddedListener l);
}
