package shem.com.materiallogin;

import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

/**
 * Created by shem on 1/15/16.
 */
public interface MaterialLoginViewListener {
    Boolean onRegister(TextInputLayout registerName, TextInputLayout registerUser,
                       TextInputLayout registerPass, TextInputLayout registerPassRep,
                       TextView registerBtn);

    Boolean onLogin(TextInputLayout logiUser, TextInputLayout loginPass, TextView loginBtn);
}
