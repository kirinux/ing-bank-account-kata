package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.dto.OperationRequestDTO;
import fr.ing.interview.bankaccountkata.dto.OperationReponseDTO;
import fr.ing.interview.bankaccountkata.model.OperationModel;

import java.util.List;

public interface OperationService {
    public OperationModel deposit(OperationRequestDTO depositRequestDTO);

    public OperationModel withdraw(OperationRequestDTO withdrawRequestDTO);

    public List<OperationModel> getOperationsHistoryForAccount(long accountID);
}
