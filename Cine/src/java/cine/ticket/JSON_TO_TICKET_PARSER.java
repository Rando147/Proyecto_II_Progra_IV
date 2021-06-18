/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.ticket;

import cine.usuario.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
////import jdk.nashorn.internal.parser.JSONParser;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author boyro
 */
public class JSON_TO_TICKET_PARSER {
    //idCartelera

    public List<Ticket> parseCJSON(String idCartelera, String json, Usuario u) {
        Ticket aux = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
//            String auxJSON = "{ \"seatsArray\":" + json + "}";
//            Object obj = new JSONParser().parse(json);
//            JSONObject jo = (JSONObject) obj;
            String cartelera = idCartelera;//(String) jo.get("idCartelera");
            String id, butaca = json, cliente;
//            JSONArray jsonArray = (JSONArray) jo.get("seatsArray");
            // iterating seatsArray       String str = "{ \"number\": [3-0, 0-4, 0-5, 4-6] }";
            //Iterator itr1 = ja.iterator();
            //StringTokenizer multiTokenizer = new StringTokenizer(json, "://.[],'\"'");
            //StringTokenizer multiTokenizer = new StringTokenizer(json, "://.[]");
            //while (multiTokenizer.hasMoreTokens()) {
            //    butaca += multiTokenizer.nextToken();               
                //System.out.println(multiTokenizer.nextToken());
            //}
        tickets.add(new Ticket(butaca, u.getId(), cartelera));

//            String str = "What are you doing today?";
//            String split[] = str.split(" ", 0);
//            for (String s : split) {
//                System.out.println(s);
//            }
//
//            String s = " ;String; String; String; String, String; String;;String;String; String; String; ;String;String;String;String";
//            //String[] strs = s.split("[,\\s\\;]"); 
//            String[] strs = s.split("[,\\;]");
//
//            System.out.println("Substrings length:" + strs.length);
//            for (int i = 0;
//                    i < strs.length;
//                    i++) {
//                System.out.println("Str[" + i + "]:" + strs[i]);
//            }
//
//            if (jsonArray
//                    != null) {
//
//                //Iterating JSON array  
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    butaca = (String) jsonArray.get(i);
//                    //Adding each element of JSON array into ArrayList  
//                    //Ticket(String butaca, String cliente, String cartelera)
//                    tickets.add(new Ticket(butaca, u.getId(), cartelera));
//                }
//            }
        } catch (Exception ec) {

        }
        return tickets;
    }
}
