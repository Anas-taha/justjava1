package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numberOfCofee = 1  ;

    public void submitOrder(View view) {
        EditText name = (EditText)findViewById(R.id.edit_name);
        String Name =  name.getText().toString();
        CheckBox checkBox = findViewById(R.id.checkbox);
        boolean ischecked = checkBox.isChecked();
        CheckBox checkBox2 = findViewById(R.id.checkbox2);
        boolean ischecked2 = checkBox2.isChecked();


        int price = calculatePrice(ischecked,ischecked2);
        displayQuantity(numberOfCofee);
        String summary = createOrderSummary(price,ischecked,ischecked2,Name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "anastaha1234@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.JustJava_order_for) + Name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    public void increment(View view) {
        if (numberOfCofee<100){
        numberOfCofee = numberOfCofee + 1;
        displayQuantity(numberOfCofee);
        }
        else {
        Toast max = Toast.makeText(this,"max number",Toast.LENGTH_SHORT);
        max.show();}
    }
    public void decrement(View view) {
        if (numberOfCofee>1){
        numberOfCofee = numberOfCofee - 1;
        displayQuantity(numberOfCofee);
        }
        else {
        Toast min = Toast.makeText(this,"min number",Toast.LENGTH_SHORT);
        min.show();}
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }





    private int calculatePrice(boolean ischecked, boolean ischecked2){
        int price = 5;
        if(ischecked){
            price +=1;
        }
        if(ischecked2){
            price += 2 ;
        }
        price = price * numberOfCofee;

    return price;
    }


    private String createOrderSummary(int price, boolean ischecked,boolean ischecked2,String Name){

        String message =  getString(R.string.name)+ " : "  + Name;
        message += "\n" + getString(R.string.quantity)+ " : " + numberOfCofee;
        message += "\n" + getString(R.string.whipped_cream) + " : "+ (ischecked? getString(R.string.yes) : getString(R.string.no));
        message += "\n" + getString(R.string.chocolate) + " : " +(ischecked2? getString(R.string.yes) : getString(R.string.no));
        message += "\n" + getString(R.string.price) + " : "+ price + "$";
        message += "\n" + getString(R.string.thanks);
        return message;
    }






}