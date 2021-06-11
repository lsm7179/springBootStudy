package com.example.springstudy.dao;

import com.example.springstudy.data.MsgData;

import java.util.List;

public interface MsgDataDao {
    public List<MsgData> getAll();
    public MsgData findById(long id);
}
