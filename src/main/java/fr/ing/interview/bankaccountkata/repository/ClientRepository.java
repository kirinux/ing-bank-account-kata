package fr.ing.interview.bankaccountkata.repository;

import fr.ing.interview.bankaccountkata.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
