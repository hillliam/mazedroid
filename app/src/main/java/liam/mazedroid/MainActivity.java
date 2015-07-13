package liam.mazedroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void compleatableclick(View v)
    {
        setContentView(new gameview(this,1));
    }
    public void randomclick(View v)
    {
        setContentView(new gameview(this,2));
    }
    public void loadmazeclick(View v)
    {
        setContentView(new gameview(this,3));
    }
    public void presetclick(View v)
    {
        setContentView(new gameview(this,4));
    }
    public void editclick(View v)
    {
        setContentView(new editview(this));
    }
}
