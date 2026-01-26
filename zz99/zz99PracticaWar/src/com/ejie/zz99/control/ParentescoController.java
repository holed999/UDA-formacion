package com.ejie.zz99.control;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.reports.ReportData;
import com.ejie.zz99.model.Mantenimiento;
import com.ejie.zz99.model.Parentesco;
import com.ejie.zz99.model.ParentescoFilter;
import com.ejie.zz99.model.enums.SituacionCombo;
import com.ejie.zz99.service.ParentescoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping(value = "/parentesco")
public class ParentescoController {

	private static final Logger logger = LoggerFactory.getLogger(ParentescoController.class);

	@Autowired
	private ParentescoService parentescoService;

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getMaint(Model model) {
		logger.info("[GET - View] : Parentesco");
		model.addAttribute("Parentesco", new Parentesco());
		model.addAttribute("ParentescoFilter", new ParentescoFilter());
		return "mantParentesco";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResponseDto<Parentesco> filter(@RequestJsonBody(param = "filter") ParentescoFilter filter,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		logger.info("[POST - filter] : Obtener servicios");
		return this.parentescoService.filter(filter, tableRequestDto);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Parentesco filter() {
		logger.info("[POST - filter] : Obtener servicios");
		Parentesco parentesco = new Parentesco();
		return this.parentescoService.find(parentesco);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Parentesco add(@RequestBody Parentesco parentesco) {
		logger.info("[POST] : Parentesco: add");
		Parentesco rdo = this.parentescoService.add(parentesco);
		logger.info("[POST] : Parentesco insertado correctamente");
		return rdo;
	}
	
	@RequestMapping(value = "/multiBaja", method = RequestMethod.PUT)
	public @ResponseBody Parentesco multibaja(@RequestBody Parentesco parentesco) {
		logger.info("[PUT] : Parentesco: multibaja");
		Parentesco rdo = this.parentescoService.add(parentesco);
		logger.info("[PUT] : Parentescos dados de baja correctamente");
		return rdo;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public @ResponseBody Parentesco edit(@RequestBody Parentesco parentesco) {
		logger.info("[PUT] : Parentesco: edit");
		Parentesco rdo = this.parentescoService.update(parentesco);
		logger.info("[PUT] : Parentesco actualizado correctamente");
		return rdo;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Parentesco get(@PathVariable String id) {
		logger.info("[GET - findBy_PK] : Obtener Parentesco por PK");

		Parentesco parentesco = new Parentesco();
		parentesco.setIdentificador(Integer.valueOf(id));
		return this.parentescoService.find(parentesco);
	}

	@RequestMapping(value = "combo/situacion", method = RequestMethod.GET)
	public @ResponseBody List<SituacionCombo> getAllSituacion() {
		List<SituacionCombo> lista = new ArrayList<>();
		SituacionCombo alta = new SituacionCombo("A", "Alta", "Alta");
		SituacionCombo baja = new SituacionCombo("B", "Baja", "Baja");
		lista.add(alta);
		lista.add(baja);
		return lista;

	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Parentesco remove(@PathVariable String codigo) {
		Parentesco parentesco = new Parentesco();
		parentesco.setIdentificador(Integer.valueOf(codigo));
		this.parentescoService.remove(parentesco);
		logger.info("[DELETE] : parentesco borrada correctamente");
		return parentesco;
	}

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	protected ModelAndView getXLSXReport(@ModelAttribute Parentesco filterparentesco,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns)
			throws JsonParseException, JsonMappingException, IOException {

		// Obtener datos
		List<Parentesco> listParentescoAll = this.parentescoService.findAll(filterparentesco, null);

		// Nombre fichero
		modelMap.put("fileName", "Fichero");

		// Datos
		List<Object> reportData = new ArrayList<Object>();

		// Hoja 1
		ReportData<Parentesco> parentescoExcelDataAll = new ReportData<Parentesco>();
		// nombre hoja
		parentescoExcelDataAll.setSheetName("parentescos");
		// cabeceras hoja
		parentescoExcelDataAll.setHeaderNames(generaColumnas());
		// datos hoja
		parentescoExcelDataAll.setModelData(listParentescoAll);
		reportData.add(parentescoExcelDataAll);

		modelMap.put("reportData", reportData);

		// Generacion del informe
		return new ModelAndView("xlsxReport", modelMap);
	}

	private LinkedHashMap<String, String> generaColumnas() {
		Field[] campos = Mantenimiento.class.getDeclaredFields();
		LinkedHashMap<String, String> mapa = new LinkedHashMap<String, String>();

		for (Field campo : campos) {

			campo.setAccessible(true);

			if(!"serialVersionUID".equals(campo.getName()) && !"descFilter".equals(campo.getName())) {
				mapa.put(campo.getName(), campo.getName());
			}

		}

		return mapa;
	}
}
