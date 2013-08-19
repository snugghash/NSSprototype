package com.example.contactslist;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorTreeAdapter;

public class CustomContactsAdapter extends SimpleCursorTreeAdapter{

	public CustomContactsAdapter(Context context, Cursor cursor,
			int collapsedGroupLayout, int expandedGroupLayout,
			String[] groupFrom, int[] groupTo, int childLayout,
			int lastChildLayout, String[] childFrom, int[] childTo) {
		super(context, cursor, collapsedGroupLayout, expandedGroupLayout, groupFrom,
				groupTo, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		// TODO Auto-generated method stub
		return null;
	}

}
