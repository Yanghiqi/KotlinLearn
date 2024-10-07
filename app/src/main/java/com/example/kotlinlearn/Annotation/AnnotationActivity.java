package com.example.kotlinlearn.Annotation;


//@ContentView(layoutId = R.layout.activity_annotation)
//class AnnotationActivity : BaseActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//}

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlinlearn.R;

//@ContentView(layoutId = R.layout.activity_annotation)
public class AnnotationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}