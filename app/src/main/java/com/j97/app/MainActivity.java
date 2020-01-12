package com.j97.app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.j97.app.ui.input.InputFragment;
import com.j97.app.ui.note.NoteFragment;
import com.j97.app.ui.output.OutputFragment;

import java.util.Arrays;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {

  private MenuItem prevMenuItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final BottomNavigationView navView = findViewById(R.id.nav_view);
    final ViewPager viewPager = findViewById(R.id.view_pager);

    final List<Fragment> fragments = Arrays.asList(
        new InputFragment(),
        new OutputFragment(),
        new NoteFragment()
    );
    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
      @NonNull
      @Override
      public Fragment getItem(int position) {
        return fragments.get(position);
      }

      @Override
      public int getCount() {
        return fragments.size();
      }
    });

    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
          case R.id.bottom_nav_input:
            viewPager.setCurrentItem(0, true);
            break;

          case R.id.bottom_nav_output:
            viewPager.setCurrentItem(1, true);
            break;
          case R.id.bottom_nav_note:
            viewPager.setCurrentItem(2, true);
            break;
        }
        return false;
      }
    });

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        if (prevMenuItem != null) {
          prevMenuItem.setChecked(false);
        } else {
          navView.getMenu().getItem(0).setChecked(false);
        }

        navView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = navView.getMenu().getItem(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }
}
