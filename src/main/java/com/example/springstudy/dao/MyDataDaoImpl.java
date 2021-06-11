package com.example.springstudy.dao;

import com.example.springstudy.data.MyData;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class MyDataDaoImpl implements MyDataDao<MyData> {

    private static final long serialVersionUID = 1L;

    private final EntityManager entityManager;

    public MyDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MyData> getAll() {
        /*Query query=entityManager.createQuery("select a from MyData a");
        List<MyData> list=query.getResultList();
        entityManager.close();
        return list;*/
        int offset=1; //추출 위치 지정
        int limit=1; //추출 개수 지정
        List<MyData> list =null;
        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query=builder.createQuery(MyData.class);
        Root<MyData> root=query.from(MyData.class);
        query.select(root).orderBy(builder.asc(root.get("name")));
        list = (List<MyData>)entityManager.createQuery(query)
                //.setFirstResult(offset)
                //.setMaxResults(limit)
                .getResultList();
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
        /*List<MyData> list =null;
        String qstr="select a from MyData a where a.id = ?1 or a.name like ?2 or a.mail like ?3 ";
        Long fid=0L;
        Query query=entityManager.createNamedQuery("findWithName").setParameter("fname","%"+fstr+"%");
        list=query.getResultList();
        return list;*/
        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
        CriteriaQuery query=builder.createQuery(MyData.class);
        Root<MyData> root=query.from(MyData.class);
        query.select(root).where(builder.equal(root.get("name"),fstr));
        List<MyData> list=null;
        list=(List<MyData>)entityManager.createQuery(query).getResultList();
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
