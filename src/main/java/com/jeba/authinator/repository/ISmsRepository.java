package com.jeba.authinator.repository;

import com.jeba.authinator.domain.entity.SmsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISmsRepository extends MongoRepository<SmsEntity, String>, QueryByExampleExecutor<SmsEntity> {

    Optional<SmsEntity> findByTo(String phoneNumber);

//    <S extends SmsEntity> Optional<S> findOne(Example<S> example);

    <S extends SmsEntity> List<S> saveAll(Iterable<S> entites);

}
