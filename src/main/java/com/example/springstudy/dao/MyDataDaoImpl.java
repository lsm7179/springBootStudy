package com.example.springstudy.dao;

import com.example.springstudy.MyData;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MyDataDaoImpl implements MyDataDao<MyData> {

    private static final long serialVersionUID = 1L;

    private EntityManager entityManager;

    public MyDataDaoImpl() {
        super();
    }

    public MyDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MyData> getAll() {
        Query query=entityManager.createQuery("select a from MyData a");
        List<MyData> list=query.getResultList();
        entityManager.close();
        return list;
    }
}
