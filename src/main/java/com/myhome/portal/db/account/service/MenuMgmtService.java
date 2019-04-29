/*package com.sa.cloud.db.service.portal.common.menu;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sa.cloud.cmm.Constants;
import com.sa.cloud.db.mybatis.SqlMapClient;
import com.sa.cloud.db.service.base.BaseService;

public class MenuMgmtService extends BaseService {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	*//**
	 * メニュー一覧情報取得
	 * (TREE構造)
	 *//*
	public List<Map<String, Object>> selectMenuList() {
		return selectMenuList(null);
	}
	public List<Map<String, Object>> selectMenuList(Integer menuSystemType) {
		List<Map<String, Object>> menuList = null;

		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();
			if (menuSystemType != null) {
				param.put("systemType", menuSystemType);
			}
			menuList = session.selectList("portal.common.menu.manage.selectMenuList", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return menuList;
	}
	*//**
	 * 指定したパートナーのメニュー一覧情報取得
	 * メニューマスタ(parentPartnerInfoIdに属しているメニュー)に対してパートナーが権限を持っているメニューリストを返す。
	 * 「has_menu=1」のものがパートナーの権限があるメニューです。
	 * (TREE構造)
	 *//*
	public List<Map<String, Object>> selectMenuListOfPartner(Integer parentPartnerInfoId, Integer partnerInfoId, Integer menuSystemType) {
		List<Map<String, Object>> menuList = null;

		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();
			if (parentPartnerInfoId != null) {
				param.put("parentPartnerInfoId", parentPartnerInfoId);
			}
			if (partnerInfoId != null) {
				param.put("partnerInfoId", partnerInfoId);
			}
			if (menuSystemType != null) {
				param.put("systemType", menuSystemType);
			}
			menuList = session.selectList("portal.common.menu.manage.selectMenuListOfPartner", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return menuList;
	}
	public void upsertPartnerMenu(Integer partnerInfoId, List<String> menuList, int deleteFlg) {
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			for (String menuViewId : menuList) {
				if (StringUtils.isEmpty(menuViewId)) {
					continue;
				}
				Map<String,Object> param = Maps.newHashMap();
				if (menuViewId != null) {
					param.put("menuViewId", Integer.valueOf(menuViewId));
				}
				if (partnerInfoId != null) {
					param.put("partnerInfoId", partnerInfoId);
				}
				param.put("deleteFlg", deleteFlg);
				if (deleteFlg == 0) {
					session.update("portal.common.menu.manage.upsertPartnerMenu", param);
				} else {
					session.update("portal.common.menu.manage.deletePartnerMenu", param);
					session.update("portal.common.menu.manage.deleteUserAuthMenu", param);
				}
			}			
			session.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	public void upsertPartnerMenu(Integer partnerInfoId, Integer menuViewId, int deleteFlg) {
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();			
			if (menuViewId != null) {
				param.put("menuViewId", menuViewId);
			}
			if (partnerInfoId != null) {
				param.put("partnerInfoId", partnerInfoId);
			}
			param.put("deleteFlg", deleteFlg);
			session.update("portal.common.menu.manage.upsertPartnerMenu", param);
			session.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	*//**
	  * パートナーのメニューと削除対象のメニューを使っているユーザのメニュー権限も削除する。
	 *//*
	public void deletePartnerMenu(Integer partnerInfoId, List<String> menuList) {
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();
			param.put("partnerInfoId", partnerInfoId);		
			if (menuList.size() == 0) {
				session.update("portal.common.menu.manage.deletePartnerMenu", param);
				session.update("portal.common.menu.manage.deleteUserAuthMenu", param);
			} else {
				List<Integer> menuViewIdList = Lists.newArrayList();
				for (String menuViewId : menuList) {
					if (StringUtils.isEmpty(menuViewId)) {
						continue;
					}		
					menuViewIdList.add(Integer.valueOf(menuViewId));
				}
				param.put("menuViewIdList", menuViewIdList);				
				session.update("portal.common.menu.manage.deletePartnerMenu", param);
				session.update("portal.common.menu.manage.deleteUserAuthMenu", param);
			}
			session.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	*//**
	 * パートナーのメニューと削除対象のメニューを使っているユーザのメニュー権限も削除する。
	 *//*
	public void deletePartnerMenu(Integer partnerInfoId, Integer menuViewId) {
		List<String> menuList = Lists.newArrayList();
		if (menuViewId != null) {
			menuList.add(menuViewId + "");
		}
		deletePartnerMenu(partnerInfoId, menuList);
	}
	*//**
	 * パートナーのメニューと削除対象のメニューを使っているユーザのメニュー権限も削除する。
	 *//*
	public void deletePartnerMenu(Integer partnerInfoId) {
		List<String> menuList = Lists.newArrayList();
		deletePartnerMenu(partnerInfoId, menuList);
	}
	protected Map<String,Object> setSystemTypeParam(Map<String,Object> param, Integer userLevel) {
		if (userLevel == Constants.USER_LEVEL_SOC) {
			param.put("systemType", Constants.MENU_SYTEM_TYPE_SOC);
		} else if (userLevel == Constants.USER_LEVEL_CONTRACT_TRIAL) {
			param.put("systemType", Constants.MENU_SYTEM_TYPE_TRIAL);
		} else {
			param.put("systemType", Constants.MENU_SYTEM_TYPE_USER);
		}
		return param;
	}
	
	*//**
	 * ユーザのメニューリストを返す。
	 * @param userId
	 * @return
	 * @throws Exception
	 *//*
	public List<Map<String, Object>> selectUserMenuList(String userId, Integer userLevel) throws Exception {
		List<Map<String, Object>> menuList = null;
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();
			param = setSystemTypeParam(param, userLevel);
			param.put("userInfoId", userId);
			menuList = session.selectList("portal.common.menu.manage.selectUserMenuList", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return menuList;
	}
	
	public List<Map<String, Object>> selectPartnerMenuList(Integer partnerInfoId, Integer systemType, Integer menuLevel) {
		List<Map<String, Object>> menuList = null;
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();
			param.put("partnerInfoId", partnerInfoId);
			param.put("systemType", systemType);
			param.put("menuLevel", menuLevel);
			menuList = session.selectList("portal.common.menu.manage.selectPartnerMenuList", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return menuList;	
	}
	public List<Map<String, Object>> selectMenuListIncludeUrl(String url, Integer userLevel) throws Exception {
		List<Map<String, Object>> menuList = null;
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String,Object> param = Maps.newHashMap();
			param = setSystemTypeParam(param, userLevel);
			param.put("url", url);
			menuList = session.selectList("portal.common.menu.manage.selectMenuListIncludeUrl", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return menuList;
	}

	public List<Map<String, Object>> selectMenuListAsDetectParent(Map<String, Object> param, Integer userLevel) throws Exception {
		List<Map<String, Object>> menuList = null;
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			param = setSystemTypeParam(param, userLevel);
			menuList = session.selectList("portal.common.menu.manage.selectMenuListAsDetectParent", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return menuList;
	}

	public List<Map<String, Object>> selectSubMenuListAsDetectParent(Map<String, Object> param, Integer userLevel) throws Exception {
		List<Map<String, Object>> menuList = null;
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			param = setSystemTypeParam(param, userLevel);
			menuList = session.selectList("portal.common.menu.manage.selectSubMenuListAsDetectParent", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return menuList;
	}
*//**
	 * 親のメニューIDが存在するかチェックする
	 * 
	 * @param menu_view_id
	 * @return
	 *//*
	public boolean selectMenuParentIdChk(Integer menu_view_id, Integer userLevel) {
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("menu_view_id", menu_view_id);
			param = setSystemTypeParam(param, userLevel);
			Integer count = session.selectOne("portal.common.menu.manage.selectMenuParentIdChk", param);
			if (count > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println();
			return false;
		}
		
	}

	*//**
	 *  親のメニューIDから子のメニューID一覧を取得
	 *  
	 *  @param menu_view_id
	 *  @return
	 *//*
	public List<Map<String, Object>> selectMenuChildlenList(Integer menu_view_id, Integer userLevel) {
		List<Map<String, Object>> menuList = null;
		Map<String, Object> param = Maps.newHashMap();
		param.put("menu_view_id", menu_view_id);
		param = setSystemTypeParam(param, userLevel);
		try (SqlSession session = SqlMapClient.getSqlSession()) {
			menuList = session.selectList("portal.common.menu.manage.selectMenuChildlenList", param);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println();
			return null;
		}
		return menuList;

	}
	
	public boolean mergeMenuList(List<Map<String, Object>> menuList, List<Map<String, Object>> menuViewIdMap, String userId, int systemType) {
		boolean result = false;
		try(SqlSession session = SqlMapClient.getSqlSession()) {
			Map<String, Object> param = Maps.newHashMap();
			List<Map<String, Object>> arrayOfSaveMenuViewId = Lists.newArrayList();
			List<Map<String, Object>> arrayOfDeleteMenuViewId = Lists.newArrayList();
			
			param.put("menuList", 	menuList);	//arrayList of menu!
			param.put("userId", 	userId);
			param.put("system_type", systemType);
			
			//0) Make null removed List of menu_view_id
			for(Map<String, Object> menu : menuList){
				Map<String, Object> tmp = Maps.newHashMap();
				if(menu.containsKey("menu_view_id")){
					String menuViewId = String.valueOf( menu.get("menu_view_id") );	//1,"2",3... ""..
					if(!menuViewId.isEmpty()){
						tmp.put("menu_view_id", Integer.parseInt(menuViewId));
						arrayOfSaveMenuViewId.add(tmp);
					}
				}
			}
			param.put("saveMenuViewIdList", 	arrayOfSaveMenuViewId);
			
			//get deleted menu_view_id
			List<Map<String, Object>> deletedMenuViewIdList = session.selectList("portal.common.menu.manage.getDeletedMenuViewIdList", param);
			
			//List<int> menuIdList = Lists.newArrayList();
			//1) delete from menu List(delege_flg -> 1)					
			int rtnCnt = session.update("portal.common.menu.manage.deleteMenuList", param);
			//2) delete from menu name List(delege_flg -> 1)
			rtnCnt = session.update("portal.common.menu.manage.deleteMenuNameList", param);			
			//3) delete from m_partner_menu_view, d_user_auth
			if(deletedMenuViewIdList.size()>0){
				param.put("deletedMenuViewIdList", deletedMenuViewIdList);
				rtnCnt = session.update("portal.common.menu.manage.deletePartnerMenuViewIdList", param);
				rtnCnt = session.update("portal.common.menu.manage.deleteUserAuthMenuViewIdList", param);
			}
			
			//4) merge menu name List, merge menu List
			for(Map<String, Object> menu : menuList){
				menu.put("userId", userId);
				menu.put("system_type", systemType);
				
				String menuViewId = String.valueOf( menu.get("menu_view_id") );	//1,"2",3... ""..
				if(menuViewId.isEmpty()){
					//add menu_view_id!!
					int rtnId = session.selectOne("portal.common.menu.manage.getMaxMenuViewId");
					menu.put("menu_view_id", rtnId);
				}
				
				if(menu.get("menu_level").equals(Constants.MENU_LEVEL_MAIN)){
					menu.put("parent_id", 	0);
				} else {
					for(Map<String, Object> tmp : menuList){
						if(tmp.get("order_no") == menu.get("parent_order_no") ){
							String tmpMenuViewId = String.valueOf( tmp.get("menu_view_id") );	//1,"2",3... ""..
							menu.put("parent_id", Integer.parseInt(tmpMenuViewId));//find parents menu_view_id
						}
					}
				}
				
				rtnCnt = session.insert("portal.common.menu.manage.mergeMenuList", menu);
				rtnCnt = session.insert("portal.common.menu.manage.mergeMenuNameList", menu);
				
				//System.out.println("menu:"+menu);
				
			}
			
			//d_user_auth, m_partner_menu_view upsert
			//9) 
			for(Map<String, Object> menu : menuList){
				//1) only admin partner(secuavail = 0)
				menu.put("deleteFlg", 0);
				menu.put("partnerInfoId", 0);	//secuavail
				menu.put("menuViewId", menu.get("menu_view_id"));
				session.update("portal.common.menu.manage.upsertPartnerMenu", menu);
				
				//2) all partner() 
				
				List<Map<String, Object>> partnerInfoIdList = session.selectList("portal.common.menu.manage.getPartnerInfoId", param);
				for(Map<String, Object> partnerMap : partnerInfoIdList){
					if(partnerMap.containsKey("partner_info_id")){
						menu.put("partnerInfoId", partnerMap.get("partner_info_id"));
						menu.put("menuViewId", menu.get("menu_view_id"));
						session.update("portal.common.menu.manage.upsertPartnerMenu", menu);
					}
				}
				
			}
			
			session.commit();
			result = true;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
}
*/