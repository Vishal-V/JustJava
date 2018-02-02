
/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
        int price = calculatePrice();
        EditText text1 = (EditText) findViewById(R.id.editText);
        String name = text1.getText().toString();
        CheckBox state1 = (CheckBox) findViewById(R.id.checkBox);
        boolean cream = state1.isChecked();
        CheckBox state2 = (CheckBox) findViewById(R.id.checkBox2);
        boolean choc = state2.isChecked();
        String msg = createOrderSummary(price, cream, choc, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, ("JustJava order for "+name));
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrice() {
        return (num*5);
    }

    private String createOrderSummary(int price, boolean cream, boolean choc, String name) {
        String message;
        if (cream && choc) {
            price += 3;
            message = "Name: " + name + "\nAdd Whipped Cream? true\nAdd Chocolate Sauce? true\nQuantity: " + num + "\nTotal: $" + price + "\nThank you!";
        } else {
            if (cream == true && choc == false) {
                price += 1;
                message = "Name: " + name + "\nAdd Whipped Cream? true\nAdd Chocolate Sauce? false\nQuantity: " + num + "\nTotal: $" + price + "\nThank you!";
            } else if (cream == false && choc == true) {
                price += 2;
                message = "Name: " + name + "\nAdd Whipped Cream? false\nAdd Chocolate Sauce? true\nQuantity: " + num + "\nTotal: $" + price + "\nThank you!";
            } else {
                message = "Name: " + name + "\nAdd Whipped Cream? false\nAdd Chocolate Sauce? false\nQuantity: " + num + "\nTotal: $" + price + "\nThank you!";
            }
        }
        return message;
    }

    public void displayMessage(String msg) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(msg);
    }

    public void increment(View view) {
        if(num < 99) {
            num++;
        }
        else {
            Toast.makeText(getApplicationContext(), "You cannot order for more than 100 cups of coffee!", Toast.LENGTH_LONG).show();
        }
        display(num);
    }

    public void decrement(View view) {
        if (num > 1) {
            num--;
        }
        if (num == 1) {

            Toast.makeText(getApplicationContext(), "You cannot have less than 1 cup of coffee!", Toast.LENGTH_LONG).show();
        }
        display(num);
    }

    private void display(int number) {
        if(number >= 1) {
            TextView quantityEditText = (TextView) findViewById(R.id.quantity_text_view);
            quantityEditText.setText("" + number);
        }

    }
}
