package org.pen.animusfirst.Activities;

import static org.pen.animusfirst.utilities.PreRender.ifNight;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;

import org.pen.animusfirst.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class ActivitySignUp2 extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private static final String TAG = "XX_ActivitySignUp2_XX";
    AppCompatEditText etVisaAppliedDate, etBioMetricsDate, etMedicalTestDate, etVisaApplicationNumber;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    int mode;
    String[] lang;
    AutoCompleteTextView acTv;
    ArrayAdapter adapter;
    String langs;
    TextView tvVANtext, tvBDtext, tvVADtext, tvMTDtext, tvVStext;
    MaterialButton btnAffirm;
    String visaApplicationNumber, visaAppliedDate, bioMetricsDate, medicalDate, visaStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = ifNight(getApplicationContext());
        setContentView(R.layout.activity_sign_up2);

        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setup();

    }


    @Override
    public void onClick(View view) {
        int length = findViewById(view.getId()).toString().length();
        int index = findViewById(view.getId()).toString().indexOf("/");
        String viewInFocus = findViewById(view.getId()).toString().substring(index + 1, length - 1);


        if (viewInFocus.contains("etVisaAppliedDate")) {
            Log.d(TAG, "onClick: sending " + etVisaAppliedDate.getId());
            setupDatePicker(etVisaAppliedDate, view, "Visa Applied Date");
        } else if (viewInFocus.contains("etBioMetricsDate")) {
            Log.d(TAG, "onClick: sending " + etBioMetricsDate.getId());
            setupDatePicker(etBioMetricsDate, view, "Biometrics Date");
        } else if (viewInFocus.contains("etMedicalTestDate")) {
            Log.d(TAG, "onClick: sending " + etMedicalTestDate.getId());
            setupDatePicker(etMedicalTestDate, view, "Medical Test Date");
        } else if (viewInFocus.contains("btnAffirm")) {
            Log.d(TAG, "onClick: clicked on " + findViewById(view.getId()).toString().substring(index + 1, length - 1));
            startActivity(new Intent(ActivitySignUp2.this, ActivitySignUp.class));

            //-- TO DO --

        }


    }


    private void setupDatePicker(AppCompatEditText editText, View v, String msg) {
        Log.d(TAG, "setupDatePicker: received editText " + editText.getId());
        date = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel(editText, v, msg);
        };

        new DatePickerDialog(ActivitySignUp2.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel(AppCompatEditText editText, View v, String msg) {
        Log.d(TAG, "updateLabel: received editText " + editText.getId());
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.UK);
        editText.setText(dateFormat.format(calendar.getTime()));
        v.clearFocus();

    }

    private boolean isEmpty(AppCompatEditText editText) {
        return editText.getText().length() == 0;
    }


    private void setup() {
        etVisaAppliedDate.setOnClickListener(this);
        etMedicalTestDate.setOnClickListener(this);
        etBioMetricsDate.setOnClickListener(this);
        btnAffirm.setOnClickListener(this);

        etVisaAppliedDate.addTextChangedListener(this);
        etMedicalTestDate.addTextChangedListener(this);
        etBioMetricsDate.addTextChangedListener(this);
        etVisaApplicationNumber.addTextChangedListener(this);
        // etVisaStatus.addTextChangedListener(this);

        acTv.setAdapter(adapter);
        acTv.setOnItemClickListener((parent, view, position, id) -> {
            if (mode == 0) {
                acTv.setTextColor(getColor(R.color.white));
            } else if (mode == 1) {
                acTv.setTextColor(getColor(R.color.grey_700));
            }


            Log.d(TAG, "onItemClickListener color changed.. : ");

            langs = acTv.getText().toString();
            Log.d(TAG, "onItemClickListener : " + langs);

            tvVStext.setVisibility(View.VISIBLE);
        });
    }

    private void init() {
        etVisaAppliedDate = findViewById(R.id.etVisaAppliedDate);
        etBioMetricsDate = findViewById(R.id.etBioMetricsDate);
        etMedicalTestDate = findViewById(R.id.etMedicalTestDate);
        etVisaApplicationNumber = findViewById(R.id.etVisaApplicationNumber);
        //etVisaStatus = findViewById(R.id.etVisaStatus);
        tvVANtext = findViewById(R.id.tvVANtext);
        tvVADtext = findViewById(R.id.tvVADtext);
        tvBDtext = findViewById(R.id.tvBDtext);
        tvMTDtext = findViewById(R.id.tvMTDtext);
        tvVStext = findViewById(R.id.tvVStext);
        calendar = Calendar.getInstance();
        btnAffirm = findViewById(R.id.btnAffirm);

        lang = getResources().getStringArray(R.array.visa_status);
        adapter = new ArrayAdapter(this, R.layout.dropdown_item, lang);
        acTv = findViewById(R.id.acTv);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.hashCode() == Objects.requireNonNull(etVisaAppliedDate.getText()).hashCode()) {
            Log.d(TAG, "afterTextChanged: visa applied ");

            if (!isEmpty(etVisaAppliedDate))
                tvVADtext.setVisibility(View.VISIBLE);
            else
                tvVADtext.setVisibility(View.GONE);
        } else if (s.hashCode() == Objects.requireNonNull(etBioMetricsDate.getText()).hashCode()) {
            Log.d(TAG, "afterTextChanged: biometrics date ");

            if (!isEmpty(etBioMetricsDate))
                tvBDtext.setVisibility(View.VISIBLE);
            else
                tvBDtext.setVisibility(View.GONE);
        } else if (s.hashCode() == Objects.requireNonNull(etMedicalTestDate.getText()).hashCode()) {
            Log.d(TAG, "afterTextChanged: medical test date ");

            if (!isEmpty(etMedicalTestDate))
                tvMTDtext.setVisibility(View.VISIBLE);
            else
                tvMTDtext.setVisibility(View.GONE);
        } else if (s.hashCode() == Objects.requireNonNull(etVisaApplicationNumber.getText()).hashCode()) {
            Log.d(TAG, "afterTextChanged: visa application number ");

            if (!isEmpty(etVisaApplicationNumber))
                tvVANtext.setVisibility(View.VISIBLE);
            else
                tvVANtext.setVisibility(View.GONE);
        }


    }
}