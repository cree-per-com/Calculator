package com.example.calculator.DAO;

import com.example.calculator.Entity.CalculationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationDataRepository extends JpaRepository<CalculationEntity,Long> {

}
