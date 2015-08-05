package com.local.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farmers.model.TesignTrnTracker;
import com.farmers.repository.TesignTrnTrackerRepo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/batch/override/appcontext.xml",	
//    "classpath:META-INF/spring/batch/jobs/ImportAgentDataJob.xml",   
//    "classpath:META-INF/spring/config/batchrepocontext.xml",

		})
public class TesteSignTrans {
	
	@Autowired
	private TesignTrnTrackerRepo esignRepo;
	
//	@Test
	public void testRepo()
	{
		String policynum = "someno"; // changing the no. 
		List<TesignTrnTracker> result = esignRepo.findeSignTransbyPCN(policynum);
		System.out.println("result size :: "+result.size());
		for(TesignTrnTracker esignTransaction:result)
		{
			String xml = new String(esignTransaction.getEsignTransData());
			Charset charset = Charset.forName("Cp1047");
			ByteArrayInputStream byteStream = new ByteArrayInputStream(esignTransaction.getEsignTransData());
			InputStreamReader is = new InputStreamReader(byteStream,charset);
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
			BufferedWriter bw = null;
			FileWriter fw = null;
			String line;
			try {
	 
				br = new BufferedReader(is);
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				File file = new File("c://static//server//silanis//"+policynum+"_PLA_xml.txt");
				 
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				fw = new FileWriter(file.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(sb.toString());
				
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
					try {
						if(bw!=null)
							bw.close();
						if(fw!=null)
							fw.close();
						if (br != null) 
							br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				
			}
	 
			
			System.out.println(xml);
			System.out.println(sb.toString());
		}
			
	}

}
