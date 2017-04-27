package com.example.michal.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int itemPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void incrementQuantity(View view) {

        quantity = quantity + 1;
        //displayQuantity(quantity);
        quantityTextView.setText("" + quantity);
    }

    public void decrementQuantity(View view) {

        quantity = quantity - 1;
        if (quantity < 1) quantity = 1;
        //displayQuantity(quantity);
        quantityTextView.setText("" + quantity);
    }

    public void incrementItemPrice(View view) {

        itemPrice = itemPrice + 1;
        itemPriceTextView.setText("" + itemPrice);
        displayItemPrice(itemPrice);



        /*Context context = getApplicationContext();
        CharSequence text = "This feature will be added soon!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
    }

    public void decrementItemPrice(View view) {
        // int itemPrice = 0;

        itemPrice = itemPrice - 1;
        if (itemPrice <= 0) itemPrice = 0;
        //itemPriceTextView.setText("" + itemPrice);
        displayItemPrice(itemPrice);


       /* Context context = getApplicationContext();
        CharSequence text = "This feature will be added soon!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
    }


    /**
     * This method is called when the order button is clicked.
     */

    public void whippedCreamToast(View view) {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_cream);
        boolean hasWhippedCream = checkBox1.isChecked();
        if (hasWhippedCream) {
            Context context = getApplicationContext();
            CharSequence text = "Price of " + quantity + " coffee increased by " + quantity * 1 + " zł.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            /**
             * można dodać nowe pole txt które wyświetli aktualną cenę kawy z dodatkami lub bez
             * do tego drugi pole txt w którm wpiszemy cenę samej kawy
             *displayItemPrice(itemPrice + 1);
             **/
        }
    }

    public void chocolateToast(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        boolean hasChocolate = checkBox.isChecked();

        if (hasChocolate) {
            Context context = getApplicationContext();
            CharSequence text = "Price of " + quantity + " coffee increased by " + quantity * 2 + " zł.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void submitOrder(View view) {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_cream);
        boolean hasWhippedCream = checkBox1.isChecked();

        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox_chocolate);
        boolean hasChocolate = checkBox2.isChecked();

        EditText textField = (EditText) findViewById(R.id.name_text_field);
        String name = textField.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate, itemPrice);
        orderSummaryTextView.setText(createOrderSummary(price, name, hasWhippedCream, hasChocolate));
        //displayMessage(createOrderSummary(price));

    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate, int itemPrice) {


        if (hasWhippedCream) itemPrice = itemPrice + 1;
        if (hasChocolate) itemPrice += +2;
        Log.v(String.valueOf(itemPrice), "Aktualna wartosc itemPrice");
        return quantity * itemPrice;



        /*int basePrice = 5;

        if (hasWhippedCream) basePrice = basePrice + 1;
        if (hasChocolate) basePrice = basePrice + 2;


        return quantity * basePrice;*/


    }

    private String createOrderSummary(int price, String name, boolean checkWhippedCream, boolean checkChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + " - " + checkWhippedCream;
        priceMessage += "\nAdd chocolate? " + " - " + checkChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal " + price + " zł";
        priceMessage += "\nThank you";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    @BindView(R.id.quantity_text_view)
    TextView quantityTextView;

    /*
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }*/

    @BindView(R.id.itemPrice_text_view)
    TextView itemPriceTextView;


    private void displayItemPrice(int number) {
        TextView itemPriceTextView = (TextView) findViewById(R.id.itemPrice_text_view);
        itemPriceTextView.setText("" + number + " zł");
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayTotalPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    @BindView(R.id.order_summary_text_view)
    TextView orderSummaryTextView;



/*

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
        orderSummaryTextView.setVisibility(message);
    }

    */


}
