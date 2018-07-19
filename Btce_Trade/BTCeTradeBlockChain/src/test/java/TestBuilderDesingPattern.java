import junit.framework.Assert;

import org.junit.Test;

import com.btcetrade.builder.Transaction;


public class TestBuilderDesingPattern {

	@Test
	public void createTrasaction(){
		Transaction tx= new Transaction.TransactionBuilder("ABCD", "pawan@gmail.com", "ICICI001", "1@3$","1234").setPassportNumber("PAWAN").setVoterId("VT01").build();
		Transaction tx2= new Transaction.TransactionBuilder("ABCD", "pawan@gmail.com", "ICICI001", "1@3$","1234").setPassportNumber("PAWAN").setVoterId("VT01").build();
		//Assert.assertEquals(tx.getAddress(),tx2.getAddress());
	
	}
	
	
}
