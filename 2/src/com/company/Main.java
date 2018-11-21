package com.company;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Main {

public static String getinfo(String s)throws Exception
{
    URL meetup = new URL(s);
    URLConnection yc = meetup.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
    String inputLine0;
    inputLine0 = in.readLine();
    in.close();
    return inputLine0;
}
    public static void main(String[] args) throws Exception{
        String inputLine=getinfo("https://api.meetup.com/2/cities?&sign=true&photo-host=public&country=rs");
        //System.out.println(inputLine);
        String novi="";
        int i=0,j=0,k,br=0;
        j=inputLine.indexOf("city",i);
        do
        {
            novi+=br;
            br++;
            novi+=" ";
            j += 7;
            k = inputLine.indexOf('"', j);
            novi += inputLine.substring(j, k);
            novi += "\n";
            i=k;
            j=inputLine.indexOf("city",i);
        }while(j!=-1);
        System.out.println(novi);
        System.out.println("Enter your city number: ");
        Scanner scanner = new Scanner(System.in);
        String r = scanner.nextLine();
        i=novi.indexOf(r);
        j=novi.indexOf(" ",i)+1;
        k=novi.indexOf("\n",j);
        String mesto=novi.substring(j,k);
        System.out.println(mesto);
        String u="https://api.meetup.com/2/concierge?&sign=true&photo-host=public&country=rs&key=7c2c503a621b312e4f47445ea7d795c";
        String inputLine2 = getinfo(u);
        //System.out.println(inputLine2);
        String dogadjaji="",str1="",str2="";
        int l=0;
        mesto="\"city\":\""+mesto+"\"";
        i=inputLine2.indexOf(mesto);
        if(i!=-1) {
            do {
                j = inputLine2.indexOf('}', i);
                k = inputLine2.indexOf("\"description\"", j);
                if (k != -1) {
                    l = inputLine2.indexOf('"', k+15);
                    str1 = inputLine2.substring(k, l+1);
                    //System.out.println(str1);
                }
                k = inputLine2.indexOf("\"name\"", l);
                if (k != -1) {
                    l = inputLine2.indexOf('"', k+8);
                    str2 = inputLine2.substring(k, l+1);
                    //System.out.println(str2);
                }
                i = inputLine2.indexOf(mesto, j);
            dogadjaji=dogadjaji+str2+" "+str1+"\n";
            } while (i != -1);
        }
        else dogadjaji="Nema dogadjaja u ovom gradu.";
        System.out.println(dogadjaji);
    }
}
