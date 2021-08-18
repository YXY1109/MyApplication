// Generated by view binder compiler. Do not edit!
package com.kjsc.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.kjsc.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityVideoBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView button1;

  @NonNull
  public final ImageView button2;

  @NonNull
  public final ImageView button3;

  @NonNull
  public final VideoView videoview;

  private ActivityVideoBinding(@NonNull LinearLayout rootView, @NonNull ImageView button1,
      @NonNull ImageView button2, @NonNull ImageView button3, @NonNull VideoView videoview) {
    this.rootView = rootView;
    this.button1 = button1;
    this.button2 = button2;
    this.button3 = button3;
    this.videoview = videoview;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityVideoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_video, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityVideoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button1;
      ImageView button1 = rootView.findViewById(id);
      if (button1 == null) {
        break missingId;
      }

      id = R.id.button2;
      ImageView button2 = rootView.findViewById(id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.button3;
      ImageView button3 = rootView.findViewById(id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.videoview;
      VideoView videoview = rootView.findViewById(id);
      if (videoview == null) {
        break missingId;
      }

      return new ActivityVideoBinding((LinearLayout) rootView, button1, button2, button3,
          videoview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}