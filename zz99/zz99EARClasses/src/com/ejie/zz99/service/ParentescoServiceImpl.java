package com.ejie.zz99.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.zz99.dao.GenericoDao;
import com.ejie.zz99.dao.ParentescoDao;
import com.ejie.zz99.model.Parentesco;

@Service(value = "ParentescoService")
public class ParentescoServiceImpl extends GenericoServiceImpl<Parentesco> implements ParentescoService {

	public ParentescoServiceImpl() {
		super(Parentesco.class);
	}

	@Autowired
	private ParentescoDao parentescoDao;

	@Override
	protected GenericoDao<Parentesco> getDao() {
		return this.parentescoDao;
	}

}
