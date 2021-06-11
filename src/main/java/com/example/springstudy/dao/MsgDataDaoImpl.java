package com.example.springstudy.dao;

import com.example.springstudy.data.MsgData;

import javax.persistence.EntityManager;
import java.util.List;

public class MsgDataDaoImpl implements MsgDataDao {

    private EntityManager entityManager;

    public MsgDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MsgData> getAll() {
        return entityManager.createQuery("select a from MsgData a").getResultList();
    }

    @Override
    public MsgData findById(long id) {
        return (MsgData)entityManager.createQuery("from MsgData where id = "+id).getSingleResult();
    }
}
