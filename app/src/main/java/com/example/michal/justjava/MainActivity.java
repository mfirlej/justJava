package com.example.michal.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int itemPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementQuantity(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrementQuantity(View view) {

        quantity = quantity - 1;
        if (quantity < 0) quantity = 0;
        displayQuantity(quantity);
    }

    public void incrementItemPrice(View view) {

        itemPrice = itemPrice + 1;
        displayItemPrice(itemPrice);
    }

    public void decrementItemPrice(View view) {

        itemPrice = itemPrice - 1;
        if (itemPrice <= 0) itemPrice = 0;
        displayItemPrice(itemPrice);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = "Free";
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayItemPrice(int number) {
        TextView itemPriceTextView = (TextView) findViewById(R.id.itemPrice_text_view);
        itemPriceTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayTotalPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);

    }
}
