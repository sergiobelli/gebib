package eu.sergiobelli.gebib.control.report;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.dao.LibriDao;
import eu.sergiobelli.gebib.model.orm.dao.PropertiesDao;
import eu.sergiobelli.gebib.model.orm.dao.PublicazioniDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;

/**
 * 
 * @author S.BELLI
 */
public class ReportManager {

	public ReportManager () {
		
		try {
			
			nomeCollezione = 	PropertiesDao.getInstance().get("gebib.collection.name");
			percorso = 			PropertiesDao.getInstance().get("gebib.report.path");
			nome = 				PropertiesDao.getInstance().get("gebib.report.name");
			estensione = 		PropertiesDao.getInstance().get("gebib.report.extension");
			visualizza = 		false;
			
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}
	
	private StringBuffer extendedFileName = null;
	
	private String nomeCollezione;
	public String getNomeCollezione() {return nomeCollezione;}
	public void setNomeCollezione(String nomeCollezione) {this.nomeCollezione = nomeCollezione;}

	private String percorso;
	public String getPercorso() {return percorso;}
	public void setPercorso(String percorso) {this.percorso = percorso;}

	private String nome;
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	private String estensione;
	public String getEstensione() {return estensione;}
	public void setEstensione(String estensione) {this.estensione = estensione;}
	
	private boolean visualizza;
	public boolean isVisualizza() {return visualizza;}
	public void setVisualizza(boolean visualizza) {this.visualizza = visualizza;}	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private HSSFCellStyle titleStyle;
	private HSSFCellStyle headerStyle;
	private HSSFCellStyle tableStyle;
	private HSSFWorkbook bibliotecaReport;

	public String redirect() { return "null"; }
	public void generateReport (ActionEvent event) {

		try {

			generateReport(LibriDao.getInstance().list());

		}
		catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}
	
	/**
	 * 
	 * @param libri
	 */
	protected void generateReport (List<Libri> libri) {

		try {
			
			bibliotecaReport = new HSSFWorkbook();

			titleStyle = getTitleStyle();
			headerStyle = getHeaderStyle();
			tableStyle = getTableStyle();

			generateSheet (libri);

			writeReportToFile (nomeCollezione);
			
			if (visualizza) {
				File file = new File(extendedFileName.toString());
		        writeFileToBrowser(file.getPath(), file.getName(), "application/xls");
			}
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
	}

	/**
	 * 
	 * @param value
	 */
	private void writeReportToFile(String value) {
		
		try {
			
			// Write the output to a file
			String filePath 			= percorso;
			logger.debug("filePath="+filePath);
			
			String fileName 			= nome;
			logger.debug("fileName="+fileName);
			
			String fileExtension 		= estensione;
			logger.debug("fileExtension="+fileExtension);
			
			String date					= new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
			logger.debug("date="+date);
			
			extendedFileName = 
				new StringBuffer()
					.append(filePath)
					.append(fileName)
					.append("_")
					.append(date)
					.append(fileExtension);
			logger.info("extendedFileName="+extendedFileName.toString());
			
			File file = new File(extendedFileName.toString());
			if (!file.exists()) {
				boolean isCreated = file.createNewFile();
				if (!isCreated) {
					throw new Exception("KO - file not created ! ");
				}
			} else {
				file.createNewFile();
			}

			FileOutputStream fileOut = new FileOutputStream(file);
			bibliotecaReport.write(fileOut);
			fileOut.close();
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}

	/**
	 * 
	 * @param libri
	 * @throws GeBibException
	 */
	private void generateSheet(List<Libri> libri) throws GeBibException {
		int i = 0;
		//Report biblioteca





		i = 0;

		HSSFSheet bibliotecaSheet = bibliotecaReport.createSheet(nomeCollezione);

		HSSFCell bibliotecaTitle = bibliotecaSheet.createRow((short) i).createCell((short) 0);
		bibliotecaTitle.setCellValue(nomeCollezione);
		bibliotecaTitle.setCellStyle(headerStyle);

		bibliotecaSheet.createRow((short) i).createCell((short) 0).setCellValue("Titolo");
		bibliotecaSheet.getRow((short) i).getCell((short) 0).setCellStyle(headerStyle);
		bibliotecaSheet.getRow((short) i).createCell((short) 1).setCellValue("Isbn");
		bibliotecaSheet.getRow((short) i).getCell((short) 1).setCellStyle(headerStyle);
		bibliotecaSheet.getRow((short) i).createCell((short) 2).setCellValue("Autori");
		bibliotecaSheet.getRow((short) i).getCell((short) 2).setCellStyle(headerStyle);
		i++;

		for (Libri libro : libri) {

			bibliotecaSheet.createRow((short) i).createCell((short) 0).setCellValue(libro.getTitolo());
			bibliotecaSheet.getRow((short) i).getCell((short) 0).setCellStyle(tableStyle);
			bibliotecaSheet.createRow((short) i).createCell((short) 1).setCellValue(libro.getIsbn());
			bibliotecaSheet.getRow((short) i).getCell((short) 1).setCellStyle(tableStyle);
			
			List<Publicazioni> publicazioni = PublicazioniDao.getInstance().list(libro.getIdLibro());
			StringBuffer listaAutori = new StringBuffer("");
			for (Publicazioni publicazione : publicazioni) {
				Autori autore = AutoriDao.getInstance().get(publicazione.getId().getIdAutore());
				listaAutori.append(autore.getCognome()).append(", ").append(autore.getNome()).append(";");
			}
			
			String str = 
				(listaAutori.toString().length() != 0) ? 
						listaAutori.toString().substring(0, listaAutori.toString().length() - 1) : 
							"";
						
			bibliotecaSheet.createRow((short) i).createCell((short) 2).setCellValue(str);
			bibliotecaSheet.getRow((short) i).getCell((short) 2).setCellStyle(tableStyle);

			i++;

		}
		//Report biblioteca
	}

    private void writeFileToBrowser(String fileSource, String downloadName, String contentType) {
        if (!FacesContext.getCurrentInstance().getResponseComplete()) {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadName + "\"");
            try {
                ServletOutputStream out = response.getOutputStream();
                BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(fileSource));

                byte abyte0[] = new byte[4096];
                int i;
                while ((i = bufferedinputstream.read(abyte0, 0, 4096)) != -1) {
                    out.write(abyte0, 0, i);
                }

                out.flush();
                out.close();

                FacesContext.getCurrentInstance().responseComplete();
            } catch (IOException ex) {
            	GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
            }
        }
    }
	/**
	 * 
	 * @return
	 */
	private HSSFCellStyle getTitleStyle () {

		//title style
		HSSFCellStyle titleStyle = bibliotecaReport.createCellStyle();
		HSSFFont titleFont = bibliotecaReport.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleStyle.setFont(titleFont);
		//title style

		return titleStyle;
	}

	/**
	 * 
	 * @return
	 */
	private HSSFCellStyle getHeaderStyle () {

		//header style                        
		HSSFCellStyle headerStyle = bibliotecaReport.createCellStyle();
		HSSFFont headerFont = bibliotecaReport.createFont();
		headerFont.setColor(HSSFColor.WHITE.index);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setFont(headerFont);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFillForegroundColor(HSSFColor.RED.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setRightBorderColor(HSSFColor.BLACK.index);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerStyle.setTopBorderColor(HSSFColor.BLACK.index);           
		//header style                        

		return headerStyle;
	}	

	/**
	 * 
	 * @return
	 */
	private HSSFCellStyle getTableStyle () {

		//table style
		HSSFCellStyle tableStyle = bibliotecaReport.createCellStyle();
		tableStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setRightBorderColor(HSSFColor.BLACK.index);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		tableStyle.setTopBorderColor(HSSFColor.BLACK.index);
		//table style

		return tableStyle;
	}

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());



	public static void main(String[] args) {

		try {

			List<Libri> libri = LibriDao.getInstance().list();
			ReportManager manager = new ReportManager();
			manager.generateReport(libri);

		}
		catch (Exception ex) {

		}
	}


}
