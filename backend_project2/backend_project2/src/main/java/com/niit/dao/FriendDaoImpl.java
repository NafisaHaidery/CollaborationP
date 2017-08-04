package com.niit.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.Users;

@Repository
public class FriendDaoImpl implements FriendDao{
@Autowired
private SessionFactory sessionFactory;
	public List<Users> listOfSuggestedUsers(String username) {
		Session session=sessionFactory.openSession();
		SQLQuery sqlQuery= session.createSQLQuery("select * from users where username in (select username from users where username!=? minus(select fromid from friend where toId=? union(select toId from friend where fromId=?)))");
		sqlQuery.setString(0, username);
		sqlQuery.setString(1, username);
		sqlQuery.setString(2, username);
		sqlQuery.addEntity(Users.class);
		List<Users> suggestedUsersList=sqlQuery.list();
		session.close();
		return suggestedUsersList;
	}
	public void friendRequest(String fromUsername, String toUsername) {
		Session session=sessionFactory.openSession();
		Friend friend=new Friend();
		friend.setFromId(fromUsername);
		friend.setToId(toUsername);
		friend.setStatus('P');
		session.save(friend);
		session.flush();
		session.close();
	}

}
