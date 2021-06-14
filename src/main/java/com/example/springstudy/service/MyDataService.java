package com.example.springstudy.service;
import com.example.springstudy.data.MyData;
import com.example.springstudy.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class MyDataService {

    @Autowired
    MyDataRepository repository;

    private static final int PAGE_SIZE = 2;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<MyData> getMyDataInPage(Integer pageNumber){
        PageRequest pageRequest =PageRequest.of(pageNumber-1,PAGE_SIZE);
        return repository.findAll(pageRequest);
    }

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
