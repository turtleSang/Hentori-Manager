package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Entity.LogBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogBookRepository extends JpaRepository<LogBookEntity, Integer> {
    Page<LogBookEntity> findAllByClientEntity(ClientEntity clientEntity, Pageable pageable);

}
