package com.example.login.loginsighnupexample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp_Fragment extends Fragment implements OnClickListener {
    private static View view;
    private static AppCompatEditText fullName, emailId, mobileNumber, location, userProductKey;
    AppCompatTextView tvEmailVerify, tvMobVerify;
    private static TextView login;
    private static AppCompatButton signUpButton;
    private static CheckBox terms_conditions;
    RadioButton rbYes, rbNo;
    AppCompatImageButton ibHelpProductKey;
    RadioGroup rbYesNo;
    View viewProductBelow;

    public SignUp_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        fullName = (AppCompatEditText) view.findViewById(R.id.fullName);
        emailId = (AppCompatEditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (AppCompatEditText) view.findViewById(R.id.mobileNumber);
        location = (AppCompatEditText) view.findViewById(R.id.location);
        signUpButton = (AppCompatButton) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);
        ibHelpProductKey = (AppCompatImageButton) view.findViewById(R.id.ibHelpProductKey);
        tvEmailVerify = (AppCompatTextView) view.findViewById(R.id.tvEmailVerify);
        tvMobVerify = (AppCompatTextView) view.findViewById(R.id.tvMobVerify);
        rbYesNo = (RadioGroup) view.findViewById(R.id.rbYesNo);
        rbYes = (RadioButton) view.findViewById(R.id.rbYes);
        rbNo = (RadioButton) view.findViewById(R.id.rbNo);
        viewProductBelow = (View) view.findViewById(R.id.view);
        userProductKey = (AppCompatEditText) view.findViewById(R.id.userProductKey);
        rbYesNo.check(R.id.rbNo);
        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
        ibHelpProductKey.setOnClickListener(this);
        tvEmailVerify.setOnClickListener(this);
        tvMobVerify.setOnClickListener(this);
        rbNo.setOnClickListener(this);
        rbYes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:
                // Call checkValidation method
                checkValidation();
                break;

            case R.id.already_user:
                // Replace login fragment
                new LoginSignUpActivity().replaceLoginFragment();
                break;
            case R.id.ibHelpProductKey:
                showDialogForHelp();
                break;
            case R.id.tvEmailVerify:
                // showDialogForHelp();
                break;
            case R.id.tvMobVerify:
                // showDialogForHelp();
                break;
            case R.id.rbYes:
                userProductKey.setVisibility(View.VISIBLE);
                viewProductBelow.setVisibility(View.VISIBLE);
                break;
            case R.id.rbNo:
                userProductKey.setVisibility(View.GONE);
                viewProductBelow.setVisibility(View.GONE);
                userProductKey.setText("");
                break;
        }

    }

    private void showDialogForHelp() {
        Dialog_Product_Features dialog = new Dialog_Product_Features();
        dialog.show(getFragmentManager(), "example");
    }

    // Check Validation Method
    private void checkValidation() {

        // Get all AppCompatEditText texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                )

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");

            // Check if email id valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");

            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Please select Terms and Conditions.");

            // Else do signup or do your stuff
        else
            Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
                    .show();

    }
}
