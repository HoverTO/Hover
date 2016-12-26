package com.hover.morris.hoverapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

	private ViewPager viewPager;
	private Button next_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new CustomPagerAdapter(this));

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
	}
}

enum CustomPagerEnum {

	RED(R.string.red, R.layout.view_red),
	BLUE(R.string.blue, R.layout.view_blue),
	ORANGE(R.string.orange, R.layout.view_orange);

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