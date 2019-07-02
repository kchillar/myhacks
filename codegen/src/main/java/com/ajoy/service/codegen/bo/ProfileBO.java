package com.ajoy.service.codegen.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.Profiles;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.CallContext;

public class ProfileBO extends BaseBO
{	
	private static Logger log = LogManager.getLogger(ProfileBO.class);
	private ProfileDAO dao = new ProfileDAO();
	
	
	public ProfileBO()
	{		
	}
	
	public ResponseCode<ProfileInfo> addProfile(ProfileInfo pInfo, CallContext context)
	{						
		log.info("addProfile() start "+pInfo.getName());
		
		SessionInfo sessionInfo = context.getSessionInfo();
		
		ResponseCode<ProfileInfo> code = new ResponseCode<>(false);
		ResponseCode<Profiles> aCode = this.getProfileList(context);
		if(aCode.isSuccess())
		{			
			Profiles profiles = aCode.getObject();
			List<ProfileInfo> list = profiles.getProfiles();
			if(list == null)
			{
				list = new ArrayList<>();
				profiles.setProfiles(list);
			}
			log.info("list().size() "+list.size());
			
			boolean exists = false;

			for(ProfileInfo tmp: list)							
				if(tmp.getName().equals(pInfo.getName()))
					exists = true;
			
						
			if(!exists)
			{				
				for(ProfileInfo tmp: list)	
				{
					if(pInfo.isSelected())
						tmp.setSelected(false);
				}
				
				list.add(pInfo);
				aCode = dao.persistToStore(profiles);				 
				if(aCode.isSuccess())
				{			
					code.setObject(pInfo);
					code.setSuccess(true);
					log.info("persisted Profile: "+pInfo);
				}
			}
			else
			{
				log.warn("Duplicate profile");
				code = new ResponseCode<>();
				code.setSuccess(false);				
			}
		}

		log.info("addProfile() end name: "+pInfo.getName()+" isSuccess: "+code.isSuccess());
		return code;
	}
	
	public ResponseCode<Profiles> getProfileList(CallContext context)
	{
		//SessionInfo sessionInfo = context.getSessionInfo();
		log.info("getProfileList() start");
		ResponseCode<Profiles> code = dao.readFromStorage();
		log.info("getProfileList() end");
		return code;
	}
	
	public ResponseCode<ProfileInfo> updateProfile(ProfileInfo pInfo, CallContext context)
	{
		log.info("updateProfile() start");		
		ResponseCode<Profiles> aCode = getProfileList(context);
		ResponseCode<ProfileInfo> code = new ResponseCode<>();
		code.setSuccess(false);
		
		if(aCode.isSuccess())
		{
			Profiles profiles = aCode.getObject();			
			for(ProfileInfo tmp: profiles.getProfiles())
			{
				tmp.setSelected(false);			
				if(tmp.getName().equals(pInfo.getName()))
				{
					tmp.setSelected(true);
					tmp.setName(pInfo.getName());
					code.setSuccess(true);
				}
			}
			
			aCode = dao.persistToStore(profiles);
			if(!aCode.isSuccess())
				throw new IllegalStateException("Unable to persist profile info");
		}		
		log.info("updateProfile() end");
		return code;
	}

	
}
