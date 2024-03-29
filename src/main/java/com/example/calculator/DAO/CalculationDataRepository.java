package com.example.calculator.DAO;

import com.example.calculator.Entity.CalculationEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CalculationDataRepository extends JpaRepository<CalculationEntity,Long> {
    @Query(value ="SELECT new com.example.calculator.DAO.DataDTO(c.username, c.calculator, c.calcstring, c.result,c.id,c.liked) " +
            "FROM CalculationEntity c WHERE c.username = :username")
            List<DataDTO> UsersData(String username);

    @Query(value="SELECT new com.example.calculator.DAO.DataDTO(c.username,c.calculator,c.calcstring,c.result,c.id,c.liked) FROM " +
            "CalculationEntity c WHERE c.username = :username AND c.liked=true")
    List<DataDTO> UsersLikedData(String username);
}
