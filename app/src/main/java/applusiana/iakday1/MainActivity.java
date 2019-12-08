package applusiana.iakday1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.support.v4.os.LocaleListCompat.create;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    TextView tvquantity;
    TextView txtotal;
    CheckBox cbCream;
    CheckBox cbChoco;
    CheckBox cbSugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
       display(quantity);
    }

    public void increment(View view) {
        if (quantity == 10) {
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }
    public void display (int quantity) {
       // TextView quantityTxt = findViewById(R.id.quantity);
        tvquantity = findViewById(R.id.quantity);
        tvquantity.setText(String.valueOf(quantity));
    }
    public void submit (View view){

        EditText namaTxt = findViewById(R.id.txtNama);
        String name = namaTxt.getText().toString();

        cbCream = findViewById
                (R.id.whipped_cream_checkbox);
        cbChoco = findViewById
                (R.id.chocolate_checkbox);
        cbSugar = findViewById
                (R.id.sugar_checkbox);

        boolean cream = cbCream.isChecked();
        boolean choco = cbChoco.isChecked();
        boolean sugar = cbSugar.isChecked();

        int total = hitung(name, cream, choco, sugar);
        String pesan = nota(name, total, cream, choco, sugar);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mail to : "));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Nota Pembelian");
        intent.putExtra(Intent.EXTRA_TEXT, pesan);

        if (intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
        alert(pesan);

        //TextView totalTxt = findViewById(R.id.total);
        //totalTxt.setText("" + total);
        //alert("Nama : " + nama + "\nTopping : " + message + "\nTotal : $" + total);
    }

    private int hitung (String name, boolean cream, boolean choco, boolean sugar){
           int price = 10;
           int total = 0;

           String message = "";

        if (cream){
            price = price + 1;
            message = message + "Whipped Cream\n";
        }

        if (choco){
            price = price + 2;
            message = message + "Chocolate\n";
        }

        if (sugar){
            price = price + 3;
            message = message + "Sugar\n";
        }
        total = quantity * price;
        return quantity * price;
    }
    private String nota(String nama, int price, boolean Cream,boolean Chocolate, boolean Sugar){
        String pesan = "Nama : "+ nama;
        pesan += "\n" + "Whipped Cream : " + Cream;
        pesan += "\n" + "Chocolate : " + Chocolate;
        pesan += "\n" + "Sugar : " + Sugar;
        pesan += "\n" + "Jumlah : " + quantity;
        pesan += "\n" + "Total Harga : " +
                NumberFormat.getCurrencyInstance().format(price);
        return pesan;
    }

    private void alert (String pesan){
        AlertDialog alertPesan = new AlertDialog
                .Builder(MainActivity.this).create();
        alertPesan.setTitle("Pesan");
        alertPesan.setMessage(pesan);
        alertPesan.show();
    }
}
