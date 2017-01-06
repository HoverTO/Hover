package com.hover.morris.hoverapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.hover.morris.hoverapp.R;

public class WelcomeActivity extends AppCompatActivity {

	private TextView fade_text;
	private View welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);

		fade_text = (TextView) findViewById(R.id.fade_text);
		Animation fade_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_fade);
		fade_text.setAnimation(fade_anim);

		welcome = findViewById(R.id.welcome_message);
		welcome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				welcome.setVisibility(View.INVISIBLE);

				Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
