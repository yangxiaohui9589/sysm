package com.sysm.repository;

import com.sysm.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : yangxh
 * @create 2018/5/21 16:54
 * @param 
 * @return 
 * @description : 
 **/
public interface ResourceRepository extends JpaRepository<Resource,Long>{

    @Query(value = "select r.* from Resource r where r.disabled = true", nativeQuery = true)
    List<Resource> getEnableResources();
}
