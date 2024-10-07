package com.example.kotlinlearn.Annotation;


import java.lang.reflect.Method;

public class InjectUtils {
    public static void inject(Object context) {
        injectLayout(context);
    }

    private static void injectLayout(Object context) {
        Class<?> clazz=context.getClass();
        ContentView contentView=clazz.getAnnotation(ContentView.class);
        int layoutId=0;
        if(contentView!=null){
            layoutId=contentView.layoutId();
        }
        try {
            Method contentMethod=context.getClass().getMethod("setContentView",int.class);
        }catch (Exception e){

        }
    }
}













































