package com.example.administrator.myimagedown.Util;

import java.io.Closeable;

public class CloseUtil{
    public static void closeQuietly(Closeable closeable){
        if(closeable != null){
            try{
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
