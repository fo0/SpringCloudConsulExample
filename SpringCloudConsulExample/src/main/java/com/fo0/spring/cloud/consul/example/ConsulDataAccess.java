package com.fo0.spring.cloud.consul.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;

@Component
public class ConsulDataAccess {

	@Autowired
	private ConsulClient cClient;

	public Response<GetValue> getMyChangeValue() {
		return cClient.getKVValue("MyChangeValue");
	}

	public void setMyChangeValue(String value) {
		cClient.setKVValue("MyChangeValue", value);
	}
}
