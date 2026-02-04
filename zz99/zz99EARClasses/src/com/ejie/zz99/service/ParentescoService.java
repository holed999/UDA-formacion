package com.ejie.zz99.service;

import java.util.List;
import com.ejie.zz99.model.Parentesco;

public interface ParentescoService extends GenericoService<Parentesco> {

	/**
	 * Da de baja múltiples parentescos cambiando su situación a 'B'
	 * 
	 * @param ids Lista de identificadores de parentescos a dar de baja
	 * @return true si se completó correctamente, false en caso contrario
	 */
	boolean bajaMultiple(List<Integer> ids);

}