package com.example.apinstagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobby,edtProfileFavoriteSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

    edtProfileName = view.findViewById(R.id.edtProfileName);
    edtProfileBio = view.findViewById(R.id.edtProfileBio);
    edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
    edtProfileHobby = view.findViewById(R.id.edtProfileHobby);
    edtProfileFavoriteSport = view.findViewById(R.id.edtProfileFavoriteSport);

    btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null ){
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName").toString());
        }
if (parseUser.get("profileBio") == null){
    edtProfileBio.setText("");
}else {
    edtProfileBio.setText(parseUser.get("profileBio").toString());
}

        if (parseUser.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }else {
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
        }

        if (parseUser.get("profileHobby") == null){
            edtProfileHobby.setText("");
        }else {
            edtProfileHobby.setText(parseUser.get("profileHobby").toString());
        }
        if (parseUser.get("profileFavoriteSport") == null){
            edtProfileFavoriteSport.setText("");
        }else {
            edtProfileFavoriteSport.setText(parseUser.get("profileFavoriteSport").toString());
        }


    btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
parseUser.put("profileName", edtProfileName.getText().toString());
parseUser.put("profileBio", edtProfileBio.getText().toString());
parseUser.put("profileProfession", edtProfileProfession.getText().toString());
parseUser.put("profileHobby", edtProfileHobby.getText().toString());
parseUser.put("profileFavoriteSport", edtProfileFavoriteSport.getText().toString());

parseUser.saveInBackground(new SaveCallback() {
    @Override
    public void done(ParseException e) {
        if(e ==null){
            FancyToast.makeText(getContext(),"Info Updated",Toast.LENGTH_SHORT,FancyToast.INFO, true).show();

        }else {
            FancyToast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT,FancyToast.ERROR, true).show();
        }
    }
});

        }
    });
    return view;


    }

}
