package com.air.service;
/**
 * 进入主页时的初始化工作
 * @author Dua
 */
public interface IHomepageService {
	public void showCities();		//设置开始的城市下拉单 -- 从数据库中取
	public void showAirlines();		//设置航空公司下拉单
}
