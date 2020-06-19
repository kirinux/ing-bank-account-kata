package fr.ing.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ing.bank.Dao.AccountRepository;
import fr.ing.bank.Dao.OperationRepository;
import fr.ing.bank.Entities.AccountEntity;
import fr.ing.bank.Entities.OperationEntity;
import fr.ing.bank.Service.OperationServiceImpl;
import fr.ing.bank.Service.PageOperation;

@RunWith(SpringRunner.class)
public class OperationServiceImplTest {
	
	private static final double MIN_AMOUNT = 0.001;
	
	@InjectMocks
    private OperationServiceImpl operationService;
	
	@Mock
	private OperationRepository operationRepository;
	
	@Mock
	AccountRepository accountRepo;
	
	AccountEntity account = new AccountEntity();
	
	private long accountNumber;
	private double balance ;
	private double amount;
	double expected;
	
	@Before
	public void setUp() {
		 
		accountNumber = 123456L;
	    balance = 100.0;
	    amount = 100.0;
	    
	    account.setNumAccount(accountNumber);
        account.setSolde(balance);
	}
	
	@Test
    public void should_deposit_amount() throws Exception {
           
        // When
        when(accountRepo.findById(accountNumber)).thenReturn(account);
        
        operationService.deposit(accountNumber, amount);
        
        //Then
        expected = balance + amount;
        assertEquals(expected,account.getSolde(),0.0);
    }
	
	@Test (expected = NullPointerException.class)
	public void should_throw_NullPointerexception_deposit() throws Exception {
		
		// When
        when(accountRepo.findById(accountNumber)).thenReturn(null);
        
        operationService.deposit(accountNumber, amount);
              
	}
	
	@Test (expected = Exception.class)
	public void should_throw_Exception_deposit() throws Exception {
		
		// When
        when(accountRepo.findById(accountNumber)).thenReturn(null);
        
        operationService.deposit(accountNumber, MIN_AMOUNT);
              
	}
	
	@Test
    public void should_pull_amount() throws Exception {
           
        // When
        when(accountRepo.findById(accountNumber)).thenReturn(account);
        
        operationService.pull(accountNumber, amount);
        
        expected = balance - amount;
        assertEquals(expected,account.getSolde(),0.0);
	}
	
	@Test (expected = NullPointerException.class)
	public void should_throw_NullPointerException_pull() throws Exception {
		
		// When
        when(accountRepo.findById(accountNumber)).thenReturn(null);
        
        operationService.pull(accountNumber, amount);
              
	}
	
	@Test (expected = RuntimeException.class)
	public void should_throw_RuntimeException_pull() throws Exception {
		
		//Given
		amount = 200.0;
		
		// When
        when(accountRepo.findById(accountNumber)).thenReturn(account);
        
        operationService.pull(accountNumber, amount);
              
	}
	
	@Test
    public void should_consult_balance() {
           
        // When
        when(accountRepo.findById(accountNumber)).thenReturn(account);
        
        expected = operationService.consult_balance(accountNumber);
          
        assertEquals(expected,balance,0.0);
    }
	
	@Test
    public void should_expose_all_operation() {
           
		//Given
		OperationEntity op = new OperationEntity();
		op.setCompte(account);
		op.setNumeroOperation(12L);
		
		List<OperationEntity> ops = new ArrayList<OperationEntity>();
		
		PageOperation pageOperation = new PageOperation();
		pageOperation.setNmbreOperations(5);
		pageOperation.setPage(1);
		pageOperation.setTotalOperations(11);
		pageOperation.setTotalPages(1);
		pageOperation.setOperations(ops);
		
		// When
		
		Page<OperationEntity> p= new Page<OperationEntity>() {

			@Override
			public int getNumber() {
				// TODO Auto-generated method stub
				return 6;
			}

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public int getNumberOfElements() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public List<OperationEntity> getContent() {
				// TODO Auto-generated method stub
				return ops;
			}

			@Override
			public boolean hasContent() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Sort getSort() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isFirst() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isLast() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Pageable nextPageable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Pageable previousPageable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator<OperationEntity> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getTotalPages() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getTotalElements() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <U> Page<U> map(Function<? super OperationEntity, ? extends U> converter) {
				// TODO Auto-generated method stub
				return null;
			}

		};
        
		when(operationRepository.getOperations( 12346L, new PageRequest( 1, 2 ) )).thenReturn(p);
        
        
        PageOperation opp = operationService.getOperations(12346L, 1, 2);
        
        //Then     
        assertEquals(opp.getOperations().get(0).getNumeroOperation().longValue(),pageOperation.getNmbreOperations());
    }
	
}
	