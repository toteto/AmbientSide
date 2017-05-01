package com.antonio.example.ambientside;

import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.activity.WearableActivityDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.AmbientItemVH>
  implements WearableActivityDelegate.AmbientCallback {
  @Override
  public AmbientItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new AmbientItemVH(inflater.inflate(R.layout.ambient_item, parent, false));
  }

  @Override
  public void onBindViewHolder(AmbientItemVH holder, int position) {
    holder.bind("Item #" + position);
  }

  @Override
  public void onBindViewHolder(AmbientItemVH holder, int position, List<Object> payloads) {
    if (!payloads.isEmpty()) {
      for (Object payload : payloads) {
        if (payload.equals(R.id.enter_ambient)) {
          holder.onEnterAmbient(null);
        } else if (payload.equals(R.id.update_ambient)) {
          holder.onUpdateAmbient();
        } else if (payload.equals(R.id.exit_ambient)) {
          holder.onExitAmbient();
        } else {
          super.onBindViewHolder(holder, position, payloads);
        }
      }
    } else {
      super.onBindViewHolder(holder, position, payloads);
    }
  }

  @Override
  public int getItemCount() {
    return 20;
  }

  @Override
  public void onEnterAmbient(Bundle bundle) {
    notifyItemRangeChanged(0, getItemCount(), R.id.enter_ambient);
  }

  @Override
  public void onUpdateAmbient() {
    notifyItemRangeChanged(0, getItemCount(), R.id.update_ambient);
  }

  @Override
  public void onExitAmbient() {
    notifyItemRangeChanged(0, getItemCount(), R.id.exit_ambient);
  }

  static class AmbientItemVH extends RecyclerView.ViewHolder implements WearableActivityDelegate.AmbientCallback {
    private TextView textView;

    public AmbientItemVH(View itemView) {
      super(itemView);
      textView = (TextView) itemView.findViewById(R.id.textView);
    }

    void bind(String content) {
      textView.setText(content);
    }

    @Override
    public void onEnterAmbient(Bundle bundle) {
      TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_Wearable_Large_Inverse);
    }

    @Override
    public void onUpdateAmbient() {
      // do nothing
    }

    @Override
    public void onExitAmbient() {
      TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_Wearable_Large);
    }
  }
}
