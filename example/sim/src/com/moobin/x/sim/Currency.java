package com.moobin.x.sim;

import com.moobin.annotation.Action;
import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.annotation.bt.BtNumber;
import com.moobin.annotation.bt.BtText;
import com.moobin.core.data.MetaAction;
import com.moobin.object.MoobinObject;

@Action({MetaAction.ADD, MetaAction.GET, MetaAction.UPDATE, MetaAction.REMOVE})
public class Currency extends MoobinObject {
	
	@Id
	@BtText(minLength=3, maxLength=3)
	public String code;

	@BtDisplay
	public String name;

	@BtNumber(min=0, max=999)
	public int currencyNumber;

	@BtNumber(min=1, max=4)
	public int mnrUnits;

	public int[] intArr = {1, 3, 5};
	
	@Override
	public String toString() {
		return currencyNumber + " " + name;
	}
}
