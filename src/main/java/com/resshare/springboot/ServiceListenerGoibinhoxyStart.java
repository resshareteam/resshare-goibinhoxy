package com.resshare.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.resshare.goibinhoxy.canbinhoxy.CallOxyLoadFormListener;
import com.resshare.goibinhoxy.canbinhoxy.CustomerLocationChangeListener;
import com.resshare.goibinhoxy.canbinhoxy.CustomerLocationOnStopListener;
import com.resshare.goibinhoxy.canbinhoxy.GetListOxySupplierListener;
import com.resshare.goibinhoxy.canbinhoxy.ListTransactionForCustomerLoadFormListener;
import com.resshare.goibinhoxy.canbinhoxy.MapTransactionForCustomerLoadFormListener;
import com.resshare.goibinhoxy.canbinhoxy.MessageRequestOxyCommitListener;
import com.resshare.goibinhoxy.canbinhoxy.MessageRequestOxyLoadFormListener;
import com.resshare.goibinhoxy.cobinhoxy.JoinSupplierOxyListener;
import com.resshare.goibinhoxy.cobinhoxy.ListTransactionForSupplierLoadFormListener;
import com.resshare.goibinhoxy.cobinhoxy.LoadFormSupplierOxyListener;
import com.resshare.goibinhoxy.cobinhoxy.MapTransactionForSupplierLoadFormListener;
import com.resshare.goibinhoxy.cobinhoxy.SupplierAcceptOrderListener;
import com.resshare.goibinhoxy.cobinhoxy.SupplierLocationChangeListener;
import com.resshare.goibinhoxy.cobinhoxy.SupplierLocationOnStopListener;

 

@SpringBootApplication // (scanBasePackages = { "com.websystique.springboot" }) // same as
						// @Configuration
						// @EnableAutoConfiguration @ComponentScan
						// combined
public class ServiceListenerGoibinhoxyStart {

	public static void startListener() {
		
		LoadFormSupplierOxyListener loadFormProviderOxyListener = new LoadFormSupplierOxyListener();
		loadFormProviderOxyListener.onStart();

		JoinSupplierOxyListener joinProviderOxyListener = new JoinSupplierOxyListener();
		joinProviderOxyListener.onStart();

		CallOxyLoadFormListener loadFormCallOxyListener = new CallOxyLoadFormListener();
		loadFormCallOxyListener.onStart();

		GetListOxySupplierListener callOxyListener = new GetListOxySupplierListener();
		callOxyListener.onStart();

		MessageRequestOxyLoadFormListener messageRequestOxyLoadFormListener = new MessageRequestOxyLoadFormListener();
		messageRequestOxyLoadFormListener.onStart();
		MessageRequestOxyCommitListener messageRequestOxyCommit = new MessageRequestOxyCommitListener();
		messageRequestOxyCommit.onStart();

		SupplierAcceptOrderListener supplierAcceptOrderListener = new SupplierAcceptOrderListener();
		supplierAcceptOrderListener.onStart();

		ListTransactionForCustomerLoadFormListener listTransactionForCustomerLoadFormListener = new ListTransactionForCustomerLoadFormListener();
		listTransactionForCustomerLoadFormListener.onStart();

		MapTransactionForCustomerLoadFormListener mapTransactionForCustomerLoadFormListener = new MapTransactionForCustomerLoadFormListener();
		mapTransactionForCustomerLoadFormListener.onStart();
		ListTransactionForSupplierLoadFormListener listTransactionForSupplierLoadFormListener = new ListTransactionForSupplierLoadFormListener();
		listTransactionForSupplierLoadFormListener.onStart();

		MapTransactionForSupplierLoadFormListener mapTransactionForSupplierLoadFormListener = new MapTransactionForSupplierLoadFormListener();
		mapTransactionForSupplierLoadFormListener.onStart();

		CustomerLocationChangeListener customerLocationChangeListener = new CustomerLocationChangeListener();
		customerLocationChangeListener.onStart();
		CustomerLocationOnStopListener customerLocationOnStopListener = new CustomerLocationOnStopListener();
		customerLocationOnStopListener.onStart();

		SupplierLocationChangeListener supplierLocationChangeListener = new SupplierLocationChangeListener();
		supplierLocationChangeListener.onStart();
		SupplierLocationOnStopListener supplierLocationOnStopListener = new SupplierLocationOnStopListener();
		supplierLocationOnStopListener.onStart();

		// CustomerLocationChangeListener
		// CustomerLocationOnStopListener
		// SupplierLocationChangeListener
		// SupplierLocationOnStopListener

	}

}
