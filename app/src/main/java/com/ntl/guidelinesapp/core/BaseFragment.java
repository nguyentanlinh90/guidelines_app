package com.ntl.guidelinesapp.core;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.NavigationDrawerToolbarFragmentActivity;

public class BaseFragment extends Fragment {

    public FirebaseUser getUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void reAuthenticateUser(NavigationDrawerToolbarFragmentActivity activity, IReAuthenticateListener listener) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_re_authenticate_user);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;

        window.setAttributes(layoutParams);

        dialog.setCancelable(false);

        String strEmail = ((EditText) dialog.findViewById(R.id.edt_email)).getText().toString();
        String strPassword = ((EditText) dialog.findViewById(R.id.edt_password)).getText().toString();
        Button btnNo = dialog.findViewById(R.id.bt_no);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm);

        btnNo.setOnClickListener(v -> dialog.dismiss());

        btnConfirm.setOnClickListener(v -> {
            activity.showProgress();
            AuthCredential credential = EmailAuthProvider
                    .getCredential(strEmail, strPassword);

            // Prompt the user to re-provide their sign-in credentials
            FirebaseUser user = getUser();
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                listener.onComplete();
                            } else {
                                activity.dismissProgress();
                                Toast.makeText(activity, "Email or Password wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        dialog.show();
    }
}
