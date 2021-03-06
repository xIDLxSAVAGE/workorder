/**
 http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/
 * */
package edu.csub.cs.WorkOrderApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import edu.csub.cs.WorkOrderApp.R;
import edu.csub.cs.WorkOrderApp.helper.SQLiteHandler;
import edu.csub.cs.WorkOrderApp.helper.SessionManager;

public class MainActivity extends AppCompatActivity {

	// Testing push on a mac

	private TextView txtName;
	private Button btnLogout;
    private Button btnNewWorkorder;
	private Button btnCompleteOrder;
	public String eid;
	private SQLiteHandler db;
	private SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.cogs_icon);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_main);

		txtName = (TextView) findViewById(R.id.name);
		btnLogout = (Button) findViewById(R.id.btnLogout);
        btnNewWorkorder = (Button) findViewById(R.id.btnNewWorkOrder);
		btnCompleteOrder = (Button) findViewById(R.id.btnComplete);

		// SqLite database handler
		db = new SQLiteHandler(getApplicationContext());

		// session manager
		session = new SessionManager(getApplicationContext());

		if (!session.isLoggedIn()) {
			logoutUser();
		}

		// Fetching user details from SQLite
		HashMap<String, String> user = db.getUserDetails();

		String name = user.get("name");
		eid = user.get("uid");

		// Displaying the user details on the screen
		txtName.setText(name);

		// Logout button click event
		btnLogout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				logoutUser();
			}
		});
		btnNewWorkorder.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(),
                            NewWOActivity.class);
                    startActivity(i);
                    //finish();
                }
            });

		//Mark Order Complete
		btnCompleteOrder.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						OrderComplete.class);
				startActivity(i);
				//finish();
			}
		});
	}

	/**
	 * Logging out the user. Will set isLoggedIn flag to false in shared
	 * preferences Clears the user data from sqlite users table
	 * */
	private void logoutUser() {
		session.setLogin(false);

		db.deleteUsers();

		// Launching the login activity
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
