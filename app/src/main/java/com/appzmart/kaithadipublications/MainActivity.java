package com.appzmart.kaithadipublications;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.widget.Toast;

import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    RecyclerView recyclerView;
    private DatabaseReference DatabaseRef;
    public long iterator = 0;
    public List<ImageUpload> mainimgList;
    MainRecyclerViewAdapter adapter;
    Toolbar MainToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpannableString MainTitle = new SpannableString("கைத்தடி பதிப்பகம்");
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        ArrayList<String> listUrl = new ArrayList<String>();
        ArrayList<String> listName = new ArrayList<String>();

        listUrl.add("https://www.revive-adserver.com/media/GitHub.jpg");
        listName.add("Test1");

        listUrl.add("https://tctechcrunch2011.files.wordpress.com/2017/02/android-studio-logo.png");
        listName.add("Test2");

        listUrl.add("http://static.tumblr.com/7650edd3fb8f7f2287d79a67b5fec211/3mg2skq/3bdn278j2/tumblr_static_idk_what.gif");
        listName.add("Test3");

        listUrl.add("https://upload.wikimedia.org/wikipedia/commons/d/db/Android_robot_2014.svg");
        listName.add("SVG - Android");

        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView.image(listUrl.get(i)).setOnSliderClickListener(this);
            //textSliderView.description(listName.get(i)).image(listUrl.get(i)).setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mainimgList = new ArrayList <>();
        DatabaseRef = FirebaseDatabase.getInstance().getReference("MainActivity/");

        DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                iterator = dataSnapshot.getChildrenCount();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    mainimgList.add(img);
                }

                recyclerView = (RecyclerView) findViewById(R.id.Main_Activity_RecyclerView);
                int numberOfColumns = 2;
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns));
                adapter = new MainRecyclerViewAdapter(getApplicationContext(), mainimgList);
                //adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Cancelled");
            }

        });
    }
    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}