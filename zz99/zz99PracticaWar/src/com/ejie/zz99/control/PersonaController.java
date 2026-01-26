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
import com.ejie.zz99.model.Persona;
import com.ejie.zz99.model.PersonaFilter;
import com.ejie.zz99.model.enums.SituacionCombo;
import com.ejie.zz99.service.PersonaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping(value = "/persona")
public class PersonaController {

	private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

	@Autowired
	private PersonaService personaService;

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getMaint(Model model) {
		logger.info("[GET - View] : Persona");
		model.addAttribute("Persona", new Persona());
		model.addAttribute("PersonaFilter", new PersonaFilter());
		return "mantPersona";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResponseDto<Persona> filter(@RequestJsonBody(param = "filter") PersonaFilter filter,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		logger.info("[POST - filter] : Obtener servicios");
		return this.personaService.filter(filter, tableRequestDto);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Persona filter() {
		logger.info("[POST - filter] : Obtener servicios");
		Persona persona = new Persona();
		return this.personaService.find(persona);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Persona add(@RequestBody Persona persona) {
		logger.info("[POST] : Persona: add");
		Persona rdo = this.personaService.add(persona);
		logger.info("[POST] : Persona insertado correctamente");
		return rdo;
	}
	
	@RequestMapping(value = "/multiBaja", method = RequestMethod.PUT)
	public @ResponseBody Persona multibaja(@RequestBody Persona persona) {
		logger.info("[PUT] : Persona: multibaja");
		Persona rdo = this.personaService.add(persona);
		logger.info("[PUT] : Personas dados de baja correctamente");
		return rdo;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public @ResponseBody Persona edit(@RequestBody Persona persona) {
		logger.info("[PUT] : Persona: edit");
		Persona rdo = this.personaService.update(persona);
		logger.info("[PUT] : Persona actualizado correctamente");
		return rdo;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Persona get(@PathVariable String id) {
		logger.info("[GET - findBy_PK] : Obtener Persona por PK");

		Persona persona = new Persona();
		persona.setIdentificador(Integer.valueOf(id));
		return this.personaService.find(persona);
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
	public @ResponseBody Persona remove(@PathVariable String codigo) {
		Persona persona = new Persona();
		persona.setIdentificador(Integer.valueOf(codigo));
		this.personaService.remove(persona);
		logger.info("[DELETE] : persona borrada correctamente");
		return persona;
	}

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	protected ModelAndView getXLSXReport(@ModelAttribute Persona filterpersona,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns)
			throws JsonParseException, JsonMappingException, IOException {

		// Obtener datos
		List<Persona> listPersonaAll = this.personaService.findAll(filterpersona, null);

		// Nombre fichero
		modelMap.put("fileName", "Fichero");

		// Datos
		List<Object> reportData = new ArrayList<Object>();

		// Hoja 1
		ReportData<Persona> personaExcelDataAll = new ReportData<Persona>();
		// nombre hoja
		personaExcelDataAll.setSheetName("personas");
		// cabeceras hoja
		personaExcelDataAll.setHeaderNames(generaColumnas());
		// datos hoja
		personaExcelDataAll.setModelData(listPersonaAll);
		reportData.add(personaExcelDataAll);

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
