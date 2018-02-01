package com.model2.mvc.service.room;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Messenger;
import com.model2.mvc.service.domain.User;

public interface RoomDao {
	
	public List<Messenger> getRoomList(Search search) throws Exception;
	
	public void addRoom(Messenger messenger, User user) throws Exception;

}
