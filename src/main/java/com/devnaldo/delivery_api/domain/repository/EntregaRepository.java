package com.devnaldo.delivery_api.domain.repository;

import com.devnaldo.delivery_api.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}