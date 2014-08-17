package com.moobin.view;

public interface ViewDefinition {

	/*
	 * <view>
	 * 	 <model type="UpdateUser">
	 *     <oncontext type="User|SystemUser">
	 *         $model.userId=$context.id;
	 *         $model.userName=$context.name;
	 *     </oncontext>
	 *     <onload>$model.state='OPEN'</onload>
	 *   <model>
	 * </view>
	 * 
	 */
	String getId();

	String getModel();

	ViewContext[] getContext();
	
}
