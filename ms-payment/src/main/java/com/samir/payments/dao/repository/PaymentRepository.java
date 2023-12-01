package com.samir.payments.dao.repository;

import com.samir.payments.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>,
        JpaSpecificationExecutor<PaymentEntity> {


}
