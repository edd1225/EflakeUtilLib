package com.eflake.utils.library;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.eflake.utils.library.db.DBManager;
import com.eflake.utils.library.utils.AsyncHttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private ImageView imgImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(getActionBarThemedContextCompat(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section1),
								getString(R.string.title_section2),
								getString(R.string.title_section3), }), this);
		imgImageView = (ImageView) findViewById(R.id.imageView1);
		HttpGetImg();
	}

	private void HttpGetImg() {
		AsyncHttpUtils.get("http://www.emptyblue.it/data/wallpaper/SteinsGate/steins_gate_1992.jpg", new BinaryHttpResponseHandler() {
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				super.onProgress(bytesWritten, totalSize);
				Log.d("eflake", "progress = "+(int)((bytesWritten/(float)totalSize)*100));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
				imgImageView.setImageBitmap(bitmap);
				ObjectAnimator animator = ObjectAnimator.ofFloat(imgImageView, "alpha", 0,1);
				animator.start();
				animator.setDuration(2000);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				
			}
		});
	}

	private void HttpPostGetImg() {
		AsyncHttpUtils.get("http://example.com/file.png", new FileAsyncHttpResponseHandler(this) {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, File arg2) {
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, Throwable arg2, File arg3) {
				
			}
		});
	}

	private void HttpPostTest() {
		JSONObject obj = new JSONObject();
		StringEntity entity = null;
		try {
			obj.put("token", "50dd6b7201f311e491840024e8793b64");
			entity = new StringEntity(obj.toString(), HTTP.UTF_8);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		AsyncHttpUtils.post(this, "http://304.mzhen.cn/poi/api/v1/" + "maps/",
				entity, "application/json", new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						super.onSuccess(statusCode, headers, response);
						Log.d("eflake", response.toString());
					}
				});
	}

	@SuppressLint("SimpleDateFormat")
	private void HttpGetTest() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		String time = formatter.format(curDate);
		AsyncHttpUtils.get("http://dev.tmlsystem.com" + "/yunji/postTrack/?" + "t="
				+ time.trim() + "", new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] arg2) {
				// called when response HTTP status is "200 OK"
				String result = new String(arg2);
				Log.d("eflake", result);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// called when response HTTP status is "4XX" (eg. 401, 403
			}
		});
	}

	private void DbTest() {
		DBManager.getInstance(this).insertPoiPosition("ken");
		String s = DBManager.getInstance(this).queryPoiPosition("_id='6'");
		Log.d("eflake", s);
	}

	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
		return true;
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
//			dummyTextView.setText(Integer.toString(getArguments().getInt(
//					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
