package mx.com.bsmexico.customertool.referencias.plugin;

import java.io.File;
import java.io.FileOutputStream;

import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.api.process.Exporter;
import mx.com.bsmexico.customertool.api.report.ContextReport;
import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
import mx.com.bsmexico.customertool.api.report.ReportGenerator;
import mx.com.bsmexico.customertool.api.report.ReportType;

public class ReferenciaXlsxExporter implements Exporter<Referencia> {

	private ExportSource<Referencia> source;

	/**
	 * @param source
	 */
	public ReferenciaXlsxExporter(ExportSource<Referencia> source) {
		if (source == null) {
			throw new IllegalArgumentException("Source can not be null");
		}
		this.source = source;
	}

	@Override
	public void export(File destFile) throws Exception {
		final FileOutputStream fout = new FileOutputStream(destFile);
		final ContextReport context = new ContextReport();
		context.setType(ReportType.XLSX);
		ReportGenerator.generateFromCompiledReport("reports/reporteReferencias.jasper", context,
				ReportDataSourceFactory.getBeanDataSource(source.getData()), fout);
		fout.close();
	}

}
