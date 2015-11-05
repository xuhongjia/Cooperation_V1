package cn.project.aoyolo.cooperation_v1.ui.my;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.kymjs.kjframe.http.HttpCallBack;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.project.aoyolo.cooperation_v1.API.FuWuApi;
import cn.project.aoyolo.cooperation_v1.API.UpLoadFileApi;
import cn.project.aoyolo.cooperation_v1.ActivityManager;
import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.UserManager;
import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;
import cn.project.aoyolo.cooperation_v1.entity.HomeMaking;

public class JzfenleiActivity extends BaseActivity {
    @ViewInject(R.id.img_head)
    private ImageButton img_head;
    @ViewInject(R.id.jzType)
    private RadioGroup jzType;
    @ViewInject(R.id.name)
    private EditText name;
    @ViewInject(R.id.xzChoose)
    private TextView xzChoose;
    @ViewInject(R.id.note)
    private EditText note;
    @ViewInject(R.id.phone)
    private EditText phone;
    @ViewInject(R.id.stopTime)
    private TextView stopTime;
    private HomeMaking homeMaking;
    private Bitmap photo;
    private Gson gson = new Gson();
    Handler handler;
    private AlertDialog myDialog = null;
    private String[] xinzi = new String[]{"1000-2000","2001-3000","3001-4000"};
    private List<String> data = new ArrayList<String>();
    private Calendar stopCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzfenlei);
        ViewUtils.inject(this);
        init();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1){
                    Toast.makeText(JzfenleiActivity.this,"成功",Toast.LENGTH_LONG).show();
                }
            }
        };
    }
    //初始化数据
    public void init(){
        homeMaking = new HomeMaking();
        for(String s : xinzi)
        {
            data.add(s);
        }
    }
    //按钮点击事件
    @OnClick({R.id.img_head,R.id.xzChoose,R.id.stopTime,R.id.send,R.id.back})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.img_head:
                setImg();
                break;
            case R.id.xzChoose:
                xzChoose();
                break;
            case R.id.stopTime:
                stopTime();
                break;
            case R.id.send:
                send();
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
    //选择结束日期
    public void stopTime(){
        final Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                stopCalendar.set(year,monthOfYear,dayOfMonth);
                if(stopCalendar.compareTo(calendar)>0) {
                    stopTime.setText(DateFormat.format("yyyy-MM-dd", stopCalendar));
                }
                else
                {
                    stopTime.setText(DateFormat.format("yyyy-MM-dd",calendar));
                    Toast.makeText(JzfenleiActivity.this,"请选择至少比当前大一天的日期.....",Toast.LENGTH_LONG).show();
                }
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    //选择薪资水平
    public void xzChoose(){
        myDialog = new AlertDialog.Builder(this).create();
        myDialog.show();
        myDialog.getWindow().setContentView(R.layout.my_alert_dialog);
        ListView listView =(ListView)myDialog.getWindow().findViewById(R.id.alert_list_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xzChoose.setText(data.get(position).toString().trim());
                myDialog.dismiss();
            }
        });
    }
    //发送提交请求
    public void send(){
        if(CheckData())
        {
            File file = new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME);
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                photo.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            UpLoadFileApi.upLoadOneImg(file, new HttpCallBack() {
                @Override
                public void onSuccess(String t) {
                    super.onSuccess(t);
                    GeneralResponse<String> response = gson.fromJson(t,new TypeToken<GeneralResponse<String>>(){}.getType());
                    if(response.isSuccess()) {
                        homeMaking.setImg(response.getData().toString().trim());
                        homeMaking.setName(name.getText().toString().trim());
                        homeMaking.setFlag(0);
                        homeMaking.setNote(note.getText().toString().trim());
                        homeMaking.setPublishTime(System.currentTimeMillis());
                        homeMaking.setPhone(phone.getText().toString().trim());
                        homeMaking.setStopTime(stopCalendar.getTimeInMillis());
                        homeMaking.setSalary(xzChoose.getText().toString().trim());
                        int type = getJzType(jzType.getCheckedRadioButtonId());
                        homeMaking.setType(type);
                        homeMaking.setVolume(0);
                        homeMaking.setEvaluationNumber(0);
                        homeMaking.setuId(UserManager.getInstance().getUser().getId());
                        FuWuApi.addJzFuWu(gson.toJson(homeMaking), new HttpCallBack() {
                            @Override
                            public void onSuccess(String t) {
                                super.onSuccess(t);
                                ActivityManager.removeActivity(ActivityManager.getTopActivity());
                                ActivityManager.getTopActivity().finish();
                                finish();
                            }
                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                super.onFailure(errorNo, strMsg);
                            }
                        });
                    }else
                    {
                        makeTextLong("请求失败，请重试.....");
                    }
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    super.onFailure(errorNo, strMsg);
                }
            });
        }
    }
    //jzType选择
    public int getJzType(int radioutton){
        switch (radioutton)
        {
            case R.id.baomu:
                return 0;
            case R.id.yuesao:
                return 1;
            case R.id.zhongdiangong:
                return 2;
            default:
                return 0;
        }
    }
    //检查数据
    public boolean CheckData(){
        if(photo==null)
        {
            BitmapDrawable da = (BitmapDrawable) getResources().getDrawable(R.mipmap.defeat_header);
            photo=da.getBitmap();
        }else if(name.getText().toString().trim().equals("")){
            makeTextLong("请输入标题！");
            return false;
        }
        else if(phone.getText().toString().trim().length()<11) {
            makeTextLong("请输入正确的手机号！");
            return false;
        }
        else if(stopTime.getText().toString().trim().equals("请选择时间")) {
            makeTextLong("请选择结束时间！");
            return false;
        }
        else if(xzChoose.getText().toString().trim().equals("请选择薪资类别")){
            makeTextLong("请选择您期望的薪资！");
            return false;
        }
        return true;
    }

    //返回事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    resizeImage(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSdcardExisting()) {
                        resizeImage(getImageUri());
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;

                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;
            }
        }
    }
    private void showResizeImage(Intent data)  {
        Bundle extras = data.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(img_head.getResources(),photo);
            img_head.setImageDrawable(drawable);
        }
    }
}
