package com.color.notification;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
① 创建一个继承RecyclerView.Adapter<VH>的Adapter类
② 创建一个继承RecyclerView.ViewHolder的静态内部类
③ 在Adapter中实现3个方法：
   onCreateViewHolder()
   onBindViewHolder()
   getItemCount()
*/
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{
    private Context mycontext;
    private List<String> list;
    private View view;

    private PackageManager packageManager;

    private ApplicationInfo applicationInfo;

    Drawable drawable;
    //构造方法，传入数据,即把展示的数据源传进来，并且复制给一个全局变量，以后的操作都在该数据源上进行
    public RecycleAdapter(Context context, List<String> list) throws PackageManager.NameNotFoundException {
        mycontext = context;
        this.list = list;

        packageManager = mycontext.getPackageManager();
        applicationInfo = packageManager.getApplicationInfo("com.mphotool.whiteboard", 0);
        drawable = applicationInfo.loadIcon(packageManager);
    }
    //由于RecycleAdapter继承自RecyclerView.Adapter,则必须重写onCreateViewHolder()，onBindViewHolder()，getItemCount()
    //onCreateViewHolder()方法用于创建ViewHolder实例，我们在这个方法将item_demo.xml布局加载进来
    //然后创建一个ViewHolder实例，并把加载出来的布局传入到构造函数，最后将实例返回
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        view = LayoutInflater.from(mycontext).inflate(R.layout.item_demo,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    //onBindViewHolder()方法用于对RecyclerView子项数据进行赋值，会在每个子项被滚动到屏幕内的时候执行
    //这里我们通过position参数的得到当前项的实例，然后将数据设置到ViewHolder的TextView即可
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //将数据和控件绑定
        holder.title.setText(packageManager.getApplicationLabel(applicationInfo));
        holder.time.setText("2023/12/20");
        holder.content.setText("通知中心内容跟测试，看看可能的效果。");
        holder.imageView.setImageDrawable(drawable);
    }
    //getItemCount()告诉RecyclerView一共有多少个子项，直接返回数据源的长度。
    @Override
    public int getItemCount() {
        //返回Item总条数
        return list.size();
    }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, time, content;

        ImageView imageView;
        public MyViewHolder(View itemView) {//这个view参数就是recyclerview子项的最外层布局
            super(itemView);
            //可以通过findViewById方法获取布局中的TextView
            title = (TextView) itemView.findViewById(R.id.title);

            time = (TextView) itemView.findViewById(R.id.time);

            content = (TextView) itemView.findViewById(R.id.content);

            imageView = (ImageView) itemView.findViewById(R.id.Icon);
        }
    }
}

