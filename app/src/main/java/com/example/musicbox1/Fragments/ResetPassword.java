package com.example.musicbox1.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.musicbox1.MainActivity;
import com.example.musicbox1.R;

import org.w3c.dom.Text;

public class ResetPassword extends Fragment {

    private TextView paraatras;
    private FrameLayout frameLayout;

    private EditText email;
    private ProgressBar resetPasswordProgressBar;
    private TextView responseMessage;
    private Button resetPasswordButton;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        paraatras = view.findViewById(R.id.paraatras);
        frameLayout = getActivity().findViewById(R.id.register_frame_layout);

        email= view.findViewById(R.id.email);
        resetPasswordProgressBar = view.findViewById(R.id.resetPasswordProgressBar);
        responseMessage = view.findViewById(R.id.responseMessage);
        resetPasswordButton = view.findViewById(R.id.resetPasswordButton);

        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paraatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        if(email.getText().toString().matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+")){
            resetPasswordProgressBar.setVisibility(View.VISIBLE);
            mAuth.sendPasswordResetEmail(email.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                responseMessage.setText("Revisa tu Email! El correo llego con exito  ");
                                responseMessage.setTextColor(getResources().getColor(R.color.green));
                            }else{
                                responseMessage.setText("Hubo un problema al enviar el Email");
                                responseMessage.setTextColor(getResources().getColor(R.color.red));
                            }
                            resetPasswordProgressBar.setVisibility(View.INVISIBLE);
                            responseMessage.setVisibility(View.VISIBLE);
                        }
                    });

        }else{
            email.setError("Formato de email invalido!");
            resetPasswordButton.setEnabled(false);
            resetPasswordButton.setTextColor(getResources().getColor(R.color.white));

        }
    }

    private void checkInputs() {

        if(!email.getText().toString().isEmpty()){
                resetPasswordButton.setEnabled(true);
                resetPasswordButton.setTextColor(getResources().getColor(R.color.white));
            }else{
                resetPasswordButton.setEnabled(false);
                resetPasswordButton.setTextColor(getResources().getColor(R.color.white));
            }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left, R.anim.out_from_right);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}