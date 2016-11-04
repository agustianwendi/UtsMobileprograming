package wendistian.utsmobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wendistian.utsmobileprogramming.utils.ProductListAdapter;
import wendistian.utsmobileprogramming.utils.SessionManagement;

public class MainActivity extends AppCompatActivity {

    SessionManagement session;
    ListView productListView;
    List<Product> products;
    ProductListAdapter productListAdapter;

    public static int[] image = {
            R.drawable.cookies,
            R.drawable.bolen,
            R.drawable.kuegulung,
            R.drawable.picnicroll,
            R.drawable.rotikeju
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        setProduct();

        productListView = (ListView) findViewById(R.id.list_product);
        productListAdapter = new ProductListAdapter(getApplicationContext(), products, image);
        productListView.setAdapter(productListAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Product product = (Product)adapterView.getItemAtPosition(position);
                String price = String.valueOf(product.getPrice());

                Intent iDetail = new Intent(getApplicationContext(), Pemesanan.class);
                iDetail.putExtra("position", position);
                iDetail.putExtra("nama",product.getName());
                startActivity(iDetail);
            }
        });
    }

    private void setProduct(){

        Product product1 = new Product(1,"Cookies","Kue dengan toping keju",70000);
        Product product2 = new Product(2,"Bolen","Bolen dengan berbagai rasa yang harus dicoba",75000);
        Product product3 = new Product(3,"Kue Gulung","Kue dengan gulungan yang menggugah selera",85000);
        Product product4 = new Product(4,"Picnicroll","Kue dengan gulungan yang paling enak",50000);
        Product product5 = new Product(5,"Roti Keju","Kue dengan rasa keju yang fantastis",55000);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                session.logoutUser();
                break;
        }
        return true;
    }
}
