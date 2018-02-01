package com.model2.mvc.web.messenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Messenger;
import com.model2.mvc.service.domain.RoomUser;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.room.RoomDao;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Controller
@RequestMapping("/messenger/*")
public class MessengerController {
	
	@Autowired
	@Qualifier("roomDaoImpl")
	private RoomDao roomDao;
	
	RoomUser roomUser = null;

	public MessengerController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping( value="listRoom" )
	public String listUser( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		
		System.out.println("/messenger/listRoom : GET / POST");
		List<Messenger> list = roomDao.getRoomList(search);
		request.setAttribute("list", list);
		
		/*
		MongoClient mongoClient = null;
		List<String> list = new ArrayList<String>();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
//		Map<String , Object> map=userService.getUserList(search);
		try {
			mongoClient = new MongoClient("localhost",27017);
			System.out.println("MONGODB SUCCESS");
			//������� �ο� 
			WriteConcern w = new WriteConcern(1,2000);//���� �� ����, ���� �ð� 2000 //������ ���ԵǸ� 2�� ���ÿ� �� ��쵵 ����ϱ�
            mongoClient.setWriteConcern(w);
            
          //�����ͺ��̽� ����
            DB db = mongoClient.getDB("test");
            //�÷��� ��������
            DBCollection coll = db.getCollection("rooms");
            
            DBCursor cursor = coll.find();
            while(cursor.hasNext()){
                //Ŀ���� �̸��߿� _id�� �÷� ���� ���
                System.out.println(cursor.next().get("roomname"));   
                list.add(""+cursor.next().get("roomname"));
            }
            
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//		
//		// Model �� View ����
		model.addAttribute("list", list);
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
 * 
 */
 
		return "forward:/messenger/listRoom.jsp";
	}
	@RequestMapping( value="addRoom", method=RequestMethod.GET )
	public String addRoom(HttpServletRequest request) throws Exception {
		System.out.println("/messenger/addRoom : GET");
		return "forward:/messenger/addRoomView.jsp";
	}
	
	@RequestMapping(value="addRoom", method=RequestMethod.POST)
	public String addRoom(@ModelAttribute("messenger") Messenger messenger,HttpSession session) throws Exception{
		
		System.out.println("/messenger/addRoom : POST");
		System.out.println(messenger);
		
		
		User user = (User)session.getAttribute("user");
				
		System.out.println("messenger END==>"+messenger);
		roomDao.addRoom(messenger,user);
		return "redirect:/messenger/listRoom";
	}

}
