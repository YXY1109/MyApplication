// Generated by view binder compiler. Do not edit!
package com.kjsc.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.kjsc.myapplication.R;
import com.suke.widget.SwitchButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySwitchBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final SwitchButton switchButton;

  @NonNull
  public final TextView tvMarquee;

  private ActivitySwitchBinding(@NonNull LinearLayout rootView, @NonNull SwitchButton switchButton,
      @NonNull TextView tvMarquee) {
    this.rootView = rootView;
    this.switchButton = switchButton;
    this.tvMarquee = tvMarquee;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySwitchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySwitchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_switch, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySwitchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.switch_button;
      SwitchButton switchButton = rootView.findViewById(id);
      if (switchButton == null) {
        break missingId;
      }

      id = R.id.tv_marquee;
      TextView tvMarquee = rootView.findViewById(id);
      if (tvMarquee == null) {
        break missingId;
      }

      return new ActivitySwitchBinding((LinearLayout) rootView, switchButton, tvMarquee);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}