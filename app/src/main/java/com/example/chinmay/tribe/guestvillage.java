package com.example.chinmay.tribe;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class guestvillage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Bitmap> images;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataSet;
    private ArrayList<Double> myDataSet2;
    private ArrayList<String> villages,rating;
    private ArrayList<descs> descaraay;
 private   ContextMenu.ContextMenuInfo mi;
    private String key;
    private String tag;
 private FirebaseDatabase database;
 private DatabaseReference myRef;
 private String email;
 private ProgressBar progressBar;
 private String username;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guest);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        // setting Toolbar as Action Bar for the App
        setSupportActionBar(mToolbar);
         final ActionBar actionBar=getSupportActionBar();


        actionBar.setDisplayUseLogoEnabled(true);
  //     FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        actionBar.setLogo(R.drawable.ic_person_black_24dp);
      actionBar.setTitle("Guest");
        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);





      //  actionbar.setDisplayShowTitleEnabled(false);
      //  actionbar.setDisplayShowCustomEnabled(true);
       // actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
   //  actionBar.setDisplayHomeAsUpEnabled(true);
//
//        actionbar.setIcon(R.drawable.a2);
//        actionbar.setLogo(R.drawable.download);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //Center the textview in the ActionBar !


      //  TextView _Header_Text_View = (TextView) viewActionBar.findViewById(R.id.header_Name);
       // _Header_Text_View.setText("" + header_Text);


       mLayoutManager = new GridLayoutManager(this,2);
        //mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataSet = new ArrayList<String>();
        myDataSet2 = new ArrayList<Double>();
        villages=new ArrayList<String>();
        descaraay=new ArrayList<descs>();
        rating=new ArrayList<String>();
        images = new ArrayList<Bitmap>();
        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.b1);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.b2);
        Bitmap image3 = BitmapFactory.decodeResource(getResources(), R.drawable.b3);
        Bitmap image4 = BitmapFactory.decodeResource(getResources(), R.drawable.b4);
        Bitmap image5 = BitmapFactory.decodeResource(getResources(), R.drawable.background_mountain);
        Bitmap image6 = BitmapFactory.decodeResource(getResources(), R.drawable.b5);
        Bitmap image7 = BitmapFactory.decodeResource(getResources(), R.drawable.b6);
        Bitmap image8 = BitmapFactory.decodeResource(getResources(), R.drawable.a2);
        Bitmap image9 = BitmapFactory.decodeResource(getResources(), R.drawable.b8);
        Bitmap image10 = BitmapFactory.decodeResource(getResources(), R.drawable.b9);

        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        images.add(image6);
        images.add(image7);
        images.add(image8);
        images.add(image9);
        images.add(image10);
        for (int i = 0; i < 10; i++) {
            myDataSet.add("Village" + i);
            myDataSet2.add(i*0.5);
        }

              myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot myitem : dataSnapshot.getChildren())

                {   if(myitem.getKey().equals("UserRelation")) {
                    for (DataSnapshot myitem2 : myitem.getChildren()) {
                        if ((myitem2.child("email").getValue().toString().equals(email))) {
                            key = myitem2.getKey();

                            username = key;


                        }

                    }
                } else if(myitem.getKey().equals("VillageRelation"))
                {
                    for (DataSnapshot myitem2 : myitem.getChildren()) {

                        descs d1=new descs(
                                myitem2.child("DescriptionRelation").child("shortdes").getValue().toString(),

                                myitem2.child("DescriptionRelation").child("nameOfVillage").getValue().toString(),
                                myitem2.child("DescriptionRelation").child("description").getValue().toString(),
                                myitem2.child("DescriptionRelation").child("galleryImage1").getValue().toString(),
                                myitem2.child("DescriptionRelation").child("history").getValue().toString(),
                                myitem2.child("DescriptionRelation").child("culture").getValue().toString(),

                                Double.parseDouble(myitem2.child("DescriptionRelation").child("rating").getValue().toString()),

                                myitem2.child("DescriptionRelation").child("speciality").getValue().toString());
                        descaraay.add(d1);


                      //  rating.add(myitem2.child("Description").child("rating").getValue().toString());


                    }

                }
                }
                madapter = new MyAdapter(villages,myDataSet2,images,guestvillage.this,email,descaraay);
                mRecyclerView.setAdapter(madapter);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                registerForContextMenu(mRecyclerView);


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(0));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(guestvillage.this, R.drawable.divider));


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent i=new Intent(guestvillage.this,guestupload.class);

                        i.putExtra("village",descaraay.get(position).getNameOfVillage());
                        i.putExtra("username",username);


                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {


                    }


                })
        );

    }
    public class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private  final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable divider;

        /**
         * Default divider will be used
         */
        public DividerItemDecoration(Context context) {
            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
            divider = styledAttributes.getDrawable(0);
            styledAttributes.recycle();
        }

        /**
         * Custom divider will be used
         */
        public DividerItemDecoration(Context context, int resId) {
            divider = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_signout:
                finish();

                break;
            // action with ID action_settings was selected

            default:
                break;
        }

        return true;
    }
    @Override
    public void onBackPressed() {


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Tourist Guide"){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Intent i =new Intent(guestvillage.this,GuideReg.class);
            i.putExtra("village",descaraay.get(info.position).getNameOfVillage());
            i.putExtra("username",username);
            startActivity(i,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        }
        else if(item.getTitle()=="Home"){

        }
        else if(item.getTitle()=="Trader") {


        }else{
            return false;
        }
        return true;
    }
}



