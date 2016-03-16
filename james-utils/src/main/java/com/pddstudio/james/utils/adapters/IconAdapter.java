package com.pddstudio.james.utils.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.view.IconicsImageView;
import com.pddstudio.james.utils.R;

/**
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class IconAdapter extends BaseAdapter {


    private Context context;
    private CommunityMaterial.Icon[] itemList;
    @ColorInt
    private int iconColor = Color.BLACK;

    public IconAdapter(Context mContext) {
        context = mContext;
        itemList = CommunityMaterial.Icon.values();
    }

    public IconAdapter withColoredIcons(@ColorInt int color) {
        this.iconColor = color;
        return this;
    }

    public IconAdapter withColoredIcons(String hexValue) {
        this.iconColor = Color.parseColor(hexValue);
        return this;
    }

    @Override
    public int getCount() {
        return itemList.length;
    }

    @Override
    public CommunityMaterial.Icon getItem(int position) {
        if(position > 0 && position < itemList.length) {
            return itemList[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = View.inflate(context, R.layout.item_icon, null);
        IconicsImageView icon = (IconicsImageView) convertView.findViewById(R.id.icon_list_icon);
        icon.setColor(iconColor);
        icon.setIcon(itemList[position]);
        return convertView;
    }

}