package com.ejie.zz99.service;

import java.util.List;

import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author ofernandez
 *
 * @param <T>
 */
public interface GenericoService<T> {

	/**
	 * Finds a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	T find(T bean);

	/**
	 * Finds a list of rows in the T table.
	 *
	 * @param bean            T
	 * @param TableRequestDto tableRequestDto
	 * @return List<T>
	 */
	List<T> findAll(T bean, TableRequestDto tableRequestDto);

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Filter method in the T table.
	 *
	 * @param filter          T
	 * @param TableRequestDto tableRequestDto
	 * @return JQGridResponseDto<T>
	 */
	TableResponseDto<T> filter(T filter, TableRequestDto tableRequestDto);

	/**
	 * Searches rows in the T table.
	 *
	 * @param filter          T
	 * @param search          T
	 * @param TableRequestDto tableRequestDto
	 * @return List<TableRowDto<T>>
	 */
	List<TableRowDto<T>> search(T filter, T search, TableRequestDto tableRequestDto);

	/**
	 * Reorder the selection made in T table.
	 *
	 * @param filter          T
	 * @param TableRequestDto tableRequestDto
	 * @return Object
	 */
	Object reorderSelection(T filter, TableRequestDto tableRequestDto);

	/**
	 * Inserts a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	T add(T bean);

	/**
	 * Updates a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	T update(T bean);

	/**
	 * Deletes a single row in the T table.
	 *
	 * @param bean T
	 */
	void remove(T bean);

	/**
	 * Devuelve los datos recuperados de la DB.
	 *
	 * @param filterUsuario   Usuario
	 * @param tableRequestDto TableRequestDto
	 */
	List<T> getDataForReports(T filter, TableRequestDto tableRequestDto);

	/**
	 *
	 * @param bean            T
	 * @param tableRequestDto TableRequestDto
	 * @return List<T>
	 */
	List<T> getSelected(T bean, TableRequestDto tableRequestDto);
}
