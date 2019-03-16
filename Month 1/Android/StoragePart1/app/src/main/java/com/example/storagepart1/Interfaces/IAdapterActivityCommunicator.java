package com.example.storagepart1.Interfaces;

import com.example.storagepart1.POJO.DataModel;

public interface IAdapterActivityCommunicator {
    void deleteData(int id,int position);
    void editData(DataModel dataModel,int position);
}
