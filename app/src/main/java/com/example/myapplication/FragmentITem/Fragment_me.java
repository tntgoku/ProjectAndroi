package com.example.myapplication.FragmentITem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Controller.IntentKeys;
import com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.Controller.ui.LoginViewModelFactory;
import com.example.myapplication.Controller.ui.login.LoginActivity;
import com.example.myapplication.Controller.ui.login.LoginViewModel;
import com.example.myapplication.ActivityiTem.MainActivity2;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMeBinding;

public class Fragment_me extends Fragment implements eventSystem {
        TextView tv,tv1,tv2;
        ImageView img,img1,img2,img3,img4;
        private final int REQUEST_LOGIN=1;
        FragmentMeBinding binding;
        LoginViewModel  logo;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMeBinding.inflate(inflater, container, false);
         logo= new ViewModelProvider(requireActivity(), new LoginViewModelFactory())
                .get(LoginViewModel.class);
        setID();
        eVentID();

        return  binding.getRoot();
    }

    @Override
    public void setID() {
        tv=binding.logintext;
        tv1=binding.registertext;
        tv2=binding.logout;
        img= binding.cartus;
    }

    @Override
    public void eVentID() {
        loginn();
        gotocart(this);

        // Xử lý sự kiện đăng xuất
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi phương thức logout trong ViewModel
                logo.logout();
                // Hiển thị thông báo hoặc cập nhật UI
                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Đăng nhập");
                        tv1.setVisibility(View.VISIBLE);
                        IntentKeys.setIsuser(false);
                    }
                },3000);
            }
        });
    }
    private  void loginn(){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!IntentKeys.isIsuser()){
                    Intent i= new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(i,REQUEST_LOGIN);
                }else{
                    Intent i= new Intent(getActivity(), MainActivity2.class);
                    startActivity(i);
                }

            }
        });
    }

    private  void gotocart(Fragment fragment){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Fragment_me", "Image clicked, replacing with Fragment_1");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment,new Fragment_1()).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                String username = data.getStringExtra(IntentKeys.EXTRA_RESULT);
                // Cập nhật UI hoặc thực hiện hành động nào đó với username
                tv.setText(username);
                tv1.setVisibility(View.GONE);
            }
        }
    }


}
