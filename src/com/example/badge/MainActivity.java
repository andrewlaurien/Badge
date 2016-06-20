package com.example.badge;

import android.app.Activity;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	  private int mNotificationsCount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 //new FetchCountTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.main, menu);

	        // Get the notifications MenuItem and 
	        // its LayerDrawable (layer-list)
	        MenuItem item = menu.findItem(R.id.action_notifications);
	        LayerDrawable icon = (LayerDrawable) item.getIcon();

	        // Update LayerDrawable's BadgeDrawable
	        Utils.setBadgeCount(this, icon, mNotificationsCount);

	        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_notifications) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	class FetchCountTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            // example count. This is where you'd 
            // query your data store for the actual count.
            return 5; 
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }
	
	 private void updateNotificationsBadge(int count) {
	        mNotificationsCount = count;

	        // force the ActionBar to relayout its MenuItems.
	        // onCreateOptionsMenu(Menu) will be called again.
	        invalidateOptionsMenu(); 
	    }

}
