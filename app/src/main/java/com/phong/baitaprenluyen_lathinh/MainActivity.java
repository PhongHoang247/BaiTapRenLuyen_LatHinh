package com.phong.baitaprenluyen_lathinh;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
    private void latHinh(View h1, View h2){//đối tượng là 1
        final View visibleList;
        final View invisibleList;
        if (h1.getVisibility() == View.GONE){
            visibleList = h2;
            invisibleList = h1;
        }
        else
        {
            invisibleList = h2;
            visibleList = h1;
        }
        h1.setVisibility(View.GONE);
        ObjectAnimator objectAnimatorVisible = ObjectAnimator.ofFloat(visibleList,"rotationX",0f,90f);
        objectAnimatorVisible.setDuration(500);
        objectAnimatorVisible.setInterpolator(accelerateInterpolator);

        final ObjectAnimator objectAnimatorInvisible = ObjectAnimator.ofFloat(invisibleList,"rotationX",-90f,0f);
        objectAnimatorInvisible.setDuration(500);
        objectAnimatorInvisible.setInterpolator(decelerateInterpolator);

        objectAnimatorVisible.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                visibleList.setVisibility(View.GONE);//ẩn
                objectAnimatorInvisible.start();
                invisibleList.setVisibility(View.VISIBLE);//hiển thị
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimatorVisible.start();
        h1.setVisibility(View.VISIBLE);//hiển thị
    }

    ImageView imgHinh;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latHinh(imgHinh,view);//imgHinh và view là 1
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latHinh(btn1,view);
            }
        });
    }

    private void addControls() {
        imgHinh = findViewById(R.id.imgHinh);
        btn1 = findViewById(R.id.btn1);
    }
}
