package com.kienle.famous.people.ngoctrinh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.kienle.famous.people.ngoctrinh.adapter.FullScreenImageAdapter;
import com.kienle.famous.people.ngoctrinh.helper.Utils;

public class FullScreenViewActivity extends Activity{

	private Utils mUtils;
	private FullScreenImageAdapter mAdapter;
	private ViewPager mViewPager;
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen_view);

		mViewPager = (ViewPager) findViewById(R.id.pager);

		mUtils = new Utils(this);

		Intent i = getIntent();
		int position = i.getIntExtra(GridViewActivity.POSITION, 0);

		mAdapter = new FullScreenImageAdapter(FullScreenViewActivity.this, mUtils.getFilePaths());
		mViewPager.setAdapter(mAdapter);

		// displaying selected image first
		mViewPager.setCurrentItem(position);
		
		mAdView = new AdView(this);
		mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
		mAdView.setAdSize(AdSize.BANNER);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(mAdView, params);
		mAdView.loadAd(new AdRequest.Builder().build());
	}
}
