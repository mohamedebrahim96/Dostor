package com.vacuum.app.dostor;

/**
 * Created by Home on 10/14/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONObject;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;
import com.vacuum.app.dostor.NavigationDrawer.PrivacyPolicyActivity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import io.fabric.sdk.android.Fabric;

import retrofit2.Call;


public class SplashScreen extends Activity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final String TAG ="GOOGLE: " ;
    private static final String TWITTER_KEY = "D53zRds75GVfwNJ5ayb57dZTT";
    private static final String TWITTER_SECRET ="kwdqsf7F8olPxARgD6Yrkq8CA3euYrtHPAuTYn7ZvJCjBEaeDi";
    private static final String TAG_twitter = "TwitterLogin" ;
    SharedPreferences.Editor editor;
    private static int SPLASH_TIME_OUT = 3000;
    private static final int RC_SIGN_IN = 007;
    CallbackManager callbackManager;
    JSONObject response2, profile_pic_data, profile_pic_url;
    String name ,email,imageurl,id,cover;
    Context context;
    TextView later;
    LinearLayout social;
    LoginButton loginButton;
    SignInButton SignInButton;
    TwitterLoginButton twitterLoginButton;
    public GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_splash);

        later =  findViewById(R.id.later);
        social =  findViewById(R.id.social);
        loginButton =  findViewById(R.id.login_button);
        TextView terms =  findViewById(R.id.terms);
        TextView terms2 =  findViewById(R.id.terms2);

        context = this.getApplicationContext();
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        SharedPreferences prefsss = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefsss.getString("name", null);
        if (restoredText != null) {
            social.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                   skipSplash();
                }
            }, SPLASH_TIME_OUT);
        }


        Log.d("KeyHash:", getKeyHash(context));
        Log.d("KeyHash:SHA-1: ", getCertificateSHA1Fingerprint());

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.vacuum.app.dostor",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               skipSplash();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        Typeface face2 = Typeface.createFromAsset(getAssets(),
                "fonts/airbnb.ttf");
        Typeface face3 = Typeface.createFromAsset(getAssets(),
                "fonts/DK Magical Brush.otf");


        terms.setTypeface(face2);
        terms2.setTypeface(face2);
        later.setTypeface(face3);

        terms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this, PrivacyPolicyActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        Facebook();
        Twitter();
        Google();

    }



    private void Facebook() {
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    private void Twitter() {

        twitterLoginButton =  findViewById(R.id.twitterLoginButton);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = result.data;
                final String userName = session.getUserName();

                //Getting the account service of the user logged in
                Call<User> call = Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true, false);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void failure(TwitterException e) {
                        //If any error occurs handle it here
                    }
                    @Override
                    public void success(Result<User> userResult) {
                        //If it succeeds creating a User object from userResult.data
                        User user = userResult.data;
                        twitterLoginButton.setVisibility(View.GONE);

                        //email = user.email;

                        name = user.name;
                        imageurl = user.profileImageUrl;
                        cover = user.profileBannerUrl;
                        editor.putString("email", userName);
                        editor.putString("name", name);
                        editor.putString("imageurl", imageurl);
                        editor.putString("cover", cover);
                        editor.apply();
                        skipSplash();

                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG_twitter, "Login with Twitter failure", exception);
            }
        });


    }
    private void Google() {
        SignInButton = findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //twitter
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        //GoogleSignIn
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());
            name = acct.getDisplayName();
            imageurl = acct.getPhotoUrl().toString();
            email = acct.getEmail();


            editor.putString("email", email);
            editor.putString("name", name);
            editor.putString("imageurl", imageurl);
            //editor.putString("cover", cover);

            editor.apply();
            skipSplash();
        } else {


            Toast.makeText(context, "Erorr google", Toast.LENGTH_SHORT).show();
        }
    }

    protected void getUserDetails(final LoginResult loginResult) {
        Bundle params = new Bundle();
        params.putBoolean("redirect", false);
        params.putString("fields", "cover");

        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        skipSplash();
                        System.out.println("====================================");
                        String token = AccessToken.getCurrentAccessToken().getToken();
                        Log.d("access token is: ",token);
                        System.out.println("====================================");
                        Log.i("json_object",json_object.toString());
                        try {
                            response2 = new JSONObject(json_object.toString());
                            email = response2.get("email").toString();
                            name = response2.get("name").toString();
                            id = response2.get("id").toString();

                            profile_pic_data = new JSONObject(response2.get("picture").toString());
                            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
                            imageurl = profile_pic_url.getString("url");

                            JSONObject JOSource =  new JSONObject(response2.getJSONObject("cover").toString());
                            cover = JOSource.getString("source");


                            editor.putString("email", email);
                            editor.putString("name", name);
                            editor.putString("imageurl", imageurl);
                            editor.putString("cover", cover);

                            editor.apply();
                            Toast.makeText(context,"Login sucessful" , Toast.LENGTH_SHORT).show();
                        } catch(Exception e){
                            e.printStackTrace();
                        }

                    }



                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,cover,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }


    private void skipSplash()
    {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public String getKeyHash(final Context context) {

        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(
                    "com.vacuum.app.dostor",
                    PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info == null)
            return null;

        for (Signature signature : info.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }


    private String getCertificateSHA1Fingerprint() {
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        X509Certificate c = null;
        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        String hexString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(c.getEncoded());
            hexString = byte2HexFormatted(publicKey);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        return hexString;
    }

    public static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1) h = "0" + h;
            if (l > 2) h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1)) str.append(':');
        }
        return str.toString();
    }
}