package com.myhome.portal.db.account.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.myhome.portal.db.mybatis.SqlMapClient;

public class AccountManageService {
	
	public List<Map<String,Object>> selectAccountMonthList(){
		List<Map<String,Object>> list = Lists.newArrayList();
		try(SqlSession session = new SqlMapClient().getSqlSession()){
			Map<String,Object> param = Maps.newHashMap();
			list = session.selectList("", param);
		} catch (Exception e) {

		}
		
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
