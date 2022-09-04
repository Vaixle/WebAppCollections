package com.petushkov.webappcollections.services.impl;


import com.petushkov.webappcollections.models.Collection;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateSearchServiceImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Collection> search(String terms, int limit, int offset) {
        return Search.session(em).search(Collection.class)
                .where(f -> f.match()
                        .fields("name", "description", "topic", "items.name", "items.tags.name")
                        .matching(terms))
                .fetchHits(offset, limit);
    }
}
