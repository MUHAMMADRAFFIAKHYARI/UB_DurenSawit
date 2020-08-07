package android.example.ub_durensawit.Fragment;

import android.app.Activity;
import android.content.Intent;

import android.example.ub_durensawit.Adapter.MainRecyclerAdapter;
import android.example.ub_durensawit.AllitemActivity;
import android.example.ub_durensawit.CartActivity;
import android.example.ub_durensawit.DbConn.DataSource.CartRepository;
import android.example.ub_durensawit.Model.AllCategory;
import android.example.ub_durensawit.Model.CategoryItem;
import android.example.ub_durensawit.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment {

    private ImageView cartList;
    private Button toAllitem;
    private NotificationBadge badge;
    private CartRepository cartRepository;
    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    Activity currentActivity;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container,false);

        currentActivity = getActivity();
        cartRepository = new CartRepository(currentActivity.getApplicationContext());
        cartList = view.findViewById(R.id.toCart);
        toAllitem = view.findViewById(R.id.toAllitem);
        badge =(NotificationBadge)view.findViewById(R.id.badge);

        mainCategoryRecycler = view.findViewById(R.id.main_recycler);



        toAllitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AllitemActivity.class));
            }
        });
        cartList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CartActivity.class));
            }
        });


        // Tambah list pertama
        List<CategoryItem> categoryItemListTerbaru = new ArrayList<>();
        categoryItemListTerbaru.add(new CategoryItem(1, R.drawable.product4));
        categoryItemListTerbaru.add(new CategoryItem(1, R.drawable.product3));
        categoryItemListTerbaru.add(new CategoryItem(1, R.drawable.product3));
        categoryItemListTerbaru.add(new CategoryItem(1, R.drawable.product1));
        categoryItemListTerbaru.add(new CategoryItem(1, R.drawable.product2));
        categoryItemListTerbaru.add(new CategoryItem(1, R.drawable.product4));


        // Tambah list ke dua
        List<CategoryItem> categoryItemListPromosi = new ArrayList<>();
        categoryItemListPromosi.add(new CategoryItem(2, R.drawable.product2));
        categoryItemListPromosi.add(new CategoryItem(2, R.drawable.product1));
        categoryItemListPromosi.add(new CategoryItem(2, R.drawable.product4));
        categoryItemListPromosi.add(new CategoryItem(2, R.drawable.product3));
        categoryItemListPromosi.add(new CategoryItem(2, R.drawable.product2));
        categoryItemListPromosi.add(new CategoryItem(2, R.drawable.product1));


        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Terbaru", categoryItemListTerbaru));
        allCategoryList.add(new AllCategory("Promosi Minggu Ini", categoryItemListPromosi));

        setMainCategoryRecycler(allCategoryList);





        return view;
    }

    private void updateCartCount(){

        if(badge==null) return;
        new Thread(){
            public void run(){
                try{
                    currentActivity.runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            int countItems = cartRepository.countItems();
                            if( countItems == 0){
                            badge.setVisibility(getView().INVISIBLE);
                            } else{
                                badge.setVisibility(getView().VISIBLE);
                                badge.setText(String.valueOf(countItems));
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);

        mainCategoryRecycler.setHasFixedSize(true);

    }


}
