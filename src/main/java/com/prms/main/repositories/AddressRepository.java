package com.prms.main.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prms.main.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}