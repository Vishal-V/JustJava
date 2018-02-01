
/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int num = 1;

    public void submitOrder(View view) {

        String msg = "Total : $" + num*5 + "\nThank You!";
        displayMessage(msg);
    }

    public void displayMessage(String msg) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(msg);
    }

    public void increment(View view) {
        num++;
        display(num);
        String msg = "Total : $" + num*5 + "\nThank You!";
        displayMessage(msg);
    }

    public void decrement(View view) {
        if(num > 0){
            num--;
        }
        display(num);
        String msg = "Total : $" + num*5 + "\n\nThank You!";
        displayMessage(msg);
    }

    private void display(int number) {
        if(number >= 0) {
            TextView quantityEditText = (TextView) findViewById(R.id.quantity_text_view);
            quantityEditText.setText("" + number);
        }
        else {
            Toast.makeText(getApplicationContext(), "You cannot have negative number of coffees!", Toast.LENGTH_LONG).show();
        }
    }
}
