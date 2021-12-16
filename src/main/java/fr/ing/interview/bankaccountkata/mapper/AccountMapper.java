package fr.ing.interview.bankaccountkata.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.entity.Account;

/**
 * AccountMapper
 *
 * @author Abir ZEFZEF
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

	    AccountDto accountEntityToAccountDto(Account accountEntity);
	    
	    Account accountDtoToAccountEntity(AccountDto accountDto);
	    
	    List<AccountDto> toListDto(List<Account> list);
	    
	    List<Account> toListEntity(List<AccountDto> accountList);

}
