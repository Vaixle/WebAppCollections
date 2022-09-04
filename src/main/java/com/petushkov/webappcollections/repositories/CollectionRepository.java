package com.petushkov.webappcollections.repositories;

import com.petushkov.webappcollections.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findAllByUserId(Long id);

    List<Collection> findAllByUserUsername(String username);

    Collection findByNameAndUserUsername(String collectionName, String username);

    boolean existsByNameAndUserUsername(String collectionName, String username);

    @Query(value = "SELECT * FROM collections c\n" +
            "LEFT JOIN items i on c.id = i.collection_id\n" +
            "GROUP BY c.id\n" +
            "ORDER BY COUNT(i.id) DESC\n LIMIT 5",
            nativeQuery = true)
    List<Collection> findTop5CollectionOrderByItemsDesc();
}