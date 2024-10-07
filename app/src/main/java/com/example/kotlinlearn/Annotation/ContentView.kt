package com.example.kotlinlearn.Annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ContentView(val layoutId: Int)
