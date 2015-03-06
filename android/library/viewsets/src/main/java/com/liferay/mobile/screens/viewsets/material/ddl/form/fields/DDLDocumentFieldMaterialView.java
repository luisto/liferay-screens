/*
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.screens.viewsets.material.ddl.form.fields;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.liferay.mobile.screens.viewsets.R;
import com.liferay.mobile.screens.viewsets.defaultviews.ddl.form.fields.DDLDocumentFieldView;

/**
 * @author Javier Gamarra
 */
public class DDLDocumentFieldMaterialView extends DDLDocumentFieldView {

	public DDLDocumentFieldMaterialView(Context context) {
		super(context);
	}

	public DDLDocumentFieldMaterialView(Context context, AttributeSet attributes) {
		super(context, attributes);
	}

	public DDLDocumentFieldMaterialView(Context context, AttributeSet attributes, int defaultStyle) {
		super(context, attributes, defaultStyle);
	}

	@Override
	public void refresh() {
		getTextEditText().setText(getField().toFormattedString());
		if (getField().isUploaded()) {
			getTextEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done, 0);
			_progressBar.setVisibility(View.GONE);
		} else if (getField().isUploadFailed()) {
			getTextEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_highlight_remove, 0);
			_progressBar.setVisibility(View.GONE);
		} else if (getField().isUploading()) {
			getTextEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			_progressBar.setVisibility(View.VISIBLE);
		} else {
			getTextEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_perm_media, 0);
			_progressBar.setVisibility(View.GONE);
		}
	}

}