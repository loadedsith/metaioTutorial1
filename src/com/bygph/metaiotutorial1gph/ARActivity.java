package com.bygph.metaiotutorial1gph;

import android.util.Log;
import android.view.View;

import com.metaio.sdk.SensorsComponentAndroid;
import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.LLACoordinate;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;
//
public class ARActivity extends ARViewActivity implements SensorsComponentAndroid.Callback {

	private String mTrackingFile;
	private IGeometry mMan;
	private IGeometry mMan2;
	private IGeometry mSphere;
	
	@Override
	protected int getGUILayout() {
		return R.layout.ar_view;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadContents() {
		mTrackingFile = AssetsManager.getAssetPath("assets1/TrackingData_MarkerlessFast.xml");
		boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile);
		MetaioDebug.log("Tracking data loaded: "+result);
		
		String modelPath = AssetsManager.getAssetPath("assets1/metaioman.md2");
		
		if(modelPath != null){
			mMan = metaioSDK.createGeometry(modelPath);
			if(mMan != null){
				mMan.setScale(new Vector3d(4.0f,4.0f,4.0f));
				mMan.setVisible(true);
				MetaioDebug.log("loading geometry: ");
			}else{
				MetaioDebug.log(Log.ERROR,"Error loading geometry: "+modelPath);
			}
		}
		String model2Path = AssetsManager.getAssetPath("assets1/sailboat.zip");
		if(model2Path != null)
		{
			mMan2 = metaioSDK.createGeometry(model2Path);
			if(mMan2 != null){
				mMan2.setScale(new Vector3d(4.0f,4.0f,4.0f));
				mMan2.setVisible(false);
				MetaioDebug.log("loading geometry: ");
			}else{
				MetaioDebug.log(Log.ERROR,"Error loading geometry: "+model2Path);
			}
		}
		String modelSpherePath = AssetsManager.getAssetPath("assets2/sphere.md2");
		String modelSphereTexturePath = AssetsManager.getAssetPath("assets2/skySphere1.png");
		String modelSphereMovieTexturePath = AssetsManager.getAssetPath("assets2/out.alpha.3g2");
		
		if(modelSpherePath != null){
			mSphere = metaioSDK.createGeometry(modelSpherePath);
			if(mSphere != null){
				mSphere.setScale(new Vector3d(24.0f,24.0f,24.0f));
				mSphere.setVisible(false);
//				mSphere.setTexture(modelSphereTexturePath);
				mSphere.setMovieTexture(modelSphereMovieTexturePath, true);
				mSphere.startMovieTexture(true);
				mSphere.setRotation(new Rotation(new Vector3d((float)(Math.PI/2),0,0)));
				MetaioDebug.log("loading geometry: ");
			}else{
				MetaioDebug.log(Log.ERROR,"Error loading geometry: "+modelSpherePath);
			}
		}
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub

	}
	public void onMan2ButtonClick(View v) {
 		mMan.setVisible(false);
		mMan2.setVisible(true);
		mSphere.setVisible(false);
	}
	public void onManButtonClick(View v) {
		mMan.setVisible(true);
		mMan2.setVisible(false);
		mSphere.setVisible(false);
	}
	
	public void onSphereButtonClick(View v) {
		mSphere.setVisible(true);
		mMan.setVisible(false);
		mMan2.setVisible(false);
	}
	
	public void onMarkerlessButtonClick(View v) {
		mTrackingFile = AssetsManager.getAssetPath("assets1/TrackingData_MarkerlessFast.xml");
		boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile);
		MetaioDebug.log("Tracking data loaded: "+result);
	}
	public void onPictureButtonClick(View v) {
		mTrackingFile = AssetsManager.getAssetPath("assets1/TrackingData_PictureMarker.xml");
		boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile);
		MetaioDebug.log("Tracking data loaded: "+result);
	}
	public void onIdButtonClick(View v) {
		mTrackingFile = AssetsManager.getAssetPath("assets1/TrackingData_Marker.xml");
		boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile);

	}
	public void onOrientationClick(View v){
		boolean result = metaioSDK.setTrackingConfiguration("ORIENTATION");
		MetaioDebug.log("Tracking data loaded: "+result);
	}

	@Override
	public void onGravitySensorChanged(float[] gravity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHeadingSensorChanged(float[] orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationSensorChanged(LLACoordinate location) {
		// TODO Auto-generated method stub
		
	}
}

