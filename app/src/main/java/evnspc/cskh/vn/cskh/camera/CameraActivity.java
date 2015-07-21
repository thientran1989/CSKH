package evnspc.cskh.vn.cskh.camera;

import static evnspc.cskh.vn.cskh.camera.CameraHelper.cameraAvailable;
import static evnspc.cskh.vn.cskh.camera.CameraHelper.getCameraInstance;
import static evnspc.cskh.vn.cskh.camera.MediaHelper.getOutputMediaFile;
import static evnspc.cskh.vn.cskh.camera.MediaHelper.saveToFile;
import java.io.File;
import java.text.DecimalFormat;

import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;

import evnspc.cskh.vn.cskh.R;


@SuppressWarnings("deprecation")
public class CameraActivity extends Activity implements PictureCallback {

	public static final String EXTRA_IMAGE_PATH = "vn.evnspc.evnsmartcare.camera.CameraActivity.EXTRA_IMAGE_PATH";

	private Camera camera;
	private CameraPreview cameraPreview;
	Direction direction;
	int w, h;
	String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.activity_camera);
		setResult(RESULT_CANCELED);
		camera = getCameraInstance();
		if (cameraAvailable(camera)) {
			try {
				initCameraPreview();
			} catch (Exception e) {
			}

		} else {
			finish();
		}

	}

	private void initCameraPreview() {
		cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);
		cameraPreview.init(camera);
		Camera.Parameters params = camera.getParameters();
		//*EDIT*//params.setFocusMode("continuous-picture");
		//It is better to use defined constraints as opposed to String, thanks to AbdelHady
		params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
		camera.setParameters(params);
	}

	@FromXML
	public void onCaptureClick(View button) {
		camera.takePicture(null, null, this);
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		path = savePictureToFileSystem(data);
		setResult(path);
		releaseCamera();
		finish();
	}

	private static String savePictureToFileSystem(byte[] data) {
		File file = getOutputMediaFile();
		saveToFile(data, file);
		return file.getAbsolutePath();
	}

	private void setResult(String path) {
		Intent intent = new Intent();
		intent.putExtra(EXTRA_IMAGE_PATH, path);
		setResult(RESULT_OK, intent);
	}

	private void releaseCamera() {
		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseCamera();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}

}
