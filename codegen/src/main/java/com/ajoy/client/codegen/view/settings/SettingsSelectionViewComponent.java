package com.ajoy.client.codegen.view.settings;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.ViewComponent;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.codegen.main.UIDataModel;
import com.ajoy.model.codegen.ProfileInfo;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class SettingsSelectionViewComponent implements ViewComponent
{
	private static Logger log = LogManager.getLogger(SettingsSelectionViewComponent.class);

	@Override
	public Node createJavaFXNodeObject(ViewConfig config)  
	{
		log.info("createJavaFXNodeObject() start");
		
		ComboBox<String> listView = new ComboBox<String>();
		List<ProfileInfo> list = UIDataModel.get().getProfileInfoList();
		
		if(list != null && list.size()>0)
			for(ProfileInfo pInfo: list)			
				listView.getItems().add(pInfo.getName());				
		
		log.info("createJavaFXNodeObject() end");
		return listView;
	}


}
