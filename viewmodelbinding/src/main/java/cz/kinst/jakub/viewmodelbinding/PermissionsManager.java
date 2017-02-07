package cz.kinst.jakub.viewmodelbinding;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;


public class PermissionsManager {
	private static final int RC_PERMISSIONS = 62538;
	ViewModel mViewModel;
	private PermissionCallback mCallback;


	public interface PermissionCallback {
		void onPermissionsResult(PermissionsResult permissionsResult);
	}


	PermissionsManager(ViewModel viewModel) {
		mViewModel = viewModel;
	}


	public void checkOrRequestPermissions(String permission, PermissionCallback callback) {
		checkOrRequestPermissions(new String[]{permission}, callback);
	}


	public void checkOrRequestPermissions(String[] permissions, PermissionCallback callback) {
		PermissionsResult result = checkPermissions(permissions);
		if(result.isGranted())
			callback.onPermissionsResult(result);
		else {
			requestPermissions(permissions, callback);
		}
	}


	public boolean shouldShowRequestPermissionRationale(String permission) {
		if(mViewModel.getView() instanceof Fragment) {
			return ((Fragment) mViewModel.getView()).shouldShowRequestPermissionRationale(permission);
		} else {
			return ActivityCompat.shouldShowRequestPermissionRationale(mViewModel.getActivity(), permission);
		}
	}


	public PermissionsResult checkPermissions(String... permissions) {
		Map<String, Boolean> results = new HashMap<>();

		for(String permission : permissions) {
			int result = ContextCompat.checkSelfPermission(mViewModel.getApplicationContext(), permission);
			results.put(permission, result == PackageManager.PERMISSION_GRANTED);
		}
		return new PermissionsResult(results);
	}


	public void requestPermissions(String permission, PermissionCallback callback) {
		requestPermissions(new String[]{permission}, callback);
	}


	public void requestPermissions(String[] permissions, PermissionCallback callback) {
		mCallback = callback;
		if(mViewModel.getView() instanceof Fragment) {
			((Fragment) mViewModel.getView()).requestPermissions(permissions, RC_PERMISSIONS);
		} else {
			ActivityCompat.requestPermissions(mViewModel.getActivity(), permissions, RC_PERMISSIONS);
		}
	}


	public Intent getApplicationPermissionSettingsIntent() {
		Intent intent = new Intent();
		intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		Uri uri = Uri.fromParts("package", mViewModel.getApplicationContext().getPackageName(), null);
		intent.setData(uri);
		return intent;
	}


	void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if(requestCode == RC_PERMISSIONS) {
			if(mCallback != null) {
				mCallback.onPermissionsResult(new PermissionsResult(permissions, grantResults));
				mCallback = null;
			}
		}
	}


	public class PermissionsResult {
		Map<String, Boolean> mResult;


		private PermissionsResult(Map<String, Boolean> result) {
			mResult = result;
		}


		private PermissionsResult(String[] permissions, int[] grantResults) {
			mResult = new HashMap<>();
			for(int i = 0; i < permissions.length; i++) {
				mResult.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
			}
		}


		public boolean isGranted() {
			String[] permissionsArray = mResult.keySet().toArray(new String[mResult.keySet().size()]);
			return isGranted(permissionsArray);
		}


		public boolean isGranted(String... permissions) {
			for(String permission : permissions) {
				if(!mResult.get(permission))
					return false;
			}
			return true;
		}


		public Map<String, Boolean> getResultsMap() {
			return new HashMap<>(mResult);
		}
	}
}
