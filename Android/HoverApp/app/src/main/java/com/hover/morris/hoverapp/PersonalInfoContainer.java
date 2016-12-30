package com.hover.morris.hoverapp;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by morris on 2016-12-28.
 */

public class PersonalInfoContainer {
	public ImageView profile_image;
	public FloatingActionButton camera_button;
	public EditText input_name, input_email, input_phone;
	public RadioGroup radio_gender;

	public EditText input_home_address, input_postal_code, input_work_address;
	public Spinner spinner_office, spinner_group, spinner_role;
	public RadioGroup radio_action;

	public PersonalInfoContainer(View view) {
		profile_image = (ImageView) view.findViewById(R.id.profile_image);
		camera_button = (FloatingActionButton) view.findViewById(R.id.btn_camera);

		input_name = (EditText) view.findViewById(R.id.input_name);
		input_email = (EditText) view.findViewById(R.id.input_email);
		input_phone = (EditText) view.findViewById(R.id.input_phone);

		radio_gender = (RadioGroup) view.findViewById(R.id.gender_radiogroup);

		input_home_address = (EditText) view.findViewById(R.id.input_home_address);
		input_postal_code = (EditText) view.findViewById(R.id.input_postal_code);
		input_work_address = (EditText) view.findViewById(R.id.input_work_address);

		spinner_office = (Spinner) view.findViewById(R.id.office_spinner);
		spinner_group = (Spinner) view.findViewById(R.id.group_spinner);
		spinner_role = (Spinner) view.findViewById(R.id.role_spinner);

		radio_action = (RadioGroup) view.findViewById(R.id.action_radiogroup);

		camera_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("DEBUG", "Camera Button Click");
			}
		});
	}
}
