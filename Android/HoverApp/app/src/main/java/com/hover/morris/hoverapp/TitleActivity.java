package com.hover.morris.hoverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TitleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		if(isLoggedIn()) {
			// TODO go to main app
		}
	}

	public void join(View view) {
		Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
		startActivity(intent);
		finish();
	}

	public void login(View view) {
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(intent);
		finish();
	}

	public boolean isLoggedIn() {
		// TODO return true if the user has logged in
		return false;
	}
}
