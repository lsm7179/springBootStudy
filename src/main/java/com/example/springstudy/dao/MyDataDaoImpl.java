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

    @Override
    public MyData findById(long id) {
        return (MyData) entityManager.createQuery("from MyData where id = "+ id).getSingleResult();
    }

    @Override
    public List<MyData> findByName(String name) {
        return (List<MyData>)entityManager.createQuery("from MyData where name = "+name).getResultList();
    }

    @Override
    public List<MyData> find(String fstr) {
        List<MyData> list =null;
        String qstr="select a from MyData a where a.id = ?1 or a.name like ?2 or a.mail like ?3 ";
        Long fid=0L;
        /*Query query=entityManager.createQuery(qstr).setParameter(1,fid)
                .setParameter(2, "%"+fstr+"%")
                .setParameter(3,fstr+"@%");*/
        Query query=entityManager.createNamedQuery("findWithName").setParameter("fname","%"+fstr+"%");
        list=query.getResultList();
        return list;
    }

    @Override
    public List<MyData> findByAge(int min, int max) {
        return (List<MyData>)entityManager
                .createNamedQuery("findByAge")
                .setParameter("min",min)
                .setParameter("max",max)
                .getResultList();
    }
}
