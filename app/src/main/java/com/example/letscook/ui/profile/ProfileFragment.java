package com.example.letscook.ui.profile;

import static android.graphics.Color.BLACK;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.letscook.R;
import com.example.letscook.databinding.FragmentProfileBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private EditText edt_fullname;
    private EditText edt_email;
    private TextView txt_birthDay;
    private EditText edt_birthDay;
    private TextView txt_job;
    private EditText edt_job;
    private TextView txt_description;
    private EditText edt_description;
    private LinearLayout ll_ButonMain;
    private LinearLayout ll_ButonEdit;
    public View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        getProfileAccount();
        root.findViewById(R.id.imgBtn_editProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnableTrueEdit();
                root.findViewById(R.id.imgBtn_editProfile).setVisibility(View.GONE);
                ll_ButonMain.setVisibility(View.GONE);
                ll_ButonEdit.setVisibility(View.VISIBLE);
            }
        });
        root.findViewById(R.id.btn_cancel_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProfileAccount();
                setEnableFalseEdit();
                root.findViewById(R.id.imgBtn_editProfile).setVisibility(View.VISIBLE);
                ll_ButonMain.setVisibility(View.VISIBLE);
                ll_ButonEdit.setVisibility(View.GONE);
            }
        });
        root.findViewById(R.id.btn_changePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),ProfileChangePasswordFragment.class);
                view.getContext();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setEnableTrueEdit() {

        txt_birthDay.setTextColor(BLACK);
        txt_job.setTextColor(BLACK);
        txt_description.setTextColor(BLACK);

        edt_fullname.setEnabled(true);
        edt_email.setEnabled(true);
        edt_birthDay.setEnabled(true);
        edt_job.setEnabled(true);
        edt_description.setEnabled(true);

        edt_fullname.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        edt_email.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        edt_birthDay.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        edt_job.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        edt_description.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));

    }

    private void setEnableFalseEdit() {

        txt_birthDay.setTextColor(Color.parseColor("#c4c4c4"));
        txt_job.setTextColor(Color.parseColor("#c4c4c4"));
        txt_description.setTextColor(Color.parseColor("#c4c4c4"));

        edt_fullname.setEnabled(false);
        edt_email.setEnabled(false);
        edt_birthDay.setEnabled(false);
        edt_job.setEnabled(false);
        edt_description.setEnabled(false);

        edt_fullname.setBackground(null);
        edt_email.setBackground(null);
        edt_birthDay.setBackground(getResources().getDrawable(R.drawable.style_profile));
        edt_job.setBackground(getResources().getDrawable(R.drawable.style_profile));
        edt_description.setBackground(getResources().getDrawable(R.drawable.style_profile));
    }

    private void getProfileAccount() {
        Account account = new Account();
        account.setFullname("Nguyễn Nhân");
        account.setEmail("nguyennhan@gmail.com");
        account.setBirthDay("14/01/2021");
        account.setJob("Lập trình viên");
        account.setDescription("Miêu tả bản thân");

        ll_ButonMain = root.findViewById(R.id.layoutBtnMain);
        ll_ButonEdit = root.findViewById(R.id.layoutBtnEdit);

        txt_birthDay = root.findViewById(R.id.txt_birthDay);
        txt_job = root.findViewById(R.id.txt_job);
        txt_description = root.findViewById(R.id.txt_description);

        edt_fullname = root.findViewById(R.id.edt_fullname);
        edt_fullname.setText(account.getFullname());
        edt_email = root.findViewById(R.id.edt_email);
        edt_email.setText(account.getEmail());
        edt_birthDay = root.findViewById(R.id.edt_birthDay);
        edt_birthDay.setText(account.getBirthDay());
        edt_job = root.findViewById(R.id.edt_job);
        edt_job.setText(account.getJob());
        edt_description = root.findViewById(R.id.edt_description);
        edt_description.setText(account.getDescription());
    }
}