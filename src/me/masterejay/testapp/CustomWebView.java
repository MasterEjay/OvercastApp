package me.masterejay.testapp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import me.masterejzz.testapp.R;

/**
 * @author MasterEjay
 */
public class CustomWebView extends WebView{
		public CustomWebView(Context context)
		{
			this(context, null);
		}

		public CustomWebView(Context context, AttributeSet attrs)
		{
			this(context, attrs, 0);
		}

		public CustomWebView(Context context, AttributeSet attrs, int defStyle)
		{
			super(context, attrs, defStyle);
		}

		public OnBottomReachedListener mOnBottomReachedListener = null;
		private int mMinDistance = 0;

		/**
		 * Set the listener which will be called when the WebView is scrolled to within some
		 * margin of the bottom.
		 * @param bottomReachedListener
		 * @param allowedDifference
		 */
		public void setOnBottomReachedListener(OnBottomReachedListener bottomReachedListener, int allowedDifference ) {
			mOnBottomReachedListener = bottomReachedListener;
			mMinDistance = allowedDifference;
		}

		/**
		 * Implement this interface if you want to be notified when the WebView has scrolled to the bottom.
		 */
		public interface OnBottomReachedListener {
			void onBottomReached(View v);
		}

		@Override
		protected void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
			if ( mOnBottomReachedListener != null ) {
				if ( (getContentHeight() - (top + getHeight())) <= mMinDistance )
					mOnBottomReachedListener.onBottomReached(this);
			}
			super.onScrollChanged(left, top, oldLeft, oldTop);
		}

	}

