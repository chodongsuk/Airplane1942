package com.kr.airplane1942;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 종료 시 팝업 관련
 * 티스토어 및 마켓 구분 onclick 리스너 수정
 */
@SuppressLint("ValidFragment")
public class MainStoreTypeDialog extends DialogFragment implements OnClickListener{
	private Button mButton1, mButton2, mButton3, mButton4, mButton5;
	private TextView mTextView1, mTextView2, mTextView3, mTextView4;


	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	public MainStoreTypeDialog() {
		// TODO Auto-generated constructor stub
	}

	private DialogResultListner mDialogResultListner;
	public interface DialogResultListner {
		public void onCancel();
	}
	public MainStoreTypeDialog callback (DialogResultListner dialogresultlistner){
		this.mDialogResultListner = dialogresultlistner;
		return this;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
		View view = mLayoutInflater.inflate(R.layout.dialog, null);
		(mTextView1 = (TextView)view.findViewById(R.id.textView1)).setOnClickListener(this);
		(mTextView2 = (TextView)view.findViewById(R.id.textView2)).setOnClickListener(this);
		(mTextView3 = (TextView)view.findViewById(R.id.textView3)).setOnClickListener(this);
		(mTextView4 = (TextView)view.findViewById(R.id.textView4)).setOnClickListener(this);

		mBuilder.setView(view);
		return mBuilder.create();
	}

	private void MarketLink() {

		try {
			startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("market://details?id=com.kr.airplane1942")));
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getActivity(), "Please use after installation", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.textView1: //리뷰 달기
				try {
					Intent NextIntent = new Intent(Intent.ACTION_SEND);
					NextIntent.setType("text/plain");
					NextIntent.putExtra(Intent.EXTRA_SUBJECT, getActivity().getString(R.string.app_name));
					NextIntent.putExtra(Intent.EXTRA_TEXT, "It's nice to meet you.\n\n"+"Download:\n" + "https://play.google.com/store/apps/details?id=kr.ds.airplane1942");
					startActivity(Intent.createChooser(NextIntent, getActivity().getString(R.string.app_name) + "Share"));
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getActivity(), "Please contact the administrator.", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.textView2:
				MarketLink();
				break;
			case R.id.textView3:
				dismiss();
				break;
			case R.id.textView4:
				getActivity().finish();
				break;
			default:
				break;
		}
	}
}
