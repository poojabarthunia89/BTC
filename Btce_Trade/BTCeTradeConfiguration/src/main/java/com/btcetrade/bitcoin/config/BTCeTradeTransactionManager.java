package com.btcetrade.bitcoin.config;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class BTCeTradeTransactionManager implements PlatformTransactionManager{
	@Override
	public TransactionStatus getTransaction(TransactionDefinition defination){
		try{
		TransactionStatus status=new TransactionStatus() {
			
			@Override
			public void rollbackToSavepoint(Object arg0) throws TransactionException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void releaseSavepoint(Object arg0) throws TransactionException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object createSavepoint() throws TransactionException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void setRollbackOnly() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isRollbackOnly() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isNewTransaction() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isCompleted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasSavepoint() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void flush() {
				// TODO Auto-generated method stub
				
			}
		};
		return status;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	@Override
	public void commit(TransactionStatus status)throws TransactionException{
		
	}
	@Override
	public void rollback(TransactionStatus status)throws TransactionException{
		
	}

}
