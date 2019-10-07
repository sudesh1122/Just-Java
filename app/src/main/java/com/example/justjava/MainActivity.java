package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


    int noc = 1;


    public void increment(View view) {

        if (noc == 10) {
            Toast.makeText(this, "You cannot have more than ten coffee ", Toast.LENGTH_LONG).show();
            display(noc);
        } else {
            noc++;
            display(noc);

        }
    }

    public void decrement(View view) {

        noc--;
        if (noc < 1) {
            Toast.makeText(this, "You cannot have less than one coffee ", Toast.LENGTH_LONG).show();
            noc = 1;
            display(noc);
        } else {
            display(noc);
        }

    }


    public void submitOrder(View view) {

        String k = summary();
        displayPrice(k);

        String email = "sudeshchaudhary1122@gmail.com";
        String subject = "Just Java order for " + name;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, k);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */


    private void displayPrice(String s) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("" + s);
    }

    /**
     * This method displays the summary of the order om the screen.
     **/
    public String summary() {
        int tp = noc * 5;
        if (checked1) tp = (noc) * 7;                     /** price of cheese is 2$**/
        if (checked2) tp = (noc) * 6;                   /** price of cheese is 1$**/
        if (checked1 && checked2) {
            tp = noc * 8;
        }

        EditText editName = (EditText) findViewById(R.id.edit_name);
        name = editName.getText().toString();
        EditText editMobileNo = (EditText) findViewById(R.id.edit_phone);
        phone = editMobileNo.getText().toString();


        String message = getString(R.string._name) + " : " + name + "\n" + getString(R.string.mobile_no) + " : " + phone + "\n" + getString(R.string.is_cheese_checked)
                + " ? " + checked1 + "\n" + getString(R.string.is_whipped_cream_checked) + " ? " + checked2 + "\n" + getString(R.string.quantity) + " : " + noc + "\n" + getString(R.string.total)
                + " : " + tp + "\n" + getString(R.string.thank_you) + " !!";
        return message;

    }

    String name, phone;


    boolean checked1;
    boolean checked2;

    public void checkBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.check_cheese:
                if (checked) checked1 = checked;
                else checked1 = false;
                break;
            case R.id.check_whippedCream:
                if (checked) checked2 = checked;
                else checked2 = checked;
                break;
        }


    }
}