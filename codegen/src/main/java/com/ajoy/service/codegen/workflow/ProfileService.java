package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.Profiles;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.bo.ProfileBO;

/**
 * 
 * A Service will use one or more BOs.<br>
 * 
 * @author kalyanc
 *
 */
public class ProfileService 
{
	private static Logger log = LogManager.getLogger(ProfileService.class);
	private ProfileBO profileBO = new ProfileBO();
	
	public ResponseCode<ProfileInfo> createProfile(ProfileInfo pInfo, CallContext context)
	{
		log.info("createProfile() start");
		ProfileBO bo = getProfileBO() ;
		ResponseCode<ProfileInfo> code = bo.addProfile(pInfo, context);
		log.info("createProfile() end "+code.isSuccess());
		return code;
	}
	
	public ResponseCode<Profiles> getProfileList(CallContext context)
	{
		log.info("getProfileList() start");
		ProfileBO bo = getProfileBO();
		ResponseCode<Profiles> code = bo.getProfileList(context);
		log.info("getProfileList() end "+code.isSuccess());
		return code;
	}

	public ResponseCode<ProfileInfo> updateProfile(ProfileInfo pInfo, CallContext context)
	{
		ProfileBO bo = getProfileBO() ;		
		return bo.updateProfile(pInfo, context);
	}

	
	private ProfileBO getProfileBO()
	{
		return profileBO;
	}

}
