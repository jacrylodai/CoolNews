package com.ldp.coolnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.ldp.coolnews.R;


public class SplashActivity extends ActionBarActivity {
	
	private static final long DURATION_MILLIS = 1000;
	private RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);
        
        initAnim();
    }

	private void goToAppDesc() {

		Intent intent = new Intent(this,AppDescActivity.class);
		startActivity(intent);
		finish();
	}

	private void initAnim() {
		
		AnimationSet animationSet = new AnimationSet(false);

//		RotateAnimation rotateAnim = 
//				new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF
//						, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//		rotateAnim.setDuration(DURATION_MILLIS);
		
		ScaleAnimation scaleAnim = 
				new ScaleAnimation(0.5f, 1f, 0.5f, 1f, ScaleAnimation.RELATIVE_TO_SELF
						,0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnim.setDuration(DURATION_MILLIS);
		
		AlphaAnimation alphaAnim = 
				new AlphaAnimation(0.3f, 1f);
		alphaAnim.setDuration(DURATION_MILLIS);
		
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnim);
		
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {

				goToAppDesc();
			}
		});
		
		rlSplash.startAnimation(animationSet);
	}

}
