package com.LZP.bdmap;

import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	private Marker markerA;
	private Marker markerB;
	private Marker markerC;
	private Marker markerD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		// ��ȡ��ͼ�ؼ�����
		mMapView = (MapView) findViewById(R.id.bmapView);

		// ���õ�ͼ����λ��
		LatLng center = new LatLng(22.352921, 113.596621);
		mMapView = new MapView(this,
				new BaiduMapOptions().mapStatus(new MapStatus.Builder().target(
						center).build()));
		setContentView(mMapView);

		mBaiduMap = mMapView.getMap();

		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);
		// ���춨λ����
		MyLocationData locData = new MyLocationData.Builder()
		// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
				.latitude(22.352921).longitude(113.596621).build();
		// ���ö�λ����
		mBaiduMap.setMyLocationData(locData);

		// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩
		BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
				.fromResource(R.drawable.location);
		MyLocationConfiguration config = new MyLocationConfiguration(
				com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL,
				true, mCurrentMarker);
		mBaiduMap.getLocationConfigeration();

		// ��Ӹ�����
		// ͼ��ݾ�γ��
		// ��Ӱ���о�γ�ȣ�113.592003,22.35618
		// �й����о�γ�ȣ�113.595615,22.352821
		// �����ݾ�γ�ȣ�113.587332,22.355788
		LatLng pointA = new LatLng(22.349821, 113.595543);
		LatLng pointB = new LatLng(22.35618, 113.592003);
		LatLng pointC = new LatLng(22.352821, 113.595615);
		LatLng pointD = new LatLng(22.355788, 113.587332);
		BitmapDescriptor bitmapA = BitmapDescriptorFactory
				.fromResource(R.drawable.marker_a);
		BitmapDescriptor bitmapB = BitmapDescriptorFactory
				.fromResource(R.drawable.marker_b);
		BitmapDescriptor bitmapC = BitmapDescriptorFactory
				.fromResource(R.drawable.marker_c);
		BitmapDescriptor bitmapD = BitmapDescriptorFactory
				.fromResource(R.drawable.marker_d);
		OverlayOptions optionA = new MarkerOptions().position(pointA)// ����marker��λ��
				.icon(bitmapA)// ����marker��ͼ��
				.zIndex(9)// ����marker���ڲ㼶
				.draggable(true);// ����������ҷ
		OverlayOptions optionB = new MarkerOptions().position(pointB)// ����marker��λ��
				.icon(bitmapB)// ����marker��ͼ��
				.zIndex(9)// ����marker���ڲ㼶
				.draggable(true);// ����������ҷ
		OverlayOptions optionC = new MarkerOptions().position(pointC)// ����marker��λ��
				.icon(bitmapC)// ����marker��ͼ��
				.zIndex(9)// ����marker���ڲ㼶
				.draggable(true);// ����������ҷ
		OverlayOptions optionD = new MarkerOptions().position(pointD)// ����marker��λ��
				.icon(bitmapD)// ����marker��ͼ��
				.zIndex(9)// ����marker���ڲ㼶
				.draggable(true);// ����������ҷ

		markerA = (Marker) (mBaiduMap.addOverlay(optionA));
		markerB = (Marker) (mBaiduMap.addOverlay(optionB));
		markerC = (Marker) (mBaiduMap.addOverlay(optionC));
		markerD = (Marker) (mBaiduMap.addOverlay(optionD));

		// ������ĵ����Ӧ
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				if (marker.equals(markerA)) {
					Toast t = Toast.makeText(MainActivity.this, "ͼ���",
							Toast.LENGTH_SHORT);
					t.show();
				} else if (marker.equals(markerB)) {
					Toast t = Toast.makeText(MainActivity.this, "��Ӱ����",
							Toast.LENGTH_SHORT);
					t.show();
				} else if (marker.equals(markerC)) {
					Toast t = Toast.makeText(MainActivity.this, "�й�����",
							Toast.LENGTH_SHORT);
					t.show();
				} else if (marker.equals(markerD)) {
					Toast t = Toast.makeText(MainActivity.this, "������",
							Toast.LENGTH_SHORT);
					t.show();
				}
				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// ��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onResume();
	}

}
