package fr.ing.interview.bankaccountkata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ing.interview.bankaccountkata.entity.Client;

/**
 * Repository of {@link Client}
 */
public interface ClientRepository extends JpaRepository<Client, Long>{

}
