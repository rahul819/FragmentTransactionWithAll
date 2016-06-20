package first.quizemaine.com.fragmenttransaction;

import android.app.Fragment;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity implements android.app.FragmentManager.OnBackStackChangedListener{


    LinearLayout groupLayout;

    String TAG = "MainActiviy";
    String FRAGMENT_TAG = "fragment_tag";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupLayout = (LinearLayout) findViewById(R.id.group_id);

        android.app.FragmentManager fm=getFragmentManager();
        fm.addOnBackStackChangedListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void onClickButtonAdd(View view) {

        android.app.FragmentManager fm = getFragmentManager();

        FragmentFirst first_fragment = new FragmentFirst();

        android.app.FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.group_id, first_fragment, FRAGMENT_TAG);
        ft.addToBackStack("add");

        ft.commit();


    }

    void onClickRemoveThenAdd(View view) {

        android.app.FragmentManager fm = getFragmentManager();

        FragmentFirst fragmentfirst = (FragmentFirst) fm.findFragmentByTag(FRAGMENT_TAG);

        if (fragmentfirst != null && fragmentfirst.isVisible()) {
            android.app.FragmentTransaction ft = fm.beginTransaction();

            ft.remove(fragmentfirst);

            FragmentSecond fragment_second = new FragmentSecond();

            ft.add(R.id.group_id, fragment_second, FRAGMENT_TAG);
            ft.addToBackStack("remove_add");
            ft.commit();


        }


    }

    void onClickReplaceWithOne(View view) {

        android.app.FragmentManager fm = getFragmentManager();

        android.app.FragmentTransaction ft = fm.beginTransaction();

        FragmentFirst fragment_first = new FragmentFirst();

        ft.replace(R.id.group_id, fragment_first, FRAGMENT_TAG);
        ft.addToBackStack("replace");

        ft.commit();


    }

    void onClickMovePrev(View view) {

        android.app.FragmentManager fm = getFragmentManager();
        fm.popBackStack();


    }

    void onClickAttach(View view) {

        android.app.FragmentManager fm = getFragmentManager();


        Fragment fragment = fm.findFragmentByTag(FRAGMENT_TAG);

        if (fragment != null) {
            android.app.FragmentTransaction ft = fm.beginTransaction();

            ft.attach(fragment);
            ft.addToBackStack("attach");
            ft.commit();


        }


    }

    void onClickDetach(View view) {

        android.app.FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentByTag(FRAGMENT_TAG);

        if (fragment != null) {
            android.app.FragmentTransaction ft = fm.beginTransaction();

            ft.detach(fragment);
            ft.addToBackStack("detch");
            ft.commit();


        }


    }


    void showMessage(String msg)
    {

        Log.d(TAG,msg);
        Toast t=Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT);
        t.show();

    }


    void showBackStackEntry(android.app.FragmentManager.BackStackEntry entry)
    {
        if(entry!=null)
        {
            Log.d(TAG,"BackStack EntryName::"+entry.getName());
        }else {
            Log.d(TAG,"Entry Is null");
        }


    }

    @Override
    public void onBackStackChanged() {

  showMessage("Back Stack Changed");


        android.app.FragmentManager fm=getFragmentManager();

        int totalBackStackCount=fm.getBackStackEntryCount();

        for(int i=0;i<totalBackStackCount;i++)
            showBackStackEntry(fm.getBackStackEntryAt(i));


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://first.quizemaine.com.fragmenttransaction/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://first.quizemaine.com.fragmenttransaction/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
