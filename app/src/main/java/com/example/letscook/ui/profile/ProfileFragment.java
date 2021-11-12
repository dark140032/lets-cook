package com.example.letscook.ui.profile;

import static android.graphics.Color.BLACK;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.letscook.DAO.UserDAO;
import com.example.letscook.R;
import com.example.letscook.activity.WelcomeActivity;
import com.example.letscook.databinding.FragmentProfileBinding;
import com.example.letscook.model.User;
import com.example.letscook.validation.Validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private EditText edt_username;
    private EditText edt_email;
    private TextView txt_birthDay;
    private TextView txt_dateOfBirth;
    private TextView txt_job;
    private EditText edt_job;
    private TextView txt_description;
    private EditText edt_description;
    private LinearLayout ll_ButonMain;
    private LinearLayout ll_ButonEdit;
    private int mYear = 0, mMonth = 0, mDay = 0;
    public View root;
    public User user;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Get user đã đăng nhập tại code này
        Intent intent = this.getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        user = (User) bundle.getSerializable("user");
        //
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        getProfileAccount();

        UserDAO userDAO = new UserDAO(getContext());

        root.findViewById(R.id.txt_dateOfBirth).
                setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View view) {
                                           final Calendar c = Calendar.getInstance();
                                           mYear = c.get(Calendar.YEAR);
                                           mMonth = c.get(Calendar.MONTH);
                                           mDay = c.get(Calendar.DAY_OF_MONTH);

                                           DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                                                   new DatePickerDialog.OnDateSetListener() {
                                                       @Override
                                                       public void onDateSet(DatePicker view, int year,
                                                                             int monthOfYear, int dayOfMonth) {

                                                           if (dayOfMonth > 10 && monthOfYear > 10) {
                                                               txt_dateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                                           } else {
                                                               if (dayOfMonth < 10 && monthOfYear < 10)
                                                                txt_dateOfBirth.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                                               else if (dayOfMonth < 10)
                                                                   txt_dateOfBirth.setText("0" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                                               else if (monthOfYear < 10) {
                                                                   txt_dateOfBirth.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                                               }
                                                           }

                                                       }
                                                   }, mYear, mMonth, mDay);

                                           datePickerDialog.show();
                                       }
                                   }
                );

        root.findViewById(R.id.imgBtn_editProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnableTrueEdit();
                root.findViewById(R.id.imgBtn_editProfile).setVisibility(View.GONE);
                ll_ButonMain.setVisibility(View.GONE);
                ll_ButonEdit.setVisibility(View.VISIBLE);
            }
        });

        root.findViewById(R.id.btn_update_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO.open();
                User userUpdate = new User(user.getUserId(), edt_username.getText().toString(), "link_avatar"
                        , txt_dateOfBirth.getText().toString(), edt_job.getText().toString(),
                        edt_description.getText().toString());

                if (Validation.isFutureDate(txt_dateOfBirth.getText().toString())) {
                    if (userDAO.updateProfile(userUpdate)) {
                        Toast.makeText(getContext(), "Cập nhật profile thành công !", Toast.LENGTH_SHORT).show();
                        setEnableFalseEdit();
                        root.findViewById(R.id.imgBtn_editProfile).setVisibility(View.VISIBLE);
                        ll_ButonMain.setVisibility(View.VISIBLE);
                        ll_ButonEdit.setVisibility(View.GONE);

                        bundle.clear();
                        bundle.putSerializable("user", new User(user.getUserId(), edt_username.getText().toString(), user.getEmail(),
                                user.getPassword(), "link_avatar", txt_dateOfBirth.getText().toString(),
                                edt_job.getText().toString(), edt_description.getText().toString()));
                        intent.putExtras(bundle);

                    } else {
                        Toast.makeText(getContext(), "Cập nhật không thành công !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Ngày sinh không được vượt quá ngày hiện tại !", Toast.LENGTH_SHORT).show();
                }
                userDAO.close();
            }
        });

        root.findViewById(R.id.btn_cancel_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnableFalseEdit();
                root.findViewById(R.id.imgBtn_editProfile).setVisibility(View.VISIBLE);
                ll_ButonMain.setVisibility(View.VISIBLE);
                ll_ButonEdit.setVisibility(View.GONE);
                getProfileAccount();
            }
        });

        root.findViewById(R.id.btn_changePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft_add = getFragmentManager().beginTransaction();
                ft_add.add(R.id.nav_host_fragment_activity_main, new ProfileChangePasswordFragment());
                ft_add.commit();
            }
        });

        root.findViewById(R.id.btn_logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
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

        edt_username.setEnabled(true);
        txt_dateOfBirth.setEnabled(true);
        edt_job.setEnabled(true);
        edt_description.setEnabled(true);

        edt_username.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        txt_dateOfBirth.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        edt_job.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));
        edt_description.setBackground(getResources().getDrawable(R.drawable.style_profile_edit));

    }

    public void setEnableFalseEdit() {

        txt_birthDay.setTextColor(Color.parseColor("#c4c4c4"));
        txt_job.setTextColor(Color.parseColor("#c4c4c4"));
        txt_description.setTextColor(Color.parseColor("#c4c4c4"));

        edt_username.setEnabled(false);
        txt_dateOfBirth.setEnabled(false);
        edt_job.setEnabled(false);
        edt_description.setEnabled(false);

        edt_username.setBackground(null);
        txt_dateOfBirth.setBackground(getResources().getDrawable(R.drawable.style_profile));
        edt_job.setBackground(getResources().getDrawable(R.drawable.style_profile));
        edt_description.setBackground(getResources().getDrawable(R.drawable.style_profile));

    }

    public void getProfileAccount() {

        ll_ButonMain = root.findViewById(R.id.layoutBtnMain);
        ll_ButonEdit = root.findViewById(R.id.layoutBtnEdit);

        txt_birthDay = root.findViewById(R.id.txt_birthDay);
        txt_job = root.findViewById(R.id.txt_job);
        txt_description = root.findViewById(R.id.txt_description);

        edt_username = root.findViewById(R.id.edt_username);
        edt_email = root.findViewById(R.id.edt_email);
        txt_dateOfBirth = root.findViewById(R.id.txt_dateOfBirth);
        edt_job = root.findViewById(R.id.edt_job);
        edt_description = root.findViewById(R.id.edt_description);

        edt_username.setText(user.getUsername());
        edt_email.setText(user.getEmail());
        txt_dateOfBirth.setText(user.getDateOfBirth());
        edt_job.setText(user.getJob());
        edt_description.setText(user.getUserDescription());


    }
}