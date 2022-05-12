package com.example.demo.repository;




import com.example.demo.model.Rules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rules, Long> {
}
