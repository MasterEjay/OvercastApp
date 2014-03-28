package me.masterejay.testapp.model;

import android.view.View;

/**
 * @author MasterEjay
 */
public class ViewItem implements IListItem{

	private boolean mClickable = true;
	private View mView;

	public ViewItem(View view) {
		this.mView = view;
	}

	public View getView() {
		return this.mView;
	}

	@Override
	public boolean isClickable() {
		return mClickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		mClickable = clickable;
	}
}
