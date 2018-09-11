package mx.com.bsmexico.customertool.referencias.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;

public class OpcionReferencias extends Feature {

	ReferenciaTable t = null;

	public String getNombreMenu() {
		// TODO Auto-generated method stub
		return "Alta de Beneficiarios";
	}

	public void ejecutar() {
		System.out.println("Se ejecuto el alta de Beneficiarios");

	}

	public String getImagenMenu() {
		return "/img/beneficiarios.svg";

	}

	@SuppressWarnings("unused")
	private InputStream getImageInput(final String file) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);
		return input;

	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new mx.com.bsmexico.customertool.api.NavRoute.BuilderNavRoute(
				"TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder.addNode("Generacion de Referencias", "Generacion de Referencias", 0, false,
					getImageInput("/img/referencias.png")).build();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Layout.LayoutBuilder("l1").route(route).build();
	}

	@Override
	public void launch() {

		Pane mainPane = new BorderPane();

		mainPane.setPadding(new Insets(0, 20, 0, 20));

		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();

		ImageView atras = null;
		ImageView importarArchivo = null;
		ImageView instrucciones = null;

		try {
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			importarArchivo = new ImageView(new Image(this.getImageInput("/img/importarArchivo.png")));
			importarArchivo.setPreserveRatio(true);
			importarArchivo.setFitWidth(70);
			instrucciones = new ImageView(new Image(this.getImageInput("/img/instrucciones.png")));
			instrucciones.setPreserveRatio(true);
			instrucciones.setFitWidth(70);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Button bAtras = new Button();
		Button bInstrucciones = new Button();
		Button bImportarArchivo = new Button();

		// bAtras.setGraphic(atras);
		// bAtras.setStyle("-fx-background-color: transparent;");
		// bAtras.setTooltip(new Tooltip("Regresar"));
		bInstrucciones.setGraphic(instrucciones);
		bInstrucciones.setText("Instrucciones");
		bInstrucciones.setTextFill(Color.WHITE);
		bInstrucciones.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bInstrucciones.setContentDisplay(ContentDisplay.TOP);

		bImportarArchivo.setGraphic(importarArchivo);
		bImportarArchivo.setText("Importar Archivo");
		bImportarArchivo.setTextFill(Color.WHITE);
		bImportarArchivo.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bImportarArchivo.setContentDisplay(ContentDisplay.TOP);

		// bAtras.setOnMouseClicked(evt -> {
		// getMenuNavigator().show();
		// });

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel and Csv files (*.xls,*.csv)",
				"*.csv", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);

		// headerBox1.getChildren().add(bAtras);
		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(30);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);

		BorderPane borderpane = new BorderPane();
		borderpane.setPadding(new Insets(0, 20, 0, 20));
		Label lFormato = new Label("Formato");
		lFormato.setTextFill(Color.WHITE);
		RadioButton rbTxt = new RadioButton("txt");
		rbTxt.setTextFill(Color.WHITE);
		RadioButton rbCsv = new RadioButton("csv");
		rbCsv.setSelected(true);
		rbCsv.setTextFill(Color.WHITE);
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);
		rbTxt.setDisable(true);

		HBox hb = new HBox();
		hb.setSpacing(10);

		Label mensajeCsv = new Label("El archivo se guardara en formato csv       ");
		mensajeCsv.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;-fx-font-weight: bold");
		mensajeCsv.setTextFill(Color.WHITE);

		Button bCalcular = new Button("Calcular");
		bCalcular.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
		bCalcular.setPrefWidth(140);
		bCalcular.setTextFill(Color.WHITE);

		hb.getChildren().add(bCalcular);
		hb.setAlignment(Pos.CENTER_RIGHT);
		// borderpane.setCenter(hb);

		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);
		hb.getChildren().add(bGuardar);

		borderpane.setRight(hb);

		bCalcular.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				calcularDigitoVerificador();
			}
		});

		bGuardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				try {
					calcularDigitoVerificador();

					int numError = 0;
					int numRegistros = 0;
					boolean isValid = t.validateTable();
					for (Referencia r : t.getItems()) {
						if (t.isActiveModel(r)) {
							numRegistros++;
						}
					}
					t.refresh();

					if (!isValid) {
						Stage stage = new Stage();

						Pane canvas = new Pane();
						canvas.setPadding(new Insets(10));
						canvas.setStyle("-fx-background-color:  #e90e5c;");
						canvas.setPrefSize(512, 50);

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Archivos Bantotal - Beneficiarios - Datos Incorrectos");

						Label mensaje = new Label("Error en los datos proporcionados");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
						bContinuar.setPrefWidth(140);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(50);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 275);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 275));
						stage.setResizable(false);
						stage.show();

					} else if (numRegistros > 0) {
						String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
						FileChooser saveFile = new FileChooser();
						saveFile.setInitialDirectory(new File(currentPath));

						// Set extension filter
						FileChooser.ExtensionFilter sfFilter = new FileChooser.ExtensionFilter("csv files (*.csv)",
								"*.csv");
						saveFile.getExtensionFilters().add(sfFilter);

						// Show save file dialog
						File file = saveFile.showSaveDialog(getDesktop().getStage());

						if (file != null) {
							ReferenciaExporter exporter = new ReferenciaExporter(t);
							try {
								exporter.export(file);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						Stage stage = new Stage();

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(10));
						canvas.setStyle("-fx-background-color:  #a9d42c;");
						canvas.setPrefSize(512, 50);

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Archivos Bantotal - Beneficiarios - Archivo Guardado");

						Label mensaje = new Label("El archivo fue guardado exitosamente");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
						bContinuar.setPrefWidth(140);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(50);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 275);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 275));
						stage.setResizable(false);
						stage.show();

					}
				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}
		});

		bInstrucciones.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Stage stage = new Stage();

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #239d45;");
				canvas.setPrefSize(800, 60);

				Label instruccionesLabel = new Label(
						"Banco Sabadell agradece su preferencia, a continuacion detallamos los pasos que debe seguir para capturar los datos de alta de beneficiario.");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.JUSTIFY);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.WHITE);
				canvas.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Beneficiarios - Instrucciones");

				TextArea textArea = new TextArea();
				textArea.setText("\n"
						+ "1) Revise que la configuracion regional de su sistema operativo este en Español (México)."
						+ "\n\n2) Los datos que se capturan deben estar en mayusculas y sin caracteres especiales."
						+ "\n\n3) Finalmente le pedimos validar que los datos marcados como obligatorios se encuentren con la información requerida."
						+ "\n\n4) Al concluir la captura de beneficiarios, dar un click en el boton de Guardar, en seguida se abrira una ventana donde usted podrá guardar el archivo en la ruta que indique y con el nombre que desee."
						+ "\n\n5) Al concluir el guardado correcto del archivo de Beneficiarios el siguiente paso es ingresar a su banca en linea de Banco Sabadell, para iniciar el proceso de Alta de Beneficiarios."
						+ "\n\n6) Los Beneficiarios que se dan de alta estarán disponibles para transaccionar despues de 30 minutos.");
				textArea.setEditable(false);
				textArea.setWrapText(true);

				VBox vbox = new VBox();
				textArea.prefHeightProperty().bind(vbox.prefHeightProperty().add(-60));
				vbox.setPrefSize(600, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(textArea);

				stage.setScene(new Scene(vbox, 600, 600));
				stage.setResizable(false);
				stage.show();
				// Hide this current window (if this is what you want)
				// ((Node)(event.getSource())).getScene().getWindow().hide();

			}
		});

		VBox vbox = new VBox(headerBox1, borderpane);
		vbox.setSpacing(20);

		((BorderPane) mainPane).setTop(vbox);

		t = new ReferenciaTable();

		t.setMaxSize(500, 400);

		((BorderPane) mainPane).setCenter(t);
		// BorderPane.setMargin(t, new Insets(25, 0, 0, 0));

		bImportarArchivo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				fileChooser.setInitialDirectory(new File(currentPath));
				File file = fileChooser.showOpenDialog(getDesktop().getStage());
				ReferenciaImporter benImporter = new ReferenciaImporter(t);
				try {
					benImporter.importFile(file);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		getDesktop().setWorkArea(mainPane);
	}

	private void calcularDigitoVerificador() {
		try {

			for (Referencia r : t.getItems()) {

				r.setReferenciaCompleta("");
				r.setDigitoVerificador("");

				if (t.isActiveModel(r)) {
					if (t.validateModel(r)) {
						int suma = 0;
						for (int i = 0; i < 6; i++) {
							suma += Integer.valueOf(r.getReferencia().substring(i, i + 1)) * (7 - i);
						}
						String dv = String.valueOf(7 - (suma % 7));
						r.setDigitoVerificador(dv);
						r.setReferenciaCompleta(r.getReferencia() + r.getDigitoVerificador());
					}
				}
			}
			t.refresh();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
