package cn.project.aoyolo.cooperation_v1.ui.my.login;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.utils.BombSMSUtils;


/**
 * 注册
 * Created by Hy on 2015/11/3.
 */
public class RegisterActivity extends BaseActivity {
    private TextView tvTitle;
    private EditText etPhone,etPsw,etValid;
    private String phone,psw,valid;
    private Button btRegister,btValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.my_info);
        tvTitle.setText("手机号注册");
        etPhone=(EditText)findViewById(R.id.etPhone);
        etPsw=(EditText)findViewById(R.id.etPsw);
        etValid=(EditText)findViewById(R.id.etValid);
        initEtPhone();
        btRegister=(Button)findViewById(R.id.btRegister);
        btValid=(Button)findViewById(R.id.btValid);
        BombSMSUtils.getInstance(this);//注册短信服务
        initBtValid();
    }

    /**
     * 获取验证码
     */
    private void initBtValid() {
        btValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btValid.isEnabled())
                    btValid.setEnabled(false);
                String phoneNumber=etPhone.getText().toString();
//                BombSMSUtils.sendRegisterSMS(phoneNumber,);
            }
        });
    }

    private void initEtPhone() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //当输入字符达到11个时，注册按钮可用
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==11)
                {
                    if(!btValid.isEnabled())
                        btValid.setEnabled(true);
                }
                else
                {
                    btValid.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void back(View view)
    {
        finish();
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }


}
