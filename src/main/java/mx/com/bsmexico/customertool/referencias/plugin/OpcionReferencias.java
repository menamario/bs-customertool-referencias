package mx.com.bsmexico.customertool.referencias.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;
import mx.com.bsmexico.customertool.api.report.ContextReport;
import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
import mx.com.bsmexico.customertool.api.report.ReportGenerator;
import mx.com.bsmexico.customertool.api.report.ReportType;

public class OpcionReferencias extends Feature {

	ReferenciaTable t = null;
	int hashCodeGuardado;
	Button bCerrar = new Button();
	ImageView error = new ImageView();
	ImageView check = new ImageView();
	double xOffset=0;
	double yOffset=0;

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
			route = navRuoteBuilder.addNode("Generación de referencias", "Generación de referencias", 0, false,
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
		getMenuNavigator().hide();
		getDesktop().updatePleca("#f0a21d", null);

		Pane mainPane = new BorderPane();

		mainPane.setPadding(new Insets(0, 20, 0, 20));

		ImageView importarArchivo = null;
		ImageView instrucciones = null;
		ImageView atras = null;
		ImageView cerrar = null;

		try {
			error = new ImageView(new Image(this.getImageInput("/img/error.png")));
			error.setPreserveRatio(true);
			error.setFitWidth(66);
			check = new ImageView(new Image(this.getImageInput("/img/check.png")));
			check.setPreserveRatio(true);
			check.setFitWidth(66);
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			atras.setPreserveRatio(true);
			atras.setFitWidth(40);
			cerrar = new ImageView(new Image(this.getImageInput("/img/close.png")));
			cerrar.setPreserveRatio(true);
			cerrar.setFitWidth(25);
			importarArchivo = new ImageView(new Image(this.getImageInput("/img/importarReferencias.png")));
			importarArchivo.setPreserveRatio(true);
			importarArchivo.setFitWidth(70);
			instrucciones = new ImageView(new Image(this.getImageInput("/img/instrucciones.png")));
			instrucciones.setPreserveRatio(true);
			instrucciones.setFitWidth(70);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button bAtras = new Button();
		Button bInstrucciones = new Button();
		Button bImportarArchivo = new Button();

		bCerrar.setGraphic(cerrar);
		bCerrar.setStyle("-fx-background-color: transparent;");
		StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

		bAtras.setGraphic(atras);
		bAtras.setStyle("-fx-background-color: transparent;");
		bAtras.setTooltip(new Tooltip("Regresar"));
		bInstrucciones.setGraphic(instrucciones);
		bInstrucciones.setText("Instrucciones");
		bInstrucciones.setTextFill(Color.WHITE);
		bInstrucciones.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bInstrucciones.setContentDisplay(ContentDisplay.TOP);

		bImportarArchivo.setGraphic(importarArchivo);
		bImportarArchivo.setText("Importar archivo");
		bImportarArchivo.setTextFill(Color.WHITE);
		bImportarArchivo.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bImportarArchivo.setContentDisplay(ContentDisplay.TOP);

		bAtras.setOnMouseClicked(evt -> {
			if (t.getItems().hashCode() == hashCodeGuardado) {
				salir();
			} else {
				getDesktop().opacar();
				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(5));
				canvas.setStyle("-fx-background-color: #e90e5c;");
				canvas.setPrefSize(512, 54);

				canvas.getChildren().add(bCerrar);
				StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

				bCerrar.setOnMouseClicked(ev -> {
					stage.hide();
				});

				Label mensaje = new Label("¿Quieres guardar los cambios realizados en el archivo?");
				mensaje.setWrapText(true);
				mensaje.setTextAlignment(TextAlignment.CENTER);
				mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
				mensaje.setTextFill(Color.web("#777777"));
				mensaje.setPrefWidth(400);

				Button bGuardar = new Button("Guardar");
				bGuardar.setStyle(
						"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
				bGuardar.setPrefSize(140, 40);

				bGuardar.setTextFill(Color.WHITE);

				bGuardar.setOnMouseClicked(ev -> {
					stage.hide();

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (guardar())
								salir();
						}
					});
				});

				Button bSalir = new Button("No guardar");
				bSalir.setStyle(
						"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 12px;  -fx-border-radius: 8px;-fx-background-color: rgba(255, 255, 255, 0.8);-fx-border-width: 1px;-fx-border-color: #006dff;-fx-font-weight:bold;-fx-background-radius: 8px");
				bSalir.setPrefSize(140, 40);
				bSalir.setTextFill(Color.web("#006dff"));

				bSalir.setOnMouseClicked(ev -> {
					stage.hide();
					getMenuNavigator().show();
					getDesktop().setWorkArea(null);
					getDesktop().updatePleca("black", null);
				});

				HBox opciones = new HBox();
				opciones.getChildren().addAll(bSalir, bGuardar);
				opciones.setAlignment(Pos.CENTER);
				opciones.setSpacing(35);

				VBox vbox = new VBox();
				vbox.setSpacing(50);
				vbox.setAlignment(Pos.TOP_CENTER);
				vbox.setPrefSize(512, 345);
				// VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(mensaje);
				vbox.getChildren().add(opciones);

				stage.setScene(new Scene(vbox, 512, 345));
				stage.setResizable(false);
				stage.initOwner(getDesktop().getStage());
				stage.initModality(Modality.WINDOW_MODAL);
				stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
				stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
				stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			        if (KeyCode.ESCAPE == event.getCode()) {
			            stage.close();
			        }
			    });
				stage.showAndWait();
				getDesktop().desOpacar();

			}
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files", "*.xlsx", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);

		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();
		headerBox1.setSpacing(40);
		headerBox1.getChildren().add(bAtras);
		Label l = new Label("    Generación de referencias    ");
		l.setTextFill(Color.WHITE);
		l.setStyle(
				"-fx-background-color: #f0a21d;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-border-radius: 0 0 5 5; -fx-background-radius: 0 0 4 4; ");
		headerBox1.getChildren().add(l);
		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(100);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);
		headerBox1.setPadding(new Insets(0, 30, 0, 0));

		BorderPane borderpane = new BorderPane();
		borderpane.setPadding(new Insets(0, 20, 0, 20));

		HBox hb = new HBox();
		hb.setSpacing(50);

		ObservableList<String> options = FXCollections.observableArrayList("Algoritmo 10");
		final ComboBox comboBox = new ComboBox(options);
		comboBox.getSelectionModel().selectFirst();
		comboBox.setPrefHeight(28);
		comboBox.setMinWidth(250);
		hb.getChildren().add(comboBox);

		Button bCalcular = new Button("Calcular");
		bCalcular.setStyle(
				"-fx-background-color: #accaf3;  -fx-font-family: FranklinGothicLT;-fx-font-size: 15px;-fx-font-weight:bold");
		bCalcular.setPrefWidth(140);
		bCalcular.setTextFill(Color.BLACK);

		hb.getChildren().add(bCalcular);
		hb.setAlignment(Pos.CENTER_RIGHT);
		// borderpane.setCenter(hb);

		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT;-fx-font-size: 15px;-fx-font-weight:bold");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);
		hb.getChildren().add(bGuardar);
		hb.setPadding(new Insets(0, 15, 0, 0));

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
				guardar();

			}
		});

		bInstrucciones.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #239d45;");
				canvas.setPrefSize(800, 60);
				canvas.setMinHeight(54);
				canvas.setOnMousePressed(e -> {
					xOffset = e.getSceneX();
					yOffset = e.getSceneY();

		        });
				
				canvas.setOnMouseDragged(e -> {
					stage.setX(e.getScreenX() - xOffset);
					stage.setY(e.getScreenY() - yOffset - 20);

		        });
				
				canvas.getChildren().add(bCerrar);
				StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

				bCerrar.setOnMouseClicked(ev -> {
					stage.hide();
				});	

				
				
				Label instruccionesLabel = new Label(
						"Algoritmo Base 10  (1 Dígito Verificador)\nProcedimiento para calcular el Dígito Verificador");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.CENTER);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.web("#828488"));
				instruccionesLabel.setMinHeight(40);
				StackPane p = new StackPane();
				p.setPadding(new Insets(20, 0, 20, 0));
				p.setStyle("-fx-background-color: white");
				p.getChildren().add(instruccionesLabel);
				
	

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Referencias - Instrucciones");

				TextArea textArea = new TextArea();
				textArea.setText("\nDATOS NECESARIOS PARA EL CÁLCULO:"

						+ "\n\nReferencia 	de 1 a 6 Dígitos mas 1 digito verificador."

						+ "\nEjemplo:" + "\nSi la Referencia es igual a:     3142233"

						+ "\n\n1. Si la Referencia tiene una longitud mayor a 6 posiciones, se toman los primeros 6 dígitos de  la derecha sin contar el digito verificador."

						+ "\n\n314223"

						+ "\n\n2.	De derecha a izquierda se van multiplicando cada uno de los dígitos por los números 2, 3, 4, 5.. siempre iniciando la secuencia con el número 2 aun cuando el número a multiplicar sea 0 deberá tomarse en cuenta."

						+ "\n\n3  1  4  2  2  3" + "\n*  *  *  *  *  *" + "\n7  6  5  4  3  2"

						+ "\n\n21  6 20  8  6  6"

						+ "\n\n2. Se suman todos los resultados de las multiplicaciones del punto 1."

						+ "\n\n21 + 6 + 20 + 8 + 6 + 6 = 67"

						+ "\n\n3.	El resultado de la suma indicada en el punto 2, se divide entre 7."

						+ "\n\n        __9___" + "\n 7     | 67   " + "\n         4"

						+ "\n\n4.	El residuo de la división del punto 3 se le resta a 7 y el resultado será el dígito verificador."

						+ "\n\n7 - 4 = 3" + "\nDígito Verificador: 3"

						+ "\n\n5.	A la referencia se le agregará el dígito verificador y esa será la línea de captura que recibirá el cajero en ventanilla."

						+ "\n\nReferencia Completa: 3142233");
				textArea.setEditable(false);
				textArea.setWrapText(true);
				textArea.setStyle("-fx-background-color:white;-fx-font-family: FranklinGothicLT;-fx-font-size: 14px;-fx-fill:black;-fx-focus-color: transparent; -fx-text-box-border: transparent;-fx-box-border: none;");
				textArea.setPrefWidth(790);
				textArea.setMinWidth(790);
				
				
			
				
				
				
				
				TabPane tabPane = new TabPane();
				Tab tabInstrucciones = new Tab("    Instrucciones    ");
				tabInstrucciones.setClosable(false);
				StackPane sp = new StackPane();
				sp.setPrefSize(800, 600);
				sp.getChildren().add(textArea);
				sp.setPadding(new Insets(0,30,0,30));
				sp.setStyle("-fx-background-color:white;");
				tabInstrucciones.setContent(sp);
				
				tabPane.getTabs().addAll(tabInstrucciones);
				
				

				VBox vbox = new VBox();
				textArea.prefHeightProperty().bind(vbox.prefHeightProperty().add(-60));
				vbox.setPrefSize(600, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(p);
				vbox.getChildren().add(tabPane);
				Scene scene = new Scene(vbox,820,600);
				scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();

			}
		});

		VBox vbox = new VBox(headerBox1, borderpane);
		vbox.setSpacing(40);

		((BorderPane) mainPane).setTop(vbox);

		t = new ReferenciaTable();
		hashCodeGuardado = t.getItems().hashCode();
		//t.getStyleClass().add("tabla-referencias");

		t.setMaxSize(800, 400);

		((BorderPane) mainPane).setCenter(t);
		BorderPane.setAlignment(t, Pos.CENTER_RIGHT);

		BorderPane.setMargin(t, new Insets(40, 25, 50, 0));
		// BorderPane.setMargin(t, new Insets(25, 0, 0, 0));

		bImportarArchivo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				fileChooser.setInitialDirectory(new File(currentPath));
				File file = fileChooser.showOpenDialog(getDesktop().getStage());
				if (file != null) {
					ReferenciaImporter benImporter = new ReferenciaImporter(t);
					try {
						benImporter.importFile(file);
					} catch (Exception e1) {
						getDesktop().opacar();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						Pane canvas = new Pane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color:  #e90e5c;");
						canvas.setPrefSize(512, 54);

						canvas.getChildren().add(bCerrar);
						StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

						bCerrar.setOnMouseClicked(ev -> {
							stage.hide();
						});

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Generacion de Referencias - Formato de Archivo Incorrecto");

						Label mensaje = new Label("El archivo no tiene el formato correcto");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
						bContinuar.setPrefSize(140, 40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(25);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 345);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(error);
						mensaje.setPadding(new Insets(0, 0, 35, 0));
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 345));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
						stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
					        if (KeyCode.ESCAPE == event.getCode()) {
					            stage.close();
					        }
					    });
						stage.showAndWait();
						getDesktop().desOpacar();
					}
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

	private boolean guardar() {
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
				getDesktop().opacar();
				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(5));
				canvas.setStyle("-fx-background-color: #e90e5c;");
				canvas.setPrefSize(512, 54);
				
				canvas.getChildren().add(bCerrar);
				StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

				bCerrar.setOnMouseClicked(ev -> {
					stage.hide();
				});

				Label mensaje = new Label("Error en los datos proporcionados");
				mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
				mensaje.setTextFill(Color.web("#777777"));

				Button bContinuar = new Button("Continuar");
				bContinuar.setStyle(
						"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
				bContinuar.setPrefSize(140,40);
				bContinuar.setTextFill(Color.WHITE);

				bContinuar.setOnMouseClicked(evt -> {
					stage.hide();
				});

				VBox vbox = new VBox();
				vbox.setPrefSize(512, 345);
				vbox.setSpacing(25);
				vbox.setAlignment(Pos.TOP_CENTER);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(error);
				mensaje.setPadding(new Insets(0,0,35,0));
				vbox.getChildren().add(mensaje);
				vbox.getChildren().add(bContinuar);

				stage.setScene(new Scene(vbox, 512, 345));
				stage.setResizable(false);
				stage.initOwner(getDesktop().getStage());
				stage.initModality(Modality.WINDOW_MODAL);
				stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
				stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
				stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			        if (KeyCode.ESCAPE == event.getCode()) {
			            stage.close();
			        }
			    });
				stage.showAndWait();
				getDesktop().desOpacar();
				return false;

			} else if (numRegistros > 0) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				FileChooser saveFile = new FileChooser();
				saveFile.setInitialDirectory(new File(currentPath));

				// Set extension filter
				FileChooser.ExtensionFilter sfFilter = new FileChooser.ExtensionFilter("Excel Files", "*.xlsx");
				saveFile.getExtensionFilters().add(sfFilter);

				// Show save file dialog
				File file = saveFile.showSaveDialog(getDesktop().getStage());

				if (file != null) {
					ReferenciaExporter exporter = new ReferenciaExporter(t);
					try {
						exporter.export(file);

						final FileOutputStream fout = new FileOutputStream(file);
						file.createNewFile();
						final ContextReport context = new ContextReport();
						context.setType(ReportType.XLSX);
						ReportGenerator.generateFromCompiledReport("reports/reporteReferencias.jasper", context,
								ReportDataSourceFactory.getBeanDataSource(t.getData()), fout);
						fout.close();

						hashCodeGuardado = t.getItems().hashCode();
						getDesktop().opacar();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color:  #a9d42c;");
						canvas.setPrefSize(512, 54);

						canvas.getChildren().add(bCerrar);
						StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

						bCerrar.setOnMouseClicked(ev -> {
							stage.hide();
						});

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Generacion de Referencias - Archivo Guardado");

						Label mensaje = new Label("El archivo fue guardado exitosamente");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
						bContinuar.setPrefSize(140, 40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(25);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 345);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(check);
						mensaje.setPadding(new Insets(0, 0, 35, 0));
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 345));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
						stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
					        if (KeyCode.ESCAPE == event.getCode()) {
					            stage.close();
					        }
					    });
						stage.showAndWait();
						getDesktop().desOpacar();
						return true;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return false;
					}
				}
				return false;

			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private void salir() {
		getMenuNavigator().show();
		getDesktop().setWorkArea(null);
		getDesktop().updatePleca("black", null);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 2;
	}

}
