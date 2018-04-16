package nytimes.chernousovaya.com.nytimes.controller.drawer;

import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;


public class DrawerItem extends PrimaryDrawerItem {

    private Class mActivityClass;

    public DrawerItem(Class activityClass) {
        mActivityClass = activityClass;
    }

    public Class getmActivityClass() {
        return mActivityClass;
    }
}

