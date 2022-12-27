package com.example.contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;

import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.MapReturnType;
import com.aerospike.client.exp.Exp;
import com.aerospike.client.exp.ExpOperation;
import com.aerospike.client.exp.ExpReadFlags;
import com.aerospike.client.exp.Expression;
import com.aerospike.client.exp.ListExp;
import com.aerospike.client.exp.MapExp;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
//import com.aerospike.mapper.tools.AeroMapper;
import com.example.service.AreoService;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import com.aerospike.client.Record;

@Controller("/hi")
public class AeroController {
//
//	@Get("/aeroCreate1")
//    public String createAero1() {
//     return "DOne";
//
//    }

	@Inject
     private AreoService  areoService ;
	//private Long id;



    @Post("/aeroCreate")
    public String createAero() {
     return areoService.createAer();

    }

    @Get("/aeroRead")
	public String AeroRead() {
    	return areoService.AeroRead();
	}

    @Post("/aeroUpdate")
	public String updateAero() {
    	return areoService.updateAeroo();
	}

      @Delete("/aeroDelete")
    	  public String aeroDelete() {
    	  return  areoService.aeroDelete1();
    }

      @Post("/aeroFilt")
  	public Map<String, Object> aeroFilt() {
  		return areoService.aeroFilt();
  	}


}
