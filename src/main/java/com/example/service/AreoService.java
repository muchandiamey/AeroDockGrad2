package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.MapReturnType;
import com.aerospike.client.exp.Exp;
import com.aerospike.client.exp.ListExp;
import com.aerospike.client.exp.MapExp;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;

import com.aerospike.mapper.tools.AeroMapper;
import com.example.model.SandBox;
import io.micronaut.context.annotation.Bean;
@Bean
public class AreoService {
	
	public String createAer() {
		Host[] hosts = new Host[] {
    		    new Host("localhost",3000),   		    
    		};

    		AerospikeClient client = new AerospikeClient(new ClientPolicy(), hosts);
    		
    		// Create a new write policy
    		WritePolicy writePolicy = new WritePolicy();
    		writePolicy.sendKey = true;
    		// Create the record key
    		Key key = new Key("test", "ufodata", 5002);
    		// Create a list of shapes to add to the report map
    		ArrayList<String> taluk = new ArrayList<String>();
    		taluk.add("circle");
    		taluk.add("flash");
    		taluk.add("disc"); 

    		// Create the report map
//    		Map reportMap = new HashMap<String, Object>();
//    		reportMap.put("city", "Bengaluru");
//    		reportMap.put("state", "Karnataka");
//    		reportMap.put("Taluk", taluk);
//    		reportMap.put("duration", "5 minutes");
//    		reportMap.put("Summary", "My Aerospike connection");
		SandBox sb = new SandBox();
		sb.setCity("Belagavi");
		sb.setState("Karnataka");
		sb.setTaluk("Bgm");
		sb.setDuration("2 min");
		sb.setSummary("My Aero conn");

		AeroMapper mapper = new AeroMapper.Builder(client).build();
		mapper.save(sb);


    		// Format coordinates as a GeoJSON string
    		String geoLoc = "{\"type\":\"Point\", \"coordinates\":[42.2808,83.7430]}";
    		
//    		// Create the bins as Bin("binName", value)
//    		Bin occurred = new Bin("occurred", 20220531);
//    		Bin reported = new Bin("reported", 20220601);
//    		Bin posted = new Bin("posted", 20220601);
//    		// reportMap defined in the section above
//    		Bin report = new Bin("report", ); //reportMap
//    		// geoLoc defined in the section above
//    		Bin location = new Bin("location", Value.getAsGeoJSON(geoLoc));

    		// Write the record to Aerospike
//    		client.put(writePolicy, key, occurred, reported, posted, report, location);
			client.put(writePolicy, key);
    		Policy policy = new Policy();
    		policy.socketTimeout = 300;
    		Record record = client.get(policy, key);
    		// Close the connection to the server
    		client.close();
    		//System.out.println
	       String s="Records Added Successfully" + "  " +  record.bins;
		
		return s;
	}
	
	
	public String AeroRead() {
		
		AerospikeClient client = new AerospikeClient("localhost", 3000);

		// Creates a key with the namespace "sandbox", set "ufodata", and user key 5001
		Key key = new Key("test", "ufodata", 5001);

		// Create a new read policy
		Policy policy = new Policy();
		policy.socketTimeout = 300;

		// Get whole record
		Record record = client.get(policy, key);

		// Do something
		//System.out.format("Record: %s", record.bins);

		// Close the connection to the server
		client.close();
	         return "Records Added Successfully" + "  " + record.bins;
	}
	
	
	public String updateAeroo() {
		
		AerospikeClient client = new AerospikeClient("localhost", 3000);

		// Creates a key with the namespace "sandbox", set "ufodata", and user key 5001
		Key key = new Key("test", "ufodata", 5001);

		// Can use a previously defined write policy or create a new one
		WritePolicy updatePolicy = new WritePolicy();
		updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

		// Create bin with new value
		Bin newPosted = new Bin("posted", 20220602);

		// Update record with new bin value
		client.put(updatePolicy, key, newPosted);
		Policy policy = new Policy();
		policy.socketTimeout = 300;
		Record record = client.get(policy, key);
		// Close the connection to the server
		client.close();
		String s="Records Updated Successfully" + "  "  + record.bins;
		return s;
	}
	
	public String aeroDelete1() {
		System.out.println("Welcome");
  	  Host[] hosts = new Host[] {
    		    new Host("localhost",3000),
    		    
    		};
  	  System.out.println(hosts);
    		AerospikeClient client = new AerospikeClient(new ClientPolicy(), hosts);
  	  
  // Establishes a connection to the server
  //AerospikeClient client = new AerospikeClient("localhost", 3000);

  // Creates a key with the namespace "sandbox", set "ufodata", and user key 5001
  Key key = new Key("test", "ufodata", 5002);

  // Can use a previously defined write policy or create a new one
  WritePolicy deletePolicy = new WritePolicy();
  //deletePolicy.durableDelete = true;

  // Durably delete a record with the write policy
  client.delete(deletePolicy, key);

  // Close the connection to the server
  client.close();
  return "Records Deleted Successfully";

	}

	public Map<String, Object> aeroFilt()
	{
		Host[] hosts = new Host[] { 
  				new Host("localhost", 3000),

  		};
		
  	AerospikeClient client = new AerospikeClient("localhost", 3000);

  	// Creates a key with the namespace "sandbox", set "ufodata", and user key 5001
  	Key key = new Key("test", "ufodata", 5001);
  	
  	Policy policy = new Policy();
	policy.filterExp = Exp.build(
  		    Exp.gt(
  		        ListExp.size(
  		            MapExp.getByKey(MapReturnType.VALUE, Exp.Type.LIST, Exp.val("Taluk"), Exp.mapBin("report"))
  		        ),
  		        Exp.val(2)
  		    )
  		);
	
	Record record = client.get(policy, key);
//  	   Expression exp = Exp.build(
//  		    ListExp.size(
//  		        MapExp.getByKey(MapReturnType.VALUE, Exp.Type.LIST, Exp.val("Taluk"), Exp.mapBin("report"))
//  		    )
//  		);
//
//  		// Read the record   
//  		Record record = client.operate(null, key, 
//  		    ExpOperation.read("numShapes", exp, ExpReadFlags.DEFAULT)
//  		);

  		// Do something
  		//System.out.format("Record: %s\n", record.bins);

  		// Close the connection to the server
  		
  		client.close();
  		return record.bins;
	}
}
