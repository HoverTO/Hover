package com.hover.morris.hoverapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hover.morris.hoverapp.R;

public class SignupActivity extends AppCompatActivity {

	private Button signup_button;
	private TextView login_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		signup_button = (Button) findViewById(R.id.btn_signup);
		login_link = (TextView) findViewById(R.id.lnk_login);

		signup_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				signup();
			}
		});

		login_link.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Start the Signup activity
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		});
	}

	public void signup() {
		Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
		startActivity(intent);
		finish();
	}
}
