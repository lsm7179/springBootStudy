package com.example.springstudy.repositories;

import com.example.springstudy.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDataRepository extends JpaRepository<MyData,Long> {
}
