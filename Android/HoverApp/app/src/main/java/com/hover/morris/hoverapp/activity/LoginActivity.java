package com.hover.morris.hoverapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hover.morris.hoverapp.R;

public class LoginActivity extends AppCompatActivity {
	private static final int REQUEST_SIGNUP = 0;

	private EditText email_text;
	private EditText password_text;
	private Button login_button;
	private TextView signup_link;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		email_text = (EditText) findViewById(R.id.input_email);
		password_text = (EditText) findViewById(R.id.input_password);
		login_button = (Button) findViewById(R.id.btn_login);
		signup_link = (TextView) findViewById(R.id.lnk_signup);

		login_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				login();
			}
		});

		signup_link.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Start the Signup activity
				Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
				startActivityForResult(intent, REQUEST_SIGNUP);
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		});
	}

	/**
	 * Check credentials and start application
	 */
	public void login() {
		String email = email_text.getText().toString();
		String password = password_text.getText().toString();

		// TODO

		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_SIGNUP) {
			if (resultCode == RESULT_OK) {

				// TODO: Implement successful signup logic here
				// By default we just finish the Activity and log them in automatically
				this.finish();
			}
		}
	}

}
