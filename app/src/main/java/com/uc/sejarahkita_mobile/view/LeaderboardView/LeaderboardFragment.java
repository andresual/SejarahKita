package com.uc.sejarahkita_mobile.view.LeaderboardView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.uc.sejarahkita_mobile.R;
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.model.Leaderboard;
import com.uc.sejarahkita_mobile.model.response.LeaderboardResponse;
import com.uc.sejarahkita_mobile.model.response.LeaderboardsItem;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {
    TextView btn_detail_easy_leaderboard_fragment, btn_detail_hard_leaderboard_fragment, tbody1_easy_username_leaderboard_fragment, tbody2_easy_username_leaderboard_fragment, tbody3_easy_username_leaderboard_fragment,
            tbody1_easy_rp_leaderboard_fragment, tbody2_easy_rp_leaderboard_fragment, tbody3_easy_rp_leaderboard_fragment,
            tbody1_hard_username_leaderboard_fragment, tbody2_hard_username_leaderboard_fragment, tbody3_hard_username_leaderboard_fragment,
            tbody1_hard_rp_leaderboard_fragment, tbody2_hard_rp_leaderboard_fragment, tbody3_hard_rp_leaderboard_fragment;

    private LeaderboardViewModel leaderboardViewModel;
    private SharedPreferenceHelper helper;

    List<Leaderboard.Leaderboards> easyLeaderboard = new ArrayList<>();
    List<Leaderboard.Leaderboards> hardLeaderboard = new ArrayList<>();

    List<LeaderboardsItem> easyLeaderboardv2 = new ArrayList<>();
    List<LeaderboardsItem> hardLeaderboardv2 = new ArrayList<>();

    public LeaderboardFragment() {
    }

    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        leaderboardViewModel = new ViewModelProvider(getActivity()).get(LeaderboardViewModel.class);
        leaderboardViewModel.init(helper.getAccessToken());
        leaderboardViewModel.getLeaderboards();
//        leaderboardViewModel.getResultLeaderboards().observe(getActivity(), showLeaderboard);

        leaderboardViewModel.getLeaderboardEasy();
        leaderboardViewModel.getResultLeaderboardEasy().observe(getActivity(), new Observer<LeaderboardResponse>() {
            @Override
            public void onChanged(LeaderboardResponse leaderboardResponse) {
                easyLeaderboardv2 = leaderboardResponse.getLeaderboards();

                if (!easyLeaderboardv2.isEmpty() && easyLeaderboardv2.size() > 2) {
                    tbody1_easy_username_leaderboard_fragment.setText(String.valueOf(easyLeaderboardv2.get(0).getIdStudent()));
                    tbody2_easy_username_leaderboard_fragment.setText(String.valueOf(easyLeaderboardv2.get(1).getIdStudent()));
                    tbody3_easy_username_leaderboard_fragment.setText(String.valueOf(easyLeaderboardv2.get(2).getIdStudent()));
                    tbody1_easy_rp_leaderboard_fragment.setText(String.valueOf(easyLeaderboardv2.get(0).getRankedPoint()));
                    tbody2_easy_rp_leaderboard_fragment.setText(String.valueOf(easyLeaderboardv2.get(1).getRankedPoint()));
                    tbody3_easy_rp_leaderboard_fragment.setText(String.valueOf(easyLeaderboardv2.get(2).getRankedPoint()));
                }
            }
        });

        leaderboardViewModel.getLeaderboardHard();
        leaderboardViewModel.getResultLeaderboardHard().observe(getActivity(), new Observer<LeaderboardResponse>() {
            @Override
            public void onChanged(LeaderboardResponse leaderboardResponse) {
                hardLeaderboardv2 = leaderboardResponse.getLeaderboards();

                if (!hardLeaderboardv2.isEmpty() && hardLeaderboardv2.size() > 2) {
                    tbody1_hard_username_leaderboard_fragment.setText(String.valueOf(hardLeaderboardv2.get(0).getIdStudent()));
                    tbody2_hard_username_leaderboard_fragment.setText(String.valueOf(hardLeaderboardv2.get(1).getIdStudent()));
                    tbody3_hard_username_leaderboard_fragment.setText(String.valueOf(hardLeaderboardv2.get(2).getIdStudent()));
                    tbody1_hard_rp_leaderboard_fragment.setText(String.valueOf(hardLeaderboardv2.get(0).getRankedPoint()));
                    tbody2_hard_rp_leaderboard_fragment.setText(String.valueOf(hardLeaderboardv2.get(1).getRankedPoint()));
                    tbody3_hard_rp_leaderboard_fragment.setText(String.valueOf(hardLeaderboardv2.get(2).getRankedPoint()));
                }
            }
        });

        btn_detail_easy_leaderboard_fragment = view.findViewById(R.id.btn_detail_easy_leaderboard_fragment);
        btn_detail_hard_leaderboard_fragment = view.findViewById(R.id.btn_detail_hard_leaderboard_fragment);
        tbody1_easy_username_leaderboard_fragment = view.findViewById(R.id.tbody1_easy_username_leaderboard_fragment);
        tbody2_easy_username_leaderboard_fragment = view.findViewById(R.id.tbody2_easy_username_leaderboard_fragment);
        tbody3_easy_username_leaderboard_fragment = view.findViewById(R.id.tbody3_easy_username_leaderboard_fragment);
        tbody1_easy_rp_leaderboard_fragment = view.findViewById(R.id.tbody1_easy_rp_leaderboard_fragment);
        tbody2_easy_rp_leaderboard_fragment = view.findViewById(R.id.tbody2_easy_rp_leaderboard_fragment);
        tbody3_easy_rp_leaderboard_fragment = view.findViewById(R.id.tbody3_easy_rp_leaderboard_fragment);
        tbody1_hard_username_leaderboard_fragment = view.findViewById(R.id.tbody1_hard_username_leaderboard_fragment);
        tbody2_hard_username_leaderboard_fragment = view.findViewById(R.id.tbody2_hard_username_leaderboard_fragment);
        tbody3_hard_username_leaderboard_fragment = view.findViewById(R.id.tbody3_hard_username_leaderboard_fragment);
        tbody1_hard_rp_leaderboard_fragment = view.findViewById(R.id.tbody1_hard_rp_leaderboard_fragment);
        tbody2_hard_rp_leaderboard_fragment = view.findViewById(R.id.tbody2_hard_rp_leaderboard_fragment);
        tbody3_hard_rp_leaderboard_fragment = view.findViewById(R.id.tbody3_hard_rp_leaderboard_fragment);

        btn_detail_easy_leaderboard_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LeaderboardFragmentDirections.actionLeaderboardFragmentToDetailLeaderboardFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        btn_detail_hard_leaderboard_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LeaderboardFragmentDirections.actionLeaderboardFragmentToDetailLeaderboardFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private Observer<Leaderboard> showLeaderboard = new Observer<Leaderboard>() {
        @Override
        public void onChanged(Leaderboard leaderboard) {
            easyLeaderboard = leaderboard.getLeaderboards();
            hardLeaderboard = leaderboard.getLeaderboards();

            if (!easyLeaderboard.isEmpty() && easyLeaderboard.size() > 2) {
                tbody1_easy_username_leaderboard_fragment.setText(String.valueOf(easyLeaderboard.get(0).getId_student()));
                tbody2_easy_username_leaderboard_fragment.setText(String.valueOf(easyLeaderboard.get(1).getId_student()));
                tbody3_easy_username_leaderboard_fragment.setText(String.valueOf(easyLeaderboard.get(2).getId_student()));
                tbody1_easy_rp_leaderboard_fragment.setText(String.valueOf(easyLeaderboard.get(0).getRanked_point()));
                tbody2_easy_rp_leaderboard_fragment.setText(String.valueOf(easyLeaderboard.get(1).getRanked_point()));
                tbody3_easy_rp_leaderboard_fragment.setText(String.valueOf(easyLeaderboard.get(2).getRanked_point()));
                tbody1_hard_username_leaderboard_fragment.setText(String.valueOf(hardLeaderboard.get(0).getId_student()));
                tbody2_hard_username_leaderboard_fragment.setText(String.valueOf(hardLeaderboard.get(1).getId_student()));
                tbody3_hard_username_leaderboard_fragment.setText(String.valueOf(hardLeaderboard.get(2).getId_student()));
                tbody1_hard_rp_leaderboard_fragment.setText(String.valueOf(hardLeaderboard.get(0).getRanked_point()));
                tbody2_hard_rp_leaderboard_fragment.setText(String.valueOf(hardLeaderboard.get(1).getRanked_point()));
                tbody3_hard_rp_leaderboard_fragment.setText(String.valueOf(hardLeaderboard.get(2).getRanked_point()));
            }
        }
    };

//    public void getFilteredLeaderboard(List<Leaderboard.Leaderboards> leaderboards){
//        List<Leaderboard.Leaderboards> filteredLeaderboard = new ArrayList<Leaderboard.Leaderboards>();
//        for (Leaderboard.Leaderboards leaderboard:leaderboards){
//            if (leaderboard.get);
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}