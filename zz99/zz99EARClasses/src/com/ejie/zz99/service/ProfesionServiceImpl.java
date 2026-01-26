package com.ejie.zz99.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.zz99.dao.GenericoDao;
import com.ejie.zz99.dao.ProfesionDao;
import com.ejie.zz99.model.Profesion;

@Service(value = "ProfesionService")
public class ProfesionServiceImpl extends GenericoServiceImpl<Profesion> implements ProfesionService {

	public ProfesionServiceImpl() {
		super(Profesion.class);
	}

	@Autowired
	private ProfesionDao profesionDao;

	@Override
	protected GenericoDao<Profesion> getDao() {
		return this.profesionDao;
	}

}
