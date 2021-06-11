package com.example.springstudy.repositories;

import com.example.springstudy.data.MsgData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgDataRepository extends JpaRepository<MsgData,Long> {

}
