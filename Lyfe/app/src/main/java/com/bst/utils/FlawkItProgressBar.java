package com.bst.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.bst.Lyfe.R;


public class FlawkItProgressBar {

    private ProgressDialog pDialog;
    private static FlawkItProgressBar m_instance = null;


    public FlawkItProgressBar() {
    }


    public static synchronized FlawkItProgressBar getInstance() {
        if (m_instance == null) {
            m_instance = new FlawkItProgressBar();
        }
        return m_instance;
    }


    public void showProgressBar(Context mContext) {
        try {
            /*if(mContext==null)
            {
				pDialog = new ProgressDialog(AppMainTabActivity.mActivity);
			}
			else {
				pDialog = new ProgressDialog(mContext);
			}*/
            pDialog = new ProgressDialog(mContext);
            //pDialog.setMessage(mContext.getResources().getString(R.string.please_wait));
            pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            if (pDialog != null && !pDialog.isShowing()) {
                pDialog.show();
            }
            pDialog.setCancelable(false);
            View view = LayoutInflater.from(mContext).inflate(R.layout.progressbar, null);
            LoadingImageView loadingImageView = (LoadingImageView) view.findViewById(R.id.loadingiv);
            loadingImageView.setMaskColor(mContext.getResources().getColor(R.color.colorPrimary));
            loadingImageView.setMaskOrientation(1);
            pDialog.setContentView(view);
        } catch (Exception e) {
            //IresLog.e(null, "Progress Dialog Initialization Exception", e);
        }
    }

    public void hideProgressBar(Context mContext) {
        if (pDialog != null && pDialog.isShowing()) {
            try {
                pDialog.dismiss();
            } catch (Exception e) {
                //	IresLog.e(null, "Progress Dialog Dismis Exception", e);
            }

        }
    }

}
