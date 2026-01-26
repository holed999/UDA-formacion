package com.ejie.zz99.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.zz99.dao.GenericoDao;
import com.ejie.zz99.dao.PersonaDao;
import com.ejie.zz99.model.Persona;

@Service(value = "PersonaService")
public class PersonaServiceImpl extends GenericoServiceImpl<Persona> implements PersonaService {

	public PersonaServiceImpl() {
		super(Persona.class);
	}

	@Autowired
	private PersonaDao personaDao;

	@Override
	protected GenericoDao<Persona> getDao() {
		return this.personaDao;
	}

}
