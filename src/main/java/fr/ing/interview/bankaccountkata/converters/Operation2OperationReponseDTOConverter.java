package fr.ing.interview.bankaccountkata.converters;

import fr.ing.interview.bankaccountkata.dto.OperationReponseDTO;
import fr.ing.interview.bankaccountkata.model.OperationModel;


public class Operation2OperationReponseDTOConverter {


    static public OperationReponseDTO convert(OperationModel operation) {
        OperationReponseDTO operationReponseDTO = new OperationReponseDTO();
        operationReponseDTO.setOperationDate(operation.getOperationDate());
        operationReponseDTO.setBalance(operation.getBalance());
        operationReponseDTO.setAmount(operation.getAmount());
        operationReponseDTO.setOperationType(operation.getType());
        return operationReponseDTO;
    }

}
