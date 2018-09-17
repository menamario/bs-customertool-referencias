package mx.com.bsmexico.customertool.referencia.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.api.report.ContextReport;
import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
import mx.com.bsmexico.customertool.api.report.ReportGenerator;
import mx.com.bsmexico.customertool.api.report.ReportType;
import mx.com.bsmexico.customertool.referencias.plugin.Referencia;
import mx.com.bsmexico.customertool.referencias.plugin.ReferenciaImporter;
import mx.com.bsmexico.customertool.referencias.plugin.ReferenciaXlsxExporter;

public class ImporterExporterText {

	@Test
	public void reportReferenciasXslxTest() {
		final File file = new File("referenciasReporte.xlsx");
		final List<Referencia> dataReport = new ArrayList<>();
		Referencia referencia = new Referencia();
		referencia.setDigitoVerificador("10");
		referencia.setReferencia("1234");
		referencia.setReferenciaCompleta("1234567890");
		dataReport.add(referencia);
		referencia = new Referencia();
		referencia.setDigitoVerificador("11");
		referencia.setReferencia("1231");
		referencia.setReferenciaCompleta("1234567891");
		dataReport.add(referencia);
		referencia = new Referencia();
		referencia.setDigitoVerificador("11");
		referencia.setReferencia("1231");
		referencia.setReferenciaCompleta("1234567891");
		dataReport.add(referencia);
		try {
			final FileOutputStream fout = new FileOutputStream(file);
			file.createNewFile();
			final ContextReport context = new ContextReport();
			context.setType(ReportType.XLSX);
			ReportGenerator.generateFromCompiledReport("reports/reporteReferencias.jasper", context,
					ReportDataSourceFactory.getBeanDataSource(dataReport), fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void reportExporterReferenciasXslxTest() {
		final File file = new File("referenciasReporte.xlsx");
		final ReferenciaXlsxExporter exporter = new ReferenciaXlsxExporter(new ExportSource<Referencia>() {

			@Override
			public List<Referencia> getData() {
				final List<Referencia> dataReport = new ArrayList<>();
				Referencia referencia = new Referencia();
				referencia.setDigitoVerificador("10");
				referencia.setReferencia("1234");
				referencia.setReferenciaCompleta("1234567890");
				dataReport.add(referencia);
				referencia = new Referencia();
				referencia.setDigitoVerificador("11");
				referencia.setReferencia("1231");
				referencia.setReferenciaCompleta("1234567891");
				dataReport.add(referencia);
				referencia = new Referencia();
				referencia.setDigitoVerificador("11");
				referencia.setReferencia("1231");
				referencia.setReferenciaCompleta("1234567891");
				dataReport.add(referencia);
				return dataReport;
			}

		});

		try {			
			file.createNewFile();
			exporter.export(file);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void importerTest() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File file = new File(classLoader.getResource("plain/referencias.xlsx").getFile());

		final ImportTarget<Referencia> target = new ImportTarget<Referencia>() {

			@Override
			public void setData(List<Referencia> data) {
				Assert.assertTrue(data.size() == 5);
				Assert.assertTrue("123456".equals(data.get(0).getReferencia()));
				Assert.assertTrue("234232".equals(data.get(1).getReferencia()));
				Assert.assertTrue("1234567".equals(data.get(2).getReferencia()));
				Assert.assertTrue("123".equals(data.get(3).getReferencia()));
				Assert.assertTrue("hola".equals(data.get(4).getReferencia()));
			}

		};		

		try {
			final ReferenciaImporter importer = new ReferenciaImporter(target);
			importer.importFile(file);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
