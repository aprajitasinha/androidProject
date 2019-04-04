package com.mobiloitte.androidm3retest.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiloitte.androidm3retest.MainActivity;
import com.mobiloitte.androidm3retest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class ActivityEditProfile extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Activity activity = ActivityEditProfile.this;

    private static final String TAG = "ActivityEditProfile";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button btnSave;
    ImageView ivClick,ivProfile;
    Integer REQEST_CAMERA=1, SELECT_FILE=0;
    String regexpassword= ".{10,}";
    private TextView tvDob,  mDisplayDate,nameError, userNameError, genderError, bioError, dobError, schoolError, collageError, workedError, designationError, worksError,
             homeError, livesError, emailError, webError, numberError, locatonError,tvImageError;
    private EditText etName, etUserName, etBio,  etDesignation, etEmail, etWebsite, etPhoneNumber;
    private Spinner snGender, snSchool, snCollege, snWorked, snWorks, snHomeTown, snLivesIn, snLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        ivProfile=(ImageView)findViewById(R.id.iv_avtar) ;






        snGender = findViewById(R.id.spinner_gender);
        snSchool = findViewById(R.id.sp_school);
        snCollege = findViewById(R.id.sp_collage);
        snWorked = findViewById(R.id.sp_worked_at);
        snWorks = findViewById(R.id.sp_works_at);
        snHomeTown = findViewById(R.id.sp_home_town);
        snLivesIn = findViewById(R.id.sp_lives_in);
        snLocation = findViewById(R.id.sp_location);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        tvDob=findViewById(R.id.tv_DOB);

        nameError = findViewById(R.id.tv_name_error);
        userNameError = findViewById(R.id.tvErrorUsername);
        homeError=findViewById(R.id.tv_home_eror);
        genderError = findViewById(R.id.tv_gender_error);
        bioError = findViewById(R.id.tvError_bio);
        dobError = findViewById(R.id.tv_dob_error);
        schoolError=findViewById(R.id.tv_school_error);
        collageError=findViewById(R.id.tv_collage_error);
        workedError=findViewById(R.id.tv_worked_error);
        worksError=findViewById(R.id.tv_works_error);
        livesError=findViewById(R.id.tv_lives_error);
        locatonError=findViewById(R.id.tv_location_error);
        tvImageError=findViewById(R.id.tv_imageError);

        designationError=findViewById(R.id.tv_desingnation_error);
        emailError=findViewById(R.id.tv_email_error);
        webError=findViewById(R.id.tv_website_error);
        numberError=findViewById(R.id.tv_phone_error);

        ivClick=findViewById(R.id.ic_click_image);
        ivClick.setOnClickListener(this);



        addTextWatcher();
        changeStatusBarColor();

        mDisplayDate = (TextView ) findViewById(R.id.tv_DOB);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get( Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ActivityEditProfile.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }

        };



             /*   Calendar mcurrentDate=Calendar.getInstance();
                final int year = mcurrentDate.get(Calendar.YEAR);
                final int month = mcurrentDate.get(Calendar.MONTH);
                final int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog   mDatePicker =new DatePickerDialog(ActivityEditProfile.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
                    {
                        tvDob.setText(new StringBuilder().append(year).append("-").append(month+1).append("-").append(day));
                        int month_k=selectedmonth+1;

                    }
                },year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date Here
                // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                mDatePicker.show();
            }
        });*/
        //Spinners

        String[] gender = {"Select Gender", "Male", "Female", "Others"};
        ArrayAdapter <String> adapter = new ArrayAdapter <String>(this, R.layout.spinner_color, gender);
        snGender.setAdapter(adapter);

        String[] school = {"Select School","Xabir public school", "Cambridge School", "RPS School"};
        ArrayAdapter <String> adapter1 = new ArrayAdapter <String>(this, R.layout.spinner_color, school);
        snSchool.setAdapter(adapter1);

        String[] college = {"Select College","DDU", "RGPV", "SIC"};
        ArrayAdapter <String> adapter2 = new ArrayAdapter <String>(this, R.layout.spinner_color, college);
        snCollege.setAdapter(adapter2);

        String[] worked = {"Select place","XYZ", "Mobiloitte", "ABC"};
        ArrayAdapter <String> adapter3 = new ArrayAdapter <String>(this, R.layout.spinner_color, worked);
        snWorked.setAdapter(adapter3);

        String[] works = {"Select workAt","XYZ", "Mobiloitte", "ABC"};
        ArrayAdapter <String> adapter4 = new ArrayAdapter <String>(this, R.layout.spinner_color, works);
        snWorks.setAdapter(adapter4);

        String[] homeTown = {"Select Home Town", "Varanasi", "Patna"};
        ArrayAdapter <String> adapter5 = new ArrayAdapter <String>(this, R.layout.spinner_color, homeTown);
        snHomeTown.setAdapter(adapter5);

        String[] livesIn = {"Select","Delhi", "Varanasi", "Patna"};
        ArrayAdapter <String> adapter6 = new ArrayAdapter <String>(this, R.layout.spinner_color, livesIn);
        snLivesIn.setAdapter(adapter6);

        String[] location = {"Select location","Delhi", "Varanasi", "Patna"};
        ArrayAdapter <String> adapter7 = new ArrayAdapter <String>(this, R.layout.spinner_color, location);
        snLocation.setAdapter(adapter7);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                if (checkValiadtion()) {

                    startActivity(new Intent(ActivityEditProfile.this, MainActivity.class));
                    Toast.makeText(this, "Saved Successfully.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

            case R.id.ic_click_image:
                SelectImage();
             break;
        }
    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera","Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder( ActivityEditProfile.this );
        builder.setTitle( "Add Image" );
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals( "Camera" )) {
                    Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                    startActivityForResult( intent, REQEST_CAMERA );
                } else if (items[which].equals( "Gallery" )) {
                    Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                    intent.setType( "image/*" );
                    startActivityForResult( intent, SELECT_FILE );
                } else if (items[which].equals( "Cancel" )) {
                    dialog.dismiss();
                }
            }
        } );
        builder.show();
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bmp = ( Bitmap ) bundle.get("data");
                ivProfile.setImageBitmap(bmp);
            }else if(requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                ivProfile.setImageURI(selectedImageUri);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_image, menu);
        return true;    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iv_avtar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }






    @SuppressLint("NewApi")
        public void   changeStatusBarColor(){
            Window window =  activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorBlack));

        }

   //validations


    private boolean checkValiadtion() {

       // String mediaPath=ivClick.getDrawable().toString().trim();


        String name = etName.getText().toString().trim();
        String username = etUserName.getText().toString().trim();
        String bio = etBio.getText().toString().trim();
        String dob = tvDob.getText().toString().trim();
         String designation=etDesignation.getText().toString().trim();
         String email=etEmail.getText().toString().trim();
         String website=etWebsite.getText().toString().trim();
         String phoneNo=etPhoneNumber.getText().toString().trim();



        if (ivClick.getDrawable() != null)
        {
            Toast.makeText(getApplicationContext(), "Please Pick Image", Toast.LENGTH_SHORT).show();
            return false ;

        }
        /*else if(ivClick.getDrawable()!=null){
            Toast.makeText(getApplicationContext(), " Pick Image", Toast.LENGTH_SHORT).show();
            return false;
        }
*/
       else if (name.isEmpty()) {
            nameError.setVisibility(View.VISIBLE);
            nameError.setText("*Please enter name.");
            return false;
        } else if (username.isEmpty()) {
            userNameError.setVisibility(View.VISIBLE);
            userNameError.setText("*Please enter username.");
            return false;
        }

              else if (snGender.getSelectedItem().toString().equalsIgnoreCase("Select Gender")) {
                genderError.setVisibility(View.VISIBLE);
                genderError.setText("*Please select gender.");
                return false;

        } else if (bio.isEmpty()) {
            bioError.setVisibility(View.VISIBLE);
            bioError.setText("*Please enter your bio.");
            return false;

       }  else if (dob.isEmpty()) {
            dobError.setVisibility(View.VISIBLE);
            dobError.setText("*Please select DOB.");
            return false;
        }


       else if (snSchool.getSelectedItem().toString().equalsIgnoreCase("Select School")) {
            schoolError.setVisibility(View.VISIBLE);
            schoolError.setText("*Please select school.");
            return false;
        }

        else if (snCollege.getSelectedItem().toString().equalsIgnoreCase("Select College")) {
            collageError.setVisibility(View.VISIBLE);
            collageError.setText("*Please select college.");
            return false;
        }
        else if (snWorked.getSelectedItem().toString().equalsIgnoreCase("Select place")) {
            workedError.setVisibility(View.VISIBLE);
            workedError.setText("*Please select worked place.");
            return false;
        }  else if (designation.isEmpty()){
            designationError.setVisibility(View.VISIBLE);
            designationError.setText("*Please enter your designation.");
            return false;
        }
        else if (snWorks.getSelectedItem().toString().equalsIgnoreCase("Select workAt")) {
            worksError.setVisibility(View.VISIBLE);
            worksError.setText("*Please select workAt.");
            return false;
        } else if (snHomeTown.getSelectedItem().toString().equalsIgnoreCase("Select Home Town")) {
            homeError.setVisibility(View.VISIBLE);
            homeError.setText("*Please select Home Town.");
            return false;
        }

        else if (snLivesIn.getSelectedItem().toString().equalsIgnoreCase("Select")) {
            livesError.setVisibility(View.VISIBLE);
            livesError.setText("*Please select place lives in.");
            return false;
        }



        else if (email.isEmpty()){
            emailError.setVisibility(View.VISIBLE);
            emailError.setText("*Please enter your email.");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            emailError.setVisibility(View.VISIBLE);
            emailError.setText("*Please enter a valid email");
            return false;
        }

        else if (website.isEmpty()){
            webError.setVisibility(View.VISIBLE);
            webError.setText("*Please enter your website.");
            return false;
        }
        else if (!Patterns.WEB_URL.matcher(etWebsite.getText().toString()).matches()) {
            webError.setVisibility(View.VISIBLE);
            webError.setText("*Please enter a valid url");
            return false;}
            else if (phoneNo.isEmpty()){
            numberError.setVisibility(View.VISIBLE);
            numberError.setText("*Please enter your phoneNumber.");
            return false;
        }


       else if (etPhoneNumber.getText().toString().length()<10||etPhoneNumber.getText().toString().length()>10) {
     //  else if (!Patterns.PHONE.matcher(etPhoneNumber.getText().toString()).matches()) {

            numberError.setVisibility(View.VISIBLE);
            numberError.setText("*Please enter valid phoneNumber.");
           return false;}



        else if (snLocation.getSelectedItem().toString().equalsIgnoreCase("Select location")) {
            locatonError.setVisibility(View.VISIBLE);
            locatonError.setText("*Please select location.");
            return false;}

        return true;

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        nameError.setVisibility(View.INVISIBLE);
        userNameError.setVisibility(View.INVISIBLE);
        bioError.setVisibility(View.INVISIBLE);
        designationError.setVisibility(View.INVISIBLE);
        emailError.setVisibility(View.INVISIBLE);
        webError.setVisibility(View.INVISIBLE);
        numberError.setVisibility(View.INVISIBLE);
        dobError.setVisibility(View.INVISIBLE);
        genderError.setVisibility(View.INVISIBLE);
        schoolError.setVisibility(View.INVISIBLE);
        collageError.setVisibility(View.INVISIBLE);
        workedError.setVisibility(View.INVISIBLE);
        worksError.setVisibility(View.INVISIBLE);
        homeError.setVisibility(View.INVISIBLE);
        livesError.setVisibility(View.INVISIBLE);
        locatonError.setVisibility(View.INVISIBLE);


    }

    @Override
    public void afterTextChanged(Editable editable) {
    }


    private void addTextWatcher() {

        etName = findViewById(R.id.et_name);
        etUserName = findViewById(R.id.et_username);
        etBio = findViewById(R.id.et_bio);
        etDesignation=findViewById(R.id.et_designation);
        etEmail=findViewById(R.id.et_email);
        etWebsite=findViewById(R.id.et_website);
        etPhoneNumber=findViewById(R.id.et_phone);
        tvDob=findViewById(R.id.tv_DOB);


        etName.addTextChangedListener(this);
        etUserName.addTextChangedListener(this);
        etBio.addTextChangedListener(this);
        etDesignation.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etWebsite.addTextChangedListener(this);
        etPhoneNumber.addTextChangedListener(this);
        tvDob.addTextChangedListener(this);
    }
}





