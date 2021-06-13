package com.example.springstudy.service;

import com.example.springstudy.data.MyData;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class MyDataService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<MyData> getAll(){
        return (List<MyData>) entityManager.createQuery("select a from MyData a").getResultList();
    }

    public MyData get(int num){
        return (MyData) entityManager
            .createQuery("from MyData where id = "+num)
            .getSingleResult();
    }

    public List<MyData> find(String fstr){
        CriteriaBuilder builder =entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root).where(builder.equal(root.get("name"),fstr));
        List<MyData> list = null;
        list = (List<MyData>) entityManager.createQuery(query).getResultList();
        return list;
    }
}
