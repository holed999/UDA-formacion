package com.ejie.zz99.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.zz99.dao.GenericoDao;
import com.ejie.zz99.dao.ParentescoDao;
import com.ejie.zz99.model.Parentesco;

@Service(value = "ParentescoService")
public class ParentescoServiceImpl extends GenericoServiceImpl<Parentesco> implements ParentescoService {

	private static final Logger logger = LoggerFactory.getLogger(ParentescoServiceImpl.class);

	public ParentescoServiceImpl() {
		super(Parentesco.class);
	}

	@Autowired
	private ParentescoDao parentescoDao;

	@Override
	protected GenericoDao<Parentesco> getDao() {
		return this.parentescoDao;
	}

	@Override
	public boolean bajaMultiple(List<Integer> ids) {
		if (ids == null || ids.isEmpty()) {
			return false;
		}

		try {
			for (Integer id : ids) {
				Parentesco parentesco = new Parentesco();
				parentesco.setIdentificador(id);

				// Buscar el parentesco actual
				Parentesco existente = find(parentesco);
				if (existente != null && !"B".equals(existente.getSituacion())) {
					// Cambiar situación a baja
					existente.setSituacion("B");
					// Actualizar en base de datos
					update(existente);
					logger.info("Parentesco " + id + " dado de baja");
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Error en bajaMultiple", e);
			throw e;
		}
	}

}