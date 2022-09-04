package com.petushkov.webappcollections.repositories;

import com.petushkov.webappcollections.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findFirstByCollectionIdOrderByIdDesc(Long id);

    List<Item> findTop5ByOrderByCreatedAtDesc();

}
