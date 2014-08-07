package com.moobin.output.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtNumber;
import com.moobin.annotation.bt.BtReference;
import com.moobin.test.meta.Currency;

public class InOutTest {

	@Id
	public String id;
	
	public String name;
	
	public int number;
	
	@BtNumber(min=0, max=200)
	public int[] ints;
	
	public boolean boo;
	
	public boolean[] booleans;
	
	@BtReference(Currency.class)
	public String ccy;

	public InOutTest child;
	
	static InOutTest create() {
		
		InOutTest t = new InOutTest();
		t.id = "ID";
		t.name = "Transfer Moobinson";
		t.number = 999;
		t.ints = new int[] {1,99,248}; 
		t.boo = true;
		t.booleans = new boolean[] {true, false, true};
		t.ccy = "SEK";
		t.child = new InOutTest();
		t.child.id = "ID_C";
		t.child.name = "Transfer Moobinson";
		t.child.number = 999;
		t.child.ints = new int[] {1,2,3};
		t.child.boo = true;
		t.child.booleans = null;
		t.child.ccy = "SEK";
		return t;
		
	}
	
}
