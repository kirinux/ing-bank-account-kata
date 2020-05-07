package fr.ing.interview.bankaccountkata.service;

import com.google.common.collect.Lists;
import fr.ing.interview.bankaccountkata.dto.BankDto;
import fr.ing.interview.bankaccountkata.entities.Bank;
import fr.ing.interview.bankaccountkata.repository.BankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kso
 */
@Service
public class BankService {

    private final BankRepository bankRepository;
    private final ModelMapper modelMapper;

    public BankService(BankRepository bankRepository, ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.modelMapper = modelMapper;
    }

    public Set<BankDto> getBanks() {
        Iterable<Bank> banks = bankRepository.findAll();
        return Lists.newArrayList(banks).stream()
                .map(b -> modelMapper.map(b, BankDto.class))
                .collect(Collectors.toSet());
    }

}
