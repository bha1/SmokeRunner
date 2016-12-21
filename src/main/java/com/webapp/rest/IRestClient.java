package com.webapp.rest;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;

public interface IRestClient {

	Response processExcel(InputStream fileInputStream, FormDataContentDisposition fileFormDataContentDisposition);

	Response processTestCases(InputStream fileInputStream, FormDataContentDisposition fileFormDataContentDisposition);

}
