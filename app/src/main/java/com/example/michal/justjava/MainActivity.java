package com.example.michal.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int itemPrice = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void incrementQuantity(View view) {

        quantity = quantity + 1;
        if (quantity > 100) {
            quantity = 100;
            Toast.makeText(this, "Maximum order 100 cups of coffee.", Toast.LENGTH_SHORT).show();
        }
        quantityTextView.setText("" + quantity);
    }

    public void decrementQuantity(View view) {

        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;

            /**
             * wyglad przykładowego tosta
             *
             * Context context = getApplicationContext();
             CharSequence text = "Order minimum 1 cup of coffee.";
             int duration = Toast.LENGTH_SHORT;
             Toast toast = Toast.makeText(context,text, duration);
             toast.show();
             *
             */

            Toast.makeText(this, "Order minumum 1 cup of coffe", Toast.LENGTH_SHORT).show();
        }
        quantityTextView.setText("" + quantity);

    }

    public void incrementItemPrice(View view) {

        itemPrice = itemPrice + 1;
        itemPriceTextView.setText("" + itemPrice);
        displayItemPrice(itemPrice);
    }

    public void decrementItemPrice(View view) {

        itemPrice = itemPrice - 1;
        if (itemPrice < 1) {itemPrice = 1;

            Toast.makeText(this,"No.. you can't take free coffee", Toast.LENGTH_SHORT).show();

        }
        displayItemPrice(itemPrice);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void whippedCreamToast(View view) {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_cream);
        boolean hasWhippedCream = checkBox1.isChecked();
        if (hasWhippedCream) {
            Toast.makeText(this, "Price of " + quantity + " coffee increased by " + quantity * 1 + " zł.", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Price of " + quantity + " coffee increased by " + quantity * 2 + " zł.", Toast.LENGTH_SHORT).show();
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
      String priceMessage = createOrderSummary(price, name, hasWhippedCream, hasChocolate);

      Intent intent = new Intent(Intent.ACTION_SENDTO);
      intent.setType("*/*");
      intent.setData(Uri.parse("mailto:"));
      intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
      intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
      if (intent.resolveActivity(getPackageManager()) != null) {
          startActivity(intent);
      }
   }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate, int itemPrice) {


        if (hasWhippedCream) itemPrice = itemPrice + 1;
        if (hasChocolate) itemPrice += +2;
        Log.v(String.valueOf(itemPrice), "Aktualna wartosc itemPrice");
        return quantity * itemPrice;
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

    @BindView(R.id.itemPrice_text_view)
    TextView itemPriceTextView;


    private void displayItemPrice(int number) {
        TextView itemPriceTextView = (TextView) findViewById(R.id.itemPrice_text_view);
        itemPriceTextView.setText("" + number + " zł");
    }
}
