package com.example.michal.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        ButterKnife.bind(this);

    }

    public void incrementQuantity(View view) {

        quantity = quantity + 1;
        //displayQuantity(quantity);
        quantityTextView.setText("" + quantity);
    }

    public void decrementQuantity(View view) {

        quantity = quantity - 1;
        if (quantity < 0) quantity = 0;
        //displayQuantity(quantity);
        quantityTextView.setText("" + quantity);
    }

    public void incrementItemPrice(View view) {

        itemPrice = itemPrice + 1;
        itemPriceTextView.setText("" + itemPrice);
        // displayItemPrice(itemPrice);
    }

    public void decrementItemPrice(View view) {

        itemPrice = itemPrice - 1;
        if (itemPrice <= 0) itemPrice = 0;
        itemPriceTextView.setText("" + itemPrice);
        //displayItemPrice(itemPrice);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_cream);
        boolean hasWhippedCream = checkBox1.isChecked();

        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox_chocolate);
       // boolean hasChocolate = checkBox2.isChecked();
        String addChocolate;
        if (checkBox2.isChecked()) {
            addChocolate = "yes";

        } else {
            addChocolate = "no";
        }

        int price = calculatePrice();
        orderSummaryTextView.setText(createOrderSummary(price, hasWhippedCream, addChocolate));
        //displayMessage(createOrderSummary(price));
        Log.v("Main activity", "Zrzut z loga z wybraną opcja kawy  " + hasWhippedCream);
        Log.v("Main activity", "Zrzut z loga z wybraną opcja czekolada  " + addChocolate);

    }

    private int calculatePrice() {
        return quantity * itemPrice;

    }


    private String createOrderSummary(int price, boolean checkWhippedCream, String checkChocolate) {
        String priceMessage = "Name: Kapitan Kunal";
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
        itemPriceTextView.setText("" + number);
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
