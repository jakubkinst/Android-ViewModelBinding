package cz.kinst.jakub.viewmodelbinding.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by jakubkinst on 06/12/15.
 */
public class PermissionsHelper {

	private static final int REQUEST_PERMISSIONS = 100;

	Activity mActivity;
	private PermissionRequestCallback mPermissionRequestCallback;


	public interface PermissionRequestCallback {
		void onPermissionResult(Map<String, Boolean> results);
	}


	public PermissionsHelper(Activity activity) {
		mActivity = activity;
	}


	public boolean checkGrantedPermission(String permission) {
		return ContextCompat.checkSelfPermission(mActivity, permission) == PackageManager.PERMISSION_GRANTED;
	}


	public void requestPermissions(String[] permissions, PermissionRequestCallback callback) {
		mPermissionRequestCallback = callback;
		ActivityCompat.requestPermissions(mActivity, permissions, REQUEST_PERMISSIONS);
	}


	public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
		Map<String, Boolean> results = new HashMap<>();
		for(int i = 0; i < permissions.length; i++)
			results.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);

		if(mPermissionRequestCallback != null)
			mPermissionRequestCallback.onPermissionResult(results);
		mPermissionRequestCallback = null;
	}
}
