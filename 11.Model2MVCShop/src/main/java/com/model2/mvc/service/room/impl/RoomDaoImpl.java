package com.model2.mvc.service.room.impl;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Messenger;
import com.model2.mvc.service.domain.RoomUser;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.room.RoomDao;

@Repository("roomDaoImpl")
public class RoomDaoImpl implements RoomDao {
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public RoomDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public List<Messenger> getRoomList(Search search) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("here is a RoomDAO");
//		System.out.println(mongoTemplate.getCollectionNames());
//		System.out.println(mongoTemplate.getCollectionNames());
//		System.out.println(mongoTemplate.collectionExists("rooms"));
		
		Query query = new Query(new Criteria("roomname").all("nice"));
		System.out.println(mongoTemplate.find(query, Messenger.class, "rooms"));
		List<Messenger> list = mongoTemplate.findAll(Messenger.class,"rooms");
		for (Messenger messenger : list) {
			System.out.println(messenger);
		}
		
		System.out.println(list);
	
		return mongoTemplate.findAll(Messenger.class,"rooms");
		
	}

	@Override
	public void addRoom(Messenger messenger, User user) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("here is a addRoom");
		
		//SHA-1 encrypto
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		
		String mill = System.currentTimeMillis()/1000+"";
		System.out.println("second"+mill);
		md.update(mill.getBytes());
		
		byte byteData[] = md.digest();
		 
        StringBuffer sb = new StringBuffer(); 
        for(int i=0; i<byteData.length; i++) {
            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
        }

        String retVal = sb.toString().substring(0,6);
        System.out.println(retVal);
        ////////////////////////////////////////////////////////////////////////
        messenger.setKey(retVal);
        messenger.setOnline("1");
        
        /*
        online : number,
        user{
        		name : userId,
            status : online
        }
        */
        
        System.out.println("ROOMDAO"+messenger);
        System.out.println("ROOMDAO"+user);
        
        RoomUser roomUser = new RoomUser();
        roomUser.setUser(user.getUserId());
        roomUser.setStatus("available");
        System.out.println("ROOMDAO"+roomUser);
        
        List<RoomUser> list = new Vector<>();
        list.add(roomUser);
        
        messenger.setRoomUser(list);
        
        System.out.println(messenger);
        
        
        
		mongoTemplate.insert(messenger,"rooms");
		mongoTemplate.createCollection("chat"+retVal);
		
		
		
		
	}
	
	
	
	

}
