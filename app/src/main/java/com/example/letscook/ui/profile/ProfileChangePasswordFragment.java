package com.example.letscook.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letscook.DAO.UserDAO;
import com.example.letscook.Encrypt.AESCrypt;
import com.example.letscook.R;
import com.example.letscook.activity.LoginActivity;
import com.example.letscook.activity.WelcomeActivity;
import com.example.letscook.databinding.FragmentProfileBinding;
import com.example.letscook.databinding.FragmentProfileChangePasswordBinding;
import com.example.letscook.model.User;

public class ProfileChangePasswordFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileChangePasswordBinding binding;
    public View root;


    public ProfileChangePasswordFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Get user đã đăng nhập tại code này
        Intent intent = this.getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("user");
        //

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileChangePasswordBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        TextView txt_username = root.findViewById(R.id.txt_username_change_password);
        TextView txt_email = root.findViewById(R.id.txt_email_change_password);
        txt_username.setText(user.getUsername());
        txt_email.setText(user.getEmail());

        EditText edt_oldPassword = root.findViewById(R.id.edt_oldPassword_change_password);
        EditText edt_newPassword = root.findViewById(R.id.edt_newPassword_change_password);
        EditText edt_confirmPassword = root.findViewById(R.id.edt_confirmPassword_change_password);

        root.findViewById(R.id.btn_update_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_oldPassword.getText().toString().equals("") && !edt_newPassword.getText().toString().equals("")
                        && !edt_confirmPassword.getText().toString().equals("")) {
                    if (AESCrypt.encrypt(edt_oldPassword.getText().toString().trim()).equals(user.getPassword())) {
                        if (edt_newPassword.getText().toString().equals(edt_confirmPassword.getText().toString())) {
                            UserDAO userDAO = new UserDAO(getActivity());
                            userDAO.open();
                            if (userDAO.updatePassword(user.getUserId(), AESCrypt.encrypt(edt_newPassword.getText().toString()))) {
                                Toast.makeText(getContext(), "Cập nhật mật khẩu thành công, hãy đăng nhập lại !", Toast.LENGTH_LONG).show();
                                LoginActivity loginActivity = new LoginActivity();
                                loginActivity.emailFromChangePassword = user.getEmail();
                                Intent i = new Intent(getContext(), loginActivity.getClass());
                                startActivity(i);
                                getActivity().finish();
                            } else
                                Toast.makeText(getContext(), "Đã xảy ra lỗi khi cập nhật mật khẩu !", Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(getContext(), "Nhập lại mật khẩu không đúng !", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getContext(), "Mật khẩu hiện tại không đúng !", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
            }
        });

        root.findViewById(R.id.btn_cancel_change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return root;
    }
}