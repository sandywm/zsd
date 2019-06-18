package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyStuQfTjInfo;

public interface StudyStuQfTjDao {

	StudyStuQfTjInfo getEntityById(Session sess,Integer id);
	
	List<StudyStuQfTjInfo> findInfoByOpt(Session sess,Integer userId,Integer subId,String sDate,String eDate,String prov,String city);
}
