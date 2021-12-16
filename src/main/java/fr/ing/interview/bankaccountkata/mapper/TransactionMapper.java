package fr.ing.interview.bankaccountkata.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import org.mapstruct.ReportingPolicy;

import fr.ing.interview.bankaccountkata.dto.TransactionDto;
import fr.ing.interview.bankaccountkata.entity.Transaction;


/**
 * TransactionMapper
 *
 * @author Abir ZEFZEF
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
	
	@Mappings({
	@Mapping(target = "idClient", source = "client.id"),
	@Mapping(target = "idAccount", source = "account.id")
	})
    TransactionDto transactionEntityToTransactionDto(Transaction transactionEntity);
    
	@Mappings({
	@Mapping(target = "client.id", source = "idClient"),
	@Mapping(target = "account.id", source = "idAccount")
	})
    Transaction transactionDtoToTransactionEntity(TransactionDto transactionDto);
    
    List<TransactionDto> toListDto(List<Transaction> list);
    
    List<Transaction> toListEntity(List<TransactionDto> accountList);
}
