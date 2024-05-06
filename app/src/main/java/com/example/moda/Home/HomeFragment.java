package com.example.moda.Home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moda.Models.Photo;
import com.example.moda.R;
import com.example.moda.Utils.MainfeedListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    //vars

//    private ImageView image_heart;
//    private int current_image;
//    int[] hearts = {R.drawable.ic_heartblank, R.drawable.ic_heartfilled};
    private ArrayList<Photo> mPhotos;
    private ArrayList<String> mFollowing;
    private ListView mListView;
    private MainfeedListAdapter mAdapter;
    String mUsername[] = {"Kunal", "Deepa", "Shreya", "Eashan", "Ritesh", "Mansi", "Arya", "Sagar", "Sumit", "Snehal", "Sanket", "Samuel", "Shanaya", "Tejal", "Nishant", "Prerna"};
    String mCaption[] = {"Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?", "Does this look good on me?"};
    int mImages[] = {R.drawable.kunal, R.drawable.deepa, R.drawable.shreya, R.drawable.eashan, R.drawable.ritesh, R.drawable.mansi, R.drawable.arya, R.drawable.sagar, R.drawable.sumit, R.drawable.snehal, R.drawable.sanket, R.drawable.samuel, R.drawable.shanaya, R.drawable.tejal, R.drawable.nishant, R.drawable.prerna};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        image_heart = (ImageView) view.findViewById(R.id.image_heart);
        mListView = (ListView) view.findViewById(R.id.listview);
        mFollowing = new ArrayList<>();
        mPhotos = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(getContext(), mUsername, mCaption, mImages);
        mListView.setAdapter(adapter);

//        image_heart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                current_image++;
//                current_image = current_image % hearts.length;
//                image_heart.setImageResource(hearts[current_image]);
//            }
//        });

        return view;
    }

    public void onCustomToggleClick(View view) {

    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rUsername[];
        String rCaption[];
        int rImages[];

        MyAdapter (Context c, String username[], String caption[], int images[]) {
            super(c, R.layout.layout_view_post, R.id.username, username);
            this.context = c;
            this.rUsername = username;
            this.rCaption = caption;
            this.rImages = images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            View layout_view_post = layoutInflater.inflate(R.layout.layout_view_post, parent, false);
            ImageView images = layout_view_post.findViewById(R.id.post_image);
            TextView caption = layout_view_post.findViewById(R.id.caption);
            TextView username = layout_view_post.findViewById(R.id.username);

            images.setImageResource(rImages[position]);
            caption.setText(rCaption[position]);
            username.setText(rUsername[position]);

            return layout_view_post;
        }
    }

//    private void getPhotos(){
//        Log.d(TAG, "getPhotos: getting photos");
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        for(int i = 0; i < mFollowing.size(); i++){
//            final int count = i;
//            Query query = reference
//                    .child(getString(R.string.dbname_user_photos))
//                    .child(mFollowing.get(i))
//                    .orderByChild(getString(R.string.field_user_id))
//                    .equalTo(mFollowing.get(i));
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
//
//                        Photo photo = new Photo();
//                        Map<String, Object> objectMap = (HashMap<String, Object>) singleSnapshot.getValue();
//
//                        photo.setCaption(objectMap.get(getString(R.string.field_caption)).toString());
//                        photo.setTags(objectMap.get(getString(R.string.field_tags)).toString());
//                        photo.setPhoto_id(objectMap.get(getString(R.string.field_photo_id)).toString());
//                        photo.setUser_id(objectMap.get(getString(R.string.field_user_id)).toString());
//                        photo.setDate_created(objectMap.get(getString(R.string.field_date_created)).toString());
//                        photo.setImage_path(objectMap.get(getString(R.string.field_image_path)).toString());
//
//                        mPhotos.add(photo);
//                        displayPhotos();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }

//    private void displayPhotos(){
//        if(mPhotos != null){
//            Collections.sort(mPhotos, new Comparator<Photo>() {
//                @Override
//                public int compare(Photo o1, Photo o2) {
//                    return o2.getDate_created().compareTo(o1.getDate_created());
//                }
//            });
//
//            mAdapter = new MainfeedListAdapter(getActivity(), R.layout.layout_mainfeed_listitem, mPhotos);
//            mListView.setAdapter(mAdapter);
//        }
//    }
}
