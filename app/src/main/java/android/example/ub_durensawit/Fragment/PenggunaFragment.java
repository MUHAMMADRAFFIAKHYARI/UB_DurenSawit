package android.example.ub_durensawit.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.example.ub_durensawit.AboutActivity;
import android.example.ub_durensawit.CartActivity;
import android.example.ub_durensawit.DbConn.ApiClient;
import android.example.ub_durensawit.DbConn.ApiInterface;
import android.example.ub_durensawit.HistoryActivity;
import android.example.ub_durensawit.IntroActivity;
import android.example.ub_durensawit.LandingActivity;
import android.example.ub_durensawit.Model.User;
import android.example.ub_durensawit.ProfileActivity;
import android.example.ub_durensawit.R;
import android.example.ub_durensawit.ThanksActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PenggunaFragment extends Fragment {

    ImageView image_user;
    RelativeLayout toLogout, toAbout, toHistory, toProfile;
    TextView tvNamaUser, tvEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengguna, container,false);

        LandingActivity landingActivity = (LandingActivity)getActivity();
        tvNamaUser = view.findViewById(R.id.tv_nama);
        tvEmail = view.findViewById(R.id.tv_email);

        tvNamaUser.setText(landingActivity.getNama());
        tvEmail.setText(landingActivity.getEmail());

        toLogout = view.findViewById(R.id.toLogOut);
        toLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.logout_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button denyBuy = dialog.findViewById(R.id.denyLogout);
                denyBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button acceptBuy = dialog.findViewById(R.id.acceptLogout);
                acceptBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), IntroActivity.class));
                        getActivity().finish();
                    }
                });
            }
        });

        toAbout = view.findViewById(R.id.toAbout);
        toAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AboutActivity.class));
            }
        });

        toHistory = view.findViewById(R.id.toHistory);
        toHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });

        toProfile = view.findViewById(R.id.toProfile);
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });


        return view;




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

