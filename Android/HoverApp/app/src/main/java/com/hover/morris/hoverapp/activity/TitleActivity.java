package com.hover.morris.hoverapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.hover.morris.hoverapp.R;

public class TitleActivity extends AppCompatActivity {

	private TextView company_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		company_text = (TextView) findViewById(R.id.company_text);
		company_text.setText(Html.fromHtml("<b>HOV</b>ER"));

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
