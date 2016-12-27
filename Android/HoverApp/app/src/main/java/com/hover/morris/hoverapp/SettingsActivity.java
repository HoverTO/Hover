package com.hover.morris.hoverapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.games.GamesMetadata;

public class SettingsActivity extends AppCompatActivity {

	private static final int STATION_SELECT_REQUEST = 1;

	private ViewPager viewPager;
	private CustomPagerAdapter customPagerAdapter;
	private Button next_button;

	private Spinner office_spinner, group_spinner, role_spinner;
	private Button add_station_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		viewPager = (ViewPager) findViewById(R.id.viewpager);
		customPagerAdapter = new CustomPagerAdapter(this);
		viewPager.setAdapter(customPagerAdapter);

		next_button = (Button) findViewById(R.id.btn_next);
		next_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int page = viewPager.getCurrentItem();
				int count = viewPager.getAdapter().getCount();
				if(page + 1 < count) {
					viewPager.setCurrentItem(page + 1);
				}
			}
		});

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if(viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
					next_button.setEnabled(false);
				} else {
					next_button.setEnabled(true);
				}
			}
		});

		add_station_button = (Button) ((View)customPagerAdapter.instantiateItem(viewPager, 1)).findViewById(R.id.btn_add_station);

		add_station_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("DEBUG", "WHAT THE FUCK");
			}
		});
	}

	private void init() {
		office_spinner = (Spinner) findViewById(R.id.office_spinner);
		group_spinner = (Spinner) findViewById(R.id.group_spinner);
		role_spinner = (Spinner) findViewById(R.id.role_spinner);
	}

	/**
	 * Start StationSelectActivity to choose a radio station to add to the list
	 */
	private void launchStationChooser() {
		Intent intent = new Intent(getApplicationContext(), StationSelectActivity.class);
		startActivityForResult(intent, STATION_SELECT_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Determine which request produced this result
		if(requestCode == STATION_SELECT_REQUEST) {
			// Make sure request was successful
			if(resultCode == RESULT_OK) {

			}
		}
	}
}

enum CustomPagerEnum {

	PERSONAL_INFO(R.string.personal_info, R.layout.view_personal_info),
	RADIO_STATIONS(R.string.radio_stations, R.layout.view_radio_stations),
	ORANGE(R.string.orange, R.layout.view_preferences);

	private int mTitleResId;
	private int mLayoutResId;

	CustomPagerEnum(int titleResId, int layoutResId) {
		mTitleResId = titleResId;
		mLayoutResId = layoutResId;
	}

	public int getTitleResId() {
		return mTitleResId;
	}

	public int getLayoutResId() {
		return mLayoutResId;
	}

}

class CustomPagerAdapter extends PagerAdapter {

	private Context mContext;

	public CustomPagerAdapter(Context context) {
		mContext = context;
	}

	@Override
	public Object instantiateItem(ViewGroup collection, int position) {
		CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
		LayoutInflater inflater = LayoutInflater.from(mContext);

		ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);



		collection.addView(layout);
		//TODO
		//http://stackoverflow.com/questions/7968573/android-viewpager-findviewbyid-not-working-always-returning-null
		return layout;
	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((View) view);
	}

	@Override
	public int getCount() {
		return CustomPagerEnum.values().length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
		return mContext.getString(customPagerEnum.getTitleResId());
	}

}