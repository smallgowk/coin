package com.phanduy.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.phanduy.GlobalInfo;
import com.phanduy.interfaces.ConfirmListener;
import com.phanduy.interfaces.DialogListener;
import com.phanduy.interfaces.InputDialogListener;
import com.phanduy.coinhub.R;

public class DialogUtility {

	public static void showDialogConfirm(Activity activity, String message,
			final ConfirmListener confirmListener) {
		Context context = null;

		if(activity != null && !activity.isFinishing()) {
			context = activity;
		} else {
			context = GlobalInfo
					.getInstance().getAppContext();

		}

		new MaterialDialog.Builder(context)
				.title(R.string.APP_NAME)
				.content(message)
				.positiveText(R.string.labelConfirmLower)
				.negativeText(R.string.labelCancel)
				.negativeColor(context.getResources().getColor(R.color.black54))
				.onPositive(new MaterialDialog.SingleButtonCallback() {
					@Override
					public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

						confirmListener.doAccept();
					}
				})
				.positiveColor(context.getResources().getColor(R.color.main_green))

				.show();
		
	}
	public static void showDialogPick(Activity activity,String title, String message, String pickLeft, String pickRight,
			final ConfirmListener confirmListener) {
		if(activity != null && !activity.isFinishing()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			
			builder.setTitle(title);
			// builder.setIcon(R.drawable.ic_launcher);
			builder.setMessage(message);
			builder.setCancelable(true);
			builder.setNegativeButton(pickLeft, new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					confirmListener.doCancel();
				}
			});
			
			builder.setPositiveButton(pickRight, new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					confirmListener.doAccept();
				}
			});
			AlertDialog alert = builder.create();
			alert.setCancelable(true);
			alert.show();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(GlobalInfo
					.getInstance().getAppContext());
			
			builder.setTitle(title);
			// builder.setIcon(R.drawable.ic_launcher);
			builder.setMessage(message);
			builder.setCancelable(true);
			builder.setNegativeButton(pickLeft, new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					confirmListener.doCancel();
				}
			});
			
			builder.setPositiveButton(pickRight, new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					confirmListener.doAccept();
				}
			});
			AlertDialog alert = builder.create();
			alert.setCancelable(true);
			alert.show();
		}
		
	}

	public static void showDialogAlert(Activity activity, String tittle, String message,
			String buttonLabel, final DialogListener dialogListener) {
		
		if(activity != null && !activity.isFinishing()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			if (tittle == null || tittle.length() == 0) {
				builder.setTitle(activity
						.getResources().getString(R.string.APP_NAME));
			} else {
				builder.setTitle(tittle);
			}
			builder.setMessage(message);
			builder.setCancelable(false);
			// builder.setIcon(R.drawable.icon);
			builder.setPositiveButton(activity.getResources().getString(R.string.labelClose),
					new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (dialogListener != null)
								dialogListener.after();
						}
					});

			builder.show();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(GlobalInfo
					.getInstance().getAppContext());
			if (tittle == null || tittle.length() == 0) {
				builder.setTitle(GlobalInfo.getInstance().getAppContext()
						.getResources().getString(R.string.APP_NAME));
			} else {
				builder.setTitle(tittle);
			}
			builder.setMessage(message);
			builder.setCancelable(false);
			// builder.setIcon(R.drawable.icon);
			builder.setPositiveButton(activity.getResources().getString(R.string.labelClose),
					new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (dialogListener != null)
								dialogListener.after();
						}
					});
			
			builder.show();
		}
	}

	public static void showInputDialog(Activity activity, String initText, final InputDialogListener inputDialogListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Title");

// Set up the input
		final EditText input = new EditText(activity);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		input.setText(initText);
		input.setSelection(initText.length());
		builder.setView(input);

// Set up the buttons
		builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				inputDialogListener.onChange(input.getText().toString());
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		builder.show();

	}
}
