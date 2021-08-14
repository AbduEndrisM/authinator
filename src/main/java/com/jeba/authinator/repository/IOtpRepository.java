package com.jeba.authinator.repository;

import com.jeba.authinator.domain.entity.OtpEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOtpRepository extends MongoRepository<OtpEntity, String> {

    Optional<OtpEntity> findFirstByToOrderByExpiryTimeDesc(String phoneNumber);

    List<OtpEntity> findAllByToOrderByExpiryTimeDesc(String phoneNumber);

}
