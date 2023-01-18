package ing.kata.BankAccount.mappers;

import ing.kata.BankAccount.Dtos.AccountDto;
import ing.kata.BankAccount.Dtos.CustomerDto;
import ing.kata.BankAccount.Dtos.TransactionDto;
import ing.kata.BankAccount.Entities.Account;
import ing.kata.BankAccount.Entities.Customer;
import ing.kata.BankAccount.Entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    Transaction toTransaction(TransactionDto transaction);

    @Mapping(target = "account", ignore = true)
    TransactionDto toTransactionDto(Transaction transaction);

    Account toAccount(AccountDto account);

    @Mapping(target = "transactions", ignore = true)
    AccountDto toAccountDto(Account account);


    Customer toCustomer(CustomerDto account);

    CustomerDto toCustomerDto(Customer account);
}
