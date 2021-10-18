package com.my.fromerapp.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.my.fromerapp.MainActivity;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityLoginBinding;
import com.my.fromerapp.model.LoginModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    ActivityLoginBinding binding;
    String  result="";
    private SessionManager sessionManager;

    //Google SignIn
    private RelativeLayout RR_faceBook_login;
    private RelativeLayout RR_google_login;
    private SignInButton signInButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    private GoogleApiClient googleApiClient;

    String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        sessionManager = new SessionManager(Login.this);

        SetupUI();
        
        binding.imgGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(runnable -> {
            token = runnable.getToken();
            Log.e( "Tokennnn" ,token);
        });

        //Google SignIn
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    //Google Login
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
         FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent( data );
            handleSignInResult( result );
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String UsernAME=account.getDisplayName();
            String email=account.getEmail();
            String SocialId=account.getId();
            Uri Url=account.getPhotoUrl();

           Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
/*

            if (sessionManager.isNetworkAvailable()) {

                progressBar.setVisibility(View.VISIBLE);

                SocialLoginMethod(UsernAME,"123456",email,"fghgh",SocialId);

            }else {

                Toast.makeText(LoginActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }
*/

        } else {

            Toast.makeText( this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();

        }
    }


    private void SetupUI() {

        binding.loginID.setOnClickListener(v -> {

            validation();

        });

        binding.llSingUp.setOnClickListener(v -> {
             startActivity(new Intent(this, SignUpActivity.class));
        });

        binding.txtForogtPassword.setOnClickListener(v -> {
                startActivity(new Intent(this, ForogotPassword.class));
        });
    }


    private void validation() {

       String email = binding.etEmaillogin.getText().toString();
        String password =  binding.etPasswordlogin.getText().toString();

        if(email.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter email/mobile.", Toast.LENGTH_SHORT).show();

        }else if(password.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Password.", Toast.LENGTH_SHORT).show();

        }else
        {
            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                loginMethod(email,password);

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void loginMethod(String emai,String password){

        Call<LoginModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .login(emai,password,token);
           call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                LoginModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    Preference.save(Login.this,Preference.KEY_user_id,finallyPr.getResult().getId());

                    startActivity(new Intent(Login.this, MainActivity.class));


                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}