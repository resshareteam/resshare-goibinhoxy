package com.resshare.springboot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import com.google.firebase.FirebaseApp;
import com.resshare.clienst.FileUploaderClient;
import com.resshare.framework.core.service.RequestClient;

@SpringBootApplication(scanBasePackages = { "com.resshare" }) // same as @Configuration
																// @EnableAutoConfiguration @ComponentScan
																// combined
public class ResshareGoibinhOxyApp {

	public static void main(String[] args) {
		SpringApplication.run(ResshareGoibinhOxyApp.class, args);
	}

	public static String DATABASE_URL;
	// public static final Logger log =
	// LoggerFactory.getLogger(ResshareGoibinhOxyApp.class);
	//
	// public static final String MENU_APP = "system_settings/menu_config/data";
	// public static final String RESPONSES = "responses";
	// public static final String RESPONSES_HIS = "responses_his";
	// public static final String MENU_APP_HIS =
	// "system_settings/menu_config/his/data";
	// protected static final String REST_SERVICE_URI_CORE =
	// "http://localhost:8088/config";
	// public static String REST_SERVICE_URI = "http://localhost:8080/api";
	// public static String RESSHARE_REST_SERVICE_URI_DRIVER =
	// "http://localhost:8086/api";
	public static String APPLICATION_NAME;
	public static String KEY;

	// private static void postOutput(Output output) {
	// System.out.println("Testing postOutput Output API----------");
	// RestTemplate restTemplate = new RestTemplate();
	//
	// URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/output/", output,
	// Output.class);
	// System.out.println("Location : " + uri.toASCIIString());
	// }
	private String runNgok() {
		String backend_address;
		String ngrokPathconfig = "ngrokconfig.properties";
		try {
			
			
			
		final NgrokClient ngrokClient = new NgrokClient.Builder().build();
		 final int port = 8186;//getPort(configuration);

		    final CreateTunnel createTunnel = new CreateTunnel.Builder()
		            .withAddr(port)
		            .build();
		    final Tunnel tunnel = ngrokClient.connect(createTunnel);
		    
		    System.out.println( tunnel.getPublicUrl() );
		    backend_address = tunnel.getPublicUrl().replaceFirst("http://", "");
		    
			System.out.println("NgrokClient="+backend_address);
		    Properties propconfigNgrok = StartServiceListenerCore.getConfig(ngrokPathconfig);
			 
		  propconfigNgrok.setProperty( "public_url", backend_address);
			try {
				OutputStream output = new FileOutputStream(ngrokPathconfig);

				propconfigNgrok.store(output, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
} catch (Exception e) {
		Properties propconfigNgrok = StartServiceListenerCore.getConfig(ngrokPathconfig);
		backend_address = propconfigNgrok.getProperty("public_url");
	
		System.out.println("ERROR: NgrokClient.");
		System.out.println(e.getMessage());
		System.out.println("NgrokClient="+backend_address);

		 
}
		return backend_address;
	};
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				if ((FirebaseApp.getApps() == null) || (FirebaseApp.getApps().size() == 0)) {
					try {

						// FileInputStream serviceAccount = new FileInputStream("service-account.json");
						// FirebaseOptions options;
						// try {
						// options = new FirebaseOptions.Builder()
						// .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
						// .setDatabaseUrl(StartServiceListenerCore.DATABASE_URL).build();
						// FirebaseApp.initializeApp(options);
						// System.out.println("success ");
						//
						// // loadHostInstance();
						// FirebaseDatabase.getInstance().getReference("draft").setValue(null);
						Properties properties = StartServiceListenerCore.getConfig();

						String backend_address = properties.getProperty("backend_address");
						String app_name = properties.getProperty("app_name");
						String backend_key = properties.getProperty("backend_key");
						String ngrok = properties.getProperty("ngrok");
						if("true".equals(ngrok))
						backend_address = runNgok();
						FileUploaderClient.buildUIScript();
						RequestClient.registerApp(app_name, backend_key, backend_address);
					
						StartServiceListenerCore.startListener();
						ServiceListenerGoibinhoxyStart.startListener();

					} catch (Exception e) {
						System.out.println("ERROR: invalid service account credentials. See README.");
						System.out.println(e.getMessage());

						System.exit(1);
					}
				}
			};

		};
	}
}
