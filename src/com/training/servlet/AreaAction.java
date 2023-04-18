package com.training.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.service.FrontendService;

public class AreaAction extends DispatchAction {

	 private static Map<String, List<String>> areaDatas;
	
	 public ActionForward countyCity(ActionMapping mapping, ActionForm form, 
	            HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		areaDatas = new HashMap<String, List<String>>();
		// 台北市
		List<String> taipeiArea = new ArrayList<String>();
		taipeiArea.add("中正區");
		taipeiArea.add("大同區");
		taipeiArea.add("中山區");
		taipeiArea.add("松山區");
		taipeiArea.add("信義區");
		areaDatas.put("台北市", taipeiArea);
		// 新北市
		List<String> newTaipeiArea = new ArrayList<String>();
		newTaipeiArea.add("林口區");
		newTaipeiArea.add("五股區");
		newTaipeiArea.add("新莊區");
		newTaipeiArea.add("蘆洲區");
		newTaipeiArea.add("三重區");
		areaDatas.put("新北市", newTaipeiArea);
		// 台中市
		List<String> taichungCity = new ArrayList<String>();
		taichungCity.add("豐原區");
		taichungCity.add("大甲區");
		taichungCity.add("后里區");
		taichungCity.add("霧峰區");
		taichungCity.add("和平區");
		areaDatas.put("台中市", taichungCity);
		// 高雄市
		List<String> kaohsiungCity = new ArrayList<String>();
		kaohsiungCity.add("桃源區");
		kaohsiungCity.add("茂林區");
		kaohsiungCity.add("左營區");
		kaohsiungCity.add("鳳山區");
		kaohsiungCity.add("美濃區");
		areaDatas.put("高雄市", kaohsiungCity);
		 
		String area = request.getParameter("area");
		
		if(area != null){
			List<String> areaList = areaDatas.get(area);
			JSONObject queryResult = new JSONObject();
			if(areaList != null){
				queryResult.put("queryStatus", true);
				queryResult.put("areaList", areaList);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(queryResult);
				out.flush();
				out.close();
			}else{
				queryResult.put("queryStatus", false);
				queryResult.put("queryMessage", "查無此縣市區域資料!");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(queryResult);
				out.flush();
				out.close();
			}
		}
		
    	return null;
    }

	 public ActionForward convenienceStore(ActionMapping mapping, ActionForm form, 
	           HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
			areaDatas = new HashMap<String, List<String>>();
			// 7-11便利商店
			List<String> sevenArea = new ArrayList<String>();
			sevenArea.add("古亭門市");
			sevenArea.add("酒泉門市");
			sevenArea.add("天津門市");
			sevenArea.add("成泰門市");
			sevenArea.add("新宏盛門市");
			sevenArea.add("蘆華門市");
			areaDatas.put("seven", sevenArea);
			// 全家便利商店
			List<String> familyArea = new ArrayList<String>();
			familyArea.add("公館門市");
			familyArea.add("蘭州門市");
			familyArea.add("錦江門市");
			familyArea.add("工商門市");
			familyArea.add("龍鳳門市");
			familyArea.add("光華門市");
			areaDatas.put("family", familyArea);
			 
			String shopArea = request.getParameter("shopArea");
			
			if(shopArea != null){
				List<String> areaList = areaDatas.get(shopArea);
				JSONObject queryResult = new JSONObject();
				if(areaList != null){
					queryResult.put("queryStatus", true);
					queryResult.put("areaList", areaList);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.println(queryResult);
					out.flush();
					out.close();
				}else{
					queryResult.put("queryStatus", false);
					queryResult.put("queryMessage", "查無此商店區域資料!");
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.println(queryResult);
					out.flush();
					out.close();
				}
			}
							
	 	return null;
	} 
	 
}
