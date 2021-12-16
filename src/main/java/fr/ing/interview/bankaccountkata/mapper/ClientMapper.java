package fr.ing.interview.bankaccountkata.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.ing.interview.bankaccountkata.dto.ClientDto;
import fr.ing.interview.bankaccountkata.entity.Client;

/**
 * ClientMapper
 *
 * @author Abir ZEFZEF
 */

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

	

	    ClientDto clientEntityToClientDto(Client clientEntity);
	    
	    Client clientDtoToClientEntity(ClientDto clientDto);
	    
	    List<ClientDto> toListDto(List<Client> list);
	    
	    List<Client> toListEntity(List<ClientDto> clientList);

	    
	    
	    
	
}
