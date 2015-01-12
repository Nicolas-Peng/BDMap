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
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);

		// 设置地图中心位置
		LatLng center = new LatLng(22.352921, 113.596621);
		mMapView = new MapView(this,
				new BaiduMapOptions().mapStatus(new MapStatus.Builder().target(
						center).build()));
		setContentView(mMapView);

		mBaiduMap = mMapView.getMap();

		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 构造定位数据
		MyLocationData locData = new MyLocationData.Builder()
		// 此处设置开发者获取到的方向信息，顺时针0-360
				.latitude(22.352921).longitude(113.596621).build();
		// 设置定位数据
		mBaiduMap.setMyLocationData(locData);

		// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
		BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
				.fromResource(R.drawable.location);
		MyLocationConfiguration config = new MyLocationConfiguration(
				com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL,
				true, mCurrentMarker);
		mBaiduMap.getLocationConfigeration();

		// 添加覆盖物
		// 图书馆经纬度
		// 珠影超市经纬度：113.592003,22.35618
		// 中国银行经纬度：113.595615,22.352821
		// 体育馆经纬度：113.587332,22.355788
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
		OverlayOptions optionA = new MarkerOptions().position(pointA)// 设置marker的位置
				.icon(bitmapA)// 设置marker的图标
				.zIndex(9)// 设置marker所在层级
				.draggable(true);// 设置手势拖曳
		OverlayOptions optionB = new MarkerOptions().position(pointB)// 设置marker的位置
				.icon(bitmapB)// 设置marker的图标
				.zIndex(9)// 设置marker所在层级
				.draggable(true);// 设置手势拖曳
		OverlayOptions optionC = new MarkerOptions().position(pointC)// 设置marker的位置
				.icon(bitmapC)// 设置marker的图标
				.zIndex(9)// 设置marker所在层级
				.draggable(true);// 设置手势拖曳
		OverlayOptions optionD = new MarkerOptions().position(pointD)// 设置marker的位置
				.icon(bitmapD)// 设置marker的图标
				.zIndex(9)// 设置marker所在层级
				.draggable(true);// 设置手势拖曳

		markerA = (Marker) (mBaiduMap.addOverlay(optionA));
		markerB = (Marker) (mBaiduMap.addOverlay(optionB));
		markerC = (Marker) (mBaiduMap.addOverlay(optionC));
		markerD = (Marker) (mBaiduMap.addOverlay(optionD));

		// 覆盖物的点击响应
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				if (marker.equals(markerA)) {
					Toast t = Toast.makeText(MainActivity.this, "图书馆",
							Toast.LENGTH_SHORT);
					t.show();
				} else if (marker.equals(markerB)) {
					Toast t = Toast.makeText(MainActivity.this, "珠影超市",
							Toast.LENGTH_SHORT);
					t.show();
				} else if (marker.equals(markerC)) {
					Toast t = Toast.makeText(MainActivity.this, "中国银行",
							Toast.LENGTH_SHORT);
					t.show();
				} else if (marker.equals(markerD)) {
					Toast t = Toast.makeText(MainActivity.this, "体育馆",
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
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

}
