package com.hover.morris.hoverapp;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by morris on 2016-12-28.
 */

public class PersonalInfoContainer {
	public ImageView profile_image;
	public FloatingActionButton camera_button;
	public EditText input_name, input_email, input_phone;

	public PersonalInfoContainer(View view) {
		profile_image = (ImageView) view.findViewById(R.id.profile_image);
		camera_button = (FloatingActionButton) view.findViewById(R.id.btn_camera);

		input_name = (EditText) view.findViewById(R.id.input_name);
		input_email = (EditText) view.findViewById(R.id.input_email);
		

		camera_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("DEBUG", "Camera Button Click");
			}
		});
	}
}
