package com.example.storagepart2.adapters;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storagepart2.R;
import com.example.storagepart2.interfaces.IListDataExchange;

import java.io.File;
import java.util.List;


public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private List<File> mFileArrayList;
    //    CardviewRecyclerBinding mBinding;
    private IListDataExchange mIListDataExchange;


    public void setmFileArrayList(List<File> fileArrayList) {
        mFileArrayList = fileArrayList;
    }


    public void setmIListDataExchange(IListDataExchange iListDataExchange) {
        mIListDataExchange = iListDataExchange;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mBinding = DataBindingUtil.inflate(inflater, R.layout.cardview_recycler, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recycler, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final File file = mFileArrayList.get(i);
        viewHolder.nameText.setText(file.getName());

//        mBinding.setFile(file);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.isDirectory()) {
                    if (file.list().length > 0) {
                        if (mIListDataExchange != null) {
                            mIListDataExchange.listExchange(file);
                        }
//                            mFileArrayList = Arrays.asList(file.listFiles());
//                            notifyDataSetChanged();
                    } else {
                        Toast.makeText(v.getContext(), "empty directory", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Intent fileIntent = new Intent();
                    fileIntent.setAction(Intent.ACTION_VIEW);
                    fileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    fileIntent.setDataAndType((FileProvider.getUriForFile(v.getContext(), v.getContext().getPackageName() + ".provider", file)), getMimeType(file.getAbsolutePath()));
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    try{
                        v.getContext().startActivity(fileIntent);
                    }catch (ActivityNotFoundException e){
                        e.printStackTrace();
                    }

                    Toast.makeText(v.getContext(), "this is a file", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mFileArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.tv_name);

//            mBinding = DataBindingUtil.bind(itemView);

        }
    }

    private String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

}
