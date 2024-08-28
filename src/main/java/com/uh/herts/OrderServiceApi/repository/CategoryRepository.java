package com.uh.herts.OrderServiceApi.repository;

import com.uh.herts.OrderServiceApi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Cate findByUsername(String username);
//    List<Category> fi
}