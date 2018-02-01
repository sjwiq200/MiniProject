package com.model2.mvc.web.papago;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.Papago;

@RestController
@RequestMapping("/papago/*")
public class PapagoRestController {

	public PapagoRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="/json/papagoTranslate", produces = "application/json;charset=UTF-8")
	public Papago papagoTranslate(@RequestBody String translate) {
		System.out.println("papago/json/papagoTranslate");
		String clientId = "bYH9noOTsKVGcSDuoNRk";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "nMDSi1NS_h";//애플리케이션 클라이언트 시크릿값";
        
        System.out.println(translate);
        
        //-----------------
        JSONObject jsonobj = new JSONObject();
        Papago papago = new Papago();
        //--------------
        
        try {
            
        		String text = URLEncoder.encode(translate, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                jsonobj = (JSONObject)JSONValue.parse(br);
                System.out.println("jsonobj==>"+jsonobj);
                ObjectMapper objectMapper = new ObjectMapper();
                papago = objectMapper.readValue(jsonobj.toString(), Papago.class);
                System.out.println(papago);
                System.out.println("결과값만 추출 : "+papago.getMessage().getResult().getTranslatedText());
                
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            //String inputLine;
            //StringBuffer response = new StringBuffer();
            //while ((inputLine = br.readLine()) != null) {
            //    response.append(inputLine);
            //}
            //result = response.toString();
            br.close();
            //System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
         
        return papago;
	}

}
