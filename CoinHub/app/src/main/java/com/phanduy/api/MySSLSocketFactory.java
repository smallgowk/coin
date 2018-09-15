package com.phanduy.api;

import com.phanduy.utils.SmartLog;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MySSLSocketFactory extends SSLSocketFactory {
	SSLContext sslContext = SSLContext.getInstance("TLS");
//	SSLContext sslContext = SSLContext.getInstance("SSLv1");

	public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
		super(truststore);

		TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sslContext.init(null, new TrustManager[] { tm }, null);
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
		Socket socket2 = sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
		return socket2;
		
//		SSLSocket S = (SSLSocket) sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
//		
//		String[] protocol = S.getEnabledProtocols();
//	    for(String s: protocol) {
//	    	Log.e("protocol", "" + s);
//	    }
//	    
////	    S.setEnabledProtocols(new String[] {"TLSv1","TLSv1.1","TLSv1.2"});
//	    S.setEnabledProtocols(new String[] {"TLSv1.2"});
//	    String[] protocol1 = S.getEnabledProtocols();
//	    for(String s: protocol1) {
//	    	Log.e("protocol", "" + s);
//	    }
//	    return S;

		
	}

	@Override
	public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
		
//		SSLSocket S = (SSLSocket) sslContext.getSocketFactory().createSocket();
//		
//		String[] protocol = S.getEnabledProtocols();
//	    for(String s: protocol) {
//	    	Log.e("protocol", "" + s);
//	    }
//		
////	    S.setEnabledProtocols(new String[] {"SSLv2","SSLv1"});
////		S.setEnabledProtocols(new String[] {"TLSv1","TLSv1.1","TLSv1.2"});
//		S.setEnabledProtocols(new String[] {"TLSv1.2"});
//		String[] protocol1 = S.getEnabledProtocols();
//	    for(String s: protocol1) {
//	    	Log.e("protocol", "" + s);
//	    }
//	    
//	    return S;
	}
	
	public static class NoSSLv3SSLSocket extends DelegateSSLSocket {

		private NoSSLv3SSLSocket(SSLSocket delegate) {
			super(delegate);

			String canonicalName = delegate.getClass().getCanonicalName();
			if (!canonicalName.equals("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl")) {
				// try replicate the code from HttpConnection.setupSecureSocket()
				try {
					Method msetUseSessionTickets = delegate.getClass().getMethod("setUseSessionTickets", boolean.class);
					if (null != msetUseSessionTickets) {
						msetUseSessionTickets.invoke(delegate, true);
					}
				} catch (NoSuchMethodException ignored) {
				} catch (InvocationTargetException ignored) {
				} catch (IllegalAccessException ignored) {
				}
			}
		}

		@Override
		public void setEnabledProtocols(String[] protocols) {
			if (protocols != null && protocols.length == 1 && "SSLv3".equals(protocols[0])) {
				// no way jose
				// see issue https://code.google.com/p/android/issues/detail?id=78187
				List<String> enabledProtocols = new ArrayList<String>(Arrays.asList(delegate.getEnabledProtocols()));
				if (enabledProtocols.size() > 1) {
					enabledProtocols.remove("SSLv3");
				} else {
					SmartLog.logInfo("setEnabledProtocols", "SSL stuck with protocol available for " + String.valueOf(enabledProtocols));
				}
				protocols = enabledProtocols.toArray(new String[enabledProtocols.size()]);
			}
			super.setEnabledProtocols(protocols);
		}
	}


}