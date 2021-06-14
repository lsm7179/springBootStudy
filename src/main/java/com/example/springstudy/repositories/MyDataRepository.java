package com.example.springstudy.repositories;

import com.example.springstudy.data.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData,Long> {
    public Optional<MyData> findById(Long name);

    @Query("SELECT d FROM MyData d ORDER BY d.name")
    List<MyData> findAllOderByName();

    @Query("from MyData where age > : min and age < : max ")
    public List<MyData> findByAge(@Param("min") int min,@Param("max") int max);

    /*@Query("from MyData where id = :id")
    MyData findOne(long id);*/
}
