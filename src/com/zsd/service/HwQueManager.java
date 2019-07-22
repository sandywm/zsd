package com.zsd.service;

import org.hibernate.Session;

import com.zsd.exception.WEBException;

public interface HwQueManager {

	Integer addHW(Session sess,Integer btId,Integer loreId,Integer num) throws WEBException;
}
