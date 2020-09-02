package fr.ing.bank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import fr.ing.bank.Dao.AccountRepository;
import fr.ing.bank.Dao.OperationRepository;
import fr.ing.bank.Entities.AccountEntity;
import fr.ing.bank.Entities.OperationEntity;

public class OperationServiceImpl implements OperationService{

	
	private static final double MIN_DEPOSIT_AMOUNT = 0.01;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private OperationRepository operationRepository;
	    
	   
	
	public void deposit(long accountNumber, double amount) throws Exception {
        
		if (amount <= MIN_DEPOSIT_AMOUNT) {
            
            throw new Exception("Sorry not allowed when inferior or equal to 0.01 € !");
        }
		
		AccountEntity account = accountRepo.findById(accountNumber);
			
        if (account == null)
        	throw new NullPointerException("Account not found");
		
       account.setSolde(account.getSolde()+ amount);
        
    }
	
	  
	    public void pull(long accountNumber, double montant) {
		   
		   AccountEntity account = accountRepo.findById(accountNumber);
			
	        if (account == null)
	        	throw new NullPointerException("Account not found");
	        
	        if ( account.getSolde() < montant )
	            throw new RuntimeException( "Solde insuffisant !" );
	        
	        account.setSolde( account.getSolde() - montant );
              
	        accountRepo.saveAndFlush(account);

	    }
	    
	    
	    public double consult_balance(long accountNumber) {
	
	    	 AccountEntity account = accountRepo.findById(accountNumber);
				
		        if (account == null)
		        	throw new NullPointerException("Account not found");
		    
		    return account.getSolde();
	    }
	    
	    
	    public PageOperation getOperations( long codeCompte, int page, int size ) {

	        Page<OperationEntity> ops = operationRepository.getOperations( codeCompte, new PageRequest( page, size ) );

	        PageOperation pOp = new PageOperation();
	        pOp.setOperations( ops.getContent() ); // get content retourne la liste
	                                               // des operations
	        pOp.setNmbreOperations( ops.getNumberOfElements() ); // nombre
	                                                             // operations
	        pOp.setPage( page ); // ops.getNumber()
	        pOp.setTotalPages( ops.getTotalPages() );
	        pOp.setTotalOperations((int) ops.getTotalElements() );
	        return pOp;

}
	    
}
