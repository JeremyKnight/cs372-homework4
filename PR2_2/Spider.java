/**
 * This class creates and implements the web spider
 * @author Jeremy Knight
 */

import java.util.regex.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;

public class Spider implements Runnable {

    String regexUrl = "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>"; // matches <http://google.com>
    String regexEmail = "\"mailto:(.*?)\"";
    String url;
    Map<String, Boolean> websiteMap = new ConcurrentHashMap<String, Boolean>();
    ArrayList<String> emails = new ArrayList<String>();

    /**
     * This implements spider
     */
    public Spider(String url) {
       this.url = url;

    }

    /** 
     * This creates the seperate thread to run the search
     */
    public void run() {
        addWebsites(url);
        findEmail(url);

        System.out.println("done");
        //for(Entry<String, Boolean> entry : websiteMap.entrySet()) {
        for(String key : websiteMap.keySet()) {
    
            System.out.println(key);
            if(websiteMap.get(key)==false) {
                //String web = entry.getKey();

                addWebsites(key);
                findEmail(key);
                //entry.setValue(true);
                System.out.println(Integer.toString(emails.size()));
            }
            

            if(emails.size()>=100) {
                break;
            }
        }

        for(int i=0; i<emails.size(); i++) {
            System.out.println(emails.get(i));
        }
       
    }

    /**
     * this functions adds websites to the hashmap to be searched through
     */
    private void addWebsites(String _url) {
        URL u;
        try {
            u = new URL(_url);

        } catch(Exception ex) {
            System.out.println("this failed " + ex.getMessage());
            u = null;
        }

        if(url == null) return;
        try {
            BufferedReader rdr = new BufferedReader(new InputStreamReader(u.openStream()));
            String line ="";
            while((line = rdr.readLine()) != null ) {

                //Pattern pattern = Pattern.compile(regexUrl);
                //String newThing = "";
                
                if(line.contains("https://") || line.contains("href=\"/")) {
                    int lineNum = 0;

                    for(int i = 0; i<line.length(); i++) {
                        if((line.charAt(i) == '\"') && lineNum == 0) {
                            lineNum = i;
                        } else if((line.charAt(i) == '\"') && lineNum != 0) {
                            String newUrl = line.substring(lineNum+1, i);
                            if(newUrl.contains("https://") ) { 
                                //System.out.println(newUrl);
                                if(newUrl.endsWith("js") || newUrl.endsWith("html")) {
                                } else {
                                    if(!websiteMap.containsKey(newUrl))
                                        websiteMap.put(newUrl, false);
                                    //System.out.println(newUrl + " from: " + _url);
                                }
                            } else if(newUrl.contains("\"/")) {
                                
                                System.out.println("added url:" + _url + newUrl);
                                //website.put()
                            } 
                            //System.out.println(newUrl);
                            break;
                        }
                    }

                }
            }
        } catch(Exception ex) {
            System.out.println("this failed " + ex.getMessage());
        }
    }

    /**
     * This function finds the emails when given a url string
     */
    private void findEmail(String s) {
        //href="mailto:
        URL u;
        try {
            u = new URL(s);
        } catch(Exception e) {
            u = null;
            System.out.println("this failed " + e.getMessage());
        }

        try {
            Pattern email = Pattern.compile(regexEmail);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(u.openStream()));
            String line ="";
            while((line = rdr.readLine()) != null ) {
                Matcher match = email.matcher(line);
                if(match.find() && match.group(1).length()<100) { //&& !line.contains("-")) {
                    emails.add(match.group(1));
                    System.out.println("email: " + match.group(1));
                }
            }


        } catch(Exception e) {
            System.out.println("this failed in find email " + e.getMessage());
        }



    }
}