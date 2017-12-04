package com.air.dao;

import com.air.domain.Airline;

/**
 * 航空公司内部数据配置
 * @author Dua
 */

public interface IAirlineDao {
	public void setInfo(Airline airline);
	public Airline getInfo(Airline airline);
}
