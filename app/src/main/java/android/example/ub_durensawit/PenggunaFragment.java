package android.example.ub_durensawit;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class PenggunaFragment extends Fragment {

    ImageView image_user;
    TextView nama, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pengguna, container,false);

//        IntroActivity activity = (IntroActivity)getActivity();


//        image_user = .findViewById(R.id.image_profile);
//        nama = view.findViewById(R.id.tv_nama);
//        email = view.findViewById(R.id.tv_email);

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//
//            nama.setText(personName);
//            email.setText(personEmail);
//            Glide.with(this).load(personPhoto).into(image_user);
        }
    }
