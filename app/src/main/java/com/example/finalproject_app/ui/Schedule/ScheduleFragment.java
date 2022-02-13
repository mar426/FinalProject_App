package com.example.finalproject_app.ui.Schedule;

import static com.example.finalproject_app.ui.Profile.ProfileFragment.temp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject_app.R;
import com.example.finalproject_app.databinding.FragmentDashboardBinding;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;
    private FragmentDashboardBinding binding;
    private ImageView attractionImage;
    private TextView email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_attractions_details, container, false);
        email = view.findViewById(R.id.user_profile_mail);
//        scheduleViewModel =
//                new ViewModelProvider(this).get(ScheduleViewModel.class);
//
//        binding = FragmentDashboardBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textDashboard;
//        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        attractionImage=view.findViewById(R.id.deatailsFrag_attraction_image);
        Log.d("try", "t"+temp);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}