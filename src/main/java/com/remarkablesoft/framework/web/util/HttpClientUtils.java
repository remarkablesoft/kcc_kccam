package com.remarkablesoft.framework.web.util;

import com.remarkablesoft.framework.util.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	HttpClientUtils.java
 * 		@프로그램 개요 		:	HTTP CLIENT 유틸
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2016. 12. 13.	:	이창현		-	신규생성
  * 	1.1  2018. 05. 08.	:	이균환		-	header와 json형태를 넘기는 메소드 추가.
  * 	1.2  2020. 05. 20.	:	이균환		-	ssl 무시할 수 있는 httpget 메소드 추가. 전반적으로 정리가 필요한듯.
 * 		============================================================================
 */
public abstract class HttpClientUtils {

		private static final Logger logger = LoggerFactory.getLogger( HttpClientUtils.class );

		private final static int HTTP_CLIENT_TIME = 5000;

		private HttpClientUtils() {

		}

		/**
		 * URL을 POST로 호출합니다.
		 * @param url
		 * @return
		 * @throws Exception
		 */
		public static String post( String url ) throws Exception {
				return post( url, null );
		}

		/**
		 * 2019.12.19 추가 - 미완
		 * SSL 인증서에 등록된 도메인을 체크하지 않고  POST방식으로 URL을 호출합니다.
		 * ex) 이중화가 되어있을 때 1번서버에서 2번서버를 호출할 때 사용
		 *
		 * @param urlString
		 * @return
		 * @throws Exception
		 */
		public static Map<String, Object> postDisableSslCheck( String urlString, String json ) throws Exception {

				Map<String, Object> result = new HashMap<>();

				//https 인증서 도메인 체크 무시
				HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier() {

						@Override
						public boolean verify( String hostname, SSLSession session ) {
								return true;	//체크를 하려면 false로 바꾸시면 됩니다.
						}
				} );

				try {


					logger.debug( urlString );
					URL url = new URL( urlString );
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			        conn.setRequestMethod("POST");
			        conn.setRequestProperty("Content-Type", "application/json");
			        conn.setDoOutput(true);

			        int responseCode = conn.getResponseCode();
			        StringBuffer response = new StringBuffer();

			        // success
			        if ( HttpStatus.SC_OK == responseCode) {

			        		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					        String inputLine;

					        while ((inputLine = in.readLine()) != null) {
					            response.append(inputLine);
					        }
					        in.close();
			        }

			        result.put( "response", response.toString() );
			        result.put( "responceCode", conn.getResponseCode() );

				}
				catch (MalformedURLException e) {
					e.printStackTrace();
					logger.error( e.getMessage() );
				}

				return result;
		}
		
		/**
		 * 
		 * @author james
		 * @param szUrl
		 * @return
		 * @throws Exception
		 */
	    public static String httpsGet( String szUrl) 
	    {
	        URL url = null;
	        HttpsURLConnection con = null;
	        String ret = new String();
	        
	        try {
	            url = new URL( szUrl );
	            ignoreSsl();
	            con = (HttpsURLConnection)url.openConnection();
	    
	    
	            BufferedReader br = null;
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    
	            String input = null;
	    
	            while ((input = br.readLine()) != null){
	                ret += input;
	            }
	            
	            br.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
    		catch ( Exception e ) {
    			e.printStackTrace();
    		} 
	        finally {
	            if (con != null) {
	                con.disconnect();
	            }
	        }
	        
	        return ret;
	        
	    }
		
	    public static void ignoreSsl() throws Exception{
	            HostnameVerifier hv = new HostnameVerifier() {
	    			@Override
	    			public boolean verify(String hostname, SSLSession session) {
	    				// TODO Auto-generated method stub
	    				return true;
	    			}
	            };
	            trustAllHttpsCertificates();
	            HttpsURLConnection.setDefaultHostnameVerifier(hv);
	    }
	    
	    
		private static void trustAllHttpsCertificates() throws Exception {
			    TrustManager[] trustAllCerts = new TrustManager[1];
			    TrustManager tm = new miTM();
			    trustAllCerts[0] = tm;
			    SSLContext sc = SSLContext.getInstance("SSL");
			    sc.init(null, trustAllCerts, null);
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		
		
		static class miTM implements TrustManager,X509TrustManager {

			    public boolean isServerTrusted(X509Certificate[] certs) {
			        return true;
			    }
			
			    public boolean isClientTrusted(X509Certificate[] certs) {
			        return true;
			    }
			
			    public void checkServerTrusted(X509Certificate[] certs, String authType)
			            throws CertificateException {
			        return;
			    }
			
			    public void checkClientTrusted(X509Certificate[] certs, String authType)
			            throws CertificateException {
			        return;
			    }

			    @Override
			    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			    	// TODO Auto-generated method stub
			    	return null;
			    }
			    
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					
				}
		};
	    

		/**
		 * URL을  Header와 POST로 호출합니다.
		 *
		 * @param url
		 * @param params
		 * @return
		 * @throws Exception
		 */
		public static String post( String url, Map<String, String> header, String json ) {

				try {
						return postWithException( url, header, json);
				}
				catch ( Exception e ) {

						e.printStackTrace();
						logger.error( e.getMessage() );
				}

				return "";
		}

		
		/**
		 * URL을  Header와 POST로 호출합니다.
		 *
		 * @param url
		 * @param params
		 * @return
		 * @throws Exception
		 */
		public static String postWithException( String url, Map<String, String> header, String json ) throws Exception {

				if ( StringUtils.isEmpty( url ) ) {
						return null;
				}

				String result = "";

				HttpPost post = new HttpPost( url );
				convertHeader ( post, header);

				StringEntity entity = new StringEntity( json, "UTF-8" );
				post.setEntity( entity );

				RequestConfig requestConfig = RequestConfig.copy( RequestConfig.DEFAULT ).setConnectionRequestTimeout( HTTP_CLIENT_TIME ).build();

				try ( CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultRequestConfig( requestConfig ).build(); ){
						result = closeableHttpClient.execute( post, new BasicResponseHandler() );
				}
				catch ( Exception e ) {

						throw new Exception( e);
				}

				return result;
		}


		/**
		 * URL을 POST로 호출합니다.
		 *
		 * @param url
		 * @param params
		 * @return
		 * @throws Exception
		 */
		public static String post( String url, Map<String, Object> params ) {

				if ( StringUtils.isEmpty( url ) ) {
						return null;
				}

				String result = "";

				HttpPost post = new HttpPost( url );
				post.setEntity( new UrlEncodedFormEntity( convertParam( params ), Consts.UTF_8 ) );

				RequestConfig requestConfig = RequestConfig.copy( RequestConfig.DEFAULT ).setConnectionRequestTimeout( HTTP_CLIENT_TIME ).build();

				try ( CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultRequestConfig( requestConfig ).build();){
						result = closeableHttpClient.execute( post, new BasicResponseHandler() );
				}
				catch ( Exception e ) {

						e.printStackTrace();
						logger.error( e.getMessage() );
				}


				return result;

		}

		/**
		 * URL을  Header와 GET으로 호출합니다.
		 * @author user
		 * @param url
		 * @param header
		 * @param json
		 * @return
		 * @throws Exception
		 */
		public static String get( String url, Map<String, String> header, String json )  {

				if ( StringUtils.isEmpty( url ) ) {
						return null;
				}

				String result = "";

				HttpGet get = new HttpGet( url );
				RequestConfig requestConfig = RequestConfig.copy( RequestConfig.DEFAULT ).setConnectionRequestTimeout( HTTP_CLIENT_TIME ).build();

				try ( CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultRequestConfig( requestConfig ).build(); ){
						convertHeader ( get, header );
						result = closeableHttpClient.execute( get, new BasicResponseHandler() );
				}
				catch ( Exception e ) {

						e.printStackTrace();
						logger.error( e.getMessage() );
				}

				return result;
		}

		/**
		 * URL을 GET으로 호출합니다.
		 *
		 * @param urlString
		 * @return
		 * @throws Exception
		 */
		public static String get( String urlString ) throws Exception {

				if ( StringUtils.isEmpty( urlString ) ) {
						return null;
				}

				String result = "";
				InputStream is = null;

				try {
						URL url = new URL( urlString );
						is = url.openStream();
						BufferedReader inFile = new BufferedReader( new InputStreamReader( is ) );
						String line = null;
						while ((line = inFile.readLine()) != null) {
								result += line;
						}
				}
				catch ( Exception e ) {

						e.printStackTrace();
						logger.error( e.getMessage() );
				}
				finally {
						is.close();
				}

				return result;

		}


		/**
		 * URL로부터 모든 파라메터를 추출해서 List<NameValuePair>에 담는다.
		 *
		 * @param url
		 * @return
		 */
		public static List <NameValuePair> getParameters( String url ) {

			String temp[] = url.split( "\\?" );

			String params = temp[1];
			String param[] = params.split( "&" );

			 List <NameValuePair> nvps = new ArrayList<NameValuePair>();

			for ( int i = 0; i < param.length; i++ ) {

				String pemp[] = param[i].split( "=" );
				nvps.add ( new BasicNameValuePair( pemp[0], pemp[1] ) );
			}

			return nvps;
		}



		/**
		 * HttpClient POST 방식 호출
		 *
		 * @param url
		 */
		public int postSubmit( String url ) {

				int result = 0;

				try ( CloseableHttpClient httpclient = HttpClients.createDefault() ) {

						HttpPost httpPost = new HttpPost( url );
						List<NameValuePair> param = HttpClientUtils.getParameters( url );

						//UTF-8은 한글
						httpPost.setEntity( new UrlEncodedFormEntity( param, "UTF-8" ) );

						try ( CloseableHttpResponse response = httpclient.execute( httpPost );){

								int responseCode = response.getStatusLine().getStatusCode() ;

								if ( HttpStatus.SC_OK == responseCode) {

										//API서버로부터 받은 JSON 문자열 데이터
										BufferedReader reader = new BufferedReader( new InputStreamReader( response.getEntity().getContent() ) );

										String inputLine;
										StringBuffer sb = new StringBuffer();

										while ((inputLine = reader.readLine()) != null) {
												sb.append( inputLine );
										}

										reader.close();
								}

								result = responseCode;
						}
				}
				catch ( Exception e ) {

						e.printStackTrace();
						logger.error( e.getMessage() );
				}

				return result;
		}

		/**
		 * HttpClient GET 방식 호출
		 *
		 * @param url
		 */
		public HashMap<String, String> getSubmit( String url ) {

				HashMap<String, String> map = new HashMap<>();

				try ( CloseableHttpClient httpclient = HttpClients.createDefault()) {

						//GET 방식으로 parameter를 전달
						HttpGet httpGet = new HttpGet( url );
						httpGet.setHeader( "Content-Type", "application/x-www-form-urlencoded; charset=UTF-8" ); // UTF-8

						try ( CloseableHttpResponse response = httpclient.execute( httpGet ) ){

								int responseCode = response.getStatusLine().getStatusCode() ;
								StringBuffer sb = new StringBuffer();

								if ( HttpStatus.SC_OK == responseCode) {

    								//API서버로부터 받은 JSON 문자열 데이터
    								BufferedReader reader = new BufferedReader( new InputStreamReader( response.getEntity().getContent(), "UTF-8" ) );
    								String inputLine;

    								while ((inputLine = reader.readLine()) != null) {
    										sb.append( inputLine );
    								}

    								reader.close();

								}

								//Print result
								map.put( "result", Integer.toString( responseCode ) );
								map.put( "msg", sb.toString() );

						}
				}
				catch ( Exception e ) {

						e.printStackTrace();
						logger.error( e.getMessage() );
				}

				return map;
		}

		
		/***
		 * post, get 호출의 header를 채운다.
		 *
		 * @param HttpRequestBase : 2020.02.13 get과 post에서 모두 사용하기 위해 HttpPost->  HttpRequestBase로 수정
		 * @param header
		 */
		private static void convertHeader(HttpRequestBase httpRequest, Map<String, String> header) {

			if ( MapUtils.isEmpty( header ) ) {
				return ;
			}

			Iterator<String> keys = header.keySet().iterator();
			while (keys.hasNext()) {
					String key = keys.next();
					httpRequest.setHeader( key, header.get(key));
			}

		}

		private static List<NameValuePair> convertParam( Map<String, Object> params ) {

				List<NameValuePair> paramList = new ArrayList<NameValuePair>();

				if ( MapUtils.isEmpty( params ) ) {
						return paramList;
				}

				Iterator<String> keys = params.keySet().iterator();
				while (keys.hasNext()) {
						String key = keys.next();
						paramList.add( new BasicNameValuePair( key, params.get( key ).toString() ) );
				}

				return paramList;

		}
}
