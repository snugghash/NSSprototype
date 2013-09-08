package com.example.scrolltabsfragmenttest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class MapBMPFragment extends Fragment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.iitmphysicalmap2004, container, false);

		Toast.makeText(inflater.getContext(), "You are in physical maps!",
				Toast.LENGTH_SHORT).show();

	//Implemented by getting a scaled version of the image
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//				R.drawable.iitmmap2004, options);
//		int imageHeight = options.outHeight;
//		int imageWidth = options.outWidth;
//		String imageType = options.outMimeType;
//
//		ImageView mImageView = (ImageView) view.findViewById(
//				R.id.iitmmap2004iv);
//		// mImageView.setImageBitmap(bitmap);
//
//		Bitmap bitmap2 = decodeSampledBitmapFromResource(getResources(),
//				R.drawable.iitmmap2004, 1080, 1920);
//		if (mImageView == null)
//			Toast.makeText(inflater.getContext(), "You are in deep!",
//					Toast.LENGTH_SHORT).show();
//		else
//			mImageView.setImageBitmap(bitmap2);
		
	//Implemented by loading the image in WebView
		String imageUrl = "file:///android_asset/iitmmap2004.jpg"; 
		WebView wv = (WebView) view.findViewById(R.id.mywebview);
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setUseWideViewPort(true);
		wv.loadUrl(imageUrl);

	//Attempt to copy the image to sdcard and read it using gallery
		// File file = new File("file://" + "/sdcard/NSS/", "iitmmap2004.jpg");
		// FileOutputStream out = null;
		// try {
		// out = new FileOutputStream(file);
		// } catch (FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// Bitmap bitmap =
		// BitmapFactory.decodeResource(getResources(),R.drawable.iitmmap2004);
		// bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		// try {
		// out.flush();
		//
		// out.close();} catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// // Uri path = Uri.parse("android.resource://" +
		// // getActivity().getPackageName() + "/" + R.drawable.iitmmap2004);
		// Uri path2 = Uri.parse("file://" + "/sdcard/Dev/iitmmap2004.jpg");
		// Intent intent = new Intent();
		// intent.setAction(Intent.ACTION_DEFAULT);
		// intent.setDataAndType(path2, "image/*");
		// startActivity(intent);

		return view;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

}
