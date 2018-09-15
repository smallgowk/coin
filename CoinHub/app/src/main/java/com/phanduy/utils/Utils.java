package com.phanduy.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phanduy.GlobalInfo;
import com.phanduy.coinhub.R;
import com.phanduy.model.NotifyModel;
import com.phanduy.store.GlobalValue;
import com.phanduy.view.activity.MainHomeActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

@SuppressLint({ "SdCardPath", "SimpleDateFormat" })
public class Utils {

	public static final String CATEGORY_FILE = "category.dat";

	public static final String LIST_HOME = "listhome.dat";
	public static final String LIST_HOT_NEWS = "listhot.dat";
	public static final String LIST_INSIDE_NEWS = "listInsideNews.dat";
	public static final String DOC_FORM = "doc_form";
	public static final String DOC_INFO = "doc_info";
	public static final String LIST_SCHEDULE = "listschedule";
	public static final String SCHEDULE_DETAIL = "schedule_detail";
	public static final String STRUCTURE = "structure";
	public static final String STRUCTURE_DEPART = "structure_depart";
	public static final String STRUCTURE_PART = "structure_part";
	public static final String LIST_CONTACTS = "listcontact.bat";
	public static final String LIST_ALL_CONTACTS = "listallcontact.bat";
	public static final String LIST_CONTACTS_EMPTY = "listcontactempty.bat";
	public static final String NEWSDETAIL = "newsdetail";
	public static final String ROOT = "VTCacheJson";

	public static final String CATEGORY_DETAILS_FILE = ".dat";
	private static final int LOW_DPI_STATUS_BAR_HEIGHT = 19;
	private static final int MEDIUM_DPI_STATUS_BAR_HEIGHT = 25;
	private static final int HIGH_DPI_STATUS_BAR_HEIGHT = 38;
	// public static final String key = "gomugomupistol10110";

	public static SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat timeInboxFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

	// private static byte[] key = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,
	// 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79 };
	private static byte[] key = { 'P', 'H', 'A', 'N', 'N', 'A', 'N', 'G', 'D',
			'U', 'Y', 'B', 'K', 'A', 'H', 'N' };

	// private static byte[] key = { 'A', 'S', 'E', 'R', 'N', 'A', 'L', 'N',
	// 'U', 'M', 'B', 'E', 'R', 'O', 'N', 'E' };
	// private static String key = "12345678912345678912345678912345";

	public static boolean isAvaiableNetWork(Activity activity) {
		if (activity == null)
			return false;

		ConnectivityManager cm = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;

	}

	@SuppressLint("NewApi")
	public static void fixNetwork() {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}

	public static int convertDpToPx(Context context, int dp) {

		DisplayMetrics displayMetrics = context.getResources()
				.getDisplayMetrics();
		return (int) ((dp * displayMetrics.density) + 0.5);
	}

	public static void encrypt() throws IOException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {
		// Here you read the cleartext.
		FileInputStream fis = new FileInputStream("data/cleartext");
		// This stream write the encrypted text. This stream will be wrapped by
		// another stream.
		FileOutputStream fos = new FileOutputStream("data/encrypted");

		// Length is 16 byte
		SecretKeySpec sks = new SecretKeySpec("MyDifficultPassw".getBytes(),
				"AES");
		// Create cipher
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sks);
		// Wrap the output stream
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		// Write bytes
		int b;
		byte[] d = new byte[8];
		while ((b = fis.read(d)) != -1) {
			cos.write(d, 0, b);
		}
		// Flush and close streams.
		cos.flush();
		cos.close();
		fis.close();
	}

	public static void decrypt() throws IOException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {
		FileInputStream fis = new FileInputStream("data/encrypted");

		FileOutputStream fos = new FileOutputStream("data/decrypted");
		SecretKeySpec sks = new SecretKeySpec("MyDifficultPassw".getBytes(),
				"AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, sks);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		int b;
		byte[] d = new byte[8];
		while ((b = cis.read(d)) != -1) {
			fos.write(d, 0, b);
		}
		fos.flush();
		fos.close();
		cis.close();
	}

	public static void encodes(String data, int pass, File fileWriteToEncode) {
		if (data == null || fileWriteToEncode == null)
			return;
		byte[] encodeData = Base64.decode(data, pass);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileWriteToEncode);
			outputStream.write(encodeData, 0, encodeData.length);
			outputStream.flush();
			outputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static String decodes(int pass, File fileWriteToEncode) {
		String result = null;
		BufferedReader bufferedReader = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(fileWriteToEncode);
			bufferedReader = new BufferedReader(new InputStreamReader(
					fileInputStream));
			String line;
			StringBuffer text = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				text.append(line);
			}
			if (text != null && text.length() > 0)
				result = new String(Base64.decode(text.toString().getBytes(),
						pass));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	public static void setAutoOriention(Context activity) {
		if (activity == null)
			return;
		((Activity) activity)
				.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}

	public static void setFullScreen(Activity activity) {
		if (activity == null)
			return;
		activity.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		activity.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

	}

	public static void setDisableFullScreen(Activity activity) {
		if (activity == null)
			return;
		activity.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		activity.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

	}

	public static int getOrientation(Activity activity) {
		Configuration configuration = activity.getResources()
				.getConfiguration();
		return configuration.orientation;
	}

	public static int getConfigureScreen(Activity activity) {
		Configuration configuration = activity.getResources()
				.getConfiguration();
		return (Configuration.SCREENLAYOUT_SIZE_MASK & configuration.screenLayout);
	}

	public static int getHeightStatusBar(Activity activity) {
		DisplayMetrics displayMetrics = activity.getResources()
				.getDisplayMetrics();

		int statusBarHeight;

		switch (displayMetrics.densityDpi) {
		case DisplayMetrics.DENSITY_HIGH:
			statusBarHeight = HIGH_DPI_STATUS_BAR_HEIGHT;
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
			break;
		case DisplayMetrics.DENSITY_LOW:
			statusBarHeight = LOW_DPI_STATUS_BAR_HEIGHT;
			break;
		default:
			statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
		}

		return statusBarHeight;
	}

	public static String getNameFileCatDetails(String id) {
		if (id == null)
			return null;

		return id + CATEGORY_DETAILS_FILE;
	}

	public static String getNameFromCommandType(int commandType) {

		switch (commandType) {
		case 0:

			return "Điều khiển";

		case 1:

			return "Cấu hình";
		case 2:

			return "Giám sát";
		default:
			return "Điều khiển";
		}


	}


	public static void writeStringToFile(Context activity, String nameFile,
			String data) {
		if (TextUtils.isEmpty(nameFile) || TextUtils.isEmpty(data)
				|| activity == null)
			return;

		FileOutputStream fOut = null;

		try {

			fOut = activity.openFileOutput(nameFile, Context.MODE_PRIVATE);
			fOut.write(data.getBytes());
			fOut.close();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			if (fOut != null)
				try {
					fOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		// getTotalSizeOfCache((Activity)activity);

	}

	public static void clearCache(Activity activity) {
		String[] listFile = activity.fileList();

		if (listFile == null) {
			return;
		}
		for (String fileName : listFile) {
			activity.deleteFile(fileName);
		}
	}

	public static void clearCache(String fileName, Activity activity) {
		activity.deleteFile(fileName);
	}

	public static void clearStructurePartCache(Activity activity) {
		String[] listFile = activity.fileList();

		if (listFile == null) {
			return;
		}
		for (String fileName : listFile) {
			if (fileName.contains(Utils.STRUCTURE_PART)) {
				if (fileName.length() > Utils.STRUCTURE_PART.length()) {
					activity.deleteFile(fileName);
				}
			}
		}
	}

	public static int getTotalSizeOfCache(Activity activity) {
		String[] listFile = activity.fileList();

		if (listFile == null) {
			return 0;
		}
		int size = 0;
		FileInputStream fis = null;
		for (String fileName : listFile) {
			try {
				fis = activity.openFileInput(fileName);
				size += fis.available();
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SmartLog.log("Total Size", "" + size / 1024 + " KB");
		return size;
	}

	public static String readStringFromFile(Activity activity, String nameFile) {

		if (activity == null || TextUtils.isEmpty(nameFile))
			return null;

		FileInputStream fis = null;
		StringBuffer data;
		InputStreamReader isr = null;
		BufferedReader buffreader = null;
		try {
			data = new StringBuffer();
			fis = activity.openFileInput(nameFile);
			// dataIO = new DataInputStream(fis);
			// String strLine = null;
			//
			// if ((strLine = dataIO.readLine()) != null) {
			// data.append(strLine);
			// }
			isr = new InputStreamReader(fis);
			buffreader = new BufferedReader(isr);

			String readString = buffreader.readLine();
			while (readString != null) {
				data.append(readString);
				readString = buffreader.readLine();
			}
			// dataIO.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		} finally {
			if (isr != null)
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		if (data == null)
			return null;
		else
			return data.toString();

	}

	public static String readStringFromFileAsset(Context context,
			String nameFile) {
		// load text
		try {
			// get input stream for text
			InputStream is = context.getAssets().open(nameFile);
			// check size
			int size = is.available();
			// create buffer for IO
			byte[] buffer = new byte[size];
			// get data to buffer
			is.read(buffer);
			// close stream
			is.close();
			// set result to TextView
			return new String(buffer);
		} catch (IOException ex) {
			return null;
		}
	}

	public static boolean startVideoMXplayer(Context context, String pathVideo) {

		if (checkInstalledApplication(context, "com.mxtech.videoplayer.ad")) {
			Intent intent = new Intent(Intent.ACTION_VIEW);

			Uri videoUri = Uri.parse(pathVideo);

			// intent.setDataAndType(videoUri, "application/x-mpegURL");
			intent.setData(videoUri);

			intent.setPackage("com.mxtech.videoplayer.ad");

			context.startActivity(intent);
			return true;
		} else {
			return false;
		}

	}

	public static boolean checkInstalledApplication(Context context,
			String packageName) {
		PackageManager pm = context.getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	public static void pushNotifyDownload(final Context context, String tittle,
			String url) {
		final NotificationManager mNotifyManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context);
		mBuilder.setContentTitle("" + tittle)
				.setContentText("Download in progress")
				.setSmallIcon(R.drawable.ic_launcher);

		AsyncTask<String, Integer, String> asyn = new AsyncTask<String, Integer, String>() {

			@SuppressLint("Wakelock")
			@SuppressWarnings("resource")
			@Override
			protected String doInBackground(String... sUrl) {
				// take CPU lock to prevent CPU from going off if the user
				// presses the power button during download
				PowerManager pm = (PowerManager) context
						.getSystemService(Context.POWER_SERVICE);
				PowerManager.WakeLock wl = pm.newWakeLock(
						PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
				wl.acquire();

				try {
					InputStream input = null;
					OutputStream output = null;
					HttpURLConnection connection = null;
					try {
						URL url = new URL(sUrl[0]);
						connection = (HttpURLConnection) url.openConnection();
						connection.connect();

						// expect HTTP 200 OK, so we don't mistakenly save error
						// report
						// instead of the file
						if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
							return "Server returned HTTP "
									+ connection.getResponseCode() + " "
									+ connection.getResponseMessage();

						// this will be useful to display download percentage
						// might be -1: server did not report the length
						int fileLength = connection.getContentLength();

						// download the file
						input = connection.getInputStream();
						output = new FileOutputStream("/sdcard/ViettelICT.apk");

						byte data[] = new byte[4096];
						long total = 0;
						int count;
						while ((count = input.read(data)) != -1) {
							// allow canceling with back button
							if (isCancelled())
								return null;
							total += count;
							// publishing the progress....
							int incr = (int) (total * 100 / fileLength);
							if (fileLength > 0 && incr % 10 == 0) // only if
																	// total
																	// length is
																	// known
								publishProgress(incr);

							output.write(data, 0, count);
						}
					} catch (Exception e) {
						return e.toString();
					} finally {
						try {
							if (output != null)
								output.close();
							if (input != null)
								input.close();
						} catch (IOException ignored) {
						}

						if (connection != null)
							connection.disconnect();
					}
				} finally {
					wl.release();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mBuilder.setContentText("Download complete");
				// // Removes the progress bar
				// .setProgress(0,0,false);
				// mBuilder.setProgress(100, 100, false);
				mNotifyManager.notify(0, mBuilder.build());

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(new File(Environment
						.getExternalStorageDirectory() + "/ViettelICT.apk")),
						"application/vnd.android.package-archive");
				context.startActivity(intent);
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
				mBuilder.setProgress(100, values[0], false);
				// Displays the progress bar for the first tempCreatedDate.
				mNotifyManager.notify(0, mBuilder.build());
			}

		};
		asyn.execute(url);

		// Start a lengthy operation in a background thread
		// new Thread(
		// new Runnable() {
		// @Override
		// public void run() {
		// int incr;
		// // Do the "lengthy" operation 20 times
		// for (incr = 0; incr <= 100; incr+=5) {
		// // Sets the progress indicator to a max value, the
		// // current completion percentage, and "determinate"
		// // state
		// mBuilder.setProgress(100, incr, false);
		// // Displays the progress bar for the first tempCreatedDate.
		// mNotifyManager.notify(0, mBuilder.build());
		// // Sleeps the thread, simulating an operation
		// // that takes tempCreatedDate
		// try {
		// // Sleep for 5 seconds
		// Thread.sleep(5*1000);
		// } catch (InterruptedException e) {
		// Log.d("Notify", "sleep failure");
		// }
		// }
		// // When the loop is finished, updates the notification
		// mBuilder.setContentText("Download complete")
		// // Removes the progress bar
		// .setProgress(0,0,false);
		// mNotifyManager.notify(NOTIFY_ME_ID, mBuilder.build());
		// }
		// }
		// // Starts the thread by calling the run() method in its Runnable
		// ).start();

		// }

		// mNotifyManager.notify((int)System.currentTimeMillis(),
		// mBuilder.build());
	}

	// private static final int NOTIFY_ME_ID = 1337;

	public static void CopyStream(InputStream is, OutputStream os)

	{
		int size = 1024;
		final int buffer_size = size;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			SmartLog.log("100", ":(");
		}

	}

	// Ham ma hoa AES
	public static String encrypt(String strToEncrypt)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		final String encryptedString = bytesToHex(cipher.doFinal(strToEncrypt
				.getBytes()));
		return encryptedString;
	}

	public static String decrypt(String strToDecrypt)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		final String decryptedString = new String(cipher.doFinal(Base64.decode(
				strToDecrypt.getBytes(), Base64.DEFAULT)));
		return decryptedString;
	}

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String changeFomatDate(String start_dt) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = (Date)formatter.parse(start_dt);
		SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
		String finalString = newFormat.format(date);
		return  finalString;
	}

	public static String getTimeFromTimeStamp(long timeStamp, String hourLbl, String minuteLbl, String secondLbl,String dayLbl, String monthLbl) {
		
		
		
		String result = "";
		
		if(timeStamp < 60) {
			result = "" + timeStamp + " " + secondLbl;
		} else {
			if(timeStamp / 60 < 60) {
				String second = timeStamp % 60 > 0 ? timeStamp % 60 + " " + secondLbl : "";
				result = "" + timeStamp / 60 + " " + minuteLbl+ " " + second;
			} else {
				if((timeStamp / (60 * 60)) < 24) {
					String minutes = (timeStamp%(60*60))/60 > 0 ? (timeStamp%(60*60))/60 + " " + minuteLbl : "";
					result = "" + timeStamp/(60*60) + " " + hourLbl +" " + minutes;
				} else {
					if(timeStamp/(60*60*24) < 30) {
						String hours = (timeStamp%(60*60*24))/(60*60) > 0 ? (timeStamp%(60*60*24))/(60*60) + " " + hourLbl : "";
						result = "" + timeStamp/(60*60*24) + " " + dayLbl + " " + hours;
					} else {
						result = " > 1 " + monthLbl;
					}
				}
			}
		}
		
		return result;
	}
	public static double radius = 6371;

	public static double calculateDistanceTwoPoint(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return radius * c;
	}

	public static String getDistanceString(double total) {
		NumberFormat formatter = new DecimalFormat("#.##");
		if (total < 1) {
			total *= 1000;
			return formatter.format(total) + " m";
		} else {
			return formatter.format(total) + " km";
		}
		
	}
	
	public static void sendEmail(Activity activity, String subject, String email) {
		try {
			Intent localIntent1 = new Intent();
			localIntent1.setClassName("com.google.android.gm",
					"com.google.android.gm.ComposeActivityGmail");
			localIntent1.putExtra("android.intent.extra.SUBJECT",
					subject);
			localIntent1.putExtra("android.intent.extra.EMAIL", email);

			activity.startActivity(localIntent1);
		} catch (ActivityNotFoundException localActivityNotFoundException) {
			localActivityNotFoundException.printStackTrace();
			final Intent emailIntent = new Intent(
					Intent.ACTION_SEND);
			emailIntent.setType("text/plain");
			emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);

			activity.startActivity(Intent.createChooser(emailIntent, subject));

		}
	}
	
	public static void callPhone(final Activity activity,final String phoneNumber) {

//		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//
//		String phonePackage = ConfigUtility.getPhonePackage(activity, callIntent);
//
//		if (phonePackage != null) {
//			callIntent.setPackage(phonePackage);
//		}
//		if(ActivityCompat.checkSelfPermission(activity,
//				Manifest.permission.CALL_PHONE)
//				!= PackageManager.PERMISSION_GRANTED) {
//			activity.startActivity(callIntent);
//		}

		try {
//			String number = pickContact.getPhone();
			Intent phoneIntent = new Intent(Intent.ACTION_CALL);
			phoneIntent.setData(Uri.parse("tel:" + phoneNumber));
			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
				return;
			}
			activity.startActivity(phoneIntent);

		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(activity,
					"Call failed, please try again later!", Toast.LENGTH_SHORT).show();
		}


//		AppSharePreference appSharePreference = AppSharePreference.getInstance(activity);
//		String userPhoneNumber = appSharePreference.getStringValue(AppSharePreference.SHIPS_PHONE_NUMBER_FROM);
//
//		if(StringUtility.isEmpty(userPhoneNumber)) {
////			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
////
////			String phonePackage = ConfigUtility.getPhonePackage(activity, callIntent);
////
////			if (phonePackage != null) {
////				callIntent.setPackage(phonePackage);
////			}
////			activity.startActivity(callIntent);
//			DialogUtility.showDialogAlert(activity,"","Để sử dụng tính năng này, bạn vui lòng xác thực tài khoản qua tổng đài 19006402","Đóng",null);
//			return;
//		}
//
//		if(checkInstalledApplication(activity, "com.ships.xalo")) {
//			final Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("xalo:"));
//			callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//			callIntent.putExtra("countrycodefrom", "84");
//			callIntent.putExtra("phonenumberfrom", userPhoneNumber);
//			callIntent.putExtra("phonenumberto", phoneNumber);
//			callIntent.putExtra("actiontype", "1");
//			callIntent.putExtra("countrycodefrom", "84");
//			callIntent.putExtra("countrycodeto", "84");
//			callIntent.putExtra("callfromapp", "ShipS");
//			activity.startActivity(callIntent);
//
////			Intent chooser = Intent.createChooser(callIntent, "choose application");
////
////			// Verify the intent will resolve to at least one activity
////			if (callIntent.resolveActivity(activity.getPackageManager()) != null) {
////				activity.startActivity(chooser);
////			}
//		} else {
//			DialogUtility.showDialogPick(activity, "", "ShipS mời bạn cài Xalo để liên lạc gọi/sms giữa Shop và Shipper là hoàn toàn miễn phí", "Hủy", "Đồng ý", new ConfirmListener() {
//				@Override
//				public void doAccept() {
//					Intent intent = new Intent(Intent.ACTION_VIEW);
//					intent.setData(Uri.parse("market://details?id=com.ships.xalo"));
//					activity.startActivity(intent);
//				}
//
//				@Override
//				public void doCancel() {
//					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//
//					String phonePackage = ConfigUtility.getPhonePackage(activity, callIntent);
//
//					if (phonePackage != null) {
//						callIntent.setPackage(phonePackage);
//					}
//					activity.startActivity(callIntent);
//				}
//			});
//		}
		

	}
	
	public static void sendSMS(final Activity activity,final  String sms,final String phoneNumber) {
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		sendIntent.putExtra("sms_body", sms);
		sendIntent.putExtra("address", phoneNumber);
		sendIntent.setType("vnd.android-dir/mms-sms");
		activity.startActivity(sendIntent);

//		AppSharePreference appSharePreference = AppSharePreference.getInstance(activity);
//		String userPhoneNumber = appSharePreference.getStringValue(AppSharePreference.SHIPS_PHONE_NUMBER_FROM);
//
//		if(StringUtility.isEmpty(userPhoneNumber)) {
//			Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//			sendIntent.putExtra("sms_body", sms);
//			sendIntent.putExtra("address", phoneNumber);
//			sendIntent.setType("vnd.android-dir/mms-sms");
//			activity.startActivity(sendIntent);
//			return;
//		}
//
//		if(checkInstalledApplication(activity, "com.ships.xalo")) {
//			final Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("xalo:"));
//			callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//			callIntent.putExtra("countrycodefrom", "84");
//
//			callIntent.putExtra("phonenumberfrom", userPhoneNumber);
//			callIntent.putExtra("phonenumberto", phoneNumber);
//			callIntent.putExtra("actiontype", "0");
//			callIntent.putExtra("countrycodefrom", "84");
//			callIntent.putExtra("countrycodeto", "84");
//			callIntent.putExtra("callfromapp", "ShipS");
//			activity.startActivity(callIntent);
//
////			Intent chooser = Intent.createChooser(callIntent, "choose application");
////
////			// Verify the intent will resolve to at least one activity
////			if (callIntent.resolveActivity(activity.getPackageManager()) != null) {
////				activity.startActivity(chooser);
////			}
//		} else {
//			DialogUtility.showDialogPick(activity, "", "ShipS mời bạn cài Xalo để liên lạc gọi/sms giữa Shop và Shipper là hoàn toàn miễn phí", "Hủy", "Đồng ý", new ConfirmListener() {
//				@Override
//				public void doAccept() {
//					Intent intent = new Intent(Intent.ACTION_VIEW);
//					intent.setData(Uri.parse("market://details?id=com.ships.xalo"));
//					activity.startActivity(intent);
//				}
//
//				@Override
//				public void doCancel() {
//					Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//					sendIntent.putExtra("sms_body", sms);
//					sendIntent.putExtra("address", phoneNumber);
//					sendIntent.setType("vnd.android-dir/mms-sms");
//					activity.startActivity(sendIntent);
//				}
//			});
//		}


	}

	public static void pushNotifycation(Context context, String msg, String alertMessage) {

		Gson gson = new GsonBuilder().setDateFormat(GlobalInfo.ServerConfig.DATE_FORMAT).create();
		NotifyModel notifyModel = gson.fromJson(msg, NotifyModel.class);
//		try {
//			shipInbox.bindDataFromJson(new JSONObject(msg));
//			shipInbox.setAlertMessage(alertMessage);
//			shipInbox.setMessage(message);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}

		Intent resultIntent = new Intent(context, MainHomeActivity.class);
		resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		if (notifyModel != null) {
			resultIntent.putExtra("push_message", notifyModel);
		}
		PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
				PendingIntent.FLAG_ONE_SHOT);

		String contentTitle = context.getResources().getString(R.string.contentTitleDefault);
		String contentText = context.getResources().getString(R.string.contentTextDefault);

		if (notifyModel != null) {
			if (notifyModel.getAction() != null) {
				// contentTitle = shipInbox.getAction();
//				if (shipInbox.getAction().equals("ENROLL")) {
//					contentTitle = "Nhận mua hộ";
//				}
			}
			if (!StringUtility.isEmpty(alertMessage)) {
				contentText = alertMessage;
			}

		}

		pushNotifycation(context, resultPendingIntent, contentTitle, contentText);

	}
	public static final int notifyID = 9001;

	public static void pushNotifycation(Context context, PendingIntent resultPendingIntent, String contentTitle, String contentText) {
		NotificationCompat.Builder mNotifyBuilder;
		NotificationManager mNotificationManager;

		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		mNotifyBuilder = new NotificationCompat.Builder(context).setContentTitle(contentTitle).setContentText(contentText)
				.setSmallIcon(R.drawable.logo);
		// Set pending intent
		mNotifyBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(contentText));
		mNotifyBuilder.setContentIntent(resultPendingIntent);

		// Set Vibrate, Sound and Light
		int defaults = 0;
		defaults = defaults | Notification.DEFAULT_LIGHTS;
		defaults = defaults | Notification.DEFAULT_VIBRATE;
		defaults = defaults | Notification.DEFAULT_SOUND;

		mNotifyBuilder.setSound(GlobalValue.ringToneUri != null ? GlobalValue.ringToneUri : RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

		mNotifyBuilder.setDefaults(defaults);
		// Set the content for Notification
		// mNotifyBuilder.setContentText("" + msg);
		// Set autocancel
		mNotifyBuilder.setAutoCancel(true);

		// mNotifyBuilder.setCategory(Notification.CA);

		// Intent i = new Intent("Click");
		// PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
		// mNotifyBuilder.contentIntent = pi;
		// Post a notification

		mNotificationManager.notify(notifyID, mNotifyBuilder.build());
	}

	public static void pushNotifycation(Context context, String message, boolean isVibrate) {
		NotificationCompat.Builder mNotifyBuilder;
		NotificationManager mNotificationManager;

		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		mNotifyBuilder = new NotificationCompat.Builder(context).setContentTitle("Cảnh báo").setContentText(message)
				.setSmallIcon(R.drawable.logo);
		// Set pending intent
		mNotifyBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));

		// Set Vibrate, Sound and Light
		int defaults = 0;
		defaults = defaults | Notification.DEFAULT_LIGHTS;
		if(isVibrate) {
			defaults = defaults | Notification.DEFAULT_VIBRATE;
		}
		defaults = defaults | Notification.DEFAULT_SOUND;

		mNotifyBuilder.setSound(GlobalValue.ringToneUri != null ? GlobalValue.ringToneUri : RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

		mNotifyBuilder.setDefaults(defaults);
		// Set the content for Notification
		// mNotifyBuilder.setContentText("" + msg);
		// Set autocancel
		mNotifyBuilder.setAutoCancel(true);

		// mNotifyBuilder.setCategory(Notification.CA);

		// Intent i = new Intent("Click");
		// PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
		// mNotifyBuilder.contentIntent = pi;
		// Post a notification

		mNotificationManager.notify(notifyID, mNotifyBuilder.build());
	}


}
