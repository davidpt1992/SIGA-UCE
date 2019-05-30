package ec.com.siga.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.Entregable;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.Seccion;
import ec.com.siga.repository.SectionRepository;
import ec.com.siga.service.UserServicio;

public class GeneratePdfReport {
	
	public static byte[] auditoriesReport(Informe inf, List<CheckList> preguntas, Entregable en, List<Seccion> secciones) {
		// variable data
		int numPregunta = 0;
		String respuestaPre;
		CheckList cl = new CheckList();

		// document instance
		Document document = new Document();
		
		

		// data table
		try {

			PdfPTable tData = new PdfPTable(6);
			tData.setWidthPercentage(90);
			tData.setWidths(new int[] { 1, 1, 5, 1, 5, 3 });

			Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Font replyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Nº", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Sección", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Pregunta", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("R", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Evidencia", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Soporte", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tData.addCell(hcell);

			for (CheckList pregunta : preguntas) {
				cl = pregunta;
				numPregunta = numPregunta + 1;

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(numPregunta), replyFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tData.addCell(cell);

				cell = new PdfPCell(
						new Phrase(String.valueOf(pregunta.getPreguntasId().getSeccionId().getSeccionId()), replyFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tData.addCell(cell);

				cell = new PdfPCell(new Phrase(pregunta.getPreguntasId().getPreguntas(), replyFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				tData.addCell(cell);

				if (pregunta.getDatoEspecificoId().isRespuesta() == true) {
					respuestaPre = "SI";
				} else {
					respuestaPre = "NO";
				}

				cell = new PdfPCell(new Phrase(respuestaPre, replyFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				tData.addCell(cell);

				cell = new PdfPCell(new Phrase(pregunta.getDatoEspecificoId().getEvidencia(), replyFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				tData.addCell(cell);

				try {
					if (pregunta.getDatoEspecificoId().getFotoId() != null) {
						Image image = Image.getInstance(pregunta.getDatoEspecificoId().getFotoId().getFoto());

						tData.addCell(image);
					} else {
						tData.addCell("N/A");
					}

				} catch (IOException e) {
					e.printStackTrace();
					cell = new PdfPCell(new Phrase("N/A", replyFont));
					cell.setPaddingLeft(5);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tData.addCell(cell);

				}

			}
			// end data table

			// title table
			PdfPTable tTitle = new PdfPTable(1);
			tTitle.setWidthPercentage(90);

			Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.BOLD, BaseColor.BLUE);

			PdfPCell hcellTitle;
			hcellTitle = new PdfPCell(new Phrase("SIGA", fontTitle));
			hcellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
			tTitle.addCell(hcellTitle);
			// end title table

			// picHead table
			PdfPTable picHead = new PdfPTable(1);
			picHead.setWidthPercentage(90);

			Image image;
			try {
				image = Image.getInstance(en.getInforme());
				picHead.addCell(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// end picHead table

			// header table
			PdfPTable tHeader = new PdfPTable(2);
			tHeader.setWidthPercentage(90);

			Font fontHeaderL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.DARK_GRAY);
			Font fontHeaderR = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

			PdfPCell hcellHeader;

			hcellHeader = new PdfPCell(new Phrase("Razón Social  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getNombreEmpresa(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Dirección Oficina Principal  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getUserId().getDireccion(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Cuestionario  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(
					new Phrase(cl.getPreguntasId().getTipoAuditoriaId().getTipoAuditoria(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Teléfono  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(
					new Phrase(String.valueOf(inf.getClienteId().getUserId().getNumeroTelefono1()), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Correo Electrónico  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(inf.getClienteId().getUserId().getCorreoElectronico(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Nombre de Inspector  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(
					inf.getAuditorId().getUserId().getNombre() + " " + inf.getAuditorId().getUserId().getApellido(),
					fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Fecha de Visita  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase(
					inf.getDatoComunId().getSolicitudAuditoriaId().getFechaInicio().toString(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);

			hcellHeader = new PdfPCell(new Phrase("Emitido el  ", fontHeaderL));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tHeader.addCell(hcellHeader);

			Date date = new Date();
			hcellHeader = new PdfPCell(new Phrase(date.toString(), fontHeaderR));
			hcellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			tHeader.addCell(hcellHeader);
			// end header table
								
			// sections table

				PdfPTable tSec = new PdfPTable(2);
				tSec.setWidthPercentage(60);
				tSec.setWidths(new int[] { 1, 5 });

				PdfPCell sect;
				sect = new PdfPCell(new Phrase("Nº Sección", headFont));
				sect.setHorizontalAlignment(Element.ALIGN_CENTER);
				tSec.addCell(sect);

				sect = new PdfPCell(new Phrase("Sección", headFont));
				sect.setHorizontalAlignment(Element.ALIGN_CENTER);
				tSec.addCell(sect);

				
				for (Seccion pregunta : secciones) {
					
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(pregunta.getSeccionId()), replyFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					tSec.addCell(cell);

					cell = new PdfPCell(new Phrase(String.valueOf(pregunta.getSeccion()), replyFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					tSec.addCell(cell);

					


				}
			// end sections table

			// suge table
			PdfPTable tSuge = new PdfPTable(1);
			tSuge.setWidthPercentage(90);
			int sec1, sec2, sec3, sec4, sec5, sec6, sec7, sec8, sec9, sec10, sec11, sec12, sec13, sec14;
			sec1 = sec2 = sec3 = sec4 = sec5 = sec6 = sec7 = sec8 = sec9 = sec10 = sec11 = sec12 = sec13 = sec14 = 0;

			for (CheckList checkList : preguntas) {
				if (checkList.getDatoEspecificoId().isRespuesta() == false) {
					switch (checkList.getPreguntasId().getSeccionId().getSeccionId()) {
					case 1:
						sec1 = sec1 + 1;
						break;
					case 2:
						sec2 = sec2 + 1;
						break;
					case 3:
						sec3 = sec3 + 1;
						break;
					case 4:
						sec4 = sec4 + 1;
						break;
					case 5:
						sec5 = sec5 + 1;
						break;
					case 6:
						sec6 = sec6 + 1;
						break;
					case 7:
						sec7 = sec7 + 1;
						break;
					case 8:
						sec8 = sec8 + 1;
						break;
					case 9:
						sec9 = sec9 + 1;
						break;
					case 10:
						sec10 = sec10 + 1;
						break;
					case 11:
						sec11 = sec11 + 1;
						break;
					case 12:
						sec12 = sec12 + 1;
						break;
					case 13:
						sec13 = sec13 + 1;
						break;
					case 14:
						sec14 = sec14 + 1;
						break;
					default:
						break;
					}
				}

			}

			Font secFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, BaseColor.BLACK);
			Font tituloSecFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLUE);
			PdfPCell sec;

			if (sec1 != 0) {
				sec = new PdfPCell(new Phrase(
						" Oportunidades de mejora sección Actividad De La Empresa Y Objeto Social", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase(
						"Tomar en consideración que la empresa debe tener respaldos de sus contratos, así como de sus facturas para sustentar su experiencia en la línea que trabaja, así mismo controlar a sus proveedores subcontratados.",
						secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec2 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Clientes & Proveedores", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase(
						"Tomar en consideración que la empresa debe tener respaldos de sus contratos que mantiene con sus clientes o sus proveedores",
						secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec3 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Información Legal", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa debe tener el ruc actualizado asi como las sucursales que se declara que la empresa tiene, finalmente señalar los principales socios y accionistas a traves de la super de compañías ", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec4 != 0) {
				sec = new PdfPCell(
						new Phrase("Oportunidades de mejora sección Calificación De Riesgo Financiero", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa debe actualzair su buró de credito a traves de la super intendencia de bancos", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec5 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Gestión De Servicio", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa podria implementar indicadores de gestion para la trazabilidad de su producto o servicios", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec6 != 0) {
				sec = new PdfPCell(
						new Phrase("Oportunidades de mejora sección Instalaciones De La Empresa", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa deberia someterse a una inspección por el cuerpo de bomberos con el fin de mantener una inspección de los lugares con potencial riesgo", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec7 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Maquinarias Y Equipos", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa podria mantener un control de sus inventario para mantener una trazabilidad de sus equipos, en el caso de equipos de computo deben tener las licencias correspondientes.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec8 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Personal", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa deberia manter las planillas de pago al día de sus aportaciones al seguro social, de la misma manera la empresa podria desarrollar un mecanismo para evaluar y hacer seguimiento a sus empelados", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec9 != 0) {
				sec = new PdfPCell(
						new Phrase("Oportunidades de mejora sección Sistemas De Comunicación", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa debería tener un correo institucional para evitar correo spam asi mismo mantener un contrato fortmal para sus sistemas de comunicación como son internet o telefonía fija.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec10 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Calidad", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa podria tener un mecacnismo para evaluar a sus proveedores y clientes, se podria realizar encuestas de satisfacción.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec11 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Comercial", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa podría llevar registros de sus servicio post venta,  así como de su servicio de garantía.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec12 != 0) {
				sec = new PdfPCell(
						new Phrase("Oportunidades de mejora sección Salud Y Seguridad Ocupacional", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa debería un somertese a un plan minimo de trabajo según aplique sus riesgo catalogado en el ministerio de trabajo.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec13 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Medio Ambiente", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa debería tener una clasificación según el minisiterio de medio ambiente con el cual la empresa deberia tener buenas practicas de disposicion de desechos peligrosos y no peligrosos.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}
			if (sec14 != 0) {
				sec = new PdfPCell(new Phrase("Oportunidades de mejora sección Responsabilidad Social", tituloSecFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
				sec = new PdfPCell(new Phrase("Tomar en consideración que la empresa debería fomentar las buenas prácticas de resposanbilidad social fomentadas por la OIT, Con el fin de que la organziacipon fomente la no discriminación por raza, etnia o creencia.", secFont));
				sec.setHorizontalAlignment(Element.ALIGN_LEFT);
				tSuge.addCell(sec);
			}

			// end tSuge table

			// document into stream
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, stream);

			// get document
			document.open();
			document.add(picHead);
			document.add(new Phrase("-"));
			document.add(tTitle);
			document.add(new Phrase("	INFORMACIÓN GENERAL"));
			document.add(tHeader);
			document.add(new Phrase("	ACTIVIDAD"));
			document.add(tData);
			document.add(new Phrase("	 SECIONES"));
			document.add(tSec);
			document.add(new Phrase("	 OPORTUNIDADES DE MEJORA"));
			document.add(tSuge);
			document.close();

			// return document in bytes matrix
			return stream.toByteArray();

		} catch (DocumentException ex) {

		}
		return null;
	}
}
