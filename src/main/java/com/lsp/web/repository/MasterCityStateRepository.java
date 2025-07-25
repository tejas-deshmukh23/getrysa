package com.lsp.web.repository;

import com.lsp.web.entity.Master_City_State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterCityStateRepository extends JpaRepository<Master_City_State, String> {
//    Optional<Master_City_State> findByPincode(String pincode);
	Optional<Master_City_State> findByPincode(Integer pincode);

}
