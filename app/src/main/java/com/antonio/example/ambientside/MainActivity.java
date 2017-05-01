package com.antonio.example.ambientside;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.view.ViewGroup;

public class MainActivity extends WearableActivity {
  private final MyAdapter mMyAdapter = new MyAdapter();

  private ViewGroup mContainer;
  private RecyclerView mRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setAmbientEnabled();

    mContainer = (ViewGroup) findViewById(R.id.container);
    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(mMyAdapter);
  }

  @Override
  public void onEnterAmbient(Bundle ambientDetails) {
    super.onEnterAmbient(ambientDetails);
    mContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
    mMyAdapter.onEnterAmbient(ambientDetails);
  }

  @Override
  public void onUpdateAmbient() {
    super.onUpdateAmbient();
    mMyAdapter.onUpdateAmbient();
  }

  @Override
  public void onExitAmbient() {
    super.onExitAmbient();
    mContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
    mMyAdapter.onExitAmbient();
  }
}
