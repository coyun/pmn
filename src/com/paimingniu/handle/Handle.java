package com.paimingniu.handle;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月8日
 * @version V1.0
 */
public class Handle {

	public static void main(String[] args) {

		Map<Integer,Node> map=new HashMap<Integer,Node>();
		

		
		map.put(1, new Node("男装","a",1000));
		map.put(2, new Node("44187052754","b",1000));
		map.put(3, new Node("#J_Reviews","c",1000));
		
		for(Integer k:map.keySet()){
			System.out.println(new Gson().toJson(map.get(k)));
		}
		
		System.out.println(new Gson().toJson(map));
		
	}

}
