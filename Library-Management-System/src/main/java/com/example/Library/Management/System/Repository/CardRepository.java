package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
