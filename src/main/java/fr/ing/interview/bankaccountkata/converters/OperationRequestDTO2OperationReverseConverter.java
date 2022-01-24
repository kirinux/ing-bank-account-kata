package fr.ing.interview.bankaccountkata.converters;

import fr.ing.interview.bankaccountkata.dto.OperationRequestDTO;
import fr.ing.interview.bankaccountkata.model.OperationModel;

import java.util.Date;

public class OperationRequestDTO2OperationReverseConverter {
    static public OperationModel convert(OperationRequestDTO depositRequestDTO) {
        OperationModel operation = new OperationModel();
        operation.setAmount(depositRequestDTO.getAmount());
        operation.setOperationDate(new Date());
        operation.setType(depositRequestDTO.getOperationType());
        return operation;
    }

}
