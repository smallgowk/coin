package com.phanduy.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.phanduy.GlobalInfo;
import com.phanduy.api.http.AsyncHttpClient;
import com.phanduy.api.http.AsyncHttpResponseHandler;
import com.phanduy.api.http.RequestParams;
import com.phanduy.api.oauth.OAuthConstants;
import com.phanduy.api.oauth.OAuthUtils;
import com.phanduy.api.oauth.Token;
import com.phanduy.interfaces.ResponseListener;
import com.phanduy.model.request.BaseDeleteRequestParams;
import com.phanduy.model.request.BaseGetRequest;
import com.phanduy.model.request.BasePostMultipartRequest;
import com.phanduy.model.request.BasePostRequestEntity;
import com.phanduy.model.request.BasePostRequestParams;
import com.phanduy.model.request.BasePutRequestParams;
import com.phanduy.store.AppSharePreference;
import com.phanduy.utils.SmartLog;
import com.phanduy.utils.StringUtility;
import com.phanduy.view.activity.MainHomeActivity;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class ApiController {

    public static AsyncHttpClient clientGet;
    public static void getGeometryFromAddress(Context context, String address, ResponseListener responseListener) {
        try {
            String url = "http://maps.google.com/maps/api/geocode/json?sensor=false&address=" + StringUtility.encode(address);

            doGetRequest(context, url, true, responseListener);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void doLoginFacebook(Context context, String clientId, ResponseListener responseListener) {
        String url = "https://www.facebook.com/dialog/oauth?redirect_uri=http://domain/&scope=email,user_friends&client_id=" + clientId;
        doGetRequest(context, url, true, responseListener);
    }

    public static void doPostRequest(Context context, BasePostRequestParams basePostRequestParams,
                                     ResponseListener responseListener) {
        postRequestParams(context, basePostRequestParams.genRequestParams(), basePostRequestParams.genUrl(), responseListener);
    }
    public static void doPostRequest(Context context, BasePostRequestEntity basePostRequestEntity,
                                     ResponseListener responseListener) {
        postRequest(context, basePostRequestEntity.genHttpEntity(), basePostRequestEntity.genUrl(), responseListener);
    }

    public static void doDeleteRequest(Context context, BaseDeleteRequestParams baseDeleteRequestParams,
                                       ResponseListener responseListener) {
        doDeleteRequestParams(context, baseDeleteRequestParams.genUrl(), baseDeleteRequestParams.genRequestParams(), responseListener);
    }

    public static void doGet(Context context, BaseGetRequest baseGetRequest, ResponseListener responseListener) {
        doGetRequest(context, baseGetRequest.genUrl(), false, responseListener);
    }

    public static void doPutRequestRarams(Context context, BasePutRequestParams basePutRequestParams, ResponseListener responseListener) {
        putRequestParams(context,
                basePutRequestParams.genUrl(), basePutRequestParams.genRequestParams(), responseListener);
    }

    public static void doPostMultipart(Context context, BasePostMultipartRequest basePostMultipartRequest,
                                       ResponseListener responseListener) {
        if (basePostMultipartRequest.getBitmap() == null) {
            return;
        }

        postRequest(context, basePostMultipartRequest.genMultipartEntity(), basePostMultipartRequest.genUrl(),
                responseListener);
    }


    /*
     * Hàm gửi request http get
     */
    public static void doGetRequest(Context context, final String url,
                                    final boolean isCancelAllRequest,
                                    final ResponseListener responseListener) {

        // Kiểm tra tình trạng kết nối mạng
        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }

        clientGet.addHeader("Accept", "application/json");

//        Token token = OAuthUtils.getTokenFromPreference(com.phanduy.store.AppSharePreference.getInstance(context));
//
//        if (token != null) {
//
//            if (token.isExpired()) {
//                try {
//                    token = OAuthUtils.refreshAccessToken(token);
//
//                    Gson gson = new Gson();
//                    String jsonToken = gson.toJson(token, Token.class);
//                    com.phanduy.store.AppSharePreference.getInstance(context).putStringValue(com.phanduy.store.AppSharePreference.SHIPS_ACCESS_TOKEN, jsonToken);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    if (context instanceof MainHomeActivity) {
//                        ((MainHomeActivity) context).goToLogin();
//                    } else {
//                        responseListener.processResponse(NetworkStatus.INVALID_SESSION, "");
//                    }
//                    return;
//                }
//            }
//            clientGet.addHeader(OAuthConstants.AUTHORIZATION, "Bearer " + token.getAccessToken());
//        }

        if (GlobalInfo.ServerConfig.RESOURCE_SITE.contains("https:")) {

            KeyStore trustStore;
            try {
                trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                clientGet.setSSLSocketFactory(sf);
            } catch (KeyStoreException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (KeyManagementException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            }

        }

        SmartLog.log("Normal URL ", url);
        clientGet.get(context, url, new AsyncHttpResponseHandler() {

            // Request và nhận response thành công
            @Override
            public void onSuccess(String content) {
                // Log.e("Response Get", "" + content);
                if (content != null && content.length() != 0) {
                    responseListener.processResponse(content);
                } else {
                    responseListener.processResponse(NetworkStatus.NO_DATA, null);
                }
                // getCurrentSession();
            }

            // Request thất bại
            @Override
            public void onFailure(Throwable error, String content) {
                SmartLog.log(
                        "requestProcess",
                        "onFailure " + error.getMessage() + " "
                                + error.toString());
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
                // getCurrentSession();
            }
        });
    }

    /*
     * Hàm gửi request http get
     */
    public static void doUnAuthenRequest(Context context, final String url,
                                    final boolean isCancelAllRequest,
                                    final ResponseListener responseListener) {

        // Kiểm tra tình trạng kết nối mạng
        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }

        clientGet.addHeader("Accept", "application/json");


        if (GlobalInfo.ServerConfig.RESOURCE_SITE.contains("https:")) {

            KeyStore trustStore;
            try {
                trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                clientGet.setSSLSocketFactory(sf);
            } catch (KeyStoreException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (KeyManagementException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
            }

        }

        SmartLog.log("Normal URL ", url);
        clientGet.get(context, url, new AsyncHttpResponseHandler() {

            // Request và nhận response thành công
            @Override
            public void onSuccess(String content) {
                // Log.e("Response Get", "" + content);
                if (content != null && content.length() != 0) {
                    responseListener.processResponse(content);
                } else {
                    responseListener.processResponse(NetworkStatus.NO_DATA, null);
                }
                // getCurrentSession();
            }

            // Request thất bại
            @Override
            public void onFailure(Throwable error, String content) {
                SmartLog.log(
                        "requestProcess",
                        "onFailure " + error.getMessage() + " "
                                + error.toString());
                responseListener.processResponse(NetworkStatus.CONNECT_ERROR, null);
                // getCurrentSession();
            }
        });
    }

    /*
     * Hàm gửi request http post voi content type ko co dang json
     */
    public static void postRequestParams(Context context,
                                         RequestParams requestParams, String url,
                                         final ResponseListener responseListener) {

        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }

        clientGet.addHeader("Accept", "application/json");
        Token token = OAuthUtils.getTokenFromPreference(com.phanduy.store.AppSharePreference.getInstance(context));

        if (token != null) {
            if (token.isExpired()) {
                try {
                    token = OAuthUtils.refreshAccessToken(token);

                    Gson gson = new Gson();
                    String jsonToken = gson.toJson(token, Token.class);
                    com.phanduy.store.AppSharePreference.getInstance(context).putStringValue(com.phanduy.store.AppSharePreference.SHIPS_ACCESS_TOKEN, jsonToken);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (context instanceof MainHomeActivity) {
                        ((MainHomeActivity) context).goToLogin();
                    } else {
                        responseListener.processResponse(NetworkStatus.INVALID_SESSION, "");
                    }
                    return;
                }
            }
            clientGet.addHeader(OAuthConstants.AUTHORIZATION, "Bearer " + token.getAccessToken());
        }

        Log.e("POST Url", "" + url);

        clientGet.post(url, requestParams, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(String content) {
                        if (content != null) {
                            responseListener.processResponse(content);
                        } else {
                            responseListener.processResponse(NetworkStatus.NO_DATA, null);
                        }
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        // Log.e("Post Failure", "" + content);
                        responseListener.processResponse(NetworkStatus.NO_DATA, content);
                    }

                }

        );
    }

    public static void deleteRequestParams(Context context, String url,
                                           final ResponseListener responseListener) {

        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }

        clientGet.addHeader("Accept", "application/json");
        Token token = OAuthUtils.getTokenFromPreference(com.phanduy.store.AppSharePreference.getInstance(context));

        if (token != null) {
            if (token.isExpired()) {
                try {
                    token = OAuthUtils.refreshAccessToken(token);

                    Gson gson = new Gson();
                    String jsonToken = gson.toJson(token, Token.class);
                    AppSharePreference.getInstance(context).putStringValue(com.phanduy.store.AppSharePreference.SHIPS_ACCESS_TOKEN, jsonToken);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (context instanceof MainHomeActivity) {
                        ((MainHomeActivity) context).goToLogin();
                    } else {
                        responseListener.processResponse(NetworkStatus.INVALID_SESSION, "");
                    }
                    return;
                }
            }
            clientGet.addHeader(OAuthConstants.AUTHORIZATION, "Bearer " + token.getAccessToken());
        }

        Log.e("POST Url", "" + url);

        clientGet.delete(url, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(String content) {
                        if (content != null) {
                            responseListener.processResponse(content);
                        } else {
                            responseListener.processResponse(NetworkStatus.NO_DATA, null);
                        }
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        // Log.e("Post Failure", "" + content);
                        responseListener.processResponse(NetworkStatus.NO_DATA, content);
                    }

                }

        );
    }

    public static void putRequestParams(Context context, String url, RequestParams requestParams,
                                        final ResponseListener responseListener) {

        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }

        clientGet.addHeader("Accept", "application/json");
        Token token = OAuthUtils.getTokenFromPreference(com.phanduy.store.AppSharePreference.getInstance(context));

        if (token != null) {
            if (token.isExpired()) {
                try {
                    token = OAuthUtils.refreshAccessToken(token);

                    Gson gson = new Gson();
                    String jsonToken = gson.toJson(token, Token.class);
                    com.phanduy.store.AppSharePreference.getInstance(context).putStringValue(com.phanduy.store.AppSharePreference.SHIPS_ACCESS_TOKEN, jsonToken);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (context instanceof MainHomeActivity) {
                        ((MainHomeActivity) context).goToLogin();
                    } else {
                        responseListener.processResponse(NetworkStatus.INVALID_SESSION, "");
                    }
                    return;
                }
            }
            clientGet.addHeader(OAuthConstants.AUTHORIZATION, "Bearer " + token.getAccessToken());
        }

        Log.e("POST Url", "" + url);

        clientGet.put(url, requestParams, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(String content) {
                        if (content != null) {
                            responseListener.processResponse(content);
                        } else {
                            responseListener.processResponse(NetworkStatus.NO_DATA, null);
                        }
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        // Log.e("Post Failure", "" + content);
                        responseListener.processResponse(NetworkStatus.NO_DATA, content);
                    }

                }

        );
    }

    /*
     * Hàm gửi request http post voi content type dang json
     */
    public static void postRequest(Context context, HttpEntity httpEntity,
                                   String url, final ResponseListener responseListener) {

        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }


//        clientGet.addHeader("Accept", "application/json");
        clientGet.addHeader("Content-Type", "application/json");
//        Token token = OAuthUtils.getTokenFromPreference(AppSharePreference.getInstance(context));
//
//        if (token != null) {
//            if (token.isExpired()) {
//                try {
//                    token = OAuthUtils.refreshAccessToken(token);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    if (context instanceof MainHomeActivity) {
//                        ((MainHomeActivity) context).goToLogin();
//                    } else {
//                        responseListener.processResponse(NetworkStatus.INVALID_SESSION, "");
//                    }
//                    return;
//                }
//            }
//            clientGet.addHeader(OAuthConstants.AUTHORIZATION, "Bearer " + token.getAccessToken());
//        }

        Log.e("POST Url", "" + url);

        clientGet.post(context, url, httpEntity, null,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(String content) {

                        if (content != null) {
                            responseListener.processResponse(content);
                        } else {
                            responseListener
                                    .processResponse(NetworkStatus.NO_DATA, null);
                        }
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        Log.e("onFailure", "" + content);
                        responseListener.processResponse(NetworkStatus.NO_DATA, null);
                    }

                });
    }

    public static void doDeleteRequestParams(Context context, String url, RequestParams requestParams, final ResponseListener responseListener) {

        if (clientGet == null) {
            clientGet = new AsyncHttpClient();
        }

        clientGet.addHeader("Accept", "application/json");
        Token token = OAuthUtils.getTokenFromPreference(com.phanduy.store.AppSharePreference.getInstance(context));

        if (token != null) {
            if (token.isExpired()) {
                try {
                    token = OAuthUtils.refreshAccessToken(token);

                    Gson gson = new Gson();
                    String jsonToken = gson.toJson(token, Token.class);
                    com.phanduy.store.AppSharePreference.getInstance(context).putStringValue(com.phanduy.store.AppSharePreference.SHIPS_ACCESS_TOKEN, jsonToken);
                } catch (Exception e) {
                    e.printStackTrace();
                    // responseListener.processResponse(NetworkStatus.INVALID_SESSION,
                    // "");
                    if (context instanceof MainHomeActivity) {
                        ((MainHomeActivity) context).goToLogin();
                    } else {
                        responseListener.processResponse(NetworkStatus.INVALID_SESSION, "");
                    }
                    return;
                }
            }
            clientGet.addHeader(OAuthConstants.AUTHORIZATION, "Bearer " + token.getAccessToken());
        }

        Log.e("DELETE Url", "" + url);


        clientGet.delete(context, url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String content) {
                if (content != null) {
                    responseListener.processResponse(content);
                } else {
                    responseListener.processResponse(NetworkStatus.NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                responseListener.processResponse(NetworkStatus.NO_DATA, content);
            }
        });
    }

    public String encode(String value) throws UnsupportedEncodingException {
        if (value != null) {
            return StringUtility.encode(value);
        } else {
            return "";
        }
    }
}
