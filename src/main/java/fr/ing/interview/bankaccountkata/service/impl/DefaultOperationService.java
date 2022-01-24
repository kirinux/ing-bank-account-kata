package fr.ing.interview.bankaccountkata.service.impl;

import fr.ing.interview.bankaccountkata.converters.OperationRequestDTO2OperationReverseConverter;
import fr.ing.interview.bankaccountkata.converters.Operation2OperationReponseDTOConverter;
import fr.ing.interview.bankaccountkata.dto.OperationRequestDTO;
import fr.ing.interview.bankaccountkata.dto.OperationReponseDTO;
import fr.ing.interview.bankaccountkata.exception.IllegalOperationException;
import fr.ing.interview.bankaccountkata.model.BankAccountModel;
import fr.ing.interview.bankaccountkata.model.OperationModel;
import fr.ing.interview.bankaccountkata.model.OperationType;
import fr.ing.interview.bankaccountkata.repository.OperationRepository;
import fr.ing.interview.bankaccountkata.service.AccountService;
import fr.ing.interview.bankaccountkata.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultOperationService implements OperationService {

    @Autowired
    AccountService accountService;
    @Autowired
    OperationRepository operationRepository;

    @Override
    @Transactional
    public OperationModel deposit(OperationRequestDTO depositRequestDTO) {
        final String operationType = depositRequestDTO.getOperationType();
        if (operationType != null && operationType != OperationType.DEPOSIT.name()) {
            if (depositRequestDTO.getAmount().compareTo(new BigDecimal(0.1)) > 0) {
                final BankAccountModel account = accountService.deposit(depositRequestDTO.getAccountId(), depositRequestDTO.getAmount());
                return saveOperation(account, depositRequestDTO);
            } else {
                throw new IllegalOperationException("Deposit amount must be greater than 0.1€");
            }
        } else {
            throw new IllegalOperationException("operation type: " + operationType + " not supported");
        }
    }

    @Override
    @Transactional
    public OperationModel withdraw(OperationRequestDTO withdrawRequestDTO) {
        final String operationType = withdrawRequestDTO.getOperationType();
        if (operationType != null && operationType != OperationType.WITHDRAW.name()) {
            final BankAccountModel account = accountService.getAccountBalanceByID(withdrawRequestDTO.getAccountId());
            BigDecimal balance = account.getBalance();
            if (balance.subtract(withdrawRequestDTO.getAmount()).compareTo(new BigDecimal(0)) > 0) {
                final BankAccountModel accountUpdated = accountService.withdraw(withdrawRequestDTO.getAccountId(), withdrawRequestDTO.getAmount());
                return saveOperation(accountUpdated, withdrawRequestDTO);
            } else {
                throw new IllegalOperationException("insufficient funds: " + balance);
            }
        } else {
            throw new IllegalOperationException("operation type: " + operationType + " not supported");
        }
    }

    @Override
    public List<OperationModel> getOperationsHistoryForAccount(long accountID) {
        return operationRepository.findAllByAccountId(accountID);
    }


    private OperationModel saveOperation(BankAccountModel account, OperationRequestDTO depositRequestDTO) {
        OperationModel operation = OperationRequestDTO2OperationReverseConverter.convert(depositRequestDTO);
        operation.setBalance(account.getBalance());
        operation.setAccount(account);
        operation.setType(depositRequestDTO.getOperationType());
        operationRepository.save(operation);
        return operation;
    }
}
