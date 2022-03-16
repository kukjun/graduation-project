package main.java;

import main.java.vo.Weather;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class XMLIntegrateTest {

    enum WeatherValue {
        PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
    }

    public static void main(String[] args) throws Exception {

        // 입력받을 weather 객체
        Weather weather = new Weather();

        // 변수 설정
        String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String authKey = "Tk3dN%2FBOZoQkVmZXXo3ZQSKXHvJL4SwU%2FFV8rcF%2BsMsEXFfRuSdcPl6oweXAbVNKD3TiJpBFxBop76XQb45ZFg%3D%3D";

        String nx = "69";
        String ny = "100";
        String baseDate = "20220310";
        String baseTime = "1800";

        StringBuilder urlBuilder = new StringBuilder(apiURL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + authKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows=10", "UTF-8"));    // 숫자 표
        urlBuilder.append("&" + URLEncoder.encode("pageNo=1", "UTF-8"));    // 페이지 수
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도

        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        //어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();

        System.out.println(result);

        // 문자열 Document 로 변경해서 List 형태로 가져와서 객체에 파싱함.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(result));
        Document document = db.parse(is);

        try {
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("item");
            System.out.println("--------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String category = eElement.getElementsByTagName("category").item(0).getTextContent();
                    double value = Double.parseDouble(eElement.getElementsByTagName("obsrValue").item(0).getTextContent());

                    WeatherValue weatherValue = WeatherValue.valueOf(category);
//                    System.out.println(WeatherValue.valueOf(category).ordinal());

                    switch (weatherValue) {
                        case PTY:
                            weather.setPTY(value);
                            break;
                        case REH:
                            weather.setREH(value);
                            break;
                        case RN1:
                            weather.setRN1(value);
                            break;
                        case T1H:
                            weather.setT1H(value);
                            break;
                        case UUU:
                            weather.setUUU(value);
                            break;
                        case VEC:
                            weather.setVEC(value);
                            break;
                        case VVV:
                            weather.setVVV(value);
                            break;
                        case WSD:
                            weather.setWSD(value);
                            break;
                        default:
                            throw new XMLParseException();
                    }
                }
            }

            System.out.println(weather.toString());
        } catch (XMLParseException e) {
            e.printStackTrace();
        }
    }

}